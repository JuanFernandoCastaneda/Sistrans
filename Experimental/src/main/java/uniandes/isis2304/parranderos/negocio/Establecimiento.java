package uniandes.isis2304.parranderos.negocio;

/**
 * The Class Establecimiento.
 */
public class Establecimiento implements VOEstablecimiento {
	
	/** The id. */
	private long id;
	
	/** The nombre. */
	private String nombre;
	
	/** The id tipo establecimiento. */
	private long idTipoEstablecimiento;
	
	/** The direccion. */
	private String direccion;
	
	/** The costo. */
	private double costo;
	
	/** The por dia O mes. */
	private boolean porDiaOMes;
	
	/** The id seguro arrendamiento. */
	private long idSeguroArrendamiento;
	
	/** The id duenio. */
	private long idDuenio;
	
	/** The id horario. */
	private long idHorario;
	
	/** The activo. */
	private boolean activo;

	/**
	 * Instantiates a new establecimiento.
	 */
	public Establecimiento() {
		super();
		this.id = 0;
		this.nombre = "";
		this.idTipoEstablecimiento = 0;
		this.direccion = "";
		this.costo = 0;
		this.porDiaOMes = false;
		this.idSeguroArrendamiento = 0;
		this.idDuenio = 0;
		this.idHorario = 0;
		this.activo = false;
	}
	
	/**
	 * Instantiates a new establecimiento.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param idTipoEstablecimiento the id tipo establecimiento
	 * @param direccion the direccion
	 * @param costo the costo
	 * @param porDiaOMes the por dia O mes
	 * @param idSeguroArrendamiento the id seguro arrendamiento
	 * @param idDuenio the id duenio
	 * @param idHorario the id horario
	 * @param activo the activo
	 */
	public Establecimiento(long id, String nombre, long idTipoEstablecimiento, String direccion, double costo,
			boolean porDiaOMes, long idSeguroArrendamiento, long idDuenio, long idHorario, boolean activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.idTipoEstablecimiento = idTipoEstablecimiento;
		this.direccion = direccion;
		this.costo = costo;
		this.porDiaOMes = porDiaOMes;
		this.idSeguroArrendamiento = idSeguroArrendamiento;
		this.idDuenio = idDuenio;
		this.idHorario = idHorario;
		this.activo = activo;
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
	 * Gets the id tipo establecimiento.
	 *
	 * @return the id tipo establecimiento
	 */
	public long getIdTipoEstablecimiento() {
		return idTipoEstablecimiento;
	}

	/**
	 * Sets the id tipo establecimiento.
	 *
	 * @param idTipoEstablecimiento the new id tipo establecimiento
	 */
	public void setIdTipoEstablecimiento(long idTipoEstablecimiento) {
		this.idTipoEstablecimiento = idTipoEstablecimiento;
	}

	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Sets the direccion.
	 *
	 * @param direccion the new direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	 * Checks if is por dia O mes.
	 *
	 * @return true, if is por dia O mes
	 */
	public boolean isPorDiaOMes() {
		return porDiaOMes;
	}

	/**
	 * Sets the por dia O mes.
	 *
	 * @param porDiaOMes the new por dia O mes
	 */
	public void setPorDiaOMes(boolean porDiaOMes) {
		this.porDiaOMes = porDiaOMes;
	}

	/**
	 * Gets the id seguro arrendamiento.
	 *
	 * @return the id seguro arrendamiento
	 */
	public long getIdSeguroArrendamiento() {
		return idSeguroArrendamiento;
	}

	/**
	 * Sets the id seguro arrendamiento.
	 *
	 * @param idSeguroArrendamiento the new id seguro arrendamiento
	 */
	public void setIdSeguroArrendamiento(long idSeguroArrendamiento) {
		this.idSeguroArrendamiento = idSeguroArrendamiento;
	}

	/**
	 * Gets the id duenio.
	 *
	 * @return the id duenio
	 */
	public long getIdDuenio() {
		return idDuenio;
	}

	/**
	 * Sets the id duenio.
	 *
	 * @param idDuenio the new id duenio
	 */
	public void setIdDuenio(long idDuenio) {
		this.idDuenio = idDuenio;
	}

	/**
	 * Gets the id horario.
	 *
	 * @return the id horario
	 */
	public long getIdHorario() {
		return idHorario;
	}

	/**
	 * Sets the id horario.
	 *
	 * @param idHorario the new id horario
	 */
	public void setIdHorario(long idHorario) {
		this.idHorario = idHorario;
	}

	/**
	 * Checks if is activo.
	 *
	 * @return true, if is activo
	 */
	public boolean isActivo() {
		return activo;
	}

	/**
	 * Sets the activo.
	 *
	 * @param activo the new activo
	 */
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
