package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

/**
 * The Class Horario.
 */
public class Horario implements VOHorario {
	
	/** The id. */
	private long id;
	
	/** The hora apertura. */
	private Timestamp horaApertura;
	
	/** The hora cierre. */
	private Timestamp horaCierre;

	/**
	 * Instantiates a new horario.
	 */
	public Horario() {
		super();
		this.id = 0;
		this.horaApertura = new Timestamp(0);
		this.horaCierre = new Timestamp(0);
	}
	
	/**
	 * Instantiates a new horario.
	 *
	 * @param id the id
	 * @param horaApertura the hora apertura
	 * @param horaCierre the hora cierre
	 */
	public Horario(long id, Timestamp horaApertura, Timestamp horaCierre) {
		super();
		this.id = id;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
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
	 * Gets the hora apertura.
	 *
	 * @return the hora apertura
	 */
	public Timestamp getHoraApertura() {
		return horaApertura;
	}

	/**
	 * Sets the hora apertura.
	 *
	 * @param horaApertura the new hora apertura
	 */
	public void setHoraApertura(Timestamp horaApertura) {
		this.horaApertura = horaApertura;
	}

	/**
	 * Gets the hora cierre.
	 *
	 * @return the hora cierre
	 */
	public Timestamp getHoraCierre() {
		return horaCierre;
	}

	/**
	 * Sets the hora cierre.
	 *
	 * @param horaCierre the new hora cierre
	 */
	public void setHoraCierre(Timestamp horaCierre) {
		this.horaCierre = horaCierre;
	}
	
}
