import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class Czestotliwosc {

	private String nazwaPliku = "Skany096.jpg";

	// Key - byte, Value - liczba wystąpień
	Map<Integer, Integer> bajt_LiczbaWyst = new HashMap<Integer, Integer>();

	public Czestotliwosc(String nazwaPliku) {
		this.nazwaPliku = nazwaPliku;
	}

	public Map<Integer, Integer> getBajt_LiczbaWyst() {
		return bajt_LiczbaWyst;
	}

	protected int[][] czestotslioscWystepowaniaSymboluPoDanymSymbolu(
			int[] wczytaneBajty) {
		int[][] czestotliwosc = new int[256][256];
		System.out.println("WczytaneBajty wartosc: "+wczytaneBajty[0]);
		czestotliwosc[0][wczytaneBajty[0]] = 1;
		for (int i = 1; i < wczytaneBajty.length; i++) {
			int a = wczytaneBajty[i - 1];
			int b = wczytaneBajty[i];

			// System.out.println("a: "+ a + "b: "+ b);
			czestotliwosc[a][b] += 1;
			System.out.println("i:" + a + ", j: " + b + ",  # = "
					+ czestotliwosc[a][b]);

		}
		return czestotliwosc;
	}

	protected int[] czestostliowscBajtowInnychPlikow(File file) {

		ByteArrayOutputStream byteArrayOutputStream = null;
		InputStream inputStream = null;
		try {
			byte[] readedBytes = new byte[(int) file.length()];
			byteArrayOutputStream = new ByteArrayOutputStream();
			inputStream = new FileInputStream(file);
			int read = 0;
			while ((read = inputStream.read(readedBytes)) != -1) {
				byteArrayOutputStream.write(readedBytes, 0, read);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (byteArrayOutputStream != null)
					byteArrayOutputStream.close();
			} catch (IOException e) {
			}

			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
			}
		}
		int[] wczytaneBajty = zamienUjemneNaDodatnie(byteArrayOutputStream
				.toByteArray());
		return wczytaneBajty;
	}

	protected String znajdzRozszerzenie() {
		String[] fragmentyNazwyPliku = nazwaPliku.split("[.]");
		return fragmentyNazwyPliku[fragmentyNazwyPliku.length - 1];
	}

	protected void zliczPowtarzajaceSieBajty(int[] wcztaneBajty) {

		for (int x : wcztaneBajty) {// wczytuje po bajcie
			// System.out.println("Readed byte: " + readedByte);
			if (!bajt_LiczbaWyst.isEmpty() && bajt_LiczbaWyst.containsKey(x)) {
				bajt_LiczbaWyst.put(x, bajt_LiczbaWyst.get(x) + 1);
			} else {
				bajt_LiczbaWyst.put(x, 1);
			}
		}

		// Print the content of the hashMap
		Set<Entry<Integer, Integer>> hashSet = bajt_LiczbaWyst.entrySet();
		for (Entry entry : hashSet) {

			System.out.println("Key=" + entry.getKey()
					+ ", Czestosc wystepowania=" + entry.getValue());
		}
	}

	protected int[] czestosliwoscBajtowJPG(File file) {

		ByteArrayOutputStream byteArrayOutputStream = null;

		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
			byteArrayOutputStream.flush();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (byteArrayOutputStream != null)
					byteArrayOutputStream.close();
			} catch (IOException e) {
			}

		}
		int[] wczytaneBajty = zamienUjemneNaDodatnie(byteArrayOutputStream
				.toByteArray());
		return wczytaneBajty;
	}

	private int[] zamienUjemneNaDodatnie(byte[] byteArray) {
		int [] wczytaneBajty= new int[byteArray.length];
		for (int i = 0; i < byteArray.length; i++) {
			if (byteArray[i] < 0) {
				wczytaneBajty[i] = 256+byteArray[i];
			}else{
			wczytaneBajty[i] = byteArray[i];
			}

		}
		return wczytaneBajty;
	}

}
