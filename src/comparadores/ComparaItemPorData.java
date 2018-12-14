package comparadores;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

/**
 * Classe comparadora de dois itens pela sua data.
 * @author Paulo Mateus.
 */
public class ComparaItemPorData implements Comparator<String> {

    /**
     * Compara duas Strings que representam a data de doacao de um item para saber quem vem antes em uma ordenacao.
     * @param o1 String 1 a ser comaprada.
     * @param o2 String 2 a ser comaprada.
     * @return 1 caso a String 1 venha antes na ordenacao, -1 caso a String 1 venha depois e 0 caso nao haja diferenca no criterio de ordenacao.
     */
	@Override
	public int compare(String o1, String o2) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date data1 =  formatter.parse(o1.substring(0, 9));
			Date data2 =  formatter.parse(o2.substring(0, 9));
			if(data1.compareTo(data2) == 0) {
				String[] subString1 = o1.substring(21, o1.length() - 1 ).split("");
				String[] subString2 = o2.substring(21, o2.length() - 1 ).split("");
				return subString1[2].compareTo(subString2[2]);
			}
			return data1.compareTo(data2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("algum erro na data");
	}
}