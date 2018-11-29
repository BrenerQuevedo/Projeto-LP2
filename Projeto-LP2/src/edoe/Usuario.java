package edoe;

/**
 * Representacao de um usuario no sistema. Usuario pode ser doador ou receptor.
 * @author paulomam
 *
 */
public  class Usuario {
	/**
	 * nome do usuario
	 */
	private String nome;
	/**
	 * email do usuario
	 */
	private String email;
	/**
	 * telefone celular do usuario
	 */
	private String celular;
	/**
	 * classe do usuario que esta sendo cadastrado
	 */
	private String classe;
	/**
	 * documento de identificacao do usuario que pode ser um cpf ou cnpj
	 */
	private String idUsuario;
	/**
	 * status do usuario que diz se ele é um doador ou um receptor
	 */
	private String status;
	
	/**
	 * construtor de um usuario
	 * @param nome nome do usuario
	 * @param email email do usuario
	 * @param celular celular do usuario
	 * @param classe classe do usuario
	 * @param idUsuario documento de identificacao do usuario
	 * @param status status do usuario
	 */
	public Usuario(String nome, String email, String celular, String classe, String idUsuario, String status) {
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.idUsuario = idUsuario;
		this.status = status;
	}
	public void setNome(String novoNome) {
		this.nome = novoNome;
	}

	public void setCelular(String novoCelular) { 
		this.celular = novoCelular;
	}

	public void setEmail(String novoEmail) {
		this.email = novoEmail;
	}

	public String getNome() {
		return this.nome.toLowerCase();
	}

	public String getStatus() {
		return this.status;
	}

	public String getClasse() {
		return this.classe;
	}


	/**
	 * toString de um usuario que retorna a representaçao do usuario no sistema
	 * @return retorna uma String no seguinte formato NomeDoUsuario/IdDoUsuario, EmailDoUsuario, CelularDoUsuario, status: StatusDoUsuario
	 */
	@Override
	public String toString() {
		return this.nome + "/" + this.idUsuario + ", " + this.email + ", " + this.celular + ", status: " + this.status;
	}
}