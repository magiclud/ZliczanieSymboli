package kodowanieH;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class CzytanieZapisywaniePlikow {

	private static int liczbaBitowPrzedKodowaniem;

	protected static String czytanietekstuZPliku(String nazwaPliku) {

		String wczytaneBajtyJakoString = wczytajStringZPliku(nazwaPliku);
		System.out
				.println("wczytaneBajtyJakoString " + wczytaneBajtyJakoString);
		String wczytanyTekst;
		char[] nowa = new char[wczytaneBajtyJakoString.length() - 1];
		if (wczytaneBajtyJakoString
				.charAt(wczytaneBajtyJakoString.length() - 1) == '\n') {

			wczytaneBajtyJakoString.getChars(0,
					wczytaneBajtyJakoString.length() - 1, nowa, 0);
			wczytanyTekst = String.valueOf(nowa);
		} else {
			wczytanyTekst = wczytaneBajtyJakoString;
		}
		// System.out.println("Liczba bitow przed kodowaniem = "+
		// wczytanyTekst.length());
		liczbaBitowPrzedKodowaniem = wczytanyTekst.length();

		int resultLength = wczytanyTekst.length() / 8;
		char[] result = new char[resultLength];
		for (int i = 0; i < resultLength; i++) {
			String sub = wczytanyTekst.substring(i * 8, (i + 1) * 8);
			result[i] = (char) Integer.parseInt(sub, 2);
		}
		String tekst = new String(result);

		return tekst;
	}

	protected static String wczytajStringZPliku(String nazwaPliku) {
		File file = new File(nazwaPliku);
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
		return byteArrayOutputStream.toString();
	}

	protected static String zapisywanieTekstuDoPliku(StringBuilder tekst) {
		String nazwaPlikuWyjsciowego = "out.txt";
		try {
			File file = new File(nazwaPlikuWyjsciowego);
			FileOutputStream fos = new FileOutputStream(file);
			// OutputStreamWriter osw = new OutputStreamWriter(fos);
			// Writer w = new BufferedWriter(osw);
			// w.append(tekst);
			// w.close();

			OutputStreamWriter writer = new OutputStreamWriter(
					new BufferedOutputStream(fos), "utf-8");

			for (int i = 0; i < tekst.length(); i++) {
				writer.write(tekst.charAt(i));
			}
			writer.flush();
			writer.close();
			fos.close();
		} catch (IOException e) {
			System.err.println("Problem z zapisem do pliku");
		}
		return nazwaPlikuWyjsciowego;

	}

	public static int getLiczbaBitowPrzedKodowaniem() {
		return liczbaBitowPrzedKodowaniem;
	}

	public int[] czestostliowscBajtowInnychPlikow(File file) {

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

	private int[] zamienUjemneNaDodatnie(byte[] byteArray) {
		int[] wczytaneBajty = new int[byteArray.length];
		for (int i = 0; i < byteArray.length; i++) {
			if (byteArray[i] < 0) {
				wczytaneBajty[i] = 256 + byteArray[i];
			} else {
				wczytaneBajty[i] = byteArray[i];
			}

		}
		return wczytaneBajty;
	}

	public static byte[] czytanieBajtowZPliku(String nazwaPliku) {
		File file = new File(nazwaPliku);

		ByteArrayOutputStream byteArrayOutputStream = null;
		InputStream inputStream = null;
		byte[] readedBytes = null;
		try {
			readedBytes = new byte[(int) file.length()];
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

		return readedBytes;

	}

	public static Map<Byte, Integer> zliczPowtarzajaceSieBajty(
			byte[] wcztaneBajty) {
		Map<Byte, Integer> bajt_LiczbaWyst = new HashMap<Byte, Integer>();

		for (byte x : wcztaneBajty) {// wczytuje po bajcie
			// System.out.println("Readed byte: " + readedByte);
			if (!bajt_LiczbaWyst.isEmpty() && bajt_LiczbaWyst.containsKey(x)) {
				bajt_LiczbaWyst.put(x, bajt_LiczbaWyst.get(x) + 1);
			} else {
				bajt_LiczbaWyst.put(x, 1);
			}
		}
		return bajt_LiczbaWyst;
	}

	public static String zapisSkompresowanegoTekstuDoPliku(
			byte[] skompresowanyTekst) {
		String nazwaPliku = "plikWyjsciowy.txt";

		BufferedOutputStream bos = null;
		try {
			FileOutputStream fs = new FileOutputStream(new File(nazwaPliku));
			bos = new BufferedOutputStream(fs);
			bos.write(skompresowanyTekst);
			bos.close();
			bos = null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nazwaPliku;
	}
}
