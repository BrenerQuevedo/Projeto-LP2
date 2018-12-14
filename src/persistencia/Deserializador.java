package persistencia;

import facade.Facade;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Classe responsavel por ler informacoes sobre o sistema em um arquivo e construir o sistema com base nelas.
 * @author Iago Tito
 */
public class Deserializador implements Serializable {

    /**
     * Construtor do deserializador.
     */
    public Deserializador () {}

    /**
     * Inicia o sistema com base nos dados lidos do arquivo.
     * @return Retorna o sistema iniciado com os dados salvos.
     * @throws IOException Escecao lancada caso haja uma falha na leitura do arquivo.
     * @throws ClassNotFoundException Honestamente, nao sei porque essa excecao acontece.
     */
    public Facade iniciaSistema() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream("arquivos_sistema/dados.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Facade saida = (Facade) ois.readObject();
            ois.close();
            return saida;
        } catch (FileNotFoundException e) {
            return new Facade();
        }

    }

}
