package edoe;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class comparaData implements Comparator<String> {


	@Override
	public int compare(String o1, String o2) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date data1 = (Date) formatter.parse(o1.substring(0, 9));
			Date data2 = (Date) formatter.parse(o2.substring(0, 9));
			if(data1.compareTo(data2) == 0) {
				String subString1 = o1.substring(10, o1.length() - 1 );
				String subString2 = o2.substring(10, o2.length() - 1 );
				return subString1.compareTo(subString2);
			}
			return data1.compareTo(data2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("algum erro na data");
	}
}