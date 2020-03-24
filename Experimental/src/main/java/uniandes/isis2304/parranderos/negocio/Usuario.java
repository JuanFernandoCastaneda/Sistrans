package uniandes.isis2304.parranderos.negocio;

/**
 * The Class Usuario.
 */
public class Usuario implements VOUsuario {
	
	/** The id. */
	private long id;
	
	/** The id tipo usuario. */
	private long idTipoUsuario;
	
	/** The nombre. */
	private String nombre;

	/**
	 * Instantiates a new usuario.
	 */
	public Usuario() {
		super();
		this.id = 0;
		this.idTipoUsuario = 0;
		this.nombre = "";
	}

	/**
	 * Instantiates a new usuario.
	 *
	 * @param id the id
	 * @param idTipoUsuario the id tipo usuario
	 * @param nombre the nombre
	 */
	public Usuario(long id, long idTipoUsuario, String nombre) {
		super();
		this.id = id;
		this.idTipoUsuario = idTipoUsuario;
		this.nombre = nombre;
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
	 * Gets the id tipo usuario.
	 *
	 * @return the id tipo usuario
	 */
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	/**
	 * Sets the id tipo usuario.
	 *
	 * @param idTipoUsuario the new id tipo usuario
	 */
	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
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
	
}
