package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.persistencia.PersistenciaAlohAndes;

public class PrincipalAlohAndes {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(PrincipalAlohAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohAndes pp;
	
	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public PrincipalAlohAndes() {

		pp = PersistenciaAlohAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public PrincipalAlohAndes (JsonObject tableConfig)
	{
		pp = PersistenciaAlohAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexi�n con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	//TODO m�todos necesarios
	
	/* ****************************************************************
	 * 			M�todos para manejar los USUARIOS
	 *****************************************************************/

	public Usuario adicionarUsuario (long idTipoUsuario, String nombre)
	{
		log.info ("Adicionando usuario " + nombre);
		Usuario usuario = pp.adicionarUsuario (idTipoUsuario, nombre);
        log.info ("Adicionando usuario: " + usuario);
        return usuario;
	}

	/**
	 * M�todo que se comunica con la persistencia para crear una reserva.
	 * 
	 * @param idUsuario
	 * @param idEstablecimiento
	 * @param descuento
	 * @param duracion
	 * @return nuevo arriendo creado.
	 */
	public Arriendo adicionarReserva(long idUsuario, long idEstablecimiento, int descuento, Timestamp fechaInicio, int duracion) {
		log.info("Adicionando reserva");
		Arriendo arriendo = pp.adicionarReserva (idUsuario, idEstablecimiento, descuento, fechaInicio, duracion);
		log.info("Adicionada reserva id " + arriendo.getId());
		return arriendo;
	}
	
	/**
	 * M�todo que se comunica con la persistencia para cancelar una reserva.
	 * 
	 * @param id
	 * @return id de la reserva, -1 si no se pudo.
	 */
	public long cancelarReserva(long id) {
		log.info("Cancelando reserva");
		long reserva = pp.cancelarReserva(id);
		log.info("Cancelada reserva id " + reserva);
		return reserva;
	}
	
	/**
	 * M�todo que se comunica con la persistencia para cancelar un alojamiento.
	 * 
	 * @param id
	 * @return id del alojamiento, -1 si no se pudo.
	 */
	public long cancelarOfertaAlojamiento(long id) {
		log.info("Cancelando oferta alojamiento");
		long oferta = pp.cancelarReserva(id);
		log.info("Cancelada la oferta de alojamiento con id " + oferta);
		return oferta;
	}
	
	/**
	 * M�todo que regresa el dinero recibido por cada proveedor.
	 * 
	 * @return arreglo [idProveedor, cantidadDinero].
	 */
	public List<Object[]> dineroRecibidoPorCadaProveedor() {
		log.info ("Listando dinero recibido por cada proveedor");
        List<Object []> tuplas = pp.dineroRecibidoPorCadaProveedor ();
        log.info ("Listando dinero recibido por cada proveedor: Listo!");
        return tuplas;
	}
	
	/**
	 * M�todo que regresa las ofertas m�s populares.
	 * 
	 * @return arreglo con los ids de las ofertas.
	 */
	public List<Long> ofertasMasPopulares() {
		log.info ("Listando ofertas m�s populares");
        List<Long> tuplas = pp.ofertasMasPopulares ();
        log.info ("Listando ofertas m�s populares: Listo!");
        return tuplas;
	}
	
	
	/* ****************************************************************
	 * 			M�todos para administraci�n
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AlohAndes
	 * @return Un arreglo
	 */
	//TODO no es tan necesario.
//	public long [] limpiarAlohAndes ()
//	{
//        log.info ("Limpiando la BD de AlohAndes");
//        long [] borrrados = pp.limpiarAlohAndes();	
//        log.info ("Limpiando la BD de AlohAndes: Listo!");
//        return borrrados;
//	}
}
