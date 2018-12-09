package edoe;

import java.util.Objects;

/**
 * Representação de um Item. Todo item e identificado pelo seu id e possui informacoes como: id do item, descricao, tags, quantidade e o nome do usuario que o possui.
 *
 * @author Brener Quevedo, Iago Tito.
 */
public class Item implements Comparable<Item> {
    /**
     * Descricao do item.
     */
    private String descricaoItem;
    /**
     * Identificador do item.
     */
    private String idItem;
    /**
     * Tags do item, guardando suas caracteristicas.
     */
    private String tags;
    /**
     * Quantidade a ser doada ou recebida dos itens.
     */
    private int quantidade;
    /**
     * Nome do usuario que possui ou precisa dos itens.
     */
    private String nomeUsuario;
    /**
     * Id do usuario que possui ou precisa dos itens.
     */
    private String idUsuario;

    /**
     * Cosntrutor do objeto Item.
     * @param idItem Id do item.
     * @param descricao Descricao do item.
     * @param tags Tags do item.
     * @param quantidade Quantidade a ser doada ou recebida do item.
     * @param nomeUsuario Nome do usuario que possui ou precsia do item.
     * @param idUsuario Id do usuario que possui ou precisa do item.
     */
    public Item(String idItem, String descricao, String tags, int quantidade, String nomeUsuario, String idUsuario) {
        this.descricaoItem = descricao;
        this.idItem = idItem;
        this.tags = tags;
        this.quantidade = quantidade;
        this.nomeUsuario = nomeUsuario;
        this.idUsuario = idUsuario;
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

    public String getIdUsuario () {
        return this.idUsuario;
    }


    public String getNomeUsuario () {
        return this.nomeUsuario;
    }


    /**
     * Retorna uma String com informacoes do item.
     * @return Retorna uma String com o formato "IDITEM - DESCRICAOITEM, tags: TAGS, quantidade: QUANTIDADE".
     */
    @Override
    public String toString() {
        return this.idItem + " - " + this.descricaoItem + ", tags: " + this.tags + ", quantidade: " + this.quantidade;
    }

    public String toStringComDoador () {
        return this.toString() + ", doador: " + this.nomeUsuario + "/" + this.idUsuario + ".";
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