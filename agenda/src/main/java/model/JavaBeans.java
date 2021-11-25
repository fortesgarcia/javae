package model;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class JavaBeans {
	
	/** The id contato. */
	private String idContato;
	
	/** The nome contato. */
	private String nomeContato;
	
	/** The fone contato. */
	private String foneContato;
	
	/** The email contato. */
	private String emailContato;

	/**
	 * Instantiates a new java beans.
	 */
	public JavaBeans() {
		super();
	}

	/**
	 * Instantiates a new java beans.
	 *
	 * @param idContato the id contato
	 * @param nomeContato the nome contato
	 * @param foneContato the fone contato
	 * @param emailContato the email contato
	 */
	public JavaBeans(String idContato, String nomeContato, String foneContato, String emailContato) {
		super();
		this.idContato = idContato;
		this.nomeContato = nomeContato;
		this.foneContato = foneContato;
		this.emailContato = emailContato;
	}

	/**
	 * Gets the id contato.
	 *
	 * @return the id contato
	 */
	public String getIdContato() {
		return idContato;
	}

	/**
	 * Sets the id contato.
	 *
	 * @param idContato the new id contato
	 */
	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	/**
	 * Gets the nome contato.
	 *
	 * @return the nome contato
	 */
	public String getNomeContato() {
		return nomeContato;
	}

	/**
	 * Sets the nome contato.
	 *
	 * @param nomeContato the new nome contato
	 */
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	/**
	 * Gets the fone contato.
	 *
	 * @return the fone contato
	 */
	public String getFoneContato() {
		return foneContato;
	}

	/**
	 * Sets the fone contato.
	 *
	 * @param foneContato the new fone contato
	 */
	public void setFoneContato(String foneContato) {
		this.foneContato = foneContato;
	}

	/**
	 * Gets the email contato.
	 *
	 * @return the email contato
	 */
	public String getEmailContato() {
		return emailContato;
	}

	/**
	 * Sets the email contato.
	 *
	 * @param emailContato the new email contato
	 */
	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}
}
