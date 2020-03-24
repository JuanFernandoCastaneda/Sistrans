package uniandes.isis2304.parranderos.negocio;

/**
 * The Interface VOHabitacion.
 */
public interface VOHabitacion {
	
	/**
	 * Gets the numero habitacion.
	 *
	 * @return the numero habitacion
	 */
	public long getId();

	/**
	 * Gets the id establecimiento.
	 *
	 * @return the id establecimiento
	 */
	public long getIdEstablecimiento();
	
	
	/**
	 * Gets the numero habitacion.
	 *
	 * @return the numero habitacion
	 */
	public int getNumeroHabitacion();
	
	/**
	 * Gets the capacidad.
	 *
	 * @return the capacidad
	 */
	public int getCapacidad();
	
	/**
	 * Gets the costo.
	 *
	 * @return the costo
	 */
	public double getCosto();
	
	/**
	 * Gets the compartida.
	 *
	 * @return the compartida
	 */
	public boolean isCompartida();
	
}
