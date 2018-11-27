import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edoe.Usuario;

class UsuarioTest {


	Usuario user1;
	Usuario user2;
	Usuario user3;
	Usuario userIgual1;
	
	@BeforeEach
	public void inicializaUsers() {
		this.user1 = new Usuario("paulo", "paulo.com", "40028922", "pedreiro", "88929002", "doador");
		this.user2 = new Usuario("brener", "brener.com", "88992233", "padeiro", "748347912", "receptor");
		this.user3 = new Usuario("iago", "iagootit.com" , "0987345", "atleta", "1234356", "doador");
		this.userIgual1 = new Usuario("joicy", "joicy.com", "434935845", "bailarina", "88929002", "receptor");
	}
	@Test
	void testSetNome() {
		fail("Not yet implemented");
	}

	@Test
	void testSetCelular() {
		fail("Not yet implemented");
	}

	@Test
	void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		assertEquals(this.user1.toString(),"wklfsdjf");
	}

}


