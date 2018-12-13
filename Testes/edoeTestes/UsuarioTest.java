package edoeTestes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edoe.Usuario;

class UsuarioTest {


	Usuario user1;
	Usuario user2;
	Usuario user3;
	
	@BeforeEach
	public void inicializaUsers() {
		this.user1 = new Usuario("paulo", "paulo.com", "40028922", "pedreiro", "88929002", "doador");
		this.user2 = new Usuario("brener", "brener.com", "88992233", "padeiro", "748347912", "receptor");
		this.user3 = new Usuario("iago", "iagootito.com" , "0987345", "atleta", "1234356", "doador");
		
	}
	@Test
	void testSetNome() {
		this.user1.setNome("joao");
		assertEquals(this.user1.getNome(), "joao");
		this.user3.setNome("pedro");
		assertEquals(this.user3.getNome(), "pedro");
	}

	@Test
	void testSetCelular() {
		this.user1.setCelular("000000");;
		assertEquals(this.user1.toString(),"paulo/88929002, paulo.com, 000000, status: doador");
		this.user3.setCelular("456789");
		assertEquals(this.user3.toString(),"iago/1234356, iagootito.com, 456789, status: doador" );
	}

	@Test
	void testSetEmail() {
		this.user1.setEmail("globo.com");
		assertEquals(this.user1.toString(), "paulo/88929002, globo.com, 40028922, status: doador");
		this.user3.setEmail("rabanete.com");
		assertEquals(this.user3.toString(), "iago/1234356, rabanete.com, 0987345, status: doador");
	}

	@Test
	void testToString() {
		assertEquals(this.user1.toString(),"paulo/88929002, paulo.com, 40028922, status: doador");
		assertEquals(this.user2.toString(), "brener/748347912, brener.com, 88992233, status: receptor");
		assertEquals(this.user3.toString(), "iago/1234356, iagootito.com, 0987345, status: doador");
	}
	
}


