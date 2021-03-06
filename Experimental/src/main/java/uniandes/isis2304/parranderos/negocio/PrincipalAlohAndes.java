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

	/**
	 * Método que comunica con la persistencia para adicionar un usuario.
	 * @param idTipoUsuario
	 * @param nombre
	 * @return el usuario creado
	 */
	public Usuario adicionarUsuario (long idTipoUsuario, String nombre)
	{
		log.info ("Adicionando usuario " + nombre);
		Usuario usuario = pp.adicionarUsuario (idTipoUsuario, nombre);
        log.info ("Adicionando usuario: " + usuario);
        return usuario;
	}
	
	/**
	 * Método que se comunica con la persistencia para retornar los ids de los miembros de la comunidad un.
	 * @return lista con los ids.
	 */
	public List<Long> darIdsComunidadUniversitaria() {
		log.info("Retornando los ids de la comunidad universitaria");
		List<Long> respuesta = pp.darIdsComunidadUniversitaria();
		log.info("Retornados los ids de la comunidad universitaria");
		return respuesta;
	}
	
	/**
	 * Método que retorna los ids de las ofertas activas.
	 * @return lista de longs.
	 */
	public List<Long> darIdsOfertasActivas() {
		log.info("Retornando los ids de las ofertas activas");
		return pp.darIdsOfertasActivas();
	}
	
	/**
	 * Método que retorna los ids de los arriendos activos.
	 * @return lista de longs.
	 */
	public List<Long> darIdsArriendosActivos() {
		log.info("Retornando los ids de los arriendos activas");
		return pp.darIdsArriendosActivos();
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
		long oferta = pp.retirarOfertaAlojamiento(id);
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
	public List<Object[]> ofertasMasPopulares() {
		log.info ("Listando ofertas m�s populares");
        List<Object[]> tuplas = pp.ofertasMasPopulares ();
        log.info ("Listando ofertas m�s populares: Listo!");
        return tuplas;
	}
	
	public Establecimiento adicionarEstablecimiento(String nombre, long idTipoEstablecimiento, String direccion, double costo,
			boolean porDiaOMes, long idSeguroArrendamiento, long idDuenio, long idHorario, boolean activo) {
		log.info("Adicionando Establecimiento");
		Establecimiento establecimiento = pp.adicionarEstablecimiento(nombre, idTipoEstablecimiento, direccion, costo, porDiaOMes, activo, idSeguroArrendamiento, idHorario, idDuenio);
		log.info("Adicionado Establecimiento id " + establecimiento.getId());
		return establecimiento;
	}
	/**
	 * Método que retorna las fechas de las reservas de un establecimiento.
	 * @param id
	 * @return arreglo con la información
	 */
	public List<Object[]> darFechasReservasEstablecimientoPorId(long id) {
		log.info ("Retornando las fechas de las reservas de un establecimiento");
		return pp.darFechasReservasEstablecimientoPorId(id);
	}
	
	public CaracteristicaEstablecimiento adicionarCaracteristicaEstablecimiento(long idEstablecimiento, String nombre, String descripcion, double costo) {
		log.info("Adicionando CaracteristicaEstablecimiento");
		CaracteristicaEstablecimiento caracteristicaEstablecimiento = pp.adicionarCaracteristicaEstablecimiento(idEstablecimiento, nombre, descripcion, costo);
		log.info("Adicionado CaracteristicaEstablecimiento id " + caracteristicaEstablecimiento.getId());
		return caracteristicaEstablecimiento;
	}
	
	
	/* ****************************************************************
	 * 			M�todos para administraci�n
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de AlohAndes
	 * @return Un arreglo
	 */
	//TODO no es tan necesario.
	public long [] limpiarAlohAndes ()
	{
        log.info ("Limpiando la BD de AlohAndes");
        //long [] borrrados = pp.limpiarAlohAndes();	
        log.info ("Limpiando la BD de AlohAndes: Listo!");
        return null;
	}
}
