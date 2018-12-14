package facade;

import java.io.IOException;
import java.io.Serializable;

import controllers.Controller;
import easyaccept.EasyAccept;
import exceptions.ItemInexistenteException;
import persistencia.Deserializador;
import persistencia.Serializador;

/**
 * Classe de fachada do sistema.
 *
 * @author Joicy Santos
 */
public class Facade implements Serializable {

    public static void main(String[] args) {
        args = new String[]{"facade.Facade", "acceptance-tests/use_case_1.txt",
        "acceptance-tests/use_case_2.txt",
        "acceptance-tests/use_case_3.txt",
        "acceptance-tests/use_case_4.txt",
        "acceptance-tests/use_case_5.txt",
        "acceptance-tests/use_case_6.txt",
                "acceptance-tests/use_case_7.txt"
        									};
        EasyAccept.main(args);
    }

    /**
     * Controlador do sistema.
     */
    private Controller controller;
    /**
     * Serializador que ira guardar as informacoes do sistema em um arquivo.
     */
    private Serializador serializador;
    /**
     * Deserializador que ira capturar as informacoes do sistema de um arquivo e ira atualizar o sistema com base nelas.
     */
    private Deserializador deserializador;

    /**
     * Constroi um objeto de fachada.
     */
    public  Facade () {
        this.controller = new Controller();
        this.serializador = new Serializador();
        this.deserializador = new Deserializador();
    }

    /**
     * Le um arquivo csv para cadastrar usuarios receptores no sistema.
     * @param caminho Local onde esta salvo o arquivo csv.
     * @throws IOException Excessao caso ocorra alguma falha na leitura dos arquivos.
     */
    public void lerReceptores (String caminho) throws IOException {
        this.controller.lerReceptores(caminho);
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
        return this.controller.adicionaDoador(idUsuario, nome, email, celular, classe);
    }

    /**
     * Retorna a representacao de um usuario de acordo com seu id.
     * @param idUsuario Identificacao do usuario que se quer.
     * @return Retorna o toString do usuario.
     */
    public String pesquisaUsuarioPorId (String idUsuario) {
        return this.controller.pesquisaUsuarioPorId(idUsuario);
    }

    /**
     * Retorna a representacao de um usuario pelo seu nome. Se houver mais de um usuario com o mesmo nome, retorna-se todos com o mesmo nome de acordo com a ordem de insercao do mais antigo para o mais novo.
     * @param nome Nome do usuario que se quer.
     * @return Retorna o toString do usuario, ou dos usuarios separados por "|".
     */
    public String pesquisaUsuarioPorNome (String nome) {
        return this.controller.pesquisaUsuarioPorNome(nome);
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
        return this.controller.atualizaUsuario(idUsuario, nome, email, celular);
    }

    /**
     * Remove um usuario do sistema de acordo com seu id.
     * @param idUsuario Id do usuario.
     */
    public void removeUsuario (String idUsuario) {
        this.controller.removeUsuario(idUsuario);
    }

    /**
     * Adiciona um novo descritor de um item.
     * @param descritor Novo descritor de itens a ser adicionado no sistema.
     */
    public void adicionaDescritor (String descritor) {
        this.controller.adicionaDescritor(descritor);
    }

    /**
     * Adiciona um item para a doação no sistema.
     * @param idDoador Id do doador a ter um item adicionado.
     * @param descricaoItem Descricao do item a ser adicionado.
     * @param tags Tags do item a ser adicionado.
     * @param quantidade Quantidade do item a ser adicionado.
     * @return Identificador do item.
     */
    public String adicionaItemParaDoacao (String idDoador, String descricaoItem, int quantidade, String tags){
        return this.controller.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
    }

    /**
     * Exibe as caracteristicas gerais de um item.
     * @param idDoador Id do doador que possui o item a ser exibido.
     * @param idItem Id do item a ser exibido.
     * @return Retorna o toString() do item.
     */
    public String exibeItem (String idItem, String idDoador) {
        return this.controller.exibeItem(idItem, idDoador);
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
        return this.controller.atualizaItemParaDoacao(idItem, idDoador, novaQuantidade, novasTags);
    }

    /**
     * Remove um item para doacao do Sistema.
     * @param idItem Id do item a ser excluido.
     * @param idUsuario Id do usuario a ter um item excluido.
     */
    public void removeItemParaDoacao (String idItem, String idUsuario) {
        this.controller.removeItemParaDoacao(idItem, idUsuario);
    }

