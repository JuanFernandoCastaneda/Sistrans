package uniandes.isis2304.parranderos.negocio;

/**
 * The Interface VOCaracteristicaEstablecimiento.
 */
public interface VOCaracteristicaEstablecimiento {
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId();
	
	/**
	 * Gets the id establecimiento.
	 *
	 * @return the id establecimiento
	 */
	public long getIdEstablecimiento();
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre();
	
	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion();
	
	/**
	 * Gets the costo.
	 *
	 * @return the costo
	 */
	public double getCosto();	
	
}
