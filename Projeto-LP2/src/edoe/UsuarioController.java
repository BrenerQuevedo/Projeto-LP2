package edoe;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe responsavel por todo gerenciamento de um usuario, pelo padrao CRUD
 * @author paulomam
 *
 */
public class UsuarioController{
	/**
	 * mapa de usuarios onde a key sera a identificacao do usuario o valor sera um usuario 
	 */
	private Map<String, Usuario> Usuarios;
	/**
	 * lista de Strings com as classes disponiveis de usuario para se fazer um cadastro
	 */
	private ArrayList<String> classes;
	
	/**
	 * Construtor do controller e iniciacao dos atributos.
	 * obs: o map sera um linkedHashMap pois importa a ordem de insercao dos usuarios
	 */
	public UsuarioController() {
		this.Usuarios = new LinkedHashMap<>();
		this.classes = new ArrayList<>();
		this.classes.add("PESSOA_FISICA");this.classes.add("IGREJA");this.classes.add("ORGAO_PUBLICO_MUNICIPAL");this.classes.add("ORGAO_PUBLICO_ESTADUAL");this.classes.add("ORGAO_PUBLICO_FEDERAL");
		this.classes.add("ONG");this.classes.add("ASSOCIACAO");this.classes.add("SOCIEDADE");
	}
	/**
	 * metodo para cadastrar um doador e adicionalo ao map de usuarios
	 * @param nome nome do doador
	 * @param email email do doador
	 * @param celular telefone celular do doador
	 * @param classe classe do usuario doador
	 * @param idUsuario documento de identificacao do doador
	 * @param status status do usuario que deve ser doador
	 * @return retorna o id do usuario caso ele seja cadastrado
	 */
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
						Usuario usuario = new Usuario(nome, email, celular, classe, idUsuario, "doador");
						this.Usuarios.put(idUsuario, usuario);
						return idUsuario;
					}
				}else {
					if(idUsuario.length() != 14 ) {
						throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
					}else {
						Usuario usuario = new Usuario(nome, email, celular, classe, idUsuario, "doador");
						this.Usuarios.put(idUsuario, usuario);
						return idUsuario;
					}
				}
			}
		}	
	}
	
	/**
	 * metodo para cadastrar um usuario receptor no map de usuarios
	 * @param nome nome do receptor
	 * @param email email do receptor
	 * @param celular telefone celular do receptor
	 * @param classe classe do usuario receptor
	 * @param idUsuario documento de identificao do usuario
	 * @param status status do usuario que deve ser receptor
	 */
	public void cadastraReceptor(String nome, String email, String celular, String classe, String idUsuario, String status) {
		String[] entradas = {nome, email, celular,  classe, idUsuario, status};
		String[][] dados = {{"nome","vazio ou nulo"}, {"email","vazio ou nulo"}, {"celular", "vazio ou nulo"}, { "classe", "vazia ou nula"}, {"id do usuario", "vazio ou nulo"}, {"status vazio ou nulo"}};
		this.validaEntrada(entradas, dados, "Entrada invalida: ");
		
		if(this.Usuarios.containsKey(idUsuario)) {
			this.Usuarios.get(idUsuario).setCelular(celular);this.Usuarios.get(idUsuario).setEmail(email);this.Usuarios.get(idUsuario).setNome(nome);
		}else {				
			if(!this.classes.contains(classe)) {
				throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
			}else {
				if(classe.equals("PESSOA_FISICA")) {
					if(idUsuario.length() != 11) {
						throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
					}else {
						Usuario usuario = new Usuario(nome, email, celular, classe, idUsuario, "receptor");
						this.Usuarios.put(idUsuario, usuario);
						
					}
				}else {
					if(idUsuario.length() != 14 ) {
						throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
					}else {
						Usuario usuario = new Usuario(nome, email, celular, classe, idUsuario, "receptor");
						this.Usuarios.put(idUsuario, usuario);
						
					}
				}
			}
		}
	}
	/**
	 * Metodo que retorna a representacao de um usuario de acordo com seu id
	 * @param idUsuario identificacao do usuario que se quer 
	 * @return retorna o toString de um usuario
	 */
	public String pesquisaUsuarioPorId(String idUsuario) {
		if(idUsuario == null) {
			throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		else if(idUsuario.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}else {
			if(!this.Usuarios.containsKey(idUsuario)) {
				throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + "." );
			}else {
				return this.Usuarios.get(idUsuario).toString();
			}
		}
	}
	/**
	 * metodo que retorna a representacao de um usuario pelo seu nome. Se houver mais de um usuario com o mesmo nome, se retorna
	 * todos com o mesmo nome de acordo com a ordem de insercao do mais antigo para o mais novo
	 * @param nome nome do usuario que se quer
	 * @return retorna o toString do usuario, ou dos usuarios separados por "|"
	 */
    public String pesquisaUsuarioPorNome(String nome) {
        if(nome == null) {
            throw new NullPointerException("Entrada invalida: nome nao pode ser vazio ou nulo.");
        }else if(nome.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
        }else {
            ArrayList<String> usuarios = new ArrayList<>();
            for(Usuario doador : this.Usuarios.values()) {
                if(doador.getNome().equals(nome)) {
                    usuarios.add(doador.toString());
                }
            }if(usuarios.size() == 0 ) {
                throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
            }else {
                String msg = "";
                for(int i = 0; i < usuarios.size() ; i++) {
                    if(i == usuarios.size() - 1) {
                        msg += usuarios.get(i);
                    }else {
                        msg += usuarios.get(i) + " | ";
                    }
                }return msg;
            }
        }
    }
    /**
     * metodo que diz se um usuario e ou nao um receptor
     * @param idUsuario id do usuario
     * @return retorna um valo booleano. true para "e um receptor", false para "nao e um receptor"
     */
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
	/**
	 * metod que diz se um usuario e ou nao um doador
	 * @param idUsuario id do usuario
	 * @return retorna um booleano. true para "e um doador", false para "nao e uma doador"
	 */
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
	/**
	 * metodo booleano que diz se contem um usuario no hashmap de usuarios
	 * @param idUsuario id do usuario
	 * @return retorna um booleano. true para "contem usuario", false para "nao contem usuario"
	 */
	public boolean contemUsuario(String idUsuario) {
		return this.Usuarios.containsKey(idUsuario);
	}
	/**
	 * metodo para validar as entradas de metodos que tenham muitas entradas
	 * @param entradas Array de string com as entradas
	 * @param dados Array de string com o nome de cada entrada
	 * @param mensagemEmComum mensagem em comum que os lancamentos de excecoes tem em comum
	 */
	private void validaEntrada(String[] entradas,String[][] dados, String mensagemEmComum) {
		for(int i = 0 ; i < entradas.length; i++ ) {
			if(entradas[i] == null){
				throw new NullPointerException(mensagemEmComum +   dados[i][0] + " nao pode ser " + dados[i][1] + ".");
			}else if(entradas[i].trim().equals("")) {
				throw new IllegalArgumentException(mensagemEmComum +  dados[i][0] + " nao pode ser " + dados[i][1] + ".");
			}
		}
	}
	/**
	 * metodo que atualiza os atributos de um usuario
	 * @param id id do usuario que se quer atualizar
	 * @param nome nnovo nome para o usuario
	 * @param email novo email para o usuario
	 * @param celular novo celular para o usuario
	 * @return retorna o toString do usuario quando ele e editado
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		if(id == null) {
			throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}
		if (id.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		}else {
			if(this.Usuarios.containsKey(id)) {
				if(nome != null && !nome.trim().equals("")) {
					this.Usuarios.get(id).setNome(nome);
				} 
				if(email != null && !email.equals("")) {
					this.Usuarios.get(id).setEmail(email);
				}
				if(celular != null && !celular.equals("")){
					this.Usuarios.get(id).setCelular(celular);
				}

				return this.Usuarios.get(id).toString();
			}else {
				throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
			}		
		}
	}
	/**
	 * metodo que remove um usuario do map de usuarios de acordo com seu id
	 * @param id id do usuario
	 */
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
	/**
	 * metodo que pega o nome do usuario de acordo com sua id
	 * @param idUsuario id do usuario 
	 * @return
	 */
	public String getNomeUsuario (String idUsuario) {
	    return this.Usuarios.get(idUsuario).getNome();
    }
	
	/**
	 * metodo que ira ler um arquivo csv para cadastrar usuarios receptores no sistema
	 * @param localDoArquivo local onde esta salvo o arquivo csv
	 * @throws IOException excessao caso ocorra alguma falha na leitura dos arquivos
	 */
	public void leReceptores(String localDoArquivo) throws IOException {
		File file = new File(localDoArquivo);
		Scanner sc = new Scanner(file);
		String linha = null;
		while(sc.hasNextLine()) {
			linha = sc.nextLine();
			if(linha.equals("id,nome,e-mail,celular,classe")) {
				continue;
			}String[] entradasReceptor = linha.split(",");
			if(entradasReceptor.length != 5) {
				throw new IOException();
			}this.cadastraReceptor(entradasReceptor[1], entradasReceptor[2], entradasReceptor[3], entradasReceptor[4], entradasReceptor[0], "receptor");
		}	
		sc.close();
	}

	
}