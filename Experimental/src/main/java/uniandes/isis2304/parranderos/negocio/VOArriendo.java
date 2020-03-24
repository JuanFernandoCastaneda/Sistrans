package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

/**
 * The Interface VOArriendo.
 */
public interface VOArriendo {
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId();
	
	/**
	 * Gets the id usuario.
	 *
	 * @return the id usuario
	 */
	public long getIdUsuario();
	
	/**
	 * Gets the id establecimiento.
	 *
	 * @return the id establecimiento
	 */
	public long getIdEstablecimiento();
	
	/**
	 * Gets the descuento.
	 *
	 * @return the descuento
	 */
	public int getDescuento();
	
	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public Timestamp getFechaInicio();
	
	/**
	 * Gets the duracion.
	 *
	 * @return the duracion
	 */
	public int getDuracion();
	
	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado();
	
}
