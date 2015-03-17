package kodowanieH;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class CzytanieZapisywaniePlikow {

	protected static String czytanietekstuZPliku(String nazwaPliku) {

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

		String wczytaneBajtyJakoString = byteArrayOutputStream.toString();

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
		int resultLength = wczytanyTekst.length() / 8;
		char[] result = new char[resultLength];
		for (int i = 0; i < resultLength; i++) {
			String sub = wczytanyTekst.substring(i * 8, (i + 1) * 8);
			result[i] = (char) Integer.parseInt(sub, 2);
		}
		String tekst = new String(result);

		return tekst;
	}

}
