package exceptions;

/**
 * Excecao especifica de quando um item nao existe no sistema.
 * @author Paulo Mateus
 */
public class ItemInexistenteException extends Exception {
    /**
     * Lanca uma excecao de item inexistente.
     * @param msg Mensagem do erro.
     */
	public ItemInexistenteException(String msg) {
		super(msg);
	}
}
