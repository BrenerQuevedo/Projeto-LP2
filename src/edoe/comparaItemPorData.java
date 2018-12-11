package edoe;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class comparaItemPorData implements Comparator<String> {


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