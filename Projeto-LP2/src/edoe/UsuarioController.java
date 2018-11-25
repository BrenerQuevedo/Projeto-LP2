package edoe;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UsuarioController {
	private HashMap<String, Usuario> Usuarios;
	private ArrayList<String> classes;
	
	public UsuarioController() {
		this.Usuarios = new HashMap<>();
		this.classes.add("PESSOA_FISICA");this.classes.add("IGREJA");this.classes.add("ORGAO_PUBLICO_MUNICIPAL");this.classes.add("ORGAO_PUBLICO_ESTADUAL");this.classes.add("ORGAO_PUBLICO_FEDERAL");
		this.classes.add("ONG");this.classes.add("ASSOCIACAO");this.classes.add("SOCIEDADE");
	}
	
	public String cadastraDoador(String nome, String email, String celular, String classe, String idUsuario, String status) {
		String[] entradas = {nome, email, celular,  classe, idUsuario, status};
		String[][] dados = {{"nome","vazio ou nulo"}, {"email","vazio ou nulo"}, {"celular", "vazio ou nulo"}, { "classe", "vazia ou nula"}, {"id do usuario", "vazio ou nulo"}, {"status vazio ou nulo"}};
		this.validaEntrada(entradas, dados, "Entrada invalida: ");
		
		if(this.Usuarios.containsKey(idUsuario)) {
			throw new IllegalArgumentException("Usuario ja existente: " + idUsuario + "." );
		}else {				
			if(!this.classes.contains(classe)) {
				throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
			}else {
				if(classe.equals("PESSOA_FISICA")) {
					if(idUsuario.length() != 11) {
						throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
					}else {
						Usuario usuario = new Usuario(nome, email, celular, classe, this.formataId(idUsuario), "doador");
						this.Usuarios.put(idUsuario, usuario);
						return idUsuario;
					}
				}else {
					if(idUsuario.length() != 14 ) {
						throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
					}else {
						Usuario usuario = new Usuario(nome, email, celular, classe, this.formataId(idUsuario), "doador");
						this.Usuarios.put(idUsuario, usuario);
						return idUsuario;
					}
				}
			}
		}	
	}
	
	public void cadastraReceptor(String nome, String email, String celular, String classe, String idUsuario, String status) {
		String[] entradas = {nome, email, celular,  classe, idUsuario, status};
		String[][] dados = {{"nome","vazio ou nulo"}, {"email","vazio ou nulo"}, {"celular", "vazio ou nulo"}, { "classe", "vazia ou nula"}, {"id do usuario", "vazio ou nulo"}, {"status vazio ou nulo"}};
		this.validaEntrada(entradas, dados, "Entrada invalida: ");
		
		if(this.Usuarios.containsKey(idUsuario)) {
			throw new IllegalArgumentException("Usuario ja existente: " + idUsuario + "." );
		}else {				
			if(!this.classes.contains(classe)) {
				throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
			}else {
				if(classe.equals("PESSOA_FISICA")) {
					if(idUsuario.length() != 11) {
						throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
					}else {
						Usuario usuario = new Usuario(nome, email, celular, classe, this.formataId(idUsuario), "receptor");
						this.Usuarios.put(idUsuario, usuario);
						
					}
				}else {
					if(idUsuario.length() != 14 ) {
						throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
					}else {
						Usuario usuario = new Usuario(nome, email, celular, classe, this.formataId(idUsuario), "receptor");
						this.Usuarios.put(idUsuario, usuario);
						
					}
				}
			}
		}
	}

	public String pesquisaUsuarioPorId(String idUsuario) {
		if(idUsuario.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}else {
			if(!this.Usuarios.containsKey(idUsuario)) {
				throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + "." );
			}else {
				return this.Usuarios.get(idUsuario).toString();
			}
		}
	}

	public String pesquisaUsuarioPorNome(String nome) {
		ArrayList<String> usuarios = new ArrayList<>();
		for(Usuario doador : this.Usuarios.values()) {
			if(doador.getNome().equals(nome.toLowerCase())) {
				usuarios.add(doador.toString());
			}
		}if(usuarios.size() == 0 ) {
			throw new IllegalArgumentException();
		}else {
			String msg = "";
			for(int i = 0; i < usuarios.size(); i++) {
				if(i == usuarios.size() - 1) {
					msg += usuarios.get(i);
				}else {
					msg += usuarios.get(i) + " | ";
				}
			}return msg;
		}
	}

	public boolean validaReceptor(String idUsuario) {
		if(this.Usuarios.containsKey(idUsuario)) {
			if(this.Usuarios.get(idUsuario).getStatus().equals("receptor")) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new IllegalArgumentException();
		}
		
	}

	public boolean validaDoador(String idUsuario) {
		if(this.Usuarios.containsKey(idUsuario)) {
			if(this.Usuarios.get(idUsuario).getStatus().equals("doador")) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new IllegalArgumentException();
		}	
	}

	public boolean contemUsuario(String idUsuario) {
		return this.Usuarios.containsKey(idUsuario);
	}

	private String formataId(String idUsuario) {
		if(idUsuario.length() == 11) {
			return idUsuario.substring(0, 2) + "." + idUsuario.substring(3, 5) + "." + idUsuario.substring(6, 8) + "-" + idUsuario.substring(9, 10);
		}else {
			return idUsuario.substring(0, 1) + "." + idUsuario.substring(2,4) + "." + idUsuario.substring(5, 7) + "/" + idUsuario.substring(8, 11) + "-" + idUsuario.substring(12, 13);
		}
	}

	private void validaEntrada(String[] entradas,String[][] dados, String mensagemEmComum) {
		for(int i = 0 ; i < entradas.length; i++ ) {
			if(entradas[i].trim().equals("")) {
				throw new IllegalArgumentException(mensagemEmComum + ": " + dados[i][0] + " nao pode ser" + dados[i][1] );
			}else if(entradas[i] == null){
				throw new NullPointerException(mensagemEmComum + ": " +  dados[i][0] + " nao pode ser" + dados[i][1]);
			}
		}
	}

	public String atualizaUsuario(String id, String nome, String email, String celular) {
		if(id == null) {
			throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}else if (id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}else {
			if(this.Usuarios.containsKey(id)) {
				if(nome != null || !nome.trim().equals("")) {
					this.Usuarios.get(id).setNome(nome);
					return this.Usuarios.get(id).toString();
				}else if(email != null || !email.equals("")) {
					this.Usuarios.get(id).setEmail(email);
					return this.Usuarios.get(id).toString();
				}else {
					this.Usuarios.get(id).setCelular(celular);
					return this.Usuarios.get(id).toString();
				}
			}else {
				throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
			}		
		}
	}

	public void removeUsuario(String id ) {
		if(id == null) {
			throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}else if(id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}else {
			if(this.Usuarios.containsKey(id)) {
				this.Usuarios.remove(id);
			}else {
				throw new IllegalArgumentException("Usuario nao encontrado: "+ id + ".");
			}
		}
	}

	public void leReceptores(String localDoArquivo) throws IOException {
		Scanner sc = new Scanner(new File(localDoArquivo));
		String linha = null;
		while(sc.hasNextLine()) {
			linha = sc.nextLine();
			if(linha.equals("id,nome,e-mail,celular,classe")) {
				continue;
			}String[] entradasReceptor = linha.split(",");
			if(entradasReceptor.length != 5) {
				throw new IOException();
			}this.cadastraDoador(entradasReceptor[1], entradasReceptor[2], entradasReceptor[3], entradasReceptor[4], entradasReceptor[0], "receptor");
		}	
		sc.close();
	}
}