package edoe;

import java.io.IOException;

import easyaccept.EasyAccept;

public class Facade {
    public static void main(String[] args) {
        args = new String[]{"edoe.Facade", "acceptance-tests/use_case_1.txt",
        "acceptance-tests/use_case_2.txt",
        "acceptance-tests/use_case_3.txt",
        "acceptance-tests/use_case_4.txt"};
        EasyAccept.main(args);
    }


    private Controller controller;

    public  Facade () {
        this.controller = new Controller();
    }

    public void lerReceptores (String caminho) throws IOException {
        this.controller.lerReceptores(caminho);
    }

    public String adicionaDoador (String idUsuario, String nome, String email, String celular, String classe) {
        return this.controller.adicionaDoador(idUsuario, nome, email, celular, classe);
    }

    public String pesquisaUsuarioPorId (String idUsuario) {
        return this.controller.pesquisaUsuarioPorId(idUsuario);
    }

    public String pesquisaUsuarioPorNome (String nome) {
        return this.controller.pesquisaUsuarioPorNome(nome);
    }

    public String atualizaUsuario (String idUsuario, String nome, String email, String celular) {
        return this.controller.atualizaUsuario(idUsuario, nome, email, celular);
    }

    public void removeUsuario (String idUsuario) {
        this.controller.removeUsuario(idUsuario);
    }

    public void adicionaDescritor (String descritor) {
        this.controller.adicionaDescritor(descritor);
    }

    public String adicionaItemParaDoacao (String idDoador, String descricaoItem, int quantidade, String tags){
        return this.controller.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
    }

    public String exibeItem (String idItem, String idDoador) {
        return this.controller.exibeItem(idItem, idDoador);
    }

    public String atualizaItemParaDoacao (String idItem, String idDoador, int novaQuantidade, String novasTags) {
        return this.controller.atualizaItemParaDoacao(idItem, idDoador, novaQuantidade, novasTags);
    }

    public void removeItemParaDoacao (String idItem, String idUsuario) {
        this.controller.removeItemParaDoacao(idItem, idUsuario);
    }

    public String listaDescritorDeItensParaDoacao () {
        return this.controller.listaDescritorDeItensParaDoacao();
    }

    public String listaItensParaDoacao () {
        return this.controller.listaItensParaDoacao();
    }

    public String pesquisaItemParaDoacaoPorDescricao (String descricao) {
        return this.controller.pesquisaItemParaDoacaoPorDescricao(descricao);
    }

    public String adicionaItemNecessario (String idReceptor, String descricaoItem, int quantidade, String tags) {
        return this.controller.adicionaItemNecessario (idReceptor, descricaoItem, quantidade, tags);
    }

    public String listaItensNecessarios () {
        return this.controller.listaItensNecessarios();
    }

    public String atualizaItemNecessario (String idItem, String idReceptor, int novaQuantidade, String novasTags) {
        return this.controller.atualizaItemNecessario(idReceptor, idItem, novaQuantidade, novasTags);
    }

    public void removeItemNecessario (String idReceptor, String idItem) {
        this.controller.removeItemNecessario(idReceptor,idItem);
    }


}