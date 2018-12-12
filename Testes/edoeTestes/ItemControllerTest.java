package edoeTestes;

import edoe.ItemInexistenteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edoe.ItemController;

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
    @DisplayName("teste de exibicao de item")
    public void exibeItemTest() {
        this.controle.adicionaDescritor("brinquedo");


        this.controle.adicionaItemParaDoacao("12345678912", "brinquedos", "bola,crianca", 3, "padre francisco");

        assertEquals("1 - brinquedos, tags: [bola, crianca], quantidade: 3", this.controle.exibeItem("12345678912", "1"));

        //EXCEÇÕES
        try {
            this.controle.exibeItem("000000000", "1");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Usuario nao encontrado: 000000000.");
        }

        try {
            this.controle.exibeItem("12345678912", "23");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Item nao encontrado: 23.");
        }


    }

    @Test
    @DisplayName("teste de remoção de itens")
    public void removeItemParaDoacaoTest() {
        this.controle.adicionaDescritor("travesseiro");

        this.controle.adicionaItemParaDoacao("13059752435", "travesseiro", "la, fofo, tecido", 4, "brener");

        this.controle.removeItemParaDoacao("1", "13059752435");

        //EXCEÇÕES

        try {
            this.controle.exibeItem("13059752435", "1");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Item nao encontrado: 1.");
        }

        try {
            this.controle.removeItemParaDoacao("-1", "13059752435");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do item nao pode ser negativo.");
        }

        try {
            this.controle.removeItemParaDoacao("1", null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controle.removeItemParaDoacao("1", "");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controle.removeItemParaDoacao("1", "13059752436");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "O Usuario nao possui itens cadastrados.");
        }

    }


    @Test
    @DisplayName("teste de atualizar atributos de itens")
    public void atualizaItemParaDoacaoTest() {

        this.controle.adicionaDescritor("pinturas");

        // antes
        this.controle.adicionaItemParaDoacao("11122233344", "pinturas", "tinta, decoracao, monalisa", 12, "Donald");

        assertEquals("1 - pinturas, tags: [tinta,  decoracao,  monalisa], quantidade: 12", this.controle.exibeItem("11122233344", "1"));

        // depois
        this.controle.atualizaItemParaDoacao("1", "11122233344", 15, "tinta, decoracao, monalisa, perfeicao");

        assertEquals("1 - pinturas, tags: [tinta,  decoracao,  monalisa,  perfeicao], quantidade: 15", this.controle.exibeItem("11122233344", "1"));


        //EXCEÇÕES

        try {
            this.controle.atualizaItemParaDoacao("-1", "11122233344", 15, "tinta, decoracao, monalisa, perfeicao");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do item nao pode ser negativo.");
        }

        try {
            this.controle.atualizaItemParaDoacao("1", "", 15, "tinta, decoracao, monalisa, perfeicao");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");

        }

        try {
            this.controle.atualizaItemParaDoacao("1", null, 15, "tinta, decoracao, monalisa, perfeicao");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controle.atualizaItemParaDoacao("1", "00000000000", 15, "tinta, decoracao, monalisa, perfeicao");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Usuario nao encontrado: 00000000000.");
        }

        try {
            this.controle.atualizaItemParaDoacao("2", "11122233344", 15, "tinta, decoracao, monalisa, perfeicao");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Item nao encontrado: 2.");
        }

    }


    @Test
    @DisplayName("Teste de listagem de descritores")
    public void listaDescritorDeItensParaDoacaoTest() {
        this.controle.adicionaDescritor("jogos");
        this.controle.adicionaDescritor("livros");
        this.controle.adicionaDescritor("chapeu");
        this.controle.adicionaDescritor("pinturas");


        this.controle.adicionaItemParaDoacao("11122233344", "pinturas", "tinta, decoracao, monalisa", 1, "Seu OLavo");
        this.controle.adicionaItemParaDoacao("11122233344", "pinturas", "infantil, guache", 4, "Helhão");

        this.controle.adicionaItemParaDoacao("11122233344", "chapeu", "trabalho, protecao", 2, "Ronaldo bruxo");
        this.controle.adicionaItemParaDoacao("11122233344", "chapeu", "estilo, gangster, fedora", 3, "Diego Ribeiro");

        this.controle.adicionaItemParaDoacao("11122233344", "jogos", "diversao, infantil, ps4", 100, "Iago OTito");


        assertEquals("5 - chapeu | 100 - jogos | 0 - livros | 5 - pinturas", this.controle.listaDescritorDeItensParaDoacao());

        //EXCEÇÕES

        try {
            this.controle.adicionaDescritor("");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: descricao nao pode ser vazia ou nula.");
        }

        try {
            this.controle.adicionaDescritor(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: descricao nao pode ser vazia ou nula.");
        }

        try {
            this.controle.adicionaDescritor("livros");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Descritor de Item ja existente: livros.");
        }

    }

    @Test
    @DisplayName("teste de listagem de itens para doacao")
    public void listaItensParaDoacaoTest() {
        this.controle.adicionaItemParaDoacao("11122233344", "jogos", "doom, violento, +18", 1, "brener");
        this.controle.adicionaItemParaDoacao("11122233344", "jogos", "god of war, goty", 2, "brener");
        this.controle.adicionaItemParaDoacao("11122233344", "jogos", "RDR2, rockstar, real goty", 3, "brener");
        this.controle.adicionaItemParaDoacao("11122233344", "jogos", "LoL, terrible game", 2, "brener");

        assertEquals("3 - jogos, tags: [RDR2,  rockstar,  real goty], quantidade: 3, doador: brener/11122233344 " +
                "| 2 - jogos, tags: [god of war,  goty], quantidade: 2, doador: brener/11122233344 " +
                "| 4 - jogos, tags: [LoL,  terrible game], quantidade: 2, doador: brener/11122233344 " +
                "| 1 - jogos, tags: [doom,  violento,  +18], quantidade: 1, doador: brener/11122233344", this.controle.listaItensParaDoacao());
    }


    @Test
    @DisplayName("teste de pesquisa de itens atraves de descritores")
    public void pesquisaItemParaDoacaoPorDescricaoTest() {

        this.controle.adicionaItemParaDoacao("12312312323123", "jogos adultos", "doom, violento, +18", 1, "widowmaker");
        this.controle.adicionaItemParaDoacao("12312312323123", "jogos adultos", "mario, plataforma, +4", 1, "cebolinha");


        assertEquals("1 - jogos adultos, tags: [doom,  violento,  +18], quantidade: 1 " +
                "| 2 - jogos adultos, tags: [mario,  plataforma,  +4], quantidade: 1", this.controle.pesquisaItemParaDoacaoPorDescricao("jogos adultos"));

        //EXCEÇÕES
        try {
            this.controle.pesquisaItemParaDoacaoPorDescricao("");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
        }


        try {
            this.controle.pesquisaItemParaDoacaoPorDescricao(null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");

        }

    }


    @Test
    @DisplayName("teste de cadastro de item necessário")
    public void adicionaItemNecessariotest() {
        this.controle.adicionaItemNecessario("12312312312", "bonecos", "colecionavel, batman", 4, "Bruce");
        this.controle.adicionaItemNecessario("00000000000", "jogos", "doom, fps", 2, "Marine");

        assertEquals("1 - bonecos, tags: [colecionavel,  batman], quantidade: 4, Receptor: Bruce/12312312312 | 2 - jogos, tags: [doom,  fps], quantidade: 2, Receptor: Marine/00000000000", this.controle.listaItensNecessarios());

        //EXCEÇÕES
        try {
            this.controle.adicionaItemNecessario("00000000000", "", "doom, fps", 2, "Marine");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: descricao nao pode ser vazia ou nula.");
        }

        try {
            this.controle.adicionaItemNecessario("00000000000", null, "doom, fps", 2, "Marine");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: descricao nao pode ser vazia ou nula.");
        }


        try {
            this.controle.adicionaItemNecessario("00000000000", "jogos", "doom, fps", -2, "Marine");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: quantidade deve ser maior que zero.");
        }

        try {
            this.controle.adicionaItemNecessario("", "jogos", "doom, fps", 2, "Marine");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controle.adicionaItemNecessario(null, "jogos", "doom, fps", 2, "Marine");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

    }

    @Test
    @DisplayName("teste de cadastro de item necessário")
    public void listaItensNecessariosTest() {
        this.controle.adicionaItemNecessario("11111111111", "bonecos", "SNES, Metroid", 3, "Samus");
        this.controle.adicionaItemNecessario("11111111134", "jogos", "Halo, fps", 1, "Masterchief");


        assertEquals("1 - bonecos, tags: [SNES,  Metroid], quantidade: 3, Receptor: Samus/11111111111 | 2 - jogos, tags: [Halo,  fps], quantidade: 1, Receptor: Masterchief/11111111134", this.controle.listaItensNecessarios());

    }

    @Test
    @DisplayName("teste de cadastro de item necessário")
    public void atualizaItemNecessarioTest() {
        this.controle.adicionaItemNecessario("11111111111", "cama", "ortobom, globo", 5, "Bahia");
        this.controle.adicionaItemNecessario("11111111112", "racao", "gato, cachorro", 9, "PETA");

        this.controle.atualizaItemNecessario("1", "11111111111", 7, "ortobom, globo, macio");
        this.controle.atualizaItemNecessario("2", "11111111112", 3, "gato, cachorro, aves");

        assertEquals("1 - cama, tags: [ortobom,  globo,  macio], quantidade: 7, Receptor: Bahia/11111111111 | 2 - racao, tags: [gato,  cachorro,  aves], quantidade: 3, Receptor: PETA/11111111112", this.controle.listaItensNecessarios());

        //EXCEÇÕES
        try {
            this.controle.atualizaItemNecessario("-1", "11111111111", 7, "ortobom, globo, macio");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do item nao pode ser negativo.");

        }

        try {
            this.controle.atualizaItemNecessario("1", "111111111113", 7, "ortobom, globo, macio");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "O Usuario nao possui itens cadastrados.");

        }


        try {
            this.controle.atualizaItemNecessario("1", "", 7, "ortobom, globo, macio");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");

        }


        try {
            this.controle.atualizaItemNecessario("1", null, 7, "ortobom, globo, macio");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");

        }

        try {
            this.controle.atualizaItemNecessario("4", "11111111111", 7, "ortobom, globo, macio");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Item nao encontrado: 4.");

        }
    }


    @Test
    @DisplayName("teste de remoção de item necessário")
    public void removeItemNecessarioTest() {

        this.controle.adicionaItemNecessario("12312312312", "bonecos", "colecionavel, batman", 4, "Bruce");
        this.controle.adicionaItemNecessario("00000000000", "jogos", "doom, fps", 2, "Marine");

        this.controle.removeItemNecessario("12312312312", "1");
        assertEquals("2 - jogos, tags: [doom,  fps], quantidade: 2, Receptor: Marine/00000000000", this.controle.listaItensNecessarios());



        try {
            this.controle.removeItemNecessario("12312312312", "-1");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do item nao pode ser negativo.");
        }

        try {
            this.controle.removeItemNecessario("", "1");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controle.removeItemNecessario(null, "1");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }


        try {
            this.controle.removeItemNecessario("12312312319", "2");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "O Usuario nao possui itens cadastrados.");
        }


        try {
            this.controle.removeItemNecessario("12312312312", "100");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Item nao encontrado: 100.");
        }

    }


    @Test
    @DisplayName("teste de remoção de item necessário")
    public void matchTest() {
        this.controle.adicionaItemNecessario("12312312312", "bonecos", "colecionavel, batman", 4, "Bruce");
        this.controle.adicionaItemNecessario("00000000000", "jogos", "doom, fps, colecionavel", 2, "Marine");
        this.controle.adicionaItemParaDoacao("11233211233", "bonecos", "colecionavel, superman", 4, "Clark");


        assertEquals("3 - bonecos, tags: [colecionavel,  superman], quantidade: 4, doador: Clark/11233211233", this.controle.match("12312312312", "1"));


        //EXCEÇÕES

        try {
            this.controle.match("12312312312", "2");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Item nao encontrado: 2.");
        }

        try {
            this.controle.match("00000000000", "2");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Item nao tem nenhum match.");
        }


        try {
            this.controle.match("", "2");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controle.match(null, "2");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
        }

        try {
            this.controle.match("12312312312", "-2");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do item nao pode ser negativo.");
        }

    }

    @Test
    @DisplayName("teste para a realização de uma doação")
    public void realizaDoacaoTest() throws ItemInexistenteException {
        this.controle.adicionaItemParaDoacao("11122233344", "jogos", "doom, violento, +18", 5, "brener");

        this.controle.adicionaItemNecessario("12312312312", "jogos", "colecionavel, batman", 4, "Bruce");

        assertEquals("02/12/2018 - doador: brener/11122233344, item: jogos, quantidade: 4, receptor: Bruce/12312312312", this.controle.realizaDoacao("2", "1", "02/12/2018"));

        assertEquals("1 - jogos, tags: [doom,  violento,  +18], quantidade: 1", this.controle.exibeItem("11122233344", "1"));

        //EXCEÇÕES

        try {
            this.controle.realizaDoacao("2", "1", "");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: data nao pode ser vazia ou nula.");
        }

        try {
            this.controle.realizaDoacao("2", "1", null);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: data nao pode ser vazia ou nula.");
        }

        try {
            this.controle.realizaDoacao("1", "2", "02/12/2018");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Item nao encontrado: 2.");
        }

        try {
            this.controle.realizaDoacao(null, "1", "02/12/2018");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "id item necessario nao pode ser nulo");
        }

        try {
            this.controle.realizaDoacao("2", null, "02/12/2018");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "id item para doacao nao pode ser nulo;");
        }

        try {
            this.controle.realizaDoacao("2", "-1", "02/12/2018");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do item nao pode ser negativo.");
        }

        try {
            this.controle.realizaDoacao("-2", "1", "02/12/2018");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Entrada invalida: id do item nao pode ser negativo.");
        }

    }

    @Test
    @DisplayName("Teste de listagem de doações realizadas")
    public void listaDoacoesTest() throws ItemInexistenteException {
        this.controle.adicionaItemParaDoacao("11111111111", "jogos", "doom, violento, +18", 5, "Pietro");
        this.controle.adicionaItemNecessario("33333333333", "jogos", "luta, beat and up", 4, "Clarice");

        this.controle.adicionaItemParaDoacao("22222222222", "livros", "ficcao, acao, suspense", 5, "Aurora");
        this.controle.adicionaItemNecessario("44444444444", "livros", "infantil, aventura", 4, "Dante");

        this.controle.realizaDoacao("2", "1", "11/09/2001");
        this.controle.realizaDoacao("4", "3", "16/08/2000");

        try {
            assertEquals("16/08/2000 - doador: Aurora/22222222222, item: livros, quantidade: 4, receptor: Dante/44444444444 |" +
                    " 11/09/2001 - doador: Pietro/11111111111, item: jogos, quantidade: 4, receptor: Clarice/33333333333", this.controle.listaDoacoes());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
