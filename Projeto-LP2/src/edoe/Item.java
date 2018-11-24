package projeto;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Item {
    private String descricao;
    private String id;
    private String[] tags;
    private int quantidade;



    public Item(String id, String descricao, String[] tags, int quantidade ) {
        this.descricao = descricao;
        this.id = id;
        this.tags = tags;
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.descricao + ", " + this.tags + ", " + this.quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item itens = (Item) o;
        return quantidade == itens.quantidade &&
                Objects.equals(descricao, itens.descricao) &&
                Objects.equals(id, itens.id) &&
                Arrays.equals(tags, itens.tags);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(descricao, id, quantidade);
        result = 31 * result + Arrays.hashCode(tags);
        return result;
    }
}

