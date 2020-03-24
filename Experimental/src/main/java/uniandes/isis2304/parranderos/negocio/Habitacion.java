package uniandes.isis2304.parranderos.negocio;

/**
 * The Class Habitacion.
 */
public class Habitacion implements VOHabitacion {
	
	/** The id. */
	private long id;
	
	/** The numero habitacion. */
	private int numeroHabitacion;
	
	/** The capacidad. */
	private int capacidad;
	
	/** The costo. */
	private double costo;
	
	/** The compartida. */
	private boolean compartida;
	
	/** The id establecimiento. */
	private long idEstablecimiento;

	/**
	 * Instantiates a new habitacion.
	 */
	public Habitacion() {
		super();
		this.id = 0;
		this.numeroHabitacion = 0;
		this.capacidad = 0;
		this.costo = 0;
		this.compartida = false;
		this.idEstablecimiento = 0;
	}
	
	/**
	 * Instantiates a new habitacion.
	 *
	 * @param id the id
	 * @param numeroHabitacion the numero habitacion
	 * @param capacidad the capacidad
	 * @param costo the costo
	 * @param compartida the compartida
	 * @param idEstablecimiento the id establecimiento
	 */
	public Habitacion(long id, int numeroHabitacion, int capacidad, double costo, boolean compartida,
			long idEstablecimiento) {
		super();
		this.id = id;
		this.numeroHabitacion = numeroHabitacion;
		this.capacidad = capacidad;
		this.costo = costo;
		this.compartida = compartida;
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
	 * Gets the numero habitacion.
	 *
	 * @return the numero habitacion
	 */
	public int getNumeroHabitacion() {
		return numeroHabitacion;
	}

	/**
	 * Sets the numero habitacion.
	 *
	 * @param numeroHabitacion the new numero habitacion
	 */
	public void setNumeroHabitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	/**
	 * Gets the capacidad.
	 *
	 * @return the capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * Sets the capacidad.
	 *
	 * @param capacidad the new capacidad
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
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
	 * Checks if is compartida.
	 *
	 * @return true, if is compartida
	 */
	public boolean isCompartida() {
		return compartida;
	}

	/**
	 * Sets the compartida.
	 *
	 * @param compartida the new compartida
	 */
	public void setCompartida(boolean compartida) {
		this.compartida = compartida;
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
