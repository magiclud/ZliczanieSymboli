package kodowanieH;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class MainKH {

	public static void main(String[] args) {
		// / 1. wczytuję tekst, 2. tworzę mapę 3.
		WartosciDlaDanychZPliku wartosciDanychZPliku = new WartosciDlaDanychZPliku();
		String nazwaPliku = "plik1Lista3.txt";

		String wczytanytekst = CzytanieZapisywaniePlikow
				.czytanietekstuZPliku(nazwaPliku);
		System.out.println(wczytanytekst);

		Map<String, Integer> czestotliwoscSymbolu = wartosciDanychZPliku
				.zliczPowtarzajaceSieChary(wczytanytekst);
		KodowanieHuffmanaStatyczne huffman = new KodowanieHuffmanaStatyczne();
		System.out.println("Częstotliwość występowania symboli:");
		wyswietlHashMape(czestotliwoscSymbolu);
		System.out.println("______________________________");

		ArrayList<Wezel> listaWezlow = huffman
				.tworzPosortowanaListeWezlow(czestotliwoscSymbolu);
		// wyswietlenie wezlow posortowanych
		for (Wezel wezel : listaWezlow) {
			System.out.println("Wezel " + wezel.getWartosc() + ", # = "
					+ wezel.getCzestotliwosc());
		}
		// //////////////////////////////////////

		ArrayList<Wezel> drzewoHuffmana = huffman
				.tworzDrzewoHuffmana(listaWezlow);

		// korzen w drzewie Huffmana
		Wezel korzen = drzewoHuffmana.get(0);
		// tworzenie tabel do kodowania i odkodowania - slownik
		huffman.zbudujTabeleKodow(korzen);
		System.out.println("Wyswietl slownik");
		wyswietlHashMape(KodowanieHuffmanaStatyczne.getSlownik());

		String zakodowanyT = huffman.zakodujTekst(wczytanytekst);
		System.out.println("Tekst zakodowany na  " + zakodowanyT.length()
				+ " bitach");
		System.out.println("Zakodowany tekst to: \n " + zakodowanyT);
	}

	public static void wyswietlHashMape(Map result) {
		System.out.println("*****************************");
		Set<Entry<Integer, Integer>> hashSet = result.entrySet();
		for (Entry entry : hashSet) {
			// String ascii = String.valueOf(Character.toChars((int)
			// entry.getKey()));
			System.out.println("Key=" + entry.getKey() + ", Value="
					+ entry.getValue());
		}
	}
}
