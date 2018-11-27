package edoe;

public class Facade {
    private Controller controller;

    public  Facade () {
        this.controller = new Controller();
    }

    public void lerReceptores (String caminho) {
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

    public String atualizaUsuario (String nome, String email, String celular) {
        return this.controller.atualizaUsuario(nome, email, celular);
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

    public String atualizaItemParaDoacao (String idItem, String idDoador, int quantidade, String tags) {
        return this.controller.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
    }

    public void removeItemParaDoacao (String idItem) {
        this.controller.removeItemParaDoacao(idItem);
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
}