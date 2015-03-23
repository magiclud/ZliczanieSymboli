package kodowanieH;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import lista1_2.Czestotliwosc;

public class MainKH {

	public static void main(String[] args) {
		int liczbaBitowPrzedKodowaniem=0;
		// / 1. wczytuję tekst, 2. tworzę mapę 3.
		ObliczeniaNaDanychZPliku obliczeniaNaDanychZPliku = new ObliczeniaNaDanychZPliku();
		KodowanieHuffmanaStatyczne huffman = new KodowanieHuffmanaStatyczne();
	//	String nazwaPliku = "plik1Lista3.txt";

		/**
		 * dla inych plikow - zwyklych tekstowych 
		 * 
		 * **/
	//	String nazwaPliku = "PanTadeusz.txt";
		String nazwaPliku = "test1.txt";
		Czestotliwosc czestotliwosc = new Czestotliwosc(nazwaPliku);
		File file =new File(nazwaPliku);
		int[] wczytaneBajty = czestotliwosc.czestostliowscBajtowInnychPlikow(file);
		czestotliwosc.zliczPowtarzajaceSieBajty(wczytaneBajty);
		Map<Integer, Integer> czestotliwoscSymbolu = czestotliwosc.getBajt_LiczbaWyst();
//		String wczytanytekst =CzytanieZapisywaniePlikow.wczytajStringZPliku(nazwaPliku) ;
//		liczbaBitowPrzedKodowaniem = wczytanytekst.length();
		/***/
		// 1. wczytuje bajty z pliku 
		byte[] wczytaneBajtyZPliku = CzytanieZapisywaniePlikow.czytanieBajtowZPliku(nazwaPliku);
		Map<Byte, Integer> czestotliwoscBytow=CzytanieZapisywaniePlikow.zliczPowtarzajaceSieBajty(wczytaneBajtyZPliku);
		////////////// end 1.
		// 2. tworz wezly - posortowane
		ArrayList<Wezel> listaWezlow = huffman
				.tworzPosortowanaListeBytow(czestotliwoscBytow);
		// wyswietlenie wezlow posortowanych
		for (Wezel wezel : listaWezlow) {
			System.out.println("Wezel " + wezel.getWartosc() + ", # = "
					+ wezel.getCzestotliwosc());
		}
		//////////////end 2. 
		// 3. tworz drzewo Huff
		
		
		
		
		/*****/
//		String wczytanytekst = CzytanieZapisywaniePlikow
//				.czytanietekstuZPliku(nazwaPliku);
//liczbaBitowPrzedKodowaniem = CzytanieZapisywaniePlikow.getLiczbaBitowPrzedKodowaniem();
//		Map<String, Integer> czestotliwoscSymbolu = obliczeniaNaDanychZPliku
//				.zliczPowtarzajaceSieChary(wczytanytekst);
		
		
		
//		 System.out.println("Częstotliwość występowania symboli:");
		
		//wyswietlHashMape(czestotliwoscSymbolu);
//		ArrayList<Wezel> listaWezlow = huffman
//				.tworzPosortowanaListeWezlow(czestotliwoscSymbolu);
		// wyswietlenie wezlow posortowanych
//		for (Wezel wezel : listaWezlow) {
//			System.out.println("Wezel " + wezel.getWartosc() + ", # = "
//					+ wezel.getCzestotliwosc());
//		}
		// //////////////////////////////////////

		ArrayList<Wezel> drzewoHuffmana = huffman
				.tworzDrzewoHuffmana(listaWezlow);

		// korzen w drzewie Huffmana
		Wezel korzen = drzewoHuffmana.get(0);
		// tworzenie tabel do kodowania i odkodowania - slownik
		huffman.zbudujTabeleKodow(korzen);
		
		HashMap<Byte, String> slownik = KodowanieHuffmanaStatyczne.getSlownik();
		System.out.println("Wyswietl slownik");
		wyswietlHashMape(slownik);

//		StringBuilder zakodowanyT = huffman.zakodujTekst(wczytanytekst); //TODO odkomentuj
		StringBuilder zakodowanyT = huffman.zakodujTekstByte(wczytaneBajtyZPliku); //TODO zakomentuj dla pliku z zadania
		System.out.println("Tekst zakodowany na  " + zakodowanyT.length()
				+ " bajtach");
		byte[] skompresowanyTekst = huffman.kompresuj(zakodowanyT);
		System.out.println("Tekst skompresowany na  " + skompresowanyTekst.length
				+ " bajtach");
		//zapis do pliku skompesowanego tekstu
		String nazwaPlikuWyjsciowego = CzytanieZapisywaniePlikow.zapisSkompresowanegoTekstuDoPliku(skompresowanyTekst);
		//deszyfrowane tekstu z pliku przy uzycia slownika
		byte[] wczytaneBajtyZZakodowanegoPliku = CzytanieZapisywaniePlikow.czytanieBajtowZPliku(nazwaPlikuWyjsciowego);
		ArrayList<Byte> odkodowaneBajty = KodowanieHuffmanaStatyczne.dekompresjaPrzyUzyciuSlownika(wczytaneBajtyZZakodowanegoPliku);
		 System.out.println("Oryginalny tekst składał się z " + odkodowaneBajty.size() + " znaków");
		
//		System.out.println("Zakodowany tekst to: \n " + zakodowanyT);
	
//		//zapisz do pliku zakodowany tekst
		
//		String nazwaPlikuWyjsciowego = CzytanieZapisywaniePlikow.zapisywanieTekstuDoPliku(zakodowanyT);
//
//		//deszyfrowane tekstu z pliku przy uzycia slownika 
//		StringBuilder sb = new StringBuilder();
//		sb.append(CzytanieZapisywaniePlikow.wczytajStringZPliku(nazwaPlikuWyjsciowego));
//		StringBuilder odszyfrownyTekst = KodowanieHuffmanaStatyczne.dekompresjaTekstuPrzyUzyciuSlownika(sb);
//        System.out.println("Oryginalny tekst składał się z " + odszyfrownyTekst.length() + " znaków");
//       // System.out.println(odszyfrownyTekst);
// 
//		
//		
//		// wyznacz entropie kodowania // wynik przy poprzednim zadaniu dla tych danych1.9722469794234414
//        double entropia = obliczeniaNaDanychZPliku.entropiaDlaPojedynczychBajtow(wczytanytekst, czestotliwoscSymbolu);
//        System.out.println(entropia);
//		//srednia dlugosc kodowania
//       int srDlKodowania =  obliczeniaNaDanychZPliku.wyznaczSreniaDlugoscKodowania(czestotliwoscSymbolu,slownik);
//       System.out.println("Srednia dl. kodowania = "+ srDlKodowania);
//		//stopien kompresji
//      double stKompresji =  obliczeniaNaDanychZPliku.wyznaczStopienKompresji(liczbaBitowPrzedKodowaniem, zakodowanyT.length());
//       System.out.println("Stopien kompresji = "+stKompresji );
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
