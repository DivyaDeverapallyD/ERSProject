package com.ers.Util;

/**
 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
 * system created in the Middle East.
 * 
 * The Atbash cipher is a simple substitution cipher that relies on transposing
 * all the letters in the alphabet such that the resulting alphabet is
 * backwards. The first letter is replaced with the last letter, the second with
 * the second-last, and so on.
 * 
 * An Atbash cipher for the Latin alphabet would be as follows:
 * 
 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
 * very weak cipher because it only has one possible key, and it is a simple
 * monoalphabetic substitution cipher. However, this may not have been an issue
 * in the cipher's time.
 * 
 * Ciphertext is written out in groups of fixed length, the traditional group
 * size being 5 letters, and punctuation is excluded. This is to make it harder
 * to guess things based on word boundaries.
 * 
 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
 *
 */
public class AtbashCipher {

	/**
	 * Question 13
	 * 
	 * @param string
	 * @return
	 */
	
	
public static void main(String[] args) {
		
	
	System.out.println(encode("divya@17"));
	System.out.println(decode("wrebz 17"));
	
	}
	public static String encode(String string) {
		// TODO Write an implementation for this method declaration
		
		String result = "";
		int c = 0;
		// String res = "";
		for (int i = 0; i < string.length(); i++) {

			if (Character.isLetter(string.charAt(i))) {
				result += (char) ('z' + 'a' - Character.toLowerCase(string.charAt(i)));
				c++;
			}

			if (Character.isDigit(string.charAt(i))) {
				result += string.charAt(i);
				c++;

			}
			if (c == 5) {
				result += " ";
				c = 0;
			}
		}

		return result.trim();

	}

	/**
	 * Question 14
	 * 
	 * @param string
	 * @return
	 */
	public static String decode(String string) {
		// TODO Write an implementation for this method declaration
		String result = "";
		// String res = "";
		for (int i = 0; i < string.length(); i++) {

			if (Character.isLetter(string.charAt(i))) {
				result += (char) ('z' + 'a' - Character.toLowerCase(string.charAt(i)));
				// System.out.println(string.charAt(i));
			}

			if (Character.isDigit(string.charAt(i))) {
				result += string.charAt(i);

				// System.out.println(string.charAt(i));
			}

		}

		return result;

	}
	
	
	
	
}
