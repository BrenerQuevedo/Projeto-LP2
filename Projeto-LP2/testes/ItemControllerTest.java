package edoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ItemControllerTest {

    private ItemController controle;

    @BeforeEach
    public void criaController() {
        this.controle = new ItemController();
    }

    @Test
    @DisplayName("teste de adição de descritores")
    public void adicionaDescritorTest() {
        this.controle.adicionaDescritor("carrinho");

        assertEquals("0 - carrinho", this.controle.listaDescritorDeItensParaDoacao());


        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controle.adicionaDescritor("carrinho");
                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controle.adicionaDescritor("");
                });

        assertThrows(NullPointerException.class,
                () -> {
                    this.controle.adicionaDescritor(null);
                });

    }

    @Test
    @DisplayName("teste de adição de adição de novas doações")
    public void adicionaItemParaDoacaoTest() {
        this.controle.adicionaDescritor("sal");

        this.controle.adicionaItemParaDoacao("id1", "sal", "comida,conservante", 4);

        assertEquals("2", this.controle.adicionaItemParaDoacao("id1", "sal", "comida,conservante", 4));

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao("", "sal", "comida,conservante", 4);

                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao("id1", "", "comida,conservante", 4);

                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao("id1", "sal", "comida,conservante", -1);

                });

        assertThrows(NullPointerException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao(null, "sal", "comida,conservante", 4);

                });

        assertThrows(NullPointerException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao("id1", null, "comida,conservante", 4);

                });

    }


    }

}