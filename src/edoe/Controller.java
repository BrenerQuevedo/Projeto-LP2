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

    /**
     * metodo que ira ler um arquivo csv para cadastrar usuarios receptores no sistema
     * @param caminho local onde esta salvo o arquivo csv
     * @throws IOException excessao caso ocorra alguma falha na leitura dos arquivos
     */

    public void lerReceptores (String caminho) throws IOException {
    	this.usuarioController.leReceptores(caminho);
    }

    /**
     * metodo para cadastrar um doador e adicionalo ao map de usuarios
     * @param nome nome do doador
     * @param email email do doador
     * @param celular telefone celular do doador
     * @param classe classe do usuario doador
     * @param idUsuario documento de identificacao do doador
     * @return retorna o id do usuario caso ele seja cadastrado
     */

    public String adicionaDoador (String idUsuario, String nome, String email, String celular, String classe) {
       return usuarioController.cadastraDoador(nome, email, celular, classe, idUsuario, "doador");
        
    }

    public void adicionarReceptor(String nome, String email, String celular, String classe, String idUsuario, String status ) {
        this.usuarioController.cadastraReceptor(nome, email, celular, classe, idUsuario, "receptor");
    }

    /**
     * Metodo que retorna a representacao de um usuario de acordo com seu id
     * @param idUsuario identificacao do usuario que se quer
     * @return retorna o toString de um usuario
     */

    public String pesquisaUsuarioPorId (String idUsuario) {
        return usuarioController.pesquisaUsuarioPorId(idUsuario);
    }

    /**
     * metodo que retorna a representacao de um usuario pelo seu nome. Se houver mais de um usuario com o mesmo nome, se retorna
     * todos com o mesmo nome de acordo com a ordem de insercao do mais antigo para o mais novo
     * @param nome nome do usuario que se quer
     * @return retorna o toString do usuario, ou dos usuarios separados por "|"
     */

    public String pesquisaUsuarioPorNome (String nome) {
        return usuarioController.pesquisaUsuarioPorNome(nome);
    }

    /**
     * metodo que atualiza os atributos de um usuario
     * @param idUsuario id do usuario que se quer atualizar
     * @param nome nnovo nome para o usuario
     * @param email novo email para o usuario
     * @param celular novo celular para o usuario
     * @return retorna o toString do usuario quando ele e editado
     */

    public String atualizaUsuario (String idUsuario, String nome, String email, String celular) {
        return usuarioController.atualizaUsuario(idUsuario, nome, email, celular);
    }

    /**
     * metodo que remove um usuario do map de usuarios de acordo com seu id
     * @param idUsuario id do usuario
     */

    public void removeUsuario (String idUsuario) {
        usuarioController.removeUsuario(idUsuario);
    }

    /**
     * Método responsável por adicionar um novo descritor de um item.
     * @param descritor
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */

    public void adicionaDescritor (String descritor) {
        itemController.adicionaDescritor(descritor);
    }

    /**
     * Método que adiciona um item para a doação no hashmap de item dentro do hashmap de itensDoacao
     * @param idDoador
     * @param descricaoItem
     * @param tags
     * @param quantidade
     * @return identificador do item
     */

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

    /**
     * Metodo que exibe as caracteristicas gerais de um item
     * @param idDoador
     * @param idItem
     * @return idItem + " - " + descricaoItem + ", " + tags + ", " + quantidade
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */

    public String exibeItem (String idItem, String idDoador) {
        return itemController.exibeItem(idDoador, idItem);
    }


    /**
     * Método responsável por atualizar a quantidade de itens a serem doados OU suas tags
     * @param idItem
     * @param idDoador
     * @param novaQuantidade
     * @param novasTags
     * @return idItem + " - " + descricaoItem + ", " + tags + ", " + quantidade
     */

    public String atualizaItemParaDoacao (String idItem, String idDoador, int novaQuantidade, String novasTags) {

        return itemController.atualizaItemParaDoacao(idItem, idDoador, novaQuantidade, novasTags);
    }

    /**
     * permite remover um item do mapa de itensDoacao (um item adicionado para doacao)
     * @param idItem
     * @param idUsuario
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */

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

    /**
     * Método que permite a listagem de todos os descritores que antes foram adicionados pelo metodo adicionaDescritor.
     * @return todos os descritores ordenados por ordem alfabetica.
     */

    public String listaDescritorDeItensParaDoacao () {
        return itemController.listaDescritorDeItensParaDoacao();
    }

    /**
     * Método responsável por listar todos os itens doados, ordenados por ordem alfabética
     * @return String com as caracteristicas gerais de todos os itens
     */

    public String listaItensParaDoacao () {
        return itemController.listaItensParaDoacao();
    }

    /**
     *  Método que permite retornar uma analise geral dos itens cadastrados,
     *  este método pesquisa em especifico os itens que contenham a descricao semelhante ao do parametro que foi passado.
     * @param descricao
     * @return (idItem + " - " + descricaoItem + ", " + tags + ", " + quantidade) de todos os itens cadastrados que tenham a descricao semelhante , sendo ordenados em ordem alfabética
     */

    public String pesquisaItemParaDoacaoPorDescricao (String descricao) {
        return itemController.pesquisaItemParaDoacaoPorDescricao(descricao);
    }

    /**
     * Método responsável por permitir adicionar um item que é necessário que seja doado.
     * @param idReceptor
     * @param descricaoItem
     * @param tags
     * @param quantidade
     * @return
     */

    public String adicionaItemNecessario (String idReceptor, String descricaoItem, int quantidade, String tags) {
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
        if (idReceptor == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idReceptor.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.usuarioController.contemUsuario(idReceptor)) {
            throw new NullPointerException("Usuario nao encontrado: " + idReceptor + ".");
        }
        return itemController.adicionaItemNecessario(idReceptor,descricaoItem,tags,quantidade, this.usuarioController.getNomeUsuario(idReceptor));
    }

    /**
     * Método que retorna todos os itens necessarios cadastrados
     * @return string de todos os itens adicionados
     */

    public String listaItensNecessarios () {
        return this.itemController.listaItensNecessarios();
    }

    /**
     * Permite atualizar os atributos de quantidade e tags de um item necessario.
     * @param idReceptor
     * @param idItem
     * @param novaQuantidade
     * @param novasTags
     * @return toString do item modificado
     */

    public String atualizaItemNecessario (String idItem, String idReceptor, int novaQuantidade, String novasTags) {
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (idReceptor == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idReceptor.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.usuarioController.contemUsuario(idReceptor)) {
            throw new NullPointerException("Usuario nao encontrado: " + idReceptor + ".");
        }
        return this.itemController.atualizaItemNecessario(idItem, idReceptor, novaQuantidade, novasTags);
    }

    /**
     * permite remover um item do mapa de itensNecessarios (um item adicionado para ser recebido)
     * @param idReceptor
     * @param idItem
     */
    public void removeItemNecessario (String idReceptor, String idItem) {
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (idReceptor == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idReceptor.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.usuarioController.contemUsuario(idReceptor)) {
            throw new NullPointerException("Usuario nao encontrado: " + idReceptor + ".");
        }
        this.itemController.removeItemNecessario(idReceptor,idItem);
    }
}