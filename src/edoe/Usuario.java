package edoe;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representacao de um usuario no sistema. Usuario pode ser doador ou receptor.
 * @author Paulo Mateus
 *
 */
public  class Usuario implements Serializable {
	/**
	 * Nome do usuario.
	 */
	private String nome;
	/**
	 * Email do usuario.
	 */
	private String email;
	/**
	 * Telefone celular do usuario.
	 */
	private String celular;
	/**
	 * Classe do usuario que esta sendo cadastrado.
	 */
	private String classe;
	/**
	 * Documento de identificacao do usuario que pode ser um CPF ou CNPJ.
	 */
	private String idUsuario;
	/**
	 * Status do usuario que diz se ele é um doador ou um receptor.
	 */
	private String status;
	
	/**
	 * Construtor de um usuario.
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
		return this.nome;
	}

	public String getStatus() {
		return this.status;
	}

	/**
	 * Retorna a representaçao do usuario no sistema.
	 * @return String no seguinte formato: "NomeDoUsuario/IdDoUsuario, EmailDoUsuario, CelularDoUsuario, status: StatusDoUsuario"
	 */
	@Override
	public String toString() {
		return this.nome + "/" + this.idUsuario + ", " + this.email + ", " + this.celular + ", status: " + this.status;
	}

	/**
	 * Compara um objeto com este usuario e diz se eles sao iguais.
	 * @param o Objeto a ser comparado.
	 * @return Retorna true caso o objeto seja igual a este usuario, e false se nao.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(getNome(), usuario.getNome()) &&
				Objects.equals(email, usuario.email) &&
				Objects.equals(celular, usuario.celular) &&
				Objects.equals(classe, usuario.classe) &&
				Objects.equals(idUsuario, usuario.idUsuario) &&
				Objects.equals(getStatus(), usuario.getStatus());
	}

    /**
     * Gera uma representacao em inteiro para este objeto.
     * @return Retorna a representacao em inteiro deste objeto.
     */
	@Override
	public int hashCode() {
		return Objects.hash(getNome(), email, celular, classe, idUsuario, getStatus());
	}
}