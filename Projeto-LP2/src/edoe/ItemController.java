package edoe;

import java.util.*;

/**
 * Classe responsavel pelo gerenciamento de itens, pelo padrao CRUD
 *
 * @author Brener Quevedo, Iago Oliveira
 *
 */

public class ItemController {

    /**
     * Mapa de itens doados, em que a key é o identificador do usuário, e o valor é outro mapa, sendo este de itens, no qual a key é o identificador do item e o value
     * é um objeto do tipo Item.
     */
    private Map<String, Map<String, Item>> itensDoacao;

    /**
     * Mapa de itens necessários, em que a key é o identificador do usuário, e o valor é outro mapa, sendo este de itens, no qual a key é o identificador do item e o value
     * é um objeto do tipo Item.
     */
    private Map<String, Map<String, Item>> itensNecessarios;
    /**
     * Mapa de descritores, responsável por salvar as descrições dos itens, sendo a key uma string que representa a descrição e o value uma List de itens.
     */
    private Map<String, List<Item>> descritores;
    /**
     * atributo que representa o identificador do item, do tipo inteiro.
     */
    private int idItem;

    /**
     * Construtor do controller e instanciação dos atributos
     * obs: o map de itens necessários será um TreeMap pois os itens terão de ser listados de forma ordenada.
     */
    public ItemController () {
        this.itensDoacao = new HashMap<>();
        this.itensNecessarios = new TreeMap<>();
        this.descritores = new HashMap<>();
        this.idItem = 0;
    }

    /**
     * Método responsável por adicionar um novo descritor de um item.
     * @param descricao
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public void adicionaDescritor (String descricao) throws IllegalArgumentException, NullPointerException{
        if (descricao == null) {
            throw new NullPointerException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        descricao = descricao.trim().toLowerCase();
        if (descricao.equals("")) {
            throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        if (this.descritores.containsKey(descricao)) {
            throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao);
        } else {
            this.descritores.put(descricao, new ArrayList<>());
        }
    }

    /**
     * Método que adiciona um item para a doação no hashmap de item dentro do hashmap de itensDoacao
     * @param idUsuario
     * @param descricaoItem
     * @param tags
     * @param quantidade
     * @param nomeDoador
     * @return identificador do item
     */
    public String adicionaItemParaDoacao (String idUsuario, String descricaoItem, String tags, int quantidade, String nomeDoador) {
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
        if (idUsuario.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }


        this.idItem += 1;
        Item item = new Item(Integer.toString(idItem), descricaoItem, formataTags(tags), quantidade, nomeDoador);
        if (this.itensDoacao.containsKey(idUsuario)) {
            this.itensDoacao.get(idUsuario).put(Integer.toString(idItem), item);
        } else {
            this.itensDoacao.put(idUsuario, new HashMap<>());
            this.itensDoacao.get(idUsuario).put(Integer.toString(idItem), item);
        }
        return Integer.toString(this.idItem);
    }

    /**
     * Método responsavel por remover um item do mapa de itens doados.
     * @param idItem
     * @param idUsuario
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public void removeItem(String idItem, String idUsuario) throws IllegalArgumentException, NullPointerException {
        if (idUsuario == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idUsuario.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.itensDoacao.containsKey(idUsuario)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
        }
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new NullPointerException("O Usuario nao possui itens cadastrados.");
        }
        this.itensDoacao.get(idUsuario).remove(idItem);

    }

    /**
     * Metodo que exibe as caracteristicas gerais de um item
     * @param idUsuario
     * @param idItem
     * @return idItem + " - " + descricaoItem + ", " + tags + ", " + quantidade
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public String exibeItem(String idUsuario, String idItem) throws IllegalArgumentException, NullPointerException {
        if (!this.itensDoacao.containsKey(idUsuario)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + " .");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
        }
            return this.itensDoacao.get(idUsuario).get(idItem).toString();
    }

    /**
     * Método que permite a listagem de todos os descritores que antes foram adicionados pelo metodo adicionaDescritor.
     * @return todos os descritores ordenados por ordem alfabetica.
     */

    public String listaDescritorDeItensParaDoacao () {
        StringBuilder builder = new StringBuilder();

        ArrayList<String> array = new ArrayList<>(this.descritores.keySet());
        Collections.sort(array);

        for (String s : array) {
            if (builder.length() != 0) {
                builder.append(" | ");
            }

            int quantidade = 0;
            for (Item i : this.descritores.get(s)) {
                quantidade += i.getQuantidade();
            }

            builder.append(Integer.toString(quantidade)).append(" - ").append(s);
        }

        return builder.toString();
    }

    /**
     * Método responsável por listar todos os itens doados, ordenados por ordem alfabética
     * @return String com as caracteristicas gerais de todos os itens
     */
    public String listaItensParaDoacao () {
        Map<Integer, Map<String, String>> tree = new TreeMap<>(Collections.reverseOrder());

        for (String idUsuario : itensDoacao.keySet()) {
            for (String idItem : itensDoacao.get(idUsuario).keySet()) {
                if (!tree.containsKey(this.itensDoacao.get(idUsuario).get(idItem).getQuantidade())) {
                    tree.put(this.itensDoacao.get(idUsuario).get(idItem).getQuantidade(), new TreeMap<>());
                }
                tree.get(this.itensDoacao.get(idUsuario).get(idItem).getQuantidade()).put(this.itensDoacao.get(idUsuario).get(idItem).getDescricao(),
                        itensDoacao.get(idUsuario).get(idItem).toString() + ", doador: " + itensDoacao.get(idUsuario).get(idItem).getNomeUsuario()
                        + "/" + idUsuario);
            }
        }


        StringBuilder builder = new StringBuilder();
        boolean v = false;
        for (Integer i : tree.keySet()) {
            for (String s : tree.get(i).keySet()) {
                if (v) {
                    builder.append(" | ");
                }
                builder.append(tree.get(i).get(s));
                v = true;
            }
        }

        return builder.toString();
    }

