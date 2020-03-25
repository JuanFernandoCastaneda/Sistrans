package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLUsuario {

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
	public SQLUsuario (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	public long adicionarUsuario(PersistenceManager pm, long id, long idTipoUsuario, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario () + "(id, idTipoUsuario, nombre) values (?, ?, ?)");
        q.setParameters(id, idTipoUsuario, nombre);
        return (long) q.executeUnique();
	}
	
	/**
	 * M�todo que retorna una lista con todo el dinero recibido por cada proveedor.
	 *
	 * @param pm - Persistence Manager
	 * @return id y dinero.
	 */
	public List<Object> dineroRecibidoProveedor(PersistenceManager pm) {
		String sql = "SELECT IDDUENIO, SUM(COSTO * DURACION * (100 - DESCUENTO) / 100)";
		sql += " FROM " + pp.darTablaEstablecimiento() + " ESTABLECIMIENTO";
		sql += " INNER JOIN "+ pp.darTablaArriendo() + " ARRIENDO ON ESTABLECIMIENTO.ID = ARRIENDO.IDESTABLECIMIENTO";
		sql += " WHERE EXTRACT(YEAR FROM FECHAINICIO) = EXTRACT(YEAR FROM CURRENT_DATE)"; 
		sql += " GROUP BY IDDUENIO";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

}
