package edoe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ControllerTest {

    private ItemController controleItem;
    private UsuarioController controleUsuario;
    private Controller controleGeral;


    @BeforeEach
    public void criaController() {
        this.controleUsuario = new UsuarioController();
        this.controleItem = new ItemController();
        this.controleGeral = new Controller();
        this.controleItem.adicionaItemParaDoacao("doador", "roupas", "la, algodao", 3, "paulo");
    }

    @Test
    @DisplayName("teste de adição de descritores")
    public void adicionaDescritorTest() {
        this.controleItem.adicionaDescritor("carrinho");

        assertEquals("0 - carrinho | 3 - roupas", this.controleItem.listaDescritorDeItensParaDoacao());


        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleItem.adicionaDescritor("carrinho");
                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleItem.adicionaDescritor("");
                });

        assertThrows(NullPointerException.class,
                () -> {
                    this.controleItem.adicionaDescritor(null);
                });

    }

    @Test
    @DisplayName("teste de adição de adição de novas doações")
    public void adicionaItemParaDoacaoTest() {
        this.controleItem.adicionaDescritor("sal");

        this.controleItem.adicionaItemParaDoacao("id1", "sal", "comida,conservante", 4, "fulano");

        assertEquals("2", this.controleItem.adicionaItemParaDoacao("id1", "sal", "comida,conservante", 4, "fulano"));

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleGeral.adicionaItemParaDoacao("", "sal", 4, "comida,conservante");

                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleGeral.adicionaItemParaDoacao("id1", "", 4, "comida,conservante");

                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleGeral.adicionaItemParaDoacao("id1", "sal", -1, "comida,conservante");

                });

        assertThrows(NullPointerException.class,
                () -> {
                    this.controleGeral.adicionaItemParaDoacao(null, "sal", 4, "comida,conservante");

                });

        assertThrows(NullPointerException.class,
                () -> {
                    this.controleGeral.adicionaItemParaDoacao("id1", null, 4, "comida,conservante");

                });

    }

    @Test
    public void exibeItem() {
        this.controleItem.adicionaDescritor("brinquedo");


        this.controleItem.adicionaItemParaDoacao("id1", "brinquedos", "bola,crianca", 3, "george");

        assertEquals("2 - brinquedos, tags: [bola, crianca], quantidade: 3", this.controleItem.exibeItem("id1", "2") );


        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleItem.exibeItem("doador nao existe", "1");
                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleItem.exibeItem("id1", "item nao existe");
                });


    }


    @Test
    @DisplayName("teste de remoção de itens")
    public void removeItem() {
        this.controleItem.adicionaDescritor("travesseiro");

        this.controleItem.adicionaItemParaDoacao("doador", "travesseiro", "la, fofo, tecido", 4, "brener");

        this.controleItem.removeItemParaDoacao("1", "doador");

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleItem.exibeItem("doador", "1");
                });

//
//        this.controleItem.adicionaDescritor("computador");
//        this.controleItem.adicionaItemNecessario("receptor", "computador", "tech,atual", 3, "kovalenko");
//        this.controleUsuario.cadastraReceptor("higor", "higor@", "88099473", "PESSOA_FISICA", "12312312312", "receptor");
//
//        this.controleItem.exibeItem("higor", "2");
//
//        this.controleItem.removeItemNecessario("receptor", "1");
//
//        assertThrows(NullPointerException.class,
//                () -> {
//                    this.controleItem.exibeItem("12312312312", "2");
//                });


    }




}
