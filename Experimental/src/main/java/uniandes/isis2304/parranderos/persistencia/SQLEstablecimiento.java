package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLEstablecimiento {

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
	public SQLEstablecimiento (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Método que retorna los establecimientos activos.
	 * @param pm
	 * @return lista con los ids de los establecimeintos.
	 */
	public List<Object> darIdsOfertasActivas(PersistenceManager pm) {
		String query = "SELECT ID"
				+ " FROM " + pp.darTablaEstablecimiento()
				+ " WHERE ACTIVO = 1"
				+ " ORDER BY ID";
		Query q = pm.newQuery(SQL, query);
		return q.executeList();
	}
	
	/**
	 * M�todo que modifica un establecimiento para no dejarlo activo.
	 * 
	 * @param pm - Persistence Manager.
	 * @param idEstablecimiento - Id del establecimiento a desactivar.
	 * @return cantidad de tuplas modificadas.
	 */
	public long retirarOfertaEstablecimiento(PersistenceManager pm, long idEstablecimiento) {
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaEstablecimiento () + " SET activo = ? WHERE id = ?");
        q.setParameters(0, idEstablecimiento);
        return (long) q.executeUnique();
	}
	
}
