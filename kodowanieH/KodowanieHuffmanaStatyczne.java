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
	private static HashMap<String, Byte> codeToChar;
	private static int dlTekstuZakodowanego = 0;

	// This method assumes that the tree and dictionary are already built
	protected StringBuilder zakodujTekst(String tekstDoZakodowania) {
		StringBuilder s = new StringBuilder();
		// String zakodowanyTekst = new String();

		for (int i = 0; i < tekstDoZakodowania.length(); i++) {
			String pom = String.valueOf(tekstDoZakodowania.charAt(i));
			s.append(slownik.get(pom));
			// zakodowanyTekst = zakodowanyTekst + slownik.get(pom);
		}
		return s;
		// return zakodowanyTekst;
	}

	// This method assumes that the tree and dictionary are already built
	public static StringBuilder dekompresjaTekstuPrzyUzyciuSlownika(
			StringBuilder zakodowanyTekst) {
		String pom = new String();
		StringBuilder wynik = new StringBuilder();

		for (long i = 0; i < zakodowanyTekst.length(); i++) {
			pom += zakodowanyTekst.charAt((int) i);
			if (slownik.containsValue(pom)) {
				if (getZnakPrzypisanyDoKodu(pom) == -1) {
					System.out.println("Byl null!!");
				}
				// System.out.println("it: "+ i+", wynik "+ wynik);
				wynik.append(getZnakPrzypisanyDoKodu(pom));
				pom = new String();
			}
		}
		System.out.println(wynik.length());
		return wynik;
	}

	private static Byte getZnakPrzypisanyDoKodu(String temp) {
		for (Byte k : slownik.keySet()) {
			if (slownik.get(k).equals(temp)) {
				return k;
			}
		}
		return 0;

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

	// protected ArrayList<Wezel> tworzPosortowanaListeWezlow(
	// Map<Integer, Integer> czestotliwoscSymbolu) {
	// ArrayList<Wezel> listaWezlow = new ArrayList<Wezel>();
	// Set<Entry<Integer, Integer>> hashSet = czestotliwoscSymbolu.entrySet();
	// for (Entry<Integer, Integer> entry : hashSet) {
	// listaWezlow.add(new Wezel(entry.getKey(), entry.getValue()));
	// }
	// listaWezlow = sortowanieElementowWLiscieWezlow(listaWezlow);
	// return listaWezlow;
	// }

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
		codeToChar = new HashMap<String, Byte>();

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

	public static HashMap<Byte, String> getSlownik() {
		return slownik;
	}

	public StringBuilder zakodujTekstByte(byte[] wczytaneBajtyZPliku) {
		StringBuilder s = new StringBuilder();
		// String zakodowanyTekst = new String();

		for (int i = 0; i < wczytaneBajtyZPliku.length; i++) {
			byte pom = wczytaneBajtyZPliku[i];
			s.append(slownik.get(pom));

			// zakodowanyTekst = zakodowanyTekst + slownik.get(pom);
		}
		System.out.println("WczytaneBajtyZPliku 0: " + wczytaneBajtyZPliku[0]
				+ ", zakodujTekst " + slownik.get(wczytaneBajtyZPliku[0]));
		return s;
		// return zakodowanyTekst;
	}

	public ArrayList<Wezel> tworzPosortowanaListeBytow(
			Map<Byte, Integer> czestotliwoscBytow) {

		ArrayList<Wezel> listaWezlow = new ArrayList<Wezel>();
		Set<Entry<Byte, Integer>> hashSet = czestotliwoscBytow.entrySet();
		for (Entry<Byte, Integer> entry : hashSet) {
			listaWezlow.add(new Wezel(entry.getKey(), entry.getValue()));
		}
		listaWezlow = sortowanieElementowWLiscieWezlow(listaWezlow);
		return listaWezlow;
	}

	public byte[] kompresuj(StringBuilder zakodowanyT) {
		String a = new String(zakodowanyT);
		System.out.println("Zakodowany 0: " + a.charAt(0));
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
				// System.out.println(" "+ sub);

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
		dlTekstuZakodowanego = resultLength;
		System.out.println("Result byte 0: " + resultByte[0]
				+ ", Result byte 1: " + resultByte[1]);
		return resultByte;
	}

	public static ArrayList<Byte> dekompresjaPrzyUzyciuSlownika(byte[] zakodowanyTekst) {
		String pom = new String();
		ArrayList<Byte> wynik = new ArrayList<Byte>();
//		System.out.println("zakodowanyTekst 0: " + zakodowanyTekst[0]
//				+ ", zakodowanyTekst 0: " + zakodowanyTekst[1]);
//
		String bity = "";
		for (int i = 0; i < zakodowanyTekst.length; i++) {
			byte x = zakodowanyTekst[i];
			// System.out.println(System.out.println(Integer.toBinaryString(fromByte(x)));
			for (int k = 0; k < 8; k++) {
//				System.out.println("Bit" + k + ": " + ((x >> k) & 1));
				bity += ((x >> k) & 1);

			}
			bity = new StringBuffer(bity).reverse().toString();
			if (slownik.containsValue(bity)) {
				// System.out.println("it: "+ i+", wynik "+ wynik);
				wynik.add(getZnakPrzypisanyDoKodu(bity));
				bity = "";
			}
		}
//		System.out.println("Bity " + bity);
//		byte[] byteArray = new byte[wynik.size()];
//		for(int i =0; i<wynik.size(); i++){
//			byteArray[i]=wynik.get(i);
//		}
//		String s = new String(byteArray);
//		System.out.println("WYNIK: "+ s);
		return wynik;
		
	}

	// System.out.println("x "+ x + ", string value "+ new
	// String(x.getBytes()));
	// pom += new String(zakodowanyTekst[i]);
	// if (slownik.containsValue(pom)) {
	// if (getZnakPrzypisanyDoKodu(pom) == -1) {
	// System.out.println("Byl null!!");
	// }
	// // System.out.println("it: "+ i+", wynik "+ wynik);
	// wynik.append(getZnakPrzypisanyDoKodu(pom));
	// pom = new String();
	// }
	// }
	// System.out.println(wynik.length());
	// // return wynik;
	//
	// }
	public static BitSet fromByte(byte b) {
		BitSet bits = new BitSet(8);
		for (int i = 0; i < 8; i++) {
			bits.set(i, (b & 1) == 1);
			b >>= 1;
		}
		return bits;
	}
}
