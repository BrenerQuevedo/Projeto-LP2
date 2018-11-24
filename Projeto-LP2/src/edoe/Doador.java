package EDOE;

public  class Doador {
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String identificacao;
	
	public Doador(String nome, String email, String celular, String classe, String identificacao) {
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.identificacao = identificacao;
	}
	public void doar() {
		
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
	@Override
	public String toString() {
		return this.identificacao + " " + this.nome + "," + this.email + "/" + this.celular + "/" + this.classe + ", Status: doador."; 
	}
}
