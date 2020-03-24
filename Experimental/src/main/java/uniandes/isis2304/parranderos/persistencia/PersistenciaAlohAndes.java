package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Arriendo;
import uniandes.isis2304.parranderos.negocio.Usuario;

public class PersistenciaAlohAndes {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/** Logger para escribir la traza de la ejecuci�n. */
	private static Logger log = Logger.getLogger(PersistenciaAlohAndes.class.getName());
	
	/** Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta. */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/** Atributo privado que es el �nico objeto de la clase - Patr�n SINGLETON. */
	private static PersistenciaAlohAndes instance;
	
	/** F�brica de Manejadores de persistencia, para el manejo correcto de las transacciones. */
	private PersistenceManagerFactory pmf;
	
	//TODO
	/** Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden: Secuenciador,. */
	private List <String> tablas;
	
	/** Atributo para el acceso a las sentencias SQL propias a PersistenciaAlohAndes. */
	private SQLUtil sqlUtil;
	
	/** Atributo para el acceso a la tabla ALOHANDES de la base de datos. */
	private SQLAlohAndes sqlAlohAndes;
	
	/** Atributo para el acceso a la tabla ARRIENDO de la base de datos. */
	private SQLArriendo sqlArriendo;
	
	/** Atributo para el acceso a la tabla CaracteristicaEstablecimiento de la base de datos. */
	private SQLCaracteristicaEstablecimiento sqlCaracteristicaEstablecimiento;
	
	/** Atributo para el acceso a la tabla CaracteristicaHabitacion de la base de datos. */
	private SQLCaracteristicaHabitacion sqlCaracteristicaHabitacion;
	
	/** Atributo para el acceso a la tabla ESTABLECIMIENTO de la base de datos. */
	private SQLEstablecimiento sqlEstablecimiento;
	
	/** Atributo para el acceso a la tabla HABITACION de la base de datos. */
	private SQLHabitacion sqlHabitacion;
	
	/** Atributo para el acceso a la tabla HORARIO de la base de datos. */
	private SQLHorario sqlHorario;
	
	/** Atributo para el acceso a la tabla SeguroArrendamiento de la base de datos. */
	private SQLSeguroArrendamiento sqlSeguroArrendamiento;
	
	/** Atributo para el acceso a la tabla TipoEstablecimiento de la base de datos. */
	private SQLTipoEstablecimiento sqlTipoEstablecimiento;
	
	/** Atributo para el acceso a la tabla TIPOUSUARIO de la base de datos. */
	private SQLTipoUsuario sqlTipoUsuario;
	
