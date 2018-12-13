package persistencia;

import facade.Facade;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class Deserializador implements Serializable {

    public Deserializador () {}

    public Facade iniciaSistema() throws IOException, ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream("arquivos_sistema/dados.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Facade saida = (Facade) ois.readObject();
            ois.close();
            return saida;
        } catch (FileNotFoundException e) {
            return new Facade();
        }

    }

}