    /**
     * Lista todos os descritores que antes foram adicionados pelo metodo adicionaDescritor.
     * @return Todos os descritores ordenados por ordem alfabetica.
     */
    public String listaDescritorDeItensParaDoacao () {
        return this.controller.listaDescritorDeItensParaDoacao();
    }

    /**
     * Lista todos os itens para doacao, ordenados por ordem alfabética.
     * @return String com as caracteristicas gerais de todos os itens para doacao.
     */
    public String listaItensParaDoacao () {
        return this.controller.listaItensParaDoacao();
    }

    /**
     *  Retorna uma analise geral dos itens para doacao cadastrados. Este método pesquisa em especifico os itens que contenham a descricao semelhante ao do parametro que foi passado.
     * @param descricao Descricao a ser pesquisada em todos os itens para doacao.
     * @return Retorna o toString() de todos os itens para doacao cadastrados que tenham a descricao semelhante , sendo ordenados em ordem alfabética.
     */
    public String pesquisaItemParaDoacaoPorDescricao (String descricao) {
        return this.controller.pesquisaItemParaDoacaoPorDescricao(descricao);
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
        return this.controller.adicionaItemNecessario (idReceptor, descricaoItem, quantidade, tags);
    }

    /**
     * Retorna todos os itens necessarios cadastrados.
     * @return Retorna uma String com todos os itens necessarios adicionados.
     */
    public String listaItensNecessarios () {
        return this.controller.listaItensNecessarios();
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
        return this.controller.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novasTags);
    }

    /**
     * Remove um item necessario do sistema.
     * @param idReceptor Id do receptor a ter um item excluido.
     * @param idItem Id do item a ser excluido.
     */
    public void removeItemNecessario (String idReceptor, String idItem) {
        this.controller.removeItemNecessario(idReceptor,idItem);
    }

    /**
     * Procura por matches entre os itens para doação e o item necessário passado como parâmetro juntamente com o id do usuario necessario que o possui.
     * @param idReceptor Id do receptor a ter um item para doacao procurado para match com seu item necessario.
     * @param idItemNecessario Id do item necessario procurado.
     * @return Retorna uma lista com os itens para doacao que fazem pontos de match com o item necessario passado, ordenada de maneira inversa pela quantidade de pontos.
     */
    public String match (String idReceptor, String idItemNecessario) {
        return this.controller.match(idReceptor, idItemNecessario);
    }

    /**
     * Realiza a doacao de um itemParaDoacao para um receptor que tem um itemNecessario com mesma descricao.
     * @param idItemNecessario id do item necessario.
     * @param idItemParaDoacao id do item para doacao.
     * @param data data em que esta sendo realizada a doacao.
     * @return retorna uma representacao da doacao com data, dados do doador e receptor, descricao do produto doado e quantidade doada do produto.
     * @throws ItemInexistenteException excecao lancada quando o item nao existe ou quando as descricoes dos itens nao sao iguais.
     */
    public String realizaDoacao(String idItemNecessario, String idItemParaDoacao, String data) throws NullPointerException, IllegalArgumentException, ItemInexistenteException {
    	return this.controller.realizaDoacao(idItemNecessario, idItemParaDoacao, data);
    }

    /**
     * Metodo que lista todas as doacoes ordenadas por sua data. Caso a data seja igual, ela se ordena pelo descritor do item.
     * @return retorna a representacao de cada doacao separado por " | " .
     */
    public String listaDoacoes() throws Exception {
    	return this.controller.listaDoacoes();
    }

    /**
     *  Le as informacoes do sistema em um arquivo e o atualiza o sistema com base nelas.
     * @throws ClassNotFoundException Honestamente, eu nao sei porque isso ocorre.
     * @throws IOException Ocorre caso haja uma falha na manipulacao do arquivo.
     */
    public void iniciaSistema() throws ClassNotFoundException, IOException {
        this.deserializador.iniciaSistema();
    }

    /**
     * Guarda as informacoes do sistema em um arquivo.
     * @throws IOException Ocorre caso haja uma falha na manipulacao do arquivo.
     */
    public void finalizaSistema() throws IOException {
        this.serializador.fechaSistema(this);
    }
}
