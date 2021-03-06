/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.parranderos.negocio.PrincipalAlohAndes;
import uniandes.isis2304.parranderos.negocio.VOArriendo;

/**
 * Clase principal de la interfaz
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazParranderosApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazParranderosApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private PrincipalAlohAndes alohandes;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazParranderosApp( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		alohandes = new PrincipalAlohAndes (tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			CRUD de 
	 *****************************************************************/

	/**
	 * Método para confirmar si hay reservas en las fechas deseadas
	 * @param fechaInicio
	 * @param duracion
	 * @param fechasReservadas
	 * @return true si sí hay, false si no;
	 */
	@SuppressWarnings("deprecation")
	private boolean hayConflictoFechas(Timestamp fechaInicio, int duracion, List<Object[]> fechasReservadas) {
		boolean seCruza = false;
		Timestamp diasExtras = new Timestamp(70, 0, duracion, 0, 0, 0, 0);
		long longFechaFin = fechaInicio.getTime() + diasExtras.getTime();
		Timestamp fechaFin = new Timestamp (longFechaFin);
	    fechaInicio.setDate(fechaInicio.getDate());
		for(Object[] fechaReserva: fechasReservadas)
		{
			Timestamp inicioReserva = (Timestamp) fechaReserva[0];
			Timestamp finReserva = new Timestamp(inicioReserva.getTime() + (int) fechaReserva[1]);
			if(fechaInicio.after(inicioReserva) && fechaInicio.before(finReserva)
					|| fechaFin.after(inicioReserva) && fechaFin.before(finReserva)
					|| fechaInicio.before(inicioReserva) && fechaFin.after(finReserva)) 
			{
				seCruza = true;
				break;
			}
		}
		return seCruza;
	}
	
	/**
	 * Adiciona un tipo de bebida con la información dada por el usuario
	 * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no existía
	 */
	public void adicionarReserva( )
	{
		try 
		{
			List<Long> usuariosDisponibles = alohandes.darIdsComunidadUniversitaria();
			if(usuariosDisponibles.size() > 0)
			{
				Long idUsuario = (Long) JOptionPane.showInputDialog (this, "Id del usuario a arrendar", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE, null, usuariosDisponibles.toArray(), (Object) usuariosDisponibles.get(0));
				List<Long> ofertasActivas = alohandes.darIdsOfertasActivas();
				if(ofertasActivas.size() > 0) 
				{
					Long idEstablecimiento = (Long) JOptionPane.showInputDialog (this, "Id del establecimiento", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE, null, ofertasActivas.toArray(), (Object) ofertasActivas.get(0));
					String descuento = JOptionPane.showInputDialog (this, "Descuento", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
					String dia = JOptionPane.showInputDialog (this, "Día en números", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
					String mes = JOptionPane.showInputDialog (this, "Mes en números", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
					String anio = JOptionPane.showInputDialog (this, "Anio en números", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
					String duracion = JOptionPane.showInputDialog (this, "Duración", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
					if (idUsuario != null && idEstablecimiento != null && anio != null && mes != null && dia != null && descuento != null && duracion != null)
					{
						@SuppressWarnings("deprecation")
						Timestamp fechaInicio = new Timestamp(Integer.parseInt(anio) - 1900, Integer.parseInt(mes) - 1, Integer.parseInt(dia), 0, 0, 0, 0);
						List<Object[]> fechasReservas = alohandes.darFechasReservasEstablecimientoPorId(idEstablecimiento);
						boolean seCruza = hayConflictoFechas(fechaInicio, Integer.parseInt(duracion), fechasReservas);
						if(!seCruza) 
						{
							VOArriendo ar = alohandes.adicionarReserva(idUsuario, idEstablecimiento, Integer.parseInt(descuento), fechaInicio, 
									Integer.parseInt(duracion));
							if (ar == null)
							{
								throw new Exception ("No se pudo crear la reserva");
							}
							String resultado = "En adicionarReserva\n\n";
							resultado += "Reserva adicionada exitosamente: " + ar.getId();
							resultado += "\n Operación terminada";
							panelDatos.actualizarInterfaz(resultado);
						}
						else
						{
							panelDatos.actualizarInterfaz("Ya existe una reserva para esas fechas");
						}
					}
					else
					{
						panelDatos.actualizarInterfaz("El usuario canceló la operación");
					}
				}
				else
				{
					panelDatos.actualizarInterfaz("No existen establecimientos sobre los cuales reservar");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("No existen usuarios que puedan hacer una reserva");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Método que cancela una reserva de alojamiento.
	 */
	public void cancelarReserva()
	{
		try 
		{
			List<Long> ofertasActivas = alohandes.darIdsArriendosActivos();
			if(ofertasActivas.size() > 0) {
				Long id = (Long) JOptionPane.showInputDialog (this, "Id de la reserva", "Cancelar reserva", JOptionPane.QUESTION_MESSAGE, null, ofertasActivas.toArray(), (Object) ofertasActivas.get(0));			
				if (id != null)
				{
					long idReserva = Long.valueOf (id);
					long reCancelada = alohandes.cancelarReserva(idReserva);

					String resultado = "En Cancelar Reserva\n\n";
					resultado += "La reserva " + reCancelada + " fue cancelada\n";
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
			else 
			{
				panelDatos.actualizarInterfaz("No hay reservas por cancelar");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Método que cancela una oferta de alojamiento, establecimiento.
	 */
	public void cancelarOfertaAlojamiento()
	{
		try 
		{
			List<Long> ofertasActivas = alohandes.darIdsOfertasActivas();
			if(ofertasActivas.size() > 0) 
			{
				Long id = (Long) JOptionPane.showInputDialog (this, "Id del establecimiento", "Retirar oferta de alojamiento", JOptionPane.QUESTION_MESSAGE, null, ofertasActivas.toArray(), (Object) ofertasActivas.get(0));
				if (id != null)
				{
					long idEstab = Long.valueOf (id);
					long esCancelada = alohandes.cancelarOfertaAlojamiento(idEstab);

					String resultado = "En Cancelar Oferta\n\n";
					resultado += "La oferta del establecimiento " + esCancelada + " fue cancelada\n";
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("No hay ofertas de alojamiento por cancelar");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Método que muestra la cantidad de dinero ganada por los proveedores en lo que va del año.
	 */
	public void dineroRecibidoPorCadaProveedor()
	{
		List<Object[]> respuesta = alohandes.dineroRecibidoPorCadaProveedor();
		for(Object[] proveedor: respuesta) {
			String resultado = "El usuario proveedor con id " + proveedor[0] + " ha recibido " + proveedor[1] + " cantidad de dinero";
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Método que informa de las ofertas más populares.
	 */
	public void ofertasMasPopulares()
	{
		List<Object[]> respuesta = alohandes.ofertasMasPopulares();
		int puesto = 0;
		String resultado = "";
		for(Object[] oferta: respuesta) {
			puesto += 1;
			resultado += "La oferta con id " + oferta[0] + " ocupa el puesto " + puesto + " por haber recibido " + oferta[1] + " reservas\n";
		}
		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Método que adiciona un establecimiento.
	 */
	public void adicionarEstablecimiento( )
	{

	
		String nombre = JOptionPane.showInputDialog (this, "Descuento", "Adicionar establecimiento", JOptionPane.QUESTION_MESSAGE);
		Long idTipoEstablecimiento = Long.parseLong(JOptionPane.showInputDialog (this, "Id del tipo de establecimiento", "Adicionar establecimiento", JOptionPane.QUESTION_MESSAGE));	
		String direccion = JOptionPane.showInputDialog (this, "Dirección", "Adicionar establecimiento", JOptionPane.QUESTION_MESSAGE);
		double costo = Double.parseDouble(JOptionPane.showInputDialog (this, "Costo en números", "Adicionar establecimiento", JOptionPane.QUESTION_MESSAGE));
		int porDiaOMes = Integer.parseInt(JOptionPane.showInputDialog (this, "Por día o mes: escribir 0 para días o 1 para mes", "Adicionar establecimiento", JOptionPane.QUESTION_MESSAGE));
		long idSeguroArrendamiento = Long.parseLong(JOptionPane.showInputDialog (this, "Id del seguro de arrendamiento", "Adicionar establecimiento", JOptionPane.QUESTION_MESSAGE));
		long idDuenio = Long.parseLong(JOptionPane.showInputDialog (this, "Id del dueno(usuario)", "Adicionar establecimiento", JOptionPane.QUESTION_MESSAGE));
		long idHorario = Long.parseLong(JOptionPane.showInputDialog (this, "Id del horario", "Adicionar establecimiento", JOptionPane.QUESTION_MESSAGE));
		int activo = Integer.parseInt(JOptionPane.showInputDialog (this, "activo: escribir 0 para no activo o 1 para activo", "Adicionar establecimiento", JOptionPane.QUESTION_MESSAGE));
		
		
	}

	public void adicionarCaracteristicaEstablecimiento()
	{
		long idEstablecimiento = Long.parseLong(JOptionPane.showInputDialog (this, "Id del establecimiento", "Adicionar característica de establecimiento", JOptionPane.QUESTION_MESSAGE));
		String nombre = JOptionPane.showInputDialog (this, "Nombre", "Adicionar característica de establecimiento", JOptionPane.QUESTION_MESSAGE);
		String descripcion = JOptionPane.showInputDialog (this, "Descripción", "Característica de establecimiento", JOptionPane.QUESTION_MESSAGE);
		//double costo
	}

	//
	//	/* ****************************************************************
	//	 * 			Métodos administrativos
	//	 *****************************************************************/
	//	/**
	//	 * Muestra el log de Parranderos
	//	 */
	//	public void mostrarLogParranderos ()
	//	{
	//		mostrarArchivo ("parranderos.log");
	//	}
	//	
	//	/**
	//	 * Muestra el log de datanucleus
	//	 */
	//	public void mostrarLogDatanuecleus ()
	//	{
	//		mostrarArchivo ("datanucleus.log");
	//	}
	//	
	//	/**
	//	 * Limpia el contenido del log de parranderos
	//	 * Muestra en el panel de datos la traza de la ejecución
	//	 */
	//	public void limpiarLogParranderos ()
	//	{
	//		// Ejecución de la operación y recolección de los resultados
	//		boolean resp = limpiarArchivo ("parranderos.log");
	//
	//		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
	//		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
	//		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
	//		resultado += "\nLimpieza terminada";
	//
	//		panelDatos.actualizarInterfaz(resultado);
	//	}
	//	
	//	/**
	//	 * Limpia el contenido del log de datanucleus
	//	 * Muestra en el panel de datos la traza de la ejecución
	//	 */
	//	public void limpiarLogDatanucleus ()
	//	{
	//		// Ejecución de la operación y recolección de los resultados
	//		boolean resp = limpiarArchivo ("datanucleus.log");
	//
	//		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
	//		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
	//		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
	//		resultado += "\nLimpieza terminada";
	//
	//		panelDatos.actualizarInterfaz(resultado);
	//	}
	//	
	//	/**
	//	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	//	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	//	 */
	//	public void limpiarBD ()
	//	{
	//		try 
	//		{
	//    		// Ejecución de la demo y recolección de los resultados
	//			long eliminados [] = parranderos.limpiarParranderos();
	//			
	//			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
	//			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
	//			resultado += eliminados [0] + " Gustan eliminados\n";
	//			resultado += eliminados [1] + " Sirven eliminados\n";
	//			resultado += eliminados [2] + " Visitan eliminados\n";
	//			resultado += eliminados [3] + " Bebidas eliminadas\n";
	//			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
	//			resultado += eliminados [5] + " Bebedores eliminados\n";
	//			resultado += eliminados [6] + " Bares eliminados\n";
	//			resultado += "\nLimpieza terminada";
	//   
	//			panelDatos.actualizarInterfaz(resultado);
	//		} 
	//		catch (Exception e) 
	//		{
	////			e.printStackTrace();
	//			String resultado = generarMensajeError(e);
	//			panelDatos.actualizarInterfaz(resultado);
	//		}
	//	}

	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}


	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
	//    /**
	//     * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada tipo de bebida
	//     * @param lista - La lista con los tipos de bebida
	//     * @return La cadena con una líea para cada tipo de bebida recibido
	//     */
	//    private String listarTiposBebida(List<VOTipoBebida> lista) 
	//    {
	//    	String resp = "Los tipos de bebida existentes son:\n";
	//    	int i = 1;
	//        for (VOTipoBebida tb : lista)
	//        {
	//        	resp += i++ + ". " + tb.toString() + "\n";
	//        }
	//        return resp;
	//	}

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazParranderosApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazParranderosApp interfaz = new InterfazParranderosApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
