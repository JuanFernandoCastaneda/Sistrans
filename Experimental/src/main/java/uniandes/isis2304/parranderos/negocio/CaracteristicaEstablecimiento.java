package uniandes.isis2304.parranderos.negocio;

/**
 * The Class CaracteristicaEstablecimiento.
 */
public class CaracteristicaEstablecimiento implements VOCaracteristicaEstablecimiento {
	
	/** The id. */
	private long id;
	
	/** The nombre. */
	private String nombre;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The costo. */
	private double costo;
	
	/** The id establecimiento. */
	private long idEstablecimiento;

	/**
	 * Instantiates a new caracteristica establecimiento.
	 */
	public CaracteristicaEstablecimiento() {
		super();
		this.id = 0;
		this.nombre = "";
		this.descripcion = "";
		this.costo = 0;
		this.idEstablecimiento = 0;
	}
	
	/**
	 * Instantiates a new caracteristica establecimiento.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param descripcion the descripcion
	 * @param costo the costo
	 * @param idEstablecimiento the id establecimiento
	 */
	public CaracteristicaEstablecimiento(long id, String nombre, String descripcion, double costo,
			long idEstablecimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.idEstablecimiento = idEstablecimiento;
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
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the costo.
	 *
	 * @return the costo
	 */
	public double getCosto() {
		return costo;
	}

	/**
	 * Sets the costo.
	 *
	 * @param costo the new costo
	 */
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/**
	 * Gets the id establecimiento.
	 *
	 * @return the id establecimiento
	 */
	public long getIdEstablecimiento() {
		return idEstablecimiento;
	}

	/**
	 * Sets the id establecimiento.
	 *
	 * @param idEstablecimiento the new id establecimiento
	 */
	public void setIdEstablecimiento(long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}
	
}
