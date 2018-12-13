package comparadores;

import edoe.Item;

import java.util.Comparator;

public class ComparaItemPorQuantidade implements Comparator<Item> {
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
