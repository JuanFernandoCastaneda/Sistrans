package uniandes.isis2304.parranderos.negocio;

/**
 * The Class CaracteristicaHabitacion.
 */
public class CaracteristicaHabitacion implements VOCaracteristicaHabitacion {
	
	/** The id. */
	private long id;
	
	/** The nombre. */
	private String nombre;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The costo. */
	private double costo;
	
	/** The id habitacion. */
	private long idHabitacion;

	/**
	 * Instantiates a new caracteristica habitacion.
	 */
	public CaracteristicaHabitacion() {
		super();
		this.id = 0;
		this.nombre = "";
		this.descripcion = "";
		this.costo = 0;
		this.idHabitacion = 0;
	}
	
	/**
	 * Instantiates a new caracteristica habitacion.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param descripcion the descripcion
	 * @param costo the costo
	 * @param idHabitacion the id habitacion
	 */
	public CaracteristicaHabitacion(long id, String nombre, String descripcion, double costo, long idHabitacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.idHabitacion = idHabitacion;
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
	 * Gets the id habitacion.
	 *
	 * @return the id habitacion
	 */
	public long getIdHabitacion() {
		return idHabitacion;
	}

	/**
	 * Sets the id habitacion.
	 *
	 * @param idHabitacion the new id habitacion
	 */
	public void setIdHabitacion(long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}
	
}
