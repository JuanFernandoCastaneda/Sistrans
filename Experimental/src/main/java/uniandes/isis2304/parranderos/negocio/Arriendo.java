package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

/**
 * The Class Arriendo.
 */
public class Arriendo implements VOArriendo {
	
	/** The id. */
	private long id;
	
	/** The id establecimiento. */
	private long idEstablecimiento;
	
	/** The id usuario. */
	private long idUsuario;
	
	/** The descuento. */
	private int descuento;
	
	/** The fecha inicio. */
	private Timestamp fechaInicio;
	
	/** The duracion. */
	private int duracion;
	
	/** The estado. */
	private String estado;

	/**
	 * Instantiates a new arriendo.
	 */
	public Arriendo() {
		super();
		this.id = 0;
		this.idEstablecimiento = 0;
		this.idUsuario = 0;
		this.descuento = 0;
		this.fechaInicio = new Timestamp(0);
		this.duracion = 0;
		this.estado = "";
	}
	
	/**
	 * Instantiates a new arriendo.
	 *
	 * @param id the id
	 * @param idEstablecimiento the id establecimiento
	 * @param idUsuario the id usuario
	 * @param descuento the descuento
	 * @param fechaInicio the fecha inicio
	 * @param duracion the duracion
	 * @param estado the estado
	 */
	public Arriendo(long id, long idEstablecimiento, long idUsuario, int descuento, Timestamp fechaInicio, int duracion, String estado) {
		super();
		this.id = id;
		this.idEstablecimiento = idEstablecimiento;
		this.idUsuario = idUsuario;
		this.descuento = descuento;
		this.fechaInicio = fechaInicio;
		this.duracion = duracion;
		this.estado = estado;
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

	/**
	 * Gets the id usuario.
	 *
	 * @return the id usuario
	 */
	public long getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Sets the id usuario.
	 *
	 * @param idUsuario the new id usuario
	 */
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Gets the descuento.
	 *
	 * @return the descuento
	 */
	public int getDescuento() {
		return descuento;
	}

	/**
	 * Sets the descuento.
	 *
	 * @param descuento the new descuento
	 */
	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio the new fecha inicio
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the duracion.
	 *
	 * @return the duracion
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * Sets the duracion.
	 *
	 * @param duracion the new duracion
	 */
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
