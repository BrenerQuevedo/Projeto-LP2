package edoe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item implements Comparable<Item> {
    /**
     * Descricao do item a ser adicionado
     */
    private String descricaoItem;
    /**
     * identificador de item usado como key no map de itens
     */
    private String idItem;
    /**
     * tags dos itens, mostram uma série de caracteristicas específicas de um item
     */
    private String tags;
    /**
     * quantidade de itens a serem doados
     */
    private int quantidade;
    /**
     * nome do usuario que ira doar ou receber os itens
     */
    private String nomeUsuario;

    /**
     * construtor de item
     * @param idItem
     * @param descricao
     * @param tags
     * @param quantidade
     * @param nomeUsuario
     */

    public Item(String idItem, String descricao, String tags, int quantidade, String nomeUsuario) {
        this.descricaoItem = descricao;
        this.idItem = idItem;
        this.tags = tags;
        this.quantidade = quantidade;
        this.nomeUsuario = nomeUsuario;
    }

    public String getDescricao() {
        return this.descricaoItem;
    }

    public int getQuantidade () {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setTags (String novasTags) {
        this.tags = novasTags;
    }

    public String getTags () {
        return this.tags;
    }


    public String getNomeUsuario () {
        return this.nomeUsuario;
    }


    /**
     * toString que retorna analise geral
     * @return "identificador do item" - "descricao do item", tags: "tag, tag", quantidade: "quantidade de itens"
     */
    @Override
    public String toString() {
        return this.idItem + " - " + this.descricaoItem + ", tags: " + this.tags + ", quantidade: " + this.quantidade;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(descricaoItem, item.descricaoItem) &&
                Objects.equals(tags, item.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricaoItem, tags);
    }

    @Override
    public int compareTo(Item i) {
        return this.descricaoItem.compareTo(i.descricaoItem);
    }

}