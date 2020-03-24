package uniandes.isis2304.parranderos.negocio;

/**
 * The Class TipoUsuario.
 */
public class TipoUsuario implements VOTipoUsuario {

	/** The id. */
	private long id;
	
	/** The tipo usuario. */
	private String tipoUsuario;

	/**
	 * Instantiates a new tipo usuario.
	 */
	public TipoUsuario() {
		super();
		this.id = 0;
		this.tipoUsuario = "";
	}
	
	/**
	 * Instantiates a new tipo usuario.
	 *
	 * @param id the id
	 * @param tipoUsuario the tipo usuario
	 */
	public TipoUsuario(long id, String tipoUsuario) {
		super();
		this.id = id;
		this.tipoUsuario = tipoUsuario;
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
	 * Gets the tipo usuario.
	 *
	 * @return the tipo usuario
	 */
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	/**
	 * Sets the tipo usuario.
	 *
	 * @param tipoUsuario the new tipo usuario
	 */
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
}
