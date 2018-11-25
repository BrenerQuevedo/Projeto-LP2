package edoe;

public class Controller {
    private UsuarioController usuarioController;
    private ItemController itemController;

    public Controller () {
        this.usuarioController = new UsuarioController();
        this.itemController = new ItemController();
    }

    public void lerReceptores (String caminho) {
        //TODO
    }

    public String adicionaDoador (String idUsuario, String nome, String email, String celular, String classe) {
        //TODO
        return "";
    }

    public String pesquisaUsuarioPorId (String idUsuario) {
        //TODO
        return "";
    }

    public String pesquisaUsuarioPorNome (String nome) {
        //TODO
        return "";
    }

    public String atualizaUsuario (String nome, String email, String celular) {
        //TODO
        return "";
    }

    public void removeUsuario (String idUsuario) {
        //TODO
    }

    public void adicionaDescritor (String descritor) {
        //TODO
    }

    public String adicionaItemParaDoacao (String idDoador, String descricaoItem, int quantidade, String tags){
        //TODO
        return "";
    }

    public String exibeItem (String idItem, String idDoador) {
        //TODO
        return "";
    }

    public String atualizaItemParaDoacao (String idItem, String idDoador, int quantidade, String tags) {
        //TODO
        return "";
    }

    public void removeItemParaDoacao (String idItem) {
        //TODO
    }

    public String listaDescritorDeItensParaDoacao () {
        //TODO
        return "";
    }

    public String listaItensParaDoacao () {
        //TODO
        return "";
    }

    public String pesquisaItemParaDoacaoPorDescricao (String descricao) {
        //TODO
        return "";
    }
}
