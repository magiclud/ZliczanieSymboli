package kodowanieH;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ObliczeniaNaDanychZPliku {

	public Map<String, Integer> zliczPowtarzajaceSieChary(String wczytanyTekst) {
		Map<String, Integer> char_LiczbaWyst = new HashMap<String, Integer>();

		for (int i = 0; i < wczytanyTekst.length(); i++) {// wczytuje po bajcie
			String x = String.valueOf(wczytanyTekst.charAt(i));
			if (!char_LiczbaWyst.isEmpty() && char_LiczbaWyst.containsKey(x)) {
				char_LiczbaWyst.put(x, char_LiczbaWyst.get(x) + 1);
			} else {
				char_LiczbaWyst.put(x, 1);
			}
		}
		return char_LiczbaWyst;
	}

	/*
	 * Entropia dla pojedyńczych symboli: H = suma od i=1 do n(pi * log 1/pi),
	 * pi = (#wystąpień symboli ai)/(#liczba wystąpień wszytskich symboli)
	 */
	public double entropiaDlaPojedynczychBajtow(String wczytanyTekst,
			Map<Integer, Integer> czestotliwoscSymbolu) {
		System.out.println("# wszystkich symboli: " + wczytanyTekst.length());

		// Print the content of the hashMap
		Set<Entry<Integer, Integer>> hashSet = czestotliwoscSymbolu.entrySet();
		for (int i = 0; i < wczytanyTekst.length(); i++) {
		}
		double entropia = 0;
		for (Entry<Integer, Integer> entry : hashSet) {
			double waga = (int) entry.getValue()
					/ (double) wczytanyTekst.length();
			entropia += waga * Math.log(1 / waga);
			// System.out.println("bajt = " + entry.getKey() + ", waga (pi)= "
			// + waga);
		}
		return entropia;
	}

	public int wyznaczSreniaDlugoscKodowania(Map<Integer, Integer> czestotliwoscSymbolu, HashMap<Integer, String> slownik) {

		int sredniaDlKodowania =0;
		Set<Entry<Integer, Integer>> hashSet = czestotliwoscSymbolu.entrySet();
		for (Entry entry : hashSet) {
			// czestotliwosc(symbolu)* dlugosc kodu symbolu
//			System.out.println("Key=" + entry.getKey() + ", Value="
//					+ entry.getValue()+"    *** "+slownik.get(entry.getKey())+"    # "+slownik.get(entry.getKey()).length() );
			sredniaDlKodowania+= (int)entry.getValue() * slownik.get(entry.getKey()).length();
			
		}
		return  sredniaDlKodowania;
	}

	public double wyznaczStopienKompresji(int dlWczytanegoTekstu, int dlZakodowanegotekstu) {
		System.out.println(dlWczytanegoTekstu +" : "+ dlZakodowanegotekstu);
		return (double)dlZakodowanegotekstu/(double)dlWczytanegoTekstu * 100;
	}

}
