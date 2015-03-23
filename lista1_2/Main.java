package lista1_2;
import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Main {

	private static String nazwaPliku = "test1.txt";

	public static void main(String[] args) {

		// ***************** lista 1 **********************//
		Czestotliwosc czestotliwosc = new Czestotliwosc(nazwaPliku);
		String rozszerzenie = czestotliwosc.znajdzRozszerzenie();
		File file = new File(nazwaPliku);
		int[] wczytaneBajty;

		if (rozszerzenie.equals("jpg")) {
			wczytaneBajty = czestotliwosc.czestosliwoscBajtowJPG(file);
			
		} else {
			wczytaneBajty = czestotliwosc.czestostliowscBajtowInnychPlikow(file);
			//System.out.println("# wszystkich wczytanych bajtów: " + wczytaneBajty.length);
			
		}
		czestotliwosc.zliczPowtarzajaceSieBajty(wczytaneBajty);
		czestotliwosc.wyswietlCzestotliwoscBajtow();
		
		Map<Integer, Integer> czestotliwoscSymbolu = czestotliwosc
				.getBajt_LiczbaWyst();

		
		int[][] czestotliowscSymboluPoSymbolu = czestotliwosc.czestotslioscWystepowaniaSymboluPoDanymSymbolu(wczytaneBajty);
		
		// **************** lista 2 ************************//

		Entropia entropia = new Entropia();
		double etropia1 = entropia.entropiaDlaPojedynczychBajtow(
				wczytaneBajty, czestotliwoscSymbolu);
		System.out.println("Entropia dla pojedyńczych symboli: "+etropia1);
		double entropiaWarunkowa = entropia.entropiaWarunkowana(wczytaneBajty, czestotliowscSymboluPoSymbolu, czestotliwoscSymbolu);
		System.out.println("Entopia warunkowana: "+entropiaWarunkowa);
		
		double roznicaEntropii = Math.abs(etropia1-entropiaWarunkowa);
		System.out.println("Różnica = "+roznicaEntropii);

	}
}
