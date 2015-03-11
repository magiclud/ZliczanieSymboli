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

		// Print the content of the hashMap
		Set<Entry<Integer, Integer>> hashSet = czestotliwoscSymbolu.entrySet();
		for (int i = 0; i < wczytaneBajty.length; i++) {
		}
		float entropia = 0;
		for (Entry entry : hashSet) {
			float waga = (int) entry.getValue() / (float) wczytaneBajty.length;
			entropia += waga * Math.log(1 / waga);
			// System.out.println("bajt = " + entry.getKey() + ", waga (pi)= "
			// + waga);
		}
		return entropia;
	}

	public double entropiaWarunkowana(int[] wczytaneBajty,
			int[][] czestotliowscSymboluPoSymbolu,
			Map<Integer, Integer> czestotliwoscSymbolu) {

		// Print the content of the hashMap
		Set<Entry<Integer, Integer>> hashSet = czestotliwoscSymbolu.entrySet();
		for (int i = 0; i < wczytaneBajty.length; i++) {
		}
		double entropia = 0;
		double informacjaY = 0;
		for (Entry entry : hashSet) {

			for (Entry entry2 : hashSet) {

				if (czestotliowscSymboluPoSymbolu[(int) entry.getKey()][(int) entry2
						.getKey()] != 0.0) {
					double pi = (double) czestotliowscSymboluPoSymbolu[(int) entry
							.getKey()][(int) entry2.getKey()]
							/ (int) entry.getValue();
					informacjaY += pi * Math.log(1 / pi) / Math.log(2);
				}
			}
			double waga = (int) entry.getValue()
					/ (double) wczytaneBajty.length;
			// entropia += waga * Math.log(1 / waga);
			entropia += waga * informacjaY;
			informacjaY = 0;
		}
		return entropia;

	}
}
