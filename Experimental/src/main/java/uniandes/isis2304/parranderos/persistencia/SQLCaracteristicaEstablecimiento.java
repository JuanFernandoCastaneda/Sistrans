package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLCaracteristicaEstablecimiento {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicaci�n
	 */
	private PersistenciaAlohAndes pp;

	/* ****************************************************************
	 * 			M�todos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLCaracteristicaEstablecimiento (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	//TODO los m�todos que sean necesarios para esta clase.
	
	public long adicionarCaracteristicaEstablecimiento(PersistenceManager pm, long id, long idEstablecimiento, String nombre, String descripcion, double costo) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCaracteristicaEstablecimiento() + " (id, idEstablecimiento, nombre, descripcion, costo) values (?, ?, ?, ?, ?)");
        q.setParameters(id, idEstablecimiento, nombre, descripcion, costo);
        return (long) q.executeUnique();
    }
}
