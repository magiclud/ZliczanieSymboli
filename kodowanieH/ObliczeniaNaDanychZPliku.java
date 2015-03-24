package kodowanieH;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ObliczeniaNaDanychZPliku {

	/*
	 * Entropia dla pojedyńczych symboli: H = suma od i=1 do n(pi * log 1/pi),
	 * pi = (#wystąpień symboli ai)/(#liczba wystąpień wszytskich symboli)
	 */
	public double entropiaDlaPojedynczychBajtow(byte[] wczytaneBajtyZPliku,
			Map<Byte, Integer> czestotliwoscBytow) {
		System.out.println("# wszystkich symboli: " + wczytaneBajtyZPliku.length);

		// Print the content of the hashMap
		Set<Entry<Byte, Integer>> hashSet = czestotliwoscBytow.entrySet();
		for (int i = 0; i < wczytaneBajtyZPliku.length; i++) {
		}
		double entropia = 0;
		for (Entry<Byte, Integer> entry : hashSet) {
			double waga = (int) entry.getValue()
					/ (double) wczytaneBajtyZPliku.length;
			entropia += waga * Math.log(1 / waga);
			// System.out.println("bajt = " + entry.getKey() + ", waga (pi)= "
			// + waga);
		}
		return entropia;
	}

	public int wyznaczSreniaDlugoscKodowania(Map<Byte, Integer> czestotliwoscSymbolu, HashMap<Byte, String> slownik) {

		int sredniaDlKodowania =0;
		Set<Entry<Byte, Integer>> hashSet = czestotliwoscSymbolu.entrySet();
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
