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

        this.controle.adicionaItemParaDoacao("id1", "sal", "comida,conservante", 4, "fulano");

        assertEquals("2", this.controle.adicionaItemParaDoacao("id1", "sal", "comida,conservante", 4, "fulano"));

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao("", "sal", "comida,conservante", 4, "fulano");

                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao("id1", "", "comida,conservante", 4, "fulano");

                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao("id1", "sal", "comida,conservante", -1, "fulano");

                });

        assertThrows(NullPointerException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao(null, "sal", "comida,conservante", 4, "fulano");

                });

        assertThrows(NullPointerException.class,
                () -> {
                    this.controle.adicionaItemParaDoacao("id1", null, "comida,conservante", 4, "fulano");

                });

    }

    @Test
    public void exibeItem() {
        this.controle.adicionaDescritor("brinquedo");

        this.controle.adicionaItemNecessario("id1", "brinquedos", "bola,crianca", 3, "george");

        assertEquals("1 - brinquedos, [bola, crianca], quantidade: 3", this.controle.exibeItem("id1", "1") );



    }



    @Test
    @DisplayName("teste de remoção de itens")
    public void removeItem() {
        this.controle.adicionaDescritor("roupas");

        this.controle.adicionaItemParaDoacao("doador", "roupas", "la, fofo, tecido, 3", 4, "brener");

        this.controle.removeItemParaDoacao("1", "doador");

        assertThrows(NullPointerException.class,
                () -> {
                    this.controle.exibeItem("doador", "1");
                });

        this.controle.adicionaDescritor("computador");
        this.controle.adicionaItemNecessario("receptor", "computador", "tech,atual", 3, "kovalenko");
        this.controle.removeItemNecessario("receptor", "1");

        assertThrows(NullPointerException.class,
                () -> {
                    this.controle.exibeItem("receptor", "1");
                });


    }

}