    /**
     *  Método que permite retornar uma analise geral dos itens cadastrados,
     *  este método pesquisa em especifico os itens que contenham a descricao semelhante ao do parametro que foi passado.
     * @param descricao
     * @return (idItem + " - " + descricaoItem + ", " + tags + ", " + quantidade) de todos os itens cadastrados que tenham a descricao semelhante , sendo ordenados em ordem alfabética
     */
    public String pesquisaItemParaDoacaoPorDescricao (String descricao) {
        StringBuilder builder = new StringBuilder();
        List<String> lista = new ArrayList<>();

        for (String s : this.descritores.keySet()) {
            if (s.toLowerCase().contains(descricao.toLowerCase())) {
                lista.add(s);
            }
        }

        Collections.sort(lista);

        boolean v = false;
        for (String s : lista) {
            if (v) {
                builder.append(" | ");
            }
            builder.append(itensComDescritor(s));
            v = true;
        }
        return builder.toString();
    }

    /**
     *
     * @param idItem
     * @param idUsuario
     * @param novaQuantidade
     * @param novasTags
     * @return
     */
    public String atualizaItemParaDoacao (String idItem, String idUsuario, int novaQuantidade, String novasTags) {
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (idUsuario == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idUsuario.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.itensDoacao.containsKey(idUsuario)) {
            throw new NullPointerException("Usuario nao encontrado: " + idUsuario);
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new NullPointerException("Item nao encotnrado: " + idItem);
        }

        novasTags = formataTags(novasTags);
        this.itensDoacao.get(idUsuario).get(idItem).setTags(novasTags);
        this.itensDoacao.get(idUsuario).get(idItem).setQuantidade(novaQuantidade);
        return this.itensDoacao.get(idUsuario).get(idItem).toString();
    }

    public void removeItemParaDoacao(String idItem, String idUsuario) throws IllegalArgumentException, NullPointerException {
        if (idUsuario == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idUsuario.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.itensDoacao.containsKey(idUsuario)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
        }
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new NullPointerException("O Usuario nao possui itens cadastrados.");
        } else {
            this.itensDoacao.get(idUsuario).remove(idItem);
        }
    }

    public String adicionaItemNecessario (String idUsuario, String descricaoItem, String tags, int quantidade, String nomeReceptor) {
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
        if (idUsuario.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        this.idItem += 1;

        Item item = new Item(Integer.toString(idItem), descricaoItem, formataTags(tags), quantidade, nomeReceptor);
        if (!this.itensNecessarios.containsKey(idUsuario)) {
            this.itensNecessarios.put(idUsuario, new HashMap<>());
        }
        this.itensNecessarios.get(idUsuario).put(Integer.toString(idItem), item);

        if (!this.descritores.containsKey(descricaoItem)) {
            this.descritores.put(descricaoItem, new ArrayList<>());
        }
        this.descritores.get(descricaoItem).add(item);

        return Integer.toString(this.idItem);
    }

    public String listaItensNecessarios () {
        StringBuilder builder = new StringBuilder();
        boolean v = false;
        for (String idReceptor : this.itensNecessarios.keySet()) {
            for (String idItem : this.itensNecessarios.get(idReceptor).keySet()) {
                if (v) {
                    builder.append(" - ");
                }
                builder.append(this.itensNecessarios.get(idReceptor).get(idItem).toString()).append(", Receptor: ").append(this.itensNecessarios.get(idReceptor).get(idItem).getNomeUsuario()).append("/").append(idReceptor);
                v = true;
            }
        }
        return builder.toString();
    }

    public String atualizaItemNecessario (String idReceptor, String idItem, int novaQuantidade, String novasTags) {
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (idReceptor == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idReceptor.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        if (!this.itensDoacao.containsKey(idReceptor)) {
            throw new NullPointerException("Usuario nao encontrado: " + idReceptor);
        }
        if (!this.itensDoacao.get(idReceptor).containsKey(idItem)) {
            throw new NullPointerException("Item nao encotnrado: " + idItem);
        }


        novasTags = formataTags(novasTags);
        this.itensNecessarios.get(idReceptor).get(idItem).setTags(novasTags);
        this.itensNecessarios.get(idReceptor).get(idItem).setQuantidade(novaQuantidade);
        return this.itensNecessarios.get(idReceptor).get(idItem).toString();
    }

    public void removeItemNecessario (String idReceptor, String idItem) {
        if (idReceptor == null) {
            throw new NullPointerException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (idReceptor.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }
        if (!this.itensNecessarios.get(idReceptor).containsKey(idItem)) {
            throw new NullPointerException("Item nao encontrado: " + idItem);
        }
        if (!this.itensNecessarios.containsKey(idReceptor)) {
            throw new NullPointerException("Usuario nao encontrado: " + idReceptor);
        }
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (this.itensNecessarios.get(idReceptor).size() == 0) {
            throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
        }

        this.itensNecessarios.get(idReceptor).remove(idItem);
    }

    private String formataTags(String tags) {
        return "[" + tags.replace(",", ", ") + "]";
    }

    private String itensComDescritor (String s) {
        StringBuilder builder = new StringBuilder();
        List<Item> lista = new ArrayList<>(descritores.get(s));
        Collections.sort(lista);

        boolean v = false;
        for (Item i : lista) {
            if (v) {
                builder.append(" | ");
            }
            builder.append(i.toString());
            v = true;
        }
        return builder.toString();
    }
}