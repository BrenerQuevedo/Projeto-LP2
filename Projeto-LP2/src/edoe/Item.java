package edoe;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item {
    private String descricaoItem;
    private String idItem;
    private String[] tags;
    private int quantidade;



    public Item(String idItem, String descricao, String tags, int quantidade ) {
        this.descricaoItem = descricao;
        this.idItem = idItem;
        this.tags = tags.split(",");
        this.quantidade = quantidade;
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

    @Override
    public String toString() {
        return this.idItem + " - " + this.descricaoItem + ", " + this.tags.toString() + ", " + this.quantidade;
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
}

