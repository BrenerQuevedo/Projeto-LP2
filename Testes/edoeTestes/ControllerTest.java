package edoeTestes;

import easyaccept.script.EchoProcessor;
import edoe.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerTest {

    private Controller controleGeral;


    @BeforeEach
    public void criaController() {
        this.controleGeral = new Controller();

    }

    @Test
    @DisplayName("teste de adição de descritores")
    public void adicionaDescritorTest() {
        this.controleGeral.adicionaDescritor("carrinho");

        assertEquals("0 - carrinho", this.controleGeral.listaDescritorDeItensParaDoacao());


        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleGeral.adicionaDescritor("carrinho");
                });

        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleGeral.adicionaDescritor("");
                });

        assertThrows(NullPointerException.class,
                () -> {
                    this.controleGeral.adicionaDescritor(null);
                });

    }

    @Test
    @DisplayName("teste de adição de adição de novas doações")
    public void adicionaItemParaDoacaoTest() {
        this.controleGeral.adicionaDescritor("sal");

        this.controleGeral.adicionaDoador("11111111111111", "VATICANO", "PAPA@", "URBANO0", "IGREJA" );

        this.controleGeral.adicionaItemParaDoacao("11111111111111", "sal", 4, "comida,conservante");

        assertEquals("1", this.controleGeral.adicionaItemParaDoacao("11111111111111", "sal", 4, "comida,conservante"));


        try {
            this.controleGeral.adicionaItemParaDoacao("", "sal", 4, "comida,conservante");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }


        try {
            this.controleGeral.adicionaItemParaDoacao("11111111111111", "", 4, "comida,conservante");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }


        try {
            this.controleGeral.adicionaItemParaDoacao("11111111111111", "sal", -1, "comida,conservante");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: quantidade deve ser maior que zero.");
        }


        try {
            this.controleGeral.adicionaItemParaDoacao(null, "sal", 4, "comida,conservante");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }


        try {
            this.controleGeral.adicionaItemParaDoacao("11111111111111", null, 4, "comida,conservante");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }


        try {
            this.controleGeral.adicionaItemParaDoacao("123", "sal", 4, "comida,conservante");
        } catch(Exception e) {
            e.getMessage().equals("Usuario nao encontrado: 123.");
        }

    }

    @Test
    @DisplayName("teste de exibicao de item")
    public void exibeItemTest() {
        this.controleGeral.adicionaDescritor("brinquedo");

        this.controleGeral.adicionaDoador("12345678912", "bruno", "bruno@", "90897898", "PESSOA_FISICA");
        this.controleGeral.adicionaItemParaDoacao("12345678912", "brinquedos", 3, "bola,crianca");

        assertEquals("1 - brinquedos, tags: [bola, crianca], quantidade: 3", this.controleGeral.exibeItem("1", "12345678912") );


        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.controleGeral.exibeItem("1", "doador nao existe");
                });

        try {
            this.controleGeral.exibeItem("1", "000000000");
        } catch(Exception e) {
            e.getMessage().equals("Usuario nao encontrado: 000000000.");
        }

        try {
            this.controleGeral.exibeItem("23", "12345678912");
        } catch(Exception e) {
            e.getMessage().equals("Item nao encontrado: 23.");
        }

    }

    @Test
    @DisplayName("teste de remoção de itens")
    public void removeItemParaDoacaoTest() {
        this.controleGeral.adicionaDescritor("travesseiro");

        this.controleGeral.adicionaDoador("13059752435", "nDoador", "doador@", "doador0", "PESSOA_FISICA");


        this.controleGeral.adicionaItemParaDoacao("13059752435", "travesseiro", 4, "la, fofo, tecido");

        this.controleGeral.removeItemParaDoacao("1", "13059752435");

        // Teste de excecao

        try {
            this.controleGeral.exibeItem("1", "13059752435");
        } catch(Exception e) {
            e.getMessage().equals("Item nao encontrado: 1.");
        }

        try {
            this.controleGeral.removeItemParaDoacao("-1", "13059752435");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: id do item nao pode ser negativo.");
        }

        try {
            this.controleGeral.removeItemParaDoacao("1", null);
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controleGeral.removeItemParaDoacao("1", "");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controleGeral.removeItemParaDoacao("", "13059752435");
        } catch(Exception e) {
            e.getMessage().equals("O Usuario nao possui itens cadastrados.");
        }


    }

    @Test
    @DisplayName("teste de atualizar atributos de itens")
    public void atualizaItemParaDoacaoTest() {

        this.controleGeral.adicionaDescritor("pinturas");

        this.controleGeral.adicionaDoador("11122233344", "diego", "diego@", "9089876532", "PESSOA_FISICA");

        // antes
        this.controleGeral.adicionaItemParaDoacao("11122233344", "pinturas", 12, "tinta, decoracao, monalisa");

        assertEquals("1 - pinturas, tags: [tinta,  decoracao,  monalisa], quantidade: 12", this.controleGeral.exibeItem("1", "11122233344"));

        // depois
        this.controleGeral.atualizaItemParaDoacao("1", "11122233344", 15, "tinta, decoracao, monalisa, perfeicao");

        assertEquals("1 - pinturas, tags: [tinta,  decoracao,  monalisa,  perfeicao], quantidade: 15", this.controleGeral.exibeItem("1", "11122233344"));


        // excecoes

        try {
            this.controleGeral.atualizaItemParaDoacao("-1", "11122233344", 15, "tinta, decoracao, monalisa, perfeicao");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: id do item nao pode ser negativo.");
        }

        try {
            this.controleGeral.atualizaItemParaDoacao("1", "", 15, "tinta, decoracao, monalisa, perfeicao");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controleGeral.atualizaItemParaDoacao("1", null, 15, "tinta, decoracao, monalisa, perfeicao");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controleGeral.atualizaItemParaDoacao("1", "00000000000", 15, "tinta, decoracao, monalisa, perfeicao");
        } catch(Exception e) {
            e.getMessage().equals("Usuario nao encontrado: 00000000000.");
        }

        try {
            this.controleGeral.atualizaItemParaDoacao("2", "11122233344", 15, "tinta, decoracao, monalisa, perfeicao");
        } catch(Exception e) {
            e.getMessage().equals("Item nao encontrado: 2.");
        }

    }

    @Test
    @DisplayName("Teste de listagem de descritores")
    public void listaDescritorDeItensParaDoacaoTest() {
        this.controleGeral.adicionaDescritor("jogos");
        this.controleGeral.adicionaDescritor("livros");
        this.controleGeral.adicionaDescritor("chapeu");
        this.controleGeral.adicionaDescritor("pinturas");

        this.controleGeral.adicionaDoador("11122233344", "diego", "diego@", "9089876532", "PESSOA_FISICA");

        this.controleGeral.adicionaItemParaDoacao("11122233344", "pinturas", 1, "tinta, decoracao, monalisa");
        this.controleGeral.adicionaItemParaDoacao("11122233344", "pinturas", 4, "infantil, guache");

        this.controleGeral.adicionaItemParaDoacao("11122233344", "chapeu", 2, "trabalho, protecao");
        this.controleGeral.adicionaItemParaDoacao("11122233344", "chapeu", 3, "estilo, gangster, fedora");

        this.controleGeral.adicionaItemParaDoacao("11122233344", "jogos", 100, "diversao, infantil, ps4");


        assertEquals("5 - chapeu | 100 - jogos | 0 - livros | 5 - pinturas", this.controleGeral.listaDescritorDeItensParaDoacao());

        //excecao

        try {
            this.controleGeral.adicionaDescritor("");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }

        try {
            this.controleGeral.adicionaDescritor(null);
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: descricao nao pode ser vazia ou nula.");
        }

        try {
            this.controleGeral.adicionaDescritor("livros");
        } catch(Exception e) {
            e.getMessage().equals("Descritor de Item ja existente: livros.");
        }

    }


    @Test
    @DisplayName("teste de listagem de itens para doacao")
    public void listaItensParaDoacaoTest() {
        this.controleGeral.adicionaDescritor("jogos");

        this.controleGeral.adicionaDoador("11122233344", "brener", "brener@", "9089876532", "PESSOA_FISICA");

        this.controleGeral.adicionaItemParaDoacao("11122233344", "jogos", 1, "doom, violento, +18");
        this.controleGeral.adicionaItemParaDoacao("11122233344", "jogos", 2, "god of war, goty");
        this.controleGeral.adicionaItemParaDoacao("11122233344", "jogos", 3, "RDR2, rockstar, real goty");
        this.controleGeral.adicionaItemParaDoacao("11122233344", "jogos", 2, "LoL, terrible game");

        assertEquals("3 - jogos, tags: [RDR2,  rockstar,  real goty], quantidade: 3, doador: brener/11122233344 " +
                "| 2 - jogos, tags: [god of war,  goty], quantidade: 2, doador: brener/11122233344 " +
                "| 4 - jogos, tags: [LoL,  terrible game], quantidade: 2, doador: brener/11122233344 " +
                "| 1 - jogos, tags: [doom,  violento,  +18], quantidade: 1, doador: brener/11122233344", this.controleGeral.listaItensParaDoacao());
    }

    @Test
    @DisplayName("teste de pesquisa de itens atraves de descritores")
    public void pesquisaItemParaDoacaoPorDescricaoTest() {
        this.controleGeral.adicionaDescritor("jogos adultos");
        this.controleGeral.adicionaDescritor("jogos infantis");

        this.controleGeral.adicionaDoador("12312312323123", "softGames", "softgames@", "3213211232", "SOCIEDADE");

        this.controleGeral.adicionaItemParaDoacao("12312312323123", "jogos adultos", 1, "doom, violento, +18");
        this.controleGeral.adicionaItemParaDoacao("12312312323123", "jogos adultos", 1, "mario, plataforma, +4");

        assertEquals("1 - jogos adultos, tags: [doom,  violento,  +18], quantidade: 1 " +
                "| 2 - jogos adultos, tags: [mario,  plataforma,  +4], quantidade: 1", this.controleGeral.pesquisaItemParaDoacaoPorDescricao("jogos adultos"));

        try {
            this.controleGeral.pesquisaItemParaDoacaoPorDescricao("");
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
        }

        try {
            this.controleGeral.pesquisaItemParaDoacaoPorDescricao(null);
        } catch(Exception e) {
            e.getMessage().equals("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
        }
    }
}