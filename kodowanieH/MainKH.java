package kodowanieH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class MainKH {

	public static void main(String[] args) {
		// / 1. wczytuję tekst, 2. tworzę mapę 3.
		ObliczeniaNaDanychZPliku obliczeniaNaDanychZPliku = new ObliczeniaNaDanychZPliku();
		String nazwaPliku = "plik1Lista3.txt";

		String wczytanytekst = CzytanieZapisywaniePlikow
				.czytanietekstuZPliku(nazwaPliku);
		System.out.println(wczytanytekst);

		Map<String, Integer> czestotliwoscSymbolu = obliczeniaNaDanychZPliku
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
		HashMap<String, String> slownik = KodowanieHuffmanaStatyczne.getSlownik();
		wyswietlHashMape(slownik);

		String zakodowanyT = huffman.zakodujTekst(wczytanytekst);
		System.out.println("Tekst zakodowany na  " + zakodowanyT.length()
				+ " bitach");
		System.out.println("Zakodowany tekst to: \n " + zakodowanyT);
		
		//zapisz do pliku zakodowany tekst
		String nazwaPlikuWyjsciowego = CzytanieZapisywaniePlikow.zapisywanieTekstuDoPliku(zakodowanyT);

		//deszyfrowane tekstu z pliku przy uzycia slownika 
		String wczytanyZaszyfrowanyTekst = CzytanieZapisywaniePlikow.wczytajStringZPliku(nazwaPlikuWyjsciowego);
		String odszyfrownyTekst = huffman.dekompresjaTekstuPrzyUzyciuSlownika(wczytanyZaszyfrowanyTekst);
        System.out.println("Oryginalny tekst składał się z " + odszyfrownyTekst.length() + " znaków");
        System.out.println(odszyfrownyTekst);
 
		
		
		// wyznacz entropie kodowania // wynik przy poprzednim zadaniu dla tych danych1.9722469794234414
        double entropia = obliczeniaNaDanychZPliku.entropiaDlaPojedynczychBajtow(wczytanytekst, czestotliwoscSymbolu);
        System.out.println(entropia);
		//srednia dlugosc kodowania
       int srDlKodowania =  obliczeniaNaDanychZPliku.wyznaczSreniaDlugoscKodowania(czestotliwoscSymbolu,slownik);
       System.out.println("Srednia dl. kodowania = "+ srDlKodowania);
		//stopien kompresji
      double stKompresji =  obliczeniaNaDanychZPliku.wyznaczStopienKompresji(CzytanieZapisywaniePlikow.getLiczbaBitowPrzedKodowaniem(), zakodowanyT.length());
       System.out.println("Stopien kompresji = "+stKompresji );
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
