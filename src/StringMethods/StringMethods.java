package StringMethods;

import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return s1;
		}

		return s2;
	}

	// if String s contains the word "underscores", change all of the spaces to
	// underscores
	public static String formatSpaces(String s) {
		if (s.contains("underscores"))
			return s.replace(" ", "_");

		return s;
	}

	// Return the name of the person whose LAST name would appear first if they were
	// in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		String s1trim = s1.trim();
		String s2trim = s2.trim();
		String s3trim = s3.trim();

		int s1i = s1trim.indexOf(" ") + 1;
		int s2i = s2trim.indexOf(" ") + 1;
		int s3i = s3trim.indexOf(" ") + 1;

		String s1char = s1trim.substring(s1i);
		String s2char = s2trim.substring(s2i);
		String s3char = s3trim.substring(s3i);

		if (s1char.compareTo(s2char) < 0 && s1char.compareTo(s3char) < 0) {
			return s1trim;
		}

		else if (s2char.compareTo(s1char) < 0 && s2char.compareTo(s3char) < 0) {
			return s2trim;
		}

		return s3trim;
	}

	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		int sum = 0;

		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (Character.isDigit(ch)) {
				sum += Character.getNumericValue(ch);
			}
		}
		return sum;
	}

	// Return the number of times String substring appears in String s
	public static int substringCount(String s, String substring) {
		int index = 0;
		int counter = 0;
		while (index >= 0) {
			index = s.indexOf(substring, index);
			if (index >= 0) {
				counter++;
				index += substring.length();
			}
		}
		return counter;

	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		return Utilities.encrypt(s.getBytes(), (byte) key);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte) key);
	}

	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		int counter = 0;
		String[] words = s.split(" ");
		for (String word : words) {
			if (word.endsWith(substring)) {
				counter++;
			}

		}
		return counter;
	}

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice
	public static int distance(String s, String substring) {
		
		int first = s.indexOf(substring);
		int last = s.lastIndexOf(substring);
		
		return last-first-substring.length();
	}

	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		String trim = s.toLowerCase().trim();
		String valid = "abcdefghijklmnopqrstuvwxyz";
		String norm = "";
		
		for(int i = 0; i < trim.length(); i++) {
			if(valid.indexOf(trim.charAt(i)) >= 0) {
				norm += trim.charAt(i);
			}
		}
		
		for(int i = 0; i < norm.length()/2; i++) {
			char left = norm.charAt(i);
			char right = norm.charAt(norm.length() -1 -i);
			if(left != right) {
				return false;
			}
		}
		return true;
	}

}

class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
