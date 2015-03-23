package kodowanieH;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class KodowanieHuffmanaStatyczne {

	private static HashMap<String, String> slownik;
	private static HashMap<String, String> codeToChar;

	// This method assumes that the tree and dictionary are already built
	protected StringBuilder zakodujTekst(String tekstDoZakodowania) {
		// TODO !!! najpierw zrob na kartceS
		StringBuilder s = new StringBuilder();
	//	String zakodowanyTekst = new String();

		for (int i = 0; i < tekstDoZakodowania.length(); i++) {
			String pom = String.valueOf(tekstDoZakodowania.charAt(i));
			s.append(slownik.get(pom));
		//	zakodowanyTekst = zakodowanyTekst + slownik.get(pom);
		}
return s;
		//return zakodowanyTekst;
	}

	// This method assumes that the tree and dictionary are already built
	public static StringBuilder dekompresjaTekstuPrzyUzyciuSlownika(StringBuilder zakodowanyTekst) {
		String pom = new String();
		StringBuilder wynik = new StringBuilder();

		for (long i = 0; i < zakodowanyTekst.length(); i++) {
			pom += zakodowanyTekst.charAt((int) i);
			if (slownik.containsValue(pom)) {
				if(getZnakPrzypisanyDoKodu(slownik, pom).equals(null)){
					System.out.println("Byl null!!");
				}
				wynik.append(getZnakPrzypisanyDoKodu(slownik, pom));
				pom = new String();
			}
			if(i == zakodowanyTekst.length() -1 ){
				System.out.println("Prawie Koniec");
			}
		}
		System.out.println(wynik.length());
		return wynik;
	}

	private static String getZnakPrzypisanyDoKodu(
			HashMap<String, String> slownik2, String temp) {
		for (String k : slownik.keySet()) {
			if (slownik.get(k).equals(temp)) {
				return k;
			}
		}
		return null;

	}

	protected void wyswietlKodyZnakow(int i, ArrayList<Wezel> drzewoHuffmana,
			String kod) {
		// Wezel pierwszyE = drzewoHuffmana.get(0);
		// for (Wezel wezel : listaWezlow) {
		// if( pierwszyE.g.getWartosc().equals(wezel.getWartosc())){
		//
		// }
		// }

		if (drzewoHuffmana.get(i).getLewyPotomek() == null) {
			System.out.println(drzewoHuffmana.get(i).getWartosc() + "  " + kod);
		} else {
			wyswietlKodyZnakow(i + 1, drzewoHuffmana, kod + "0");
			// wyswietlKodyZnakow(i+1, drzewoHuffmana, kod + "0");
		}
	}

	protected ArrayList<Wezel> tworzPosortowanaListeWezlow(
			Map<String, Integer> czestotliwoscSymbolu) {
		ArrayList<Wezel> listaWezlow = new ArrayList<Wezel>();
		Set<Entry<String, Integer>> hashSet = czestotliwoscSymbolu.entrySet();
		for (Entry<String, Integer> entry : hashSet) {
			listaWezlow.add(new Wezel(entry.getKey(), entry.getValue()));
		}
		listaWezlow = sortowanieElementowWLiscieWezlow(listaWezlow);
		return listaWezlow;
	}

	protected ArrayList<Wezel> sortowanieElementowWLiscieWezlow(
			ArrayList<Wezel> listaW) {
		Collections.sort(listaW, new Comparator<Wezel>() {

			public int compare(Wezel o1, Wezel o2) {
				return o1.getCzestotliwosc() - o2.getCzestotliwosc();
			}
		});
		return listaW;
	}

	protected ArrayList<Wezel> tworzDrzewoHuffmana(ArrayList<Wezel> listaWezlow) {

		while (listaWezlow.size() > 1) {
			Wezel nowyWezel = new Wezel();

			Wezel pierwszyEl = listaWezlow.get(0);
			nowyWezel.setLewyPotomek(pierwszyEl);
			listaWezlow.remove(0);
			Wezel drugiEl = listaWezlow.get(0);
			nowyWezel.setPrawyPotomek(drugiEl);
			listaWezlow.remove(0);

			int nowyCzestotliwosc = pierwszyEl.getCzestotliwosc()
					+ drugiEl.getCzestotliwosc();
			// tworze nowy Wezel
			nowyWezel.setCzestotliwosc(nowyCzestotliwosc);
			nowyWezel.setWartosc(null);

			listaWezlow.add(nowyWezel);

			listaWezlow = sortowanieElementowWLiscieWezlow(listaWezlow);
		}
		return listaWezlow;
	}

	// Stwórz slownik(tablice) do zakodowania i odkodowania
	protected void zbudujTabeleKodow(Wezel root) {
		slownik = new HashMap<String, String>();
		codeToChar = new HashMap<String, String>();

		postorder(root, new String());
	}

	// przechodzenie drzewa z korzena-do-lisci
	protected void postorder(Wezel n, String s) {
		if (n == null)
			return;

		postorder(n.getLewyPotomek(), s + "0");
		postorder(n.getPrawyPotomek(), s + "1");

		String wartosc = n.getWartosc();
		if (wartosc != null) {
			// tylko gdy w lisciu sa waertosci (litery)
			System.out.println("\'" + wartosc + "\' -> " + s);
			slownik.put(wartosc, s);
			codeToChar.put(s, wartosc);
		}

	}

	protected Map<String, Integer> sortByValue(Map<String, Integer> map) {
		List<Integer> list = new LinkedList<Integer>();
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		Map<String, Integer> result = new LinkedHashMap<String, Integer>();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it
					.next();
			result.put(entry.getKey(), entry.getValue());
		}
		System.out.println("*****************************");
		Set<Entry<String, Integer>> hashSet = result.entrySet();
		for (Entry<String, Integer> entry : hashSet) {
			// String ascii = String.valueOf(Character.toChars((int)
			// entry.getKey()));
			System.out.println("Key=" + entry.getKey()
					+ ", Czestosc wystepowania=" + entry.getValue());
		}

		return result;
	}

	public static HashMap<String, String> getSlownik() {
		return slownik;
	}

}
