package uniandes.isis2304.parranderos.negocio;

/**
 * The Interface VOEstablecimiento.
 */
public interface VOEstablecimiento {
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId();
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre();
	
	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public long getIdTipoEstablecimiento();
	
	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion();
	
	/**
	 * Gets the costo.
	 *
	 * @return the costo
	 */
	public double getCosto();
	
	/**
	 * Gets the por dia O mes.
	 *
	 * @return the por dia O mes
	 */
	public boolean isPorDiaOMes();
	
	/**
	 * Gets the activo.
	 *
	 * @return the activo
	 */
	public boolean isActivo();
	
	/**
	 * Gets the id seguro arrendamiento.
	 *
	 * @return the id seguro arrendamiento
	 */
	public long getIdSeguroArrendamiento();
	
	/**
	 * Gets the id horario.
	 *
	 * @return the id horario
	 */
	public long getIdHorario();
	
	
	/**
	 * Gets the id duenio.
	 *
	 * @return the id duenio
	 */
	public long getIdDuenio();
	
}