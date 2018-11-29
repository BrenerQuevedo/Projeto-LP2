package edoe;

public  class Usuario {
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String idUsuario;
	private String status;
	
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


	@Override
	public String toString() {
		return this.nome + "/" + this.idUsuario + ", " + this.email + ", " + this.celular + ", status: " + this.status;
	}
}