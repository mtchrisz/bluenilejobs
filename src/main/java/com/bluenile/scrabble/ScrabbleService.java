package com.bluenile.scrabble;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

@Component
public class ScrabbleService
{
	/**
	 * Dictionary of words
	 */
	private final Dictionary dictionary;

	/**
	 * Constructor
	 */
	public ScrabbleService ()
	{
		this.dictionary = readDictionary();
	}

	/**
	 * Reads the dictionary of words
	 */
	private final Dictionary readDictionary()
	{
		final Dictionary dic = new Dictionary();

		final ClassLoader classLoader = getClass().getClassLoader();
		final File file = new File(classLoader.getResource("wordsEn.txt").getFile());

		// read the dictionary words
		try (final FileReader in =  new FileReader(file)) {
			try (final BufferedReader br = new BufferedReader(in)) {

				String word = null;

				// convert each word to lower case...
				while ((word = Utils.toLowerCase(br.readLine())) != null) {
					if (word.length() > 0) {
						dic.add(word.replaceAll("[^a-z]", ""));
					}
				}
			}
		}
		catch (final Throwable e) {
			e.printStackTrace();
		}
		return dic;
	}

	/**
	 * Returns a list of words that can be spelled from the given set of letters.
	 * It is sorted by its Scrabble point value.
	 *
	 * @param letters The letters to form words from
	 * @return A sorted set of words
	 */
	public SortedSet<String> getWords (String letters)
	{
		if (letters == null || letters.length() == 0) {
			return new TreeSet<String>();
		}

		// convert the letters array to lower case and remove all non-alpha characters
		return this.dictionary.search(Utils.toLowerCase(letters).replaceAll("[^a-z]", ""));
	}

}
