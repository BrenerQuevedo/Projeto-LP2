package persistencia;

import facade.Facade;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe responsavel por gravar as informacoes sobre o sistema em um arquivo.
 * @author Iago Tito
 */
public class Serializador implements Serializable {

    /**
     * Construtor do Serializador.
     */
    public Serializador () {}

    /**
     * Salva os dados do sistema em um arquivo.
     * @param facade Sistema a ter os dados salvos.
     * @throws IOException Escecao lancada caso haja uma falha na manipulacao do arquivo.
     */
    public void fechaSistema(Facade facade) throws IOException {
        FileOutputStream fos = new FileOutputStream("arquivos_sistema/dados.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(facade);
        oos.close();
    }

}
