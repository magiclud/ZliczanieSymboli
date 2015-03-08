import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Entropia {

	Entropia() {

	}

	/*
	 * Entropia dla pojedyńczych symboli: H = suma od i=1 do n(pi * log 1/pi),
	 * pi = (#wystąpień symboli ai)/(#liczba wystąpień wszytskich symboli)
	 */
	public float entropiaDlaPojedynczychBajtow(int[] wczytaneBajty,
			Map<Integer, Integer> czestotliwoscSymbolu) {
		System.out.println("liczba wystąpień wszystkich symboli: "
				+ wczytaneBajty.length);

		ArrayList<Float> wagaSymbolu = new ArrayList<Float>();

		// Print the content of the hashMap
		Set<Entry<Integer, Integer>> hashSet = czestotliwoscSymbolu.entrySet();
		for (int i = 0; i < wczytaneBajty.length; i++) {
		}
		float entropia = 0;
		for (Entry entry : hashSet) {
			float waga = (int) entry.getValue() / (float) wczytaneBajty.length;
			wagaSymbolu.add(waga);
			entropia += waga * Math.log(1 / waga);
			// System.out.println("bajt = " + entry.getKey() + ", waga (pi)= "
			// + waga);
		}
		return entropia;
	}

	public float entropiaWarunkowana(int[][] czestotliowscSymboluPoSymbolu, Map<Integer, Integer> czestotliwoscSymbolu) {

		int czestotliwoscYwTekscie =0;
		int czestotliwoscXwTekscie =0;
		int y = 0;
		float infomracjaY = 0;
		float pi=0;
		float entropia=0;
		for (int i = 1; i < czestotliowscSymboluPoSymbolu.length; i++) {
			//System.out.println("Po symbolu i " + i + " występują: ");
			 y = 0;
			czestotliwoscYwTekscie =0;
			pi =0;
			for (int j = 1; j < czestotliowscSymboluPoSymbolu.length; j++) {

				
				if (czestotliowscSymboluPoSymbolu[i][j] != 0) {
				//	System.out.print("i: "+i+", j: "+j + " # = "
				//			+ czestotliowscSymboluPoSymbolu[i][j] + "; ");
					
					 czestotliwoscYwTekscie = czestotliwoscSymbolu.get(j);
				//	 System.out.println("# czestotliwoscYwTekscie " + czestotliwoscYwTekscie);
					 pi = (float)czestotliowscSymboluPoSymbolu[i][j]/(float)czestotliwoscYwTekscie;
					 infomracjaY  +=(float) ( pi*Math.log(1/pi));
					// System.out.println("Entropia H(Y) = "+ infomracjaY);
				}
				//infomracjaY  +=(float) ( pi*Math.log(1/pi));
				//System.out.println("Entropia H(Y) = "+ infomracjaY);

			}
			if(infomracjaY!=0){
				 czestotliwoscXwTekscie = czestotliwoscSymbolu.get(i);
				 int liczbaWszystkichWystapien = czestotliwoscSymbolu.size();
				 entropia +=  (float)czestotliwoscXwTekscie/(float)liczbaWszystkichWystapien *(float)infomracjaY;
			}
			
			infomracjaY =0;
			
			
		}
		//System.out.println("Entropia S = "+ entropia);
		return entropia;
		
	}

}
