package edoeTestes;

import edoe.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    Item p1;
    Item p3;
    Item p2;
    Item igual;


    @BeforeEach
    public void inicializaUsers() {
        this.p1 = new Item("id1", "cadeira", "de sol, de rodas, brinquedo", 3, "daniel", "12312312312", "doacao");
        this.p2 = new Item("id2", "roupas", "la, algodao, vestimenta", 5, "doritos", "11122233344", "doacao");
        this.p3 = new Item("id3", "computador" , "tech, apple", 4, "marcos", "00099988877", "doacao");
        this.igual = new Item("id1", "cadeira", "de sol, de rodas, brinquedo", 3, "daniel", "12312312312", "doacao");

    }
    @Test
    void testSetQuantidade() {
        this.p1.setQuantidade(6);
        assertEquals(this.p1.getQuantidade(), 6);
        this.p3.setQuantidade(2);
        assertEquals(this.p3.getQuantidade(), 2);
        this.p2.setQuantidade(1);
        assertEquals(this.p2.getQuantidade(), 1);
    }

    @Test
    void testSetTags() {
        this.p1.setTags("banquinho, de bar");;
        assertEquals(this.p1.toString(),"id1 - cadeira, tags: banquinho, de bar, quantidade: 3");
        this.p3.setTags("tech, android, microsoft");
        assertEquals(this.p3.toString(),"id3 - computador, tags: tech, android, microsoft, quantidade: 4" );
    }


    @Test
    void testToString() {
        assertEquals(this.p1.toString(),"id1 - cadeira, tags: de sol, de rodas, brinquedo, quantidade: 3");
        assertEquals(this.p2.toString(), "id2 - roupas, tags: la, algodao, vestimenta, quantidade: 5");
        assertEquals(this.p3.toString(), "id3 - computador, tags: tech, apple, quantidade: 4");
    }

    @Test
    void testToStringcomDoador() {
        assertEquals(this.p1.toStringComDoador(),"id1 - cadeira, tags: de sol, de rodas, brinquedo, quantidade: 3, doador: daniel/12312312312");
        assertEquals(this.p2.toStringComDoador(), "id2 - roupas, tags: la, algodao, vestimenta, quantidade: 5, doador: doritos/11122233344");
        assertEquals(this.p3.toStringComDoador(), "id3 - computador, tags: tech, apple, quantidade: 4, doador: marcos/00099988877");
    }

    @Test
    void EqualsTest() {
        assertTrue(igual.equals(p1));
        assertFalse(p1.equals(p2));
    }

}

