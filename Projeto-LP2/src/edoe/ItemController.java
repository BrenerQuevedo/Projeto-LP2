package projeto;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ItemController {

    private Item doacao;
    private int idItem;
    private HashMap<String, HashMap<String, Item>> itensDoacao;
    private HashMap<String, HashMap<String, Item>> itensNecessarios;
    private HashMap<String, Integer> descritores;



    public ItemController() {

        this.itensDoacao = new HashMap<>();
        this.itensNecessarios = new HashMap<>();
        this.descritores = new HashMap<>();

    }

    private void verificaAdiciona(String descricao, int quantidade) {
        if (!this.descritores.containsKey(descricao)) {
            this.descritores.replace(descricao,this.descritores.get(descricao) + quantidade);
        } else {
            this.descritores.put(descricao, quantidade);
        }
    }


    public void adicionaDescritor(String descricao) {
        if (descricao == null) {
            throw new NullPointerException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        } else if (descricao.trim().equals("")){
            throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        } else if (this.descritores.containsKey(descricao)) {
            throw new IllegalArgumentException("Descritor de Item ja existente:" + descricao);
        } else {
            this.descritores.put(descricao, 0);
        }
    }


    public String adicionaDoacao(String idUsuario, String descricao, String[] tags, int quantidade) throws IllegalArgumentException, NullPointerException {

        Item doacao = new Item(idUsuario, descricao,tags, quantidade);

        if (idUsuario.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        } else if (idUsuario == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        } else if(quantidade < 0) {
            throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
        } else if (descricao == null) {
            throw new NullPointerException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        } else if (descricao.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        } else {
            this.verificaAdiciona(descricao, quantidade);
            this.itensDoacao.get(idUsuario).put(idUsuario, doacao);
            return idUsuario;
        }
    }

    public String adicionaNecessarios(String idUsuario, String descricao, String[] tags, int quantidade) throws IllegalArgumentException, NullPointerException {

        Item recebido = new Item(idUsuario, descricao,tags, quantidade);

        if (idUsuario.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        } else if (idUsuario == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        } else if(quantidade < 0) {
            throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
        } else if (descricao == null) {
            throw new NullPointerException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        } else if (descricao.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        } else {
            this.verificaAdiciona(descricao, quantidade);
            this.itensNecessarios.get(idUsuario).put(idUsuario, recebido);
            return idUsuario;
        }
    }

    public String exibeItem(String idUsuario, String idItem) throws IllegalArgumentException, NullPointerException {
        if (!this.itensDoacao.containsKey(idUsuario)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + " .");
        } else if (!this.descritores) {

        }

    }

}



