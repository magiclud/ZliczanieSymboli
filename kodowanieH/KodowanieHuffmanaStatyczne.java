package kodowanieH;

import java.util.ArrayList;
import java.util.BitSet;
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

	private static HashMap<Byte, String> slownik;

	protected ArrayList<Wezel> tworzPosortowanaListeBytow(
			Map<Byte, Integer> czestotliwoscBytow) {

		ArrayList<Wezel> listaWezlow = new ArrayList<Wezel>();
		Set<Entry<Byte, Integer>> hashSet = czestotliwoscBytow.entrySet();
		for (Entry<Byte, Integer> entry : hashSet) {
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

			int nowaCzestotliwosc = pierwszyEl.getCzestotliwosc()
					+ drugiEl.getCzestotliwosc();
			// tworze nowy Wezel
			nowyWezel.setCzestotliwosc(nowaCzestotliwosc);
			// nowyWezel.setWartosc(Byte.valueOf(-1 + ""));

			listaWezlow.add(nowyWezel);

			listaWezlow = sortowanieElementowWLiscieWezlow(listaWezlow);
		}
		return listaWezlow;
	}

	// Stwórz slownik(tablice) do zakodowania i odkodowania
	protected void zbudujTabeleKodow(Wezel root) {
		slownik = new HashMap<Byte, String>();
		postorder(root, new String());
	}

	// przechodzenie drzewa z korzena-do-lisci
	protected void postorder(Wezel n, String s) {
		if (n == null)
			return;

		postorder(n.getLewyPotomek(), s + "0");
		postorder(n.getPrawyPotomek(), s + "1");

		byte wartosc = n.getWartosc();
		if (wartosc != 0) {
			// tylko gdy w lisciu sa wartosci (litery)
			System.out.println("\'" + wartosc + "\' -> " + s);
			slownik.put(wartosc, s);
		}
	}

	protected StringBuilder zakodujTekstByte(byte[] wczytaneBajtyZPliku) {
		StringBuilder s = new StringBuilder();
		// String zakodowanyTekst = new String();

		for (int i = 0; i < wczytaneBajtyZPliku.length; i++) {
			byte pom = wczytaneBajtyZPliku[i];
			s.append(slownik.get(pom));

			// zakodowanyTekst = zakodowanyTekst + slownik.get(pom);
		}
		// System.out.println("WczytaneBajtyZPliku 0: " + wczytaneBajtyZPliku[0]
		// + ", zakodujTekst " + slownik.get(wczytaneBajtyZPliku[0]));
		return s;
		// return zakodowanyTekst;
	}

	protected byte[] kompresuj(StringBuilder zakodowanyT) {
		String a = new String(zakodowanyT);
		// System.out.println("Zakodowany 0: " + a.charAt(0));

		StringBuilder tekstDoKompresji = new StringBuilder();
		tekstDoKompresji = zakodowanyT;
		byte[] resultByte;
		int[] result;

		int resultLength = tekstDoKompresji.length() / 8;
		int resultModulo = tekstDoKompresji.length() % 8;

		if (resultModulo != 0) {
			resultLength += 1;
			resultByte = new byte[resultLength];
			result = new int[resultLength];
			for (int i = 0; i < resultLength; i++) {
				if (i == resultLength - 1) {
					for (int j = resultModulo; j < 8; j++) {
						tekstDoKompresji.append("0");
					}
				}
				String sub = tekstDoKompresji.substring(i * 8, (i + 1) * 8);

				result[i] = (char) Integer.parseInt(sub, 2);
				resultByte[i] = (byte) result[i];
			}
		} else {
			resultByte = new byte[resultLength];
			result = new int[resultLength];
			for (int i = 0; i < resultLength; i++) {
				String sub = tekstDoKompresji.substring(i * 8, (i + 1) * 8);
				result[i] = (char) Integer.parseInt(sub, 2);
				resultByte[i] = (byte) result[i];
			}
		}
		return resultByte;
	}

	protected ArrayList<Byte> dekompresjaPrzyUzyciuSlownika(
			byte[] zakodowanyTekst) {
		StringBuilder pom = new StringBuilder();
		ArrayList<Byte> wynik = new ArrayList<Byte>();
		StringBuilder trescOdkodowana = new StringBuilder();

		String bity = "";
		for (int i = 0; i < zakodowanyTekst.length; i++) {
			byte x = zakodowanyTekst[i];
			for (int k = 0; k < 8; k++) {
				// System.out.println("Bit" + k + ": " + ((x >> k) & 1));
				bity += ((x >> k) & 1);

			}
			bity = new StringBuffer(bity).reverse().toString();
			// System.out.println("Bit" + bity );

			pom.append(bity);
			bity = "";
		}

		for (int j = 0; j < pom.length(); j++) {
			bity += pom.charAt(j);
			if (slownik.containsValue(bity)) {
				byte x = getZnakPrzypisanyDoKodu(bity);
				wynik.add(x);
				String sy = new Character((char) x).toString();
				trescOdkodowana.append(new Character((char) x).toString());
				bity = "";
			}
		}
		// System.out.println("Tresc "+ trescOdkodowana.toString());
		return wynik;
	}

	private Byte getZnakPrzypisanyDoKodu(String temp) {
		for (Byte k : slownik.keySet()) {
			if (slownik.get(k).equals(temp)) {
				return k;
			}
		}
		return 0;
	}

	protected void wyswietlKodyZnakow(int i, ArrayList<Wezel> drzewoHuffmana,
			String kod) {

		if (drzewoHuffmana.get(i).getLewyPotomek() == null) {
			System.out.println(drzewoHuffmana.get(i).getWartosc() + "  " + kod);
		} else {
			wyswietlKodyZnakow(i + 1, drzewoHuffmana, kod + "0");
		}
	}

	public HashMap<Byte, String> getSlownik() {
		return slownik;
	}

}
