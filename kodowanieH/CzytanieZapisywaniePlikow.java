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
