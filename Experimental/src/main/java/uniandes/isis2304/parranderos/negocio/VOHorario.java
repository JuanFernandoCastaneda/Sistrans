package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

/**
 * The Interface VOHorario.
 */
public interface VOHorario {
	
	/**
	 * Gets the id establecimiento.
	 *
	 * @return the id establecimiento
	 */
	public long getId();
	
	/**
	 * Gets the hora apertura.
	 *
	 * @return the hora apertura
	 */
	public Timestamp getHoraApertura();
	
	/**
	 * Gets the hora cierre.
	 *
	 * @return the hora cierre
	 */
	public Timestamp getHoraCierre();
	
}
