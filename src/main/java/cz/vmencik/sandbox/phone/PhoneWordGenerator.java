package cz.vmencik.sandbox.phone;

import java.util.*;
import java.io.*;

/**
 * Translate phone numbers to word-sequences that map to the number
 * according to the standard mapping on the keypad (or dial).
 * 
 * @author Josh Bloch (attributed to by Martin Odersky)
 * @see http://skillsmatter.com/podcast/home/scala-intro
 *
 */
public class PhoneWordGenerator {

	/** Map from digit-sequences to the word(s) they map to, e.g. "5278" -> ["kart", "last"] */
	private final Map<String, List<String>> wordsForNumber = new HashMap<String, List<String>>();
	
	public PhoneWordGenerator(String dictFileName) throws IOException {
		String[] lettersForDigit = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		
		char[] digitForLetter = new char[26];
		for (int digit = 0; digit < 10; digit++) {
			String letters = lettersForDigit[digit];
			for (int i = 0; i < letters.length(); i++)
				digitForLetter[letters.charAt(i) - 'a'] = (char) (digit + '0');
		}
		
		// Process dictionary into a map from digit-sequence to the word(s) it maps to
		BufferedReader br = new BufferedReader(new FileReader(dictFileName));
		String word = null;
		try {
			while ((word = br.readLine()) != null) {
				char[] digitSeq = word.toCharArray(); // Initially contains letter sequence
				
				for (int i = 0; i < digitSeq.length; i++)
					digitSeq[i] = digitForLetter[digitSeq[i] - 'a']; // Translate letter to digit
				
				String number = new String(digitSeq);
				
				List<String> words = wordsForNumber.get(number);
				if (words == null)
					wordsForNumber.put(number, words = new ArrayList<String>());
				words.add(word);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("Dictionary contains bogus word:" + word);
		} finally {
			br.close();
		}
	}
	
	/** Returns a shared instance with standard English dictionary. Initialized on first use */
	synchronized public static PhoneWordGenerator getInstance() throws IOException {
		return pwg != null ? pwg : (pwg = new PhoneWordGenerator("C:/Scala/z_files/java_words.txt"));
	}
	
	private static PhoneWordGenerator pwg;
	
	/** Returns a list of the sentences represented by the specified number */
	public List<String> phoneWords(String number) {
		return sentences(number.toCharArray(), 0);
	}
	
	/** Returns a list of sentences represented by digits(start: digits.length) */
	private List<String> sentences(char[] digits, int start) {
		// Start with any single words represented by entire digit sequence
		List<String> result = new ArrayList<String>(words(digits, start, digits.length - start));
		
		// Then add any "sentences" consisting of two or more words
		for (int firstSpace = start + 1; firstSpace < digits.length; firstSpace++)
			result.addAll(cartesianProduct(words(digits, start, firstSpace - start),
			                               sentences(digits, firstSpace)));
		return result;
	}
	
	/** Returns a list of single words represented by digits(offset : offset + len) */
	private List<String> words(char[] digits, int offset, int len) {
		List<String> result = wordsForNumber.get(new String(digits, offset, len));
		return result != null ? result : Collections.<String>emptyList();
	}
	
	/** Returns all sentences consisting of a prefix followed by a suffix */
	private static List<String> cartesianProduct(List<String> prefixes, List<String> suffixes) {
		List<String> result = new ArrayList<String>();
		for (String prefix : prefixes)
			for (String suffix : suffixes)
				result.add(prefix + " " + suffix);
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		PhoneWordGenerator pwg = PhoneWordGenerator.getInstance();
		System.out.println(pwg.phoneWords("528276257"));
	}
}
