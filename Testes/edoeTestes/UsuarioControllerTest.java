package edoeTestes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edoe.UsuarioController;

public class UsuarioControllerTest {
	UsuarioController controller;
	
	@BeforeEach
	public void IniciaController() {
		controller = new UsuarioController();
		controller.cadastraDoador("Paulo", "paulo.com", "93521561", "PESSOA_FISICA", "13059752435", "doador");
		controller.cadastraDoador("embratel", "embratel.com","33632412", "ASSOCIACAO", "12345678912345", "doador");
		controller.cadastraDoador("Paulo", "henrrique.com", "12345678", "PESSOA_FISICA", "12345678978", "doador");
		controller.cadastraReceptor("china", "china.com", "00000000", "ONG", "85274196374185", "receptor");
	}
	
	
	@Test
	void testCadastraDoador() {
		assertEquals(this.controller.cadastraDoador("jonas", "jonas@gmail", "90785634", "PESSOA_FISICA", "32165498778", "doador"), "32165498778");
		assertEquals(this.controller.cadastraDoador("Igreja do Carmo", "docarmo.com", "20121231", "IGREJA", "14725836914725", "doador"), "14725836914725");
		
		/*
		 * Excecoes para entradas nulas, vazias	ou invalidas
		 */
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador(null, "nomeNulo","000000" , "PESSOA_FISICA", "00000000000", "doador");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("", "nomeVazio","000000" , "PESSOA_FISICA", "00000000000", "doador");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador("email nulo", null ,"000000" , "PESSOA_FISICA", "00000000000", "doador");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("email vazio", "","000000" , "PESSOA_FISICA", "00000000000", "doador");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador("celular nulo", "celular nulo", null , "PESSOA_FISICA", "00000000000", "doador");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("celular vazio", "celular vazio","" , "PESSOA_FISICA", "00000000000", "doador");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador("classe nula", "classe nula", "34893905" , null , "00000000000", "doador");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("classe vazia", "classe vazia","3984590485" , "", "00000000000", "doador");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("classe invalida", "classe invalida","3984590485" , "FACCAO_CRIMINOSA", "00000000000", "doador");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador("id nulo", "id nulo","3984590485" , "PESSOA_FISICA", null, "doador");
		});
	
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("id vazio", "id vazio", "3984590485" , "PESSOA_FISICA", "", "doador");
		});

		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("id invalido", "id invalido", "3984590485" , "PESSOA_FISICA", "1245", "doador");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("id invalido", "id invalido", "3984590485" , "ASSOCIACAO", "00000", "doador");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("usario existente", "usuario existente", "3984590485" , "ASSOCIACAO", "12345678912345", "doador");
		});
	
	}

	@Test
	void testCadastraReceptor() {
		this.controller.cadastraReceptor("pessoa necessitada", "emailparareceber.com", "00000000", "PESSOA_FISICA", "00000000000", "receptor");
		this.controller.cadastraReceptor("pessoa necessitada2", "emailparareceber2.com", "00000000", "IGREJA", "14725896374185", "receptor");
		assertEquals(this.controller.pesquisaUsuarioPorId("00000000000"), "pessoa necessitada/00000000000, emailparareceber.com, 00000000, status: receptor");
		assertEquals(this.controller.pesquisaUsuarioPorId("14725896374185"), "pessoa necessitada2/14725896374185, emailparareceber2.com, 00000000, status: receptor");


		//
		this.controller.cadastraReceptor("nova pessoa necessitada", "emaildiferente.com", "0000000000011", "PESSOA_FISICA", "00000000000", "receptor");
        assertEquals("nova pessoa necessitada/00000000000, emaildiferente.com, 0000000000011, status: receptor", this.controller.pesquisaUsuarioPorId("00000000000"));
        //

        /*
		 * Excecoes para entradas nulas, vazias	ou invalidas
		 */
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraReceptor(null, "nomeNulo","000000" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("", "nomeVazio","000000" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraReceptor("email nulo", null ,"000000" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("email vazio", "","000000" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraReceptor("celular nulo", "celular nulo", null , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("celular vazio", "celular vazio","" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraReceptor("classe nula", "classe nula", "34893905" , null , "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("classe vazia", "classe vazia","3984590485" , "", "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("classe invalida", "classe invalida","3984590485" , "PESSOA_FISICA", "000000000000", "receptor");
		});


		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("classe invalida", "classe invalida","3984590485" , "IGREJA", "000000000000", "receptor");
		});

		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraReceptor("id nulo", "id nulo","3984590485" , "PESSOA_FISICA", null, "receptor");
		});
	
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("id vazio", "id vazio", "3984590485" , "PESSOA_FISICA", "", "receptor");
		});

		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("id invalido", "id invalido", "3984590485" , "PESSOA_FISICA", "1245", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("id invalido", "id invalido", "3984590485" , "ASSOCIACAO", "00000", "receptor");
		});

		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraReceptor("id invalido", "id invalido", "3984590485" , "ASSOCIACAO", "00000", "receptor");
		});



	} 

	@Test
	void testPesquisaUsuarioPorId() {
		assertEquals(this.controller.pesquisaUsuarioPorId("13059752435"), "Paulo/13059752435, paulo.com, 93521561, status: doador");
		assertEquals(this.controller.pesquisaUsuarioPorId("12345678912345"), "embratel/12345678912345, embratel.com, 33632412, status: doador");
		assertEquals(this.controller.pesquisaUsuarioPorId("12345678978"), "Paulo/12345678978, henrrique.com, 12345678, status: doador");	
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.pesquisaUsuarioPorId("2983923");
		});
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.pesquisaUsuarioPorId("");
		});
		assertThrows(NullPointerException.class,		()->{
			this.controller.pesquisaUsuarioPorId(null);
		});
	}

	@Test
	void testPesquisaUsuarioPorNome() {
		assertEquals(this.controller.pesquisaUsuarioPorNome("Paulo"), "Paulo/13059752435, paulo.com, 93521561, status: doador | Paulo/12345678978, henrrique.com, 12345678, status: doador");
		assertEquals(this.controller.pesquisaUsuarioPorNome("embratel"), "embratel/12345678912345, embratel.com, 33632412, status: doador");
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.pesquisaUsuarioPorNome(null);
		});
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.pesquisaUsuarioPorNome("");
		});
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.pesquisaUsuarioPorNome("inexistente");
		});

	}


	@Test
	void testContemUsuario() {
		assertTrue(this.controller.contemUsuario("13059752435"));
		assertFalse(this.controller.contemUsuario("01234568790"));
	}

	@Test
	void testAtualizaUsuario() {
		assertEquals(this.controller.atualizaUsuario("13059752435", "juininho da dola", "vendedor@gmail", "4239875"), "juininho da dola/13059752435, vendedor@gmail, 4239875, status: doador");
		assertEquals(this.controller.atualizaUsuario("85274196374185", "transportadores de orgao", "mercado.negro@protegido", "021230"), "transportadores de orgao/85274196374185, mercado.negro@protegido, 021230, status: receptor");
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.atualizaUsuario(null, "jorge", "da dola", "3i947895");
		});

		assertThrows(IllegalArgumentException.class,		()->{
		this.controller.atualizaUsuario("", "jorge", "da dola", "3i947895");
		});

        assertThrows(NullPointerException.class,		()->{
            this.controller.atualizaUsuario("12312332112", "jorge", "da dola", "3i947895");
        });

    }

	@Test
	void testRemoveUsuario() {
		this.controller.removeUsuario("13059752435");
		assertFalse(this.controller.contemUsuario("13059752435"));
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.removeUsuario(null);
		});
		assertThrows(IllegalArgumentException.class,		()->{
		this.controller.removeUsuario("");
		});

        assertThrows(NullPointerException.class,		()->{
            this.controller.removeUsuario("1231233213211");
        });

    }

}
