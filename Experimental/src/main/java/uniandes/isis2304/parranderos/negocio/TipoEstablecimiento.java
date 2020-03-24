package uniandes.isis2304.parranderos.negocio;

/**
 * The Class TipoEstablecimiento.
 */
public class TipoEstablecimiento implements VOTipoEstablecimiento {
	
	/** The id. */
	private long id;
	
	/** The tipo establecimiento. */
	private String tipoEstablecimiento;

	/**
	 * Instantiates a new tipo establecimiento.
	 */
	public TipoEstablecimiento() {
		super();
		this.id = 0;
		this.tipoEstablecimiento = "";
	}
	
	/**
	 * Instantiates a new tipo establecimiento.
	 *
	 * @param id the id
	 * @param tipoEstablecimiento the tipo establecimiento
	 */
	public TipoEstablecimiento(long id, String tipoEstablecimiento) {
		super();
		this.id = id;
		this.tipoEstablecimiento = tipoEstablecimiento;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the tipo establecimiento.
	 *
	 * @return the tipo establecimiento
	 */
	public String getTipoEstablecimiento() {
		return tipoEstablecimiento;
	}

	/**
	 * Sets the tipo establecimiento.
	 *
	 * @param tipoEstablecimiento the new tipo establecimiento
	 */
	public void setTipoEstablecimiento(String tipoEstablecimiento) {
		this.tipoEstablecimiento = tipoEstablecimiento;
	}
	
}
