package uniandes.isis2304.parranderos.negocio;

/**
 * The Class SeguroArrendamiento.
 */
public class SeguroArrendamiento implements VOSeguroArrendamiento {
	
	/** The id. */
	private long id;
	
	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new seguro arrendamiento.
	 */
	public SeguroArrendamiento() {
		super();
		this.id = 0;
		this.descripcion = "";
	}
	
	/**
	 * Instantiates a new seguro arrendamiento.
	 *
	 * @param id the id
	 * @param descripcion the descripcion
	 */
	public SeguroArrendamiento(long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
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
	
}
