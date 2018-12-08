package edoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de controle geral do sistema que administrara os usuarios e seus itens.
 * @author Joicy Santos
 */
public class Controller {
    /**
     * Objeto controlador de usuarios.
     */
    private UsuarioController usuarioController;
    /**
     * Objeto controlador de itens.
     */
    private ItemController itemController;

    /**
     * Constroi o objeto controlador do sistema.
     */
    public Controller () {
        this.usuarioController = new UsuarioController();
        this.itemController = new ItemController();
    }

    /**
     * Le um arquivo csv para cadastrar usuarios receptores no sistema.
     * @param caminho Local onde esta salvo o arquivo csv.
     * @throws IOException Excessao caso ocorra alguma falha na leitura dos arquivos.
     */
    public void lerReceptores (String caminho) throws IOException {
    	this.usuarioController.leReceptores(caminho);
    }

    /**
     * Cadastra um doador no sistema.
     * @param nome Nome do doador.
     * @param email Email do doador.
     * @param celular Telefone celular do doador.
     * @param classe Classe do doador.
     * @param idUsuario Documento de identificacao do doador.
     * @return Retorna o id do usuario caso ele seja cadastrado.
     */
    public String adicionaDoador (String idUsuario, String nome, String email, String celular, String classe) {
       return usuarioController.cadastraDoador(nome, email, celular, classe, idUsuario, "doador");
        
    }


    /**
     * Retorna a representacao de um usuario de acordo com seu id.
     * @param idUsuario Identificacao do usuario que se quer.
     * @return Retorna o toString do usuario.
     */
    public String pesquisaUsuarioPorId (String idUsuario) {
        return usuarioController.pesquisaUsuarioPorId(idUsuario);
    }

    /**
     * Retorna a representacao de um usuario pelo seu nome. Se houver mais de um usuario com o mesmo nome, retorna-se todos com o mesmo nome de acordo com a ordem de insercao do mais antigo para o mais novo.
     * @param nome Nome do usuario que se quer.
     * @return Retorna o toString do usuario, ou dos usuarios separados por "|".
     */
    public String pesquisaUsuarioPorNome (String nome) {
        return usuarioController.pesquisaUsuarioPorNome(nome);
    }

    /**
     * Atualiza os atributos de um usuario.
     * @param idUsuario Id do usuario que se quer atualizar.
     * @param nome Novo nome para o usuario.
     * @param email Novo email para o usuario.
     * @param celular Novo celular para o usuario.
     * @return Retorna o toString do usuario quando ele e editado.
     */
    public String atualizaUsuario (String idUsuario, String nome, String email, String celular) {
        return usuarioController.atualizaUsuario(idUsuario, nome, email, celular);
    }

    /**
     * Remove um usuario do sistema de acordo com seu id.
     * @param idUsuario Id do usuario.
     */
    public void removeUsuario (String idUsuario) {
        usuarioController.removeUsuario(idUsuario);
    }

    /**
     * Adiciona um novo descritor de um item.
     * @param descritor Novo descritor de itens a ser adicionado no sistema.
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public void adicionaDescritor (String descritor) {
        itemController.adicionaDescritor(descritor);
    }

    /**
     * Adiciona um item para a doação no sistema.
     * @param idDoador Id do doador a ter um item adicionado.
     * @param descricaoItem Descricao do item a ser adicionado.
     * @param tags Tags do item a ser adicionado.
     * @param quantidade Quantidade do item a ser adicionado.
     * @return Identificador do item.
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
     * Exibe as caracteristicas gerais de um item.
     * @param idDoador Id do doador que possui o item a ser exibido.
     * @param idItem Id do item a ser exibido.
     * @return Retorna o toString() do item.
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String exibeItem (String idItem, String idDoador) {
        return itemController.exibeItem(idDoador, idItem);
    }

    /**
     * Atualiza a quantidade de itens a serem doados OU suas tags.
     * @param idItem Id do item a ser atualizado.
     * @param idDoador Id do doador a ter um item atualizado.
     * @param novaQuantidade Nova quantidade do item. Caso a nova quantidade seja 0, ela nao sera alterada.
     * @param novasTags Novas tags do item. Caso as novas tags sejam vazias, elas nao seram atualizadas.
     * @return Retorna o toString() do item, depois de editado.
     */
    public String atualizaItemParaDoacao (String idItem, String idDoador, int novaQuantidade, String novasTags) {
        return itemController.atualizaItemParaDoacao(idItem, idDoador, novaQuantidade, novasTags);
    }

    /**
     * Remove um item para doacao do Sistema.
     * @param idItem Id do item a ser excluido.
     * @param idUsuario Id do usuario a ter um item excluido.
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
     * Lista todos os descritores que antes foram adicionados pelo metodo adicionaDescritor.
     * @return Todos os descritores ordenados por ordem alfabetica.
     */
    public String listaDescritorDeItensParaDoacao () {
        return itemController.listaDescritorDeItensParaDoacao();
    }

    /**
     * Lista todos os itens para doacao, ordenados por ordem alfabética.
     * @return String com as caracteristicas gerais de todos os itens para doacao.
     */
    public String listaItensParaDoacao () {
        return itemController.listaItensParaDoacao();
    }

    /**
     *  Retorna uma analise geral dos itens para doacao cadastrados. Este método pesquisa em especifico os itens que contenham a descricao semelhante ao do parametro que foi passado.
     * @param descricao Descricao a ser pesquisada em todos os itens para doacao.
     * @return Retorna o toString() de todos os itens para doacao cadastrados que tenham a descricao semelhante , sendo ordenados em ordem alfabética.
     */
    public String pesquisaItemParaDoacaoPorDescricao (String descricao) {
        return itemController.pesquisaItemParaDoacaoPorDescricao(descricao);
    }

    /**
     * Permite adicionar um item que é necessário que seja doado.
     * @param idReceptor Id do receptor que precisa do item.
     * @param descricaoItem Descricao do item necessario.
     * @param tags Tags do item necessario.
     * @param quantidade Quantidade do item necessario.
     * @return Retorna o identificador unico do item.
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
     * Retorna todos os itens necessarios cadastrados.
     * @return Retorna uma String com todos os itens necessarios adicionados.
     */
    public String listaItensNecessarios () {
        return this.itemController.listaItensNecessarios();
    }

    /**
     * Atualiza os atributos de quantidade e tags de um item necessario.
     * @param idReceptor Id do receptor a ter um item atualziado.
     * @param idItem Id do item a ser atualizado.
     * @param novaQuantidade Nova quantidade do item. Caso a nova quantidade seja 0, ela nao sera atualizada.
     * @param novasTags Novas tags do item. Caso as novas tags sejam vazias, elas nao seram atualizadas.
     * @return Retorna o toString do item modificado.
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
     * Remove um item necessario do sistema.
     * @param idReceptor Id do receptor a ter um item excluido.
     * @param idItem Id do item a ser excluido.
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