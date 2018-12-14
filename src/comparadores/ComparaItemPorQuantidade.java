package comparadores;

import edoe.Item;

import java.util.Comparator;

/**
 * Classe comparadora de dois itens pela sua quantidade, e caso essa seja igual em ambos, pela sua descricao.
 * @author Iago Tito
 */
public class ComparaItemPorQuantidade implements Comparator<Item> {
    /**
     * Compara dois itens para saber quem vem antes em uma listagem ordenada. O parametro de ordenacao e a quantiade desse item, e caso essa seja igual em ambos, o parametro de desempate sera a descricao do item.
     * @param item1 Item 1 a ser comaprado.
     * @param item2 Item 2 a ser comparado.
     * @return Retorna 1 caso o item 1 venha antes do item 2, -1 caso o item 1 venha depois do item 2, e 0 caso eles sejam iguais nos parametros de listagem.
     */
    @Override
    public int compare(Item item1, Item item2) {
        if (item1.getQuantidade() < item2.getQuantidade()) {
            return 1;
        } else if (item1.getQuantidade() > item2.getQuantidade()) {
            return -1;
        } else {
            return item1.compareTo(item2);
        }
    }
}
