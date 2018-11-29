package edoe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item implements Comparable<Item> {
    private String descricaoItem;
    private String idItem;
    private String tags;
    private int quantidade;
    private String nomeUsuario;



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

    public void adicionaQuantidade (int quantidadeAMais) {
        this.quantidade += quantidadeAMais;
    }

    public void setTags (String novasTags) {
        this.tags = novasTags;
    }

    public String getNomeUsuario () {
        return this.nomeUsuario;
    }

    @Override
    public String toString() {
        return this.idItem + " - " + this.descricaoItem + ", " + this.tags + ", " + this.quantidade;

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