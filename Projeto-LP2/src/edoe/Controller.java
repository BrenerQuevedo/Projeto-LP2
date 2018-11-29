package edoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private UsuarioController usuarioController;
    private ItemController itemController;
    private List<Usuario> usuarios;

    public Controller () {
        this.usuarioController = new UsuarioController();
        this.itemController = new ItemController();
        this.usuarios = new ArrayList<>();
    }

    public void lerReceptores (String caminho) throws IOException {
    	this.usuarioController.leReceptores(caminho);
    }

    public String adicionaDoador (String idUsuario, String nome, String email, String celular, String classe) {
       return usuarioController.cadastraDoador(nome, email, celular, classe, idUsuario, "doador");
        
    }
    
    public String pesquisaUsuarioPorId (String idUsuario) {
        return usuarioController.pesquisaUsuarioPorId(idUsuario);
    }

    public String pesquisaUsuarioPorNome (String nome) {
        return usuarioController.pesquisaUsuarioPorNome(nome);
    }

    public String atualizaUsuario (String idUsuario, String nome, String email, String celular) {
        return usuarioController.atualizaUsuario(idUsuario, nome, email, celular);
    }

    public void removeUsuario (String idUsuario) {
        usuarioController.removeUsuario(idUsuario);
    }

    public void adicionaDescritor (String descritor) {
        itemController.adicionaDescritor(descritor);
    }

    public String adicionaItemParaDoacao (String idDoador, String descricaoItem, int quantidade, String tags) {
        if (descricaoItem == null) {
            throw new NullPointerException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        descricaoItem = descricaoItem.trim().toLowerCase();

        if (descricaoItem.equals("")) {
            throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
        }
        if (idDoador == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idDoador.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.usuarioController.contemUsuario(idDoador)) {
            throw new NullPointerException("Usuario nao encontrado: " + idDoador + ".");
        }
        return itemController.adicionaItemParaDoacao(idDoador, descricaoItem, tags, quantidade, usuarioController.getNomeUsuario(idDoador));
    }

    public String exibeItem (String idItem, String idDoador) {
        return itemController.exibeItem(idDoador, idItem);
    }

    public String atualizaItemParaDoacao (String idItem, String idDoador, int novaQuantidade, String novasTags) {

        return itemController.atualizaItemParaDoacao(idItem, idDoador, novaQuantidade, novasTags);
    }

    public void removeItemParaDoacao (String idItem, String idUsuario) {
        if (idUsuario == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idUsuario.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.usuarioController.contemUsuario(idUsuario)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
        }
        this.itemController.removeItemParaDoacao(idItem, idUsuario);
    }

    public String listaDescritorDeItensParaDoacao () {
        return itemController.listaDescritorDeItensParaDoacao();
    }

    public String listaItensParaDoacao () {
        return itemController.listaItensParaDoacao();
    }

    public String pesquisaItemParaDoacaoPorDescricao (String descricao) {
        return itemController.pesquisaItemParaDoacaoPorDescricao(descricao);
    }

    public String adicionaItemNecessario (String idReceptor, String descricaoItem, String quantidade, String tags) {
        //TODO
        return "";
    }
}