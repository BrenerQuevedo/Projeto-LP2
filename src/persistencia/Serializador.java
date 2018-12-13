package persistencia;

import facade.Facade;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Serializador implements Serializable {

    public Serializador () {}

    public void fechaSistema(Facade facade) throws IOException {
        FileOutputStream fos = new FileOutputStream("arquivos_sistema/dados.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(facade);
        oos.close();
    }

}
