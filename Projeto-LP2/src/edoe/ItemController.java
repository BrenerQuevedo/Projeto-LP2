package edoe;

import java.util.*;

public class ItemController {
    private Map<String, Map<String, Item>> itensDoacao;
    private Map<String, Map<String, Item>> itensNecessarios;
    private Map<String, List<Item>> descritores;
    private int idItem;

    public ItemController () {
        this.itensDoacao = new HashMap<>();
        this.itensNecessarios = new HashMap<>();
        this.descritores = new HashMap<>();
        this.idItem = 0;
    }

    public void adicionaDescritor (String descricao) {
        descricao.trim().toLowerCase();
        if (descricao.equals("")) {
            throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }
        if (this.descritores.containsKey(descricao)) {
            throw new IllegalArgumentException("Descritor de Item ja existente: " + descricao);

        } else {
            this.descritores.put(descricao, new ArrayList<>());
        }
    }

    public String adicionaItemParaDoacao (String idUsuario, String descricaoItem, String tags, int quantidade) {
        descricaoItem.trim().toLowerCase();
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
        Item item = new Item(Integer.toString(idItem), descricaoItem, formataTags(tags), quantidade);
        if (this.itensDoacao.containsKey(idUsuario)) {
            this.itensDoacao.get(idUsuario).put(Integer.toString(idItem), item);
        } else {
            this.itensDoacao.put(idUsuario, new HashMap<>());
            this.itensDoacao.get(idUsuario).put(Integer.toString(idItem), item);
        }

        this.descritores.get(descricaoItem).add(item);

        return Integer.toString(this.idItem);
    }

    public String adicionaItemNecessario (String idUsuario, String descricaoItem, String tags, int quantidade) {
        descricaoItem.trim().toLowerCase();
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

        Item item = new Item(Integer.toString(idItem), descricaoItem, formataTags(tags), quantidade);
        if (this.itensNecessarios.containsKey(idUsuario)) {
            this.itensNecessarios.get(idUsuario).put(Integer.toString(idItem), item);
        } else {
            this.itensNecessarios.put(idUsuario, new HashMap<>());
            this.itensNecessarios.get(idUsuario).put(Integer.toString(idItem), item);
        }


        return Integer.toString(this.idItem);
    }

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

    public String atualizaItemParaDoacao (String idItem, String idUsuario, int novaQuantidade, String novasTags) {
        if (Integer.parseInt(idItem) < 0) {
            throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
        }
        if (idUsuario == null || idUsuario.trim().equals("")) {
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

    private String formataTags(String tags) {
        return "[" + tags.replace(",", ", ") + "]";
    }
}
