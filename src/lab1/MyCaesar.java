package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class MyCaesar {
	public static final char[] ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private int n;// shift steps (right shift)

	public MyCaesar(int shiftSteps) {
		this.n = shiftSteps % 26;

	}

	public char encryptChar(char c) {
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				int encryptedIndex = (i + n) % 26;
				if (encryptedIndex < 0) {
					encryptedIndex += 26;
				}
				return ALPHABET[encryptedIndex];
			}
		}
		return c;
	}

	public String encrypt(String input) {
		String result = "";
		for (int i = 0; i < input.length(); i++) {
			result += encryptChar(input.charAt(i));
		}
		return result;
	}

	public char decryptChar(char c) {
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				int encryptedIndex = (i - n) % 26;
				if (encryptedIndex < 0) {
					encryptedIndex += 26;
				}
				return ALPHABET[encryptedIndex];
			}
		}
		return c;
	}

	public String decrypt(String input) {
		String result = "";
		for (int i = 0; i < input.length(); i++) {
			result += decryptChar(input.charAt(i));
		}
		return result;
	}

	public void encrypt(String srcFile, String desFile) {
		File file = new File(srcFile);
		try {
			BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
			PrintWriter pw = new PrintWriter(desFile);
			String line = null;
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				pw.println(encrypt(line));
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void decrypt(String srcFile, String desFile) {
		File file = new File(srcFile);
		try {
			BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
			PrintWriter pw = new PrintWriter(desFile);
			String line = null;
			while (true) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				pw.println(decrypt(line));
			}
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MyCaesar myCaesar = new MyCaesar(-3);
		System.out.println(myCaesar.encryptChar('A'));
		System.out.println(myCaesar.encrypt("ABCa"));
		System.out.println(myCaesar.decrypt(myCaesar.encrypt("ABCa")));
		myCaesar.encrypt("src\\asset\\notYetEncrypted.txt", "src\\asset\\encrypted.txt");
		myCaesar.decrypt("src\\asset\\encrypted.txt", "src\\asset\\decoded.txt");

	}
}
