package edoe;

import javax.swing.tree.TreeCellRenderer;
import java.util.*;

public class ItemController {
    private Map<String, Map<String, Item>> itensDoacao;
    private Map<String, Map<String, Item>> itensNecessarios;
    private Map<String, List<Item>> descritores;
    private int idItem;

    public ItemController () {
        this.itensDoacao = new HashMap<>();
        this.itensNecessarios = new TreeMap<>();
        this.descritores = new TreeMap<>();
        this.idItem = 0;
    }

    public void adicionaDescritor (String descricao) throws IllegalArgumentException, NullPointerException{
        if (descricao == null) {
            throw new NullPointerException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        descricao = descricao.trim().toLowerCase();
        if (descricao.equals("")) {
            throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        if (this.descritores.containsKey(descricao)) {
            throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao + ".");
        }
        this.descritores.put(descricao, new ArrayList<>());
    }

    public String adicionaItemParaDoacao (String idDoador, String descricaoItem, String tags, int quantidade, String nomeDoador) {
        if (this.itensDoacao.containsKey(idDoador)) {
            for (String id : this.itensDoacao.get(idDoador).keySet()) {
                if (this.itensDoacao.get(idDoador).get(id).getDescricao().equals(descricaoItem) && this.itensDoacao.get(idDoador).get(id).getTags().equals(formataTags(tags))) {
                    this.atualizaItemParaDoacao(id, idDoador, quantidade, tags);
                    return id;
                }
            }
        }

        this.idItem += 1;
        Item item = new Item(Integer.toString(idItem), descricaoItem, formataTags(tags), quantidade, nomeDoador);

        if (!this.itensDoacao.containsKey(idDoador)) {
            this.itensDoacao.put(idDoador, new HashMap<>());
        }
        this.itensDoacao.get(idDoador).put(Integer.toString(idItem), item);

        if (!this.descritores.containsKey(descricaoItem)) {
            this.descritores.put(descricaoItem, new ArrayList<>());
        }

        this.descritores.get(descricaoItem).add(item);


        return Integer.toString(this.idItem);
    }

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
            throw new NullPointerException("Usuario nao encontrado: " + idUsuario + ".");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new NullPointerException("Item nao encontrado: " + idItem + ".");
        }
        if (novasTags != null) {
            novasTags = formataTags(novasTags);
            this.itensDoacao.get(idUsuario).get(idItem).setTags(novasTags);
        }
        if (novaQuantidade > 0) {
            this.itensDoacao.get(idUsuario).get(idItem).setQuantidade(novaQuantidade);
        }
        return this.itensDoacao.get(idUsuario).get(idItem).toString();
    }

    public String exibeItem(String idUsuario, String idItem) throws IllegalArgumentException, NullPointerException {
        if (!this.itensDoacao.containsKey(idUsuario)) {
            throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
        }
        return this.itensDoacao.get(idUsuario).get(idItem).toString();
    }

    public String listaDescritorDeItensParaDoacao () {
        StringBuilder builder = new StringBuilder();
        Map<String, Integer> tree = new TreeMap<>();
        int quantidade;
        boolean v = false;
        for (String s : this.descritores.keySet()) {
            if (v) {
                builder.append(" | ");
            }
            quantidade = 0;
            for (Item i : this.descritores.get(s)) {
                quantidade += i.getQuantidade();
            }
            builder.append(quantidade).append(" - ").append(s);
            v = true;
        }

        return builder.toString();
    }

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

    public String pesquisaItemParaDoacaoPorDescricao (String descricao) {
        if (descricao == null) {
            throw new NullPointerException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
        }
        if (descricao.trim().equals("")) {
            throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
        }
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

    public void removeItemParaDoacao(String idItem, String idUsuario) throws IllegalArgumentException, NullPointerException {
        if (!this.itensDoacao.containsKey(idUsuario)) {
            throw new NullPointerException("O Usuario nao possui itens cadastrados.");
        }
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (!this.itensDoacao.get(idUsuario).containsKey(idItem)) {
            throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
        }
        this.descritores.get(this.itensDoacao.get(idUsuario).get(idItem).getDescricao()).remove(this.itensDoacao.get(idUsuario).get(idItem));
        this.itensDoacao.get(idUsuario).remove(idItem);

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