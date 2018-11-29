import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edoe.UsuarioController;

class UsuarioControllerTest {
	UsuarioController controller;
	
	@BeforeEach
	public void IniciaController() {
		controller = new UsuarioController();
		controller.cadastraDoador("Paulo cesar", "paulo.com", "93521561", "PESSOA_FISICA", "13059752435", "doador");
		controller.cadastraDoador("embratel", "embratel.com","33632412", "ASSOCIACAO", "12345678912345", "doador");
		controller.cadastraDoador("Paulo henrrique", "henrrique.com", "12345678", "PESSOA_FISICA", "12345678978", "doador");
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
	
		/*
		 * Excecoes para entradas nulas, vazias	ou invalidas
		 */
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador(null, "nomeNulo","000000" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("", "nomeVazio","000000" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador("email nulo", null ,"000000" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("email vazio", "","000000" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador("celular nulo", "celular nulo", null , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("celular vazio", "celular vazio","" , "PESSOA_FISICA", "00000000000", "receptor");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador("classe nula", "classe nula", "34893905" , null , "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("classe vazia", "classe vazia","3984590485" , "", "00000000000", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("classe invalida", "classe invalida","3984590485" , "FACCAO_CRIMINOSA", "00000000000", "receptor");
		});
		
		assertThrows(NullPointerException.class,		()->{
			this.controller.cadastraDoador("id nulo", "id nulo","3984590485" , "PESSOA_FISICA", null, "receptor");
		});
	
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("id vazio", "id vazio", "3984590485" , "PESSOA_FISICA", "", "receptor");
		});

		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("id invalido", "id invalido", "3984590485" , "PESSOA_FISICA", "1245", "receptor");
		});
		
		assertThrows(IllegalArgumentException.class,		()->{
			this.controller.cadastraDoador("id invalido", "id invalido", "3984590485" , "ASSOCIACAO", "00000", "receptor");
		});
		
	} 

	@Test
	void testPesquisaUsuarioPorId() {
		assertEquals(this.controller.pesquisaUsuarioPorId("13059752435"), "Paulo cesar/13059752435, paulo.com, 93521561, status: doador");
		assertEquals(this.controller.pesquisaUsuarioPorId("12345678912345"), "embratel/12345678912345, embratel.com, 33632412, status: doador");
		assertEquals(this.controller.pesquisaUsuarioPorId("12345678978"), "Paulo henrrique/12345678978, henrrique.com, 12345678, status: doador");	
	}

	@Test
	void testPesquisaUsuarioPorNome() {
		fail("Not yet implemented");
	}

	@Test
	void testValidaReceptor() {
		fail("Not yet implemented");
	}

	@Test
	void testValidaDoador() {
		fail("Not yet implemented");
	}

	@Test
	void testContemUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testAtualizaUsuario() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveUsuario() {
		fail("Not yet implemented");
	}

}
