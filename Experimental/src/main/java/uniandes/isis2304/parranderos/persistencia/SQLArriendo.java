package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLArriendo {

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
	public SQLArriendo (PersistenciaAlohAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * M�todo para adicionar el arriendo.
	 * 
	 * @param pm - Persistence Manager.
	 * @param id - id.
	 * @param idUsuario - id del usuario.
	 * @param idEstablecimiento - id del establecimiento.
	 * @param descuento - descuento.
	 * @param fechaInicio - fecha inicio.
	 * @param duracion - duraci�n.
	 * @param estado - estado.
	 * @return
	 */
	public long adicionarArriendo(PersistenceManager pm, long id, long idUsuario, long idEstablecimiento, int descuento, Timestamp fechaInicio, int duracion, String estado) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaArriendo () + " (id, idUsuario, idEstablecimiento, descuento, fechaInicio, duracion, estado) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, idUsuario, idEstablecimiento, descuento, fechaInicio, duracion, estado);
        return (long) q.executeUnique();
    }
	
	/**
	 * M�todo para eliminar una reserva en DB.
	 * 
	 * @param pm - Persistence Manager.
	 * @param idArriendo - Id de la reserva a cancelar.
	 * @return El n�mero de tuplas modificadas
	 */
	public long cancelarArriendo(PersistenceManager pm, long idArriendo) {
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaArriendo () + " SET estado = ? WHERE id = ?");
        q.setParameters("Cancelado", idArriendo);
        return (long) q.executeUnique();
	}
	
	/**
	 * M�todo que retorna los id de las 20 ofertas m�s populares.
	 * 
	 * @param pm - Persistence Manager.
	 * @return lista con los ids.
	 */
	public List<Object> veinteOfertasMasPopulares(PersistenceManager pm) {
		String sql1 = "SELECT idEstablecimiento";
		sql1 += " FROM " + pp.darTablaArriendo ();
		sql1 += " WHERE estado = 'Activo'"
				+ " GROUP BY idEstablecimiento"
				+ " ORDER BY count(*) DESC";
		
        String sql = "SELECT *";
        sql += " FROM (" + sql1 + ")";
        sql += " WHERE rownum <= 20";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
	
}
