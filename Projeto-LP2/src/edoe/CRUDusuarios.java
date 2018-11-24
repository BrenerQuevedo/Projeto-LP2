package EDOE;


import java.util.ArrayList;
import java.util.HashMap;

public class CRUDusuarios {
	private HashMap<String, Doador> Doadores;
	private HashMap<String, Receptor> Receptores;
	private ArrayList<String> classes;
	
	public CRUDusuarios() {
		this.Doadores = new HashMap<>();
		this.Receptores = new HashMap<>();
		this.classes.add("PESSOA_FISICA");this.classes.add("IGREJA");this.classes.add("ORGAO_PUBLICO_MUNICIPAL");this.classes.add("ORGAO_PUBLICO_ESTADUAL");this.classes.add("ORGAO_PUBLICO_FEDERAL");
		this.classes.add("ONG");this.classes.add("ASSOCIACAO");this.classes.add("SOCIEDADE");
	}
	
	public String cadastraDoador(String nome, String email, String celular, String classe, String identificacao) {
		String[] entradas = {nome, email, celular,  classe, identificacao};
		String[][] dados = {{"nome","vazio ou nulo"}, {"email","vazio ou nulo"}, {"celular", "vazio ou nulo"}, { "classe", "vazia ou nula"}, {"id do usuario", "vazio ou nulo"}};
		this.validaEntrada(entradas, dados, "Entrada invalida: ");
		
		if(this.Doadores.containsKey(identificacao) || this.Receptores.containsKey(identificacao)) {
			throw new IllegalArgumentException("Usuario ja existente: " + identificacao + "." );
		}else {				
			if(!this.classes.contains(classe)) {
				throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
			}else {
				if(classe.equals("PESSOA_FISICA")) {
					if(identificacao.length() != 11) {							
						throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
					}else {
						Doador usuario = new Doador(nome, email, celular, classe, this.formataId(identificacao));
						this.Doadores.put(identificacao, usuario);
						return identificacao;
					}
				}else {
					if(identificacao.length() != 14 ) {
						throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
					}else {
						Doador usuario = new Doador(nome, email, celular, classe, this.formataId(identificacao));
						this.Doadores.put(identificacao, usuario);
						return identificacao;
					}
				}
			}
		}	
	}

	public String pesquisaUsuarioPorId(String id) {
		if(id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		}else {
			if(!this.Doadores.containsKey(id)) {
				throw new IllegalArgumentException("Usuario nao encontrado: " + id + "." );
			}else {
				return this.Doadores.get(id).toString();
			}
		}
	}

	public String pesquisaUsuarioPorNome(String nome) {
		ArrayList<String> usuarios = new ArrayList<>();
		for(Doador doador : this.Doadores.values()) {
			if(doador.getNome().equals(nome.toLowerCase())) {
				usuarios.add(doador.toString());
			}
		}for(Receptor receptor : this.Receptores.values()) {
			if(receptor.getNome().equals(nome.toLowerCase())) {
				usuarios.add(receptor.toString());
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
	
	private String formataId(String id) {
		if(id.length() == 11) {
			return id.substring(0, 2) + "." + id.substring(3, 5) + "." + id.substring(6, 8) + "-" + id.substring(9, 10);  
		}else {
			return id.substring(0, 1) + "." + id.substring(2,4) + "." + id.substring(5, 7) + "/" + id.substring(8, 11) + "-" + id.substring(12, 13);
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
}

