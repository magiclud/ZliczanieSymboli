package kodowanieH;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class WartosciDlaDanychZPliku {
	
	public Map<String, Integer> zliczPowtarzajaceSieChary(String wczytanyTekst) {
		Map<String, Integer> char_LiczbaWyst = new HashMap<String, Integer>();

		for (int i =0; i<wczytanyTekst.length(); i++) {// wczytuje po bajcie
			String x = String.valueOf(wczytanyTekst.charAt(i));
			if (!char_LiczbaWyst.isEmpty() && char_LiczbaWyst.containsKey(x)) {
				char_LiczbaWyst.put(x, char_LiczbaWyst.get(x) + 1);
			} else {
				char_LiczbaWyst.put(x, 1);
			}
		}
		return char_LiczbaWyst;
	}
	

}