	/** Atributo para el acceso a la tabla USUARIO de la base de datos. */
	private SQLUsuario sqlUsuario;
	
	
	/* ****************************************************************
	 * 			M�todos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patr�n SINGLETON.
	 */
	private PersistenciaAlohAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Parranderos");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Parranderos_sequence");
		tablas.add ("ALOHANDES");
		tablas.add ("ARRIENDO");
		tablas.add ("CARACTERISTICAESTABLECIMIENTO");
		tablas.add ("CARACTERISTICAHABITACION");
		tablas.add ("ESTABLECIMIENTO");
		tablas.add ("HABITACION");
		tablas.add ("HORARIO");
		tablas.add ("SEGUROARRENDAMIENTO");
		tablas.add ("TIPOESTABLECIMIENTO");
		tablas.add ("TIPOUSUARIO");
		tablas.add ("USUARIO");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patr�n SINGLETON.
	 *
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * Gets the single instance of PersistenciaAlohAndes.
	 *
	 * @return Retorna el �nico objeto PersistenciaParranderos existente - Patr�n SINGLETON
	 */
	public static PersistenciaAlohAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig.
	 *
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el �nico objeto PersistenciaParranderos existente - Patr�n SINGLETON
	 */
	public static PersistenciaAlohAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexi�n con la base de datos.
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos.
	 *
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL.
	 */
	private void crearClasesSQL ()
	{
		sqlAlohAndes = new SQLAlohAndes(this);
		sqlArriendo = new SQLArriendo(this);
		sqlCaracteristicaEstablecimiento = new SQLCaracteristicaEstablecimiento(this);
		sqlCaracteristicaHabitacion = new SQLCaracteristicaHabitacion(this);
		sqlEstablecimiento = new SQLEstablecimiento(this);
		sqlHabitacion = new SQLHabitacion(this);
		sqlHorario = new SQLHorario(this);
		sqlSeguroArrendamiento = new SQLSeguroArrendamiento(this);
		sqlTipoEstablecimiento = new SQLTipoEstablecimiento(this);
		sqlTipoUsuario = new SQLTipoUsuario(this);
		sqlUsuario = new SQLUsuario(this);
		sqlUtil = new SQLUtil(this);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de AlohAndes
	 */
	public String darSeqAlohAndes ()
	{
		return tablas.get (0);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaAlohAndes ()
	{
		return tablas.get (1);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaArriendo ()
	{
		return tablas.get (2);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaCaracteristicaEstablecimiento ()
	{
		return tablas.get (3);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaCaracteristicaHabitacion ()
	{
		return tablas.get (4);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaEstablecimiento ()
	{
		return tablas.get (5);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaHabitacion ()
	{
		return tablas.get (6);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaHorario ()
	{
		return tablas.get (7);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaSeguroArrendamiento ()
	{
		return tablas.get (8);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaTipoEstablecimiento ()
	{
		return tablas.get (9);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaTipoUsuario ()
	{
		return tablas.get (10);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de 
	 */
	public String darTablaUsuario ()
	{
		return tablas.get (11);
	}
	
	/**
	 * Transacci�n para el generador de secuencia de AlohAndes
	 * Adiciona entradas al log de la aplicaci�n
	 * @return El siguiente n�mero del secuenciador de AlohAndes
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle espec�fico del problema encontrado
	 * @param e - La excepci�n que ocurrio
	 * @return El mensaje de la excepci�n JDO
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
	
	//TODO m�todos para manejar necesarios.
	
	/* ****************************************************************
	 * 			M�todos para manejar los USUARIOS
	 *****************************************************************/
	
	public Usuario adicionarUsuario(long idTipoUsuario, String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlUsuario.adicionarUsuario(pmf.getPersistenceManager(), id, idTipoUsuario, nombre);
            tx.commit();

            log.trace ("Inserci�n de bebedor: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Usuario (id, idTipoUsuario, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * M�todo que se asegura de agregar una nueva reserva.
	 * 
	 * @param idUsuario
	 * @param idEstablecimiento
	 * @param descuento
	 * @param duracion
	 * @return el arriendo creado.
	 */
	public Arriendo adicionarReserva(long idUsuario, long idEstablecimiento, int descuento, Timestamp fechaInicio, int duracion) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long id = nextval();
			String estado = "Activo";
			long tuplasInsertadas = sqlArriendo.adicionarArriendo(pmf.getPersistenceManager(), id, idUsuario, idEstablecimiento, descuento, fechaInicio, duracion, estado);
			tx.commit();
			log.trace("Inserci�n de arriendo id " + id + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Arriendo(id, idUsuario, idEstablecimiento, descuento, fechaInicio, duracion, estado);
		} catch(Exception e) {
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		} finally {
			if(tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/**
	 * M�todo que cancela una reserva. 
	 * 
	 * @param id
	 * @return id de la reserva cancelada, 0 si no se pudo.
	 */
	public long cancelarReserva(long id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasModificadas = sqlArriendo.cancelarArriendo(pm, id);
			tx.commit();
			log.trace("Cancelaci�n del arriendo id " + id + ": " + tuplasModificadas + " tuplas modificadas");
			return id;
		} catch(Exception e) {
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if(tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/**
	 * M�todo que retira una oferta de alojamiento.
	 * 
	 * @param id
	 * @return id del alojamiento retirado, -1 si no se pudo.
	 */
	public long retirarOfertaAlojamiento(long id) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try {
			tx.begin();
			long tuplasModificadas = sqlEstablecimiento.retirarOfertaEstablecimiento(pm, id);
			tx.commit();
			log.trace("Cancelaci�n de la oferta del establecimiento con id " + id + ": " + tuplasModificadas + " tuplas modificadas");
			return id;
		} catch(Exception e) {
			log.error("Exception: " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		} finally {
			if(tx.isActive()) {
				tx.rollback();
			}
			pm.close();
		}
	}
	
	/**
	 * M�todo que retorna el dinero recibido por cada proveedor.
	 * @return arreglo de arreglos, en cada posici�n hay un arreglo de dos cosas, la primera es el id y la segunda el dinero ganado.
	 */
	public List<Object []> dineroRecibidoPorCadaProveedor ()
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlUsuario.dineroRecibidoProveedor (pmf.getPersistenceManager());
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			long id = ((BigDecimal) datos [0]).longValue ();
			double dineroGanado = ((Double) datos [1]).doubleValue();

			Object [] resp = new Object [2];
			resp [0] = id;
			resp [1] = dineroGanado;	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	
	/**
	 * M�todo que retorna las 20 ofertas m�s populares.
	 * 
	 * @return arreglo con el id de cada una de ellas.
	 */
	public List<Long> ofertasMasPopulares() {
		List<Long> respuesta = new LinkedList <Long> ();
		List<Object> tuplas = sqlArriendo.veinteOfertasMasPopulares(pmf.getPersistenceManager());
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			long id = ((BigDecimal) datos [0]).longValue ();			
			respuesta.add(id);
        }

		return respuesta;
	}
	
}
