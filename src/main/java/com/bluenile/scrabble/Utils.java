package com.bluenile.scrabble;
/**
 * Utility class
 *
 * @author Christian Zou
 * @since Nov 20, 2017 9:54:03 PM
 */
public final class Utils
{
	/*
    Points | Letters
   -------+-----------------------------
      1   | A, E, I, L, N, O, R, S, T, U
      2   | D, G
      3   | B, C, M, P
      4   | F, H, V, W, Y
      5   | K
      8   | J, X
     10   | Q, Z
	 */

	/**
	 * Array for all letters [1-26] with value being the score for the letter
	 */
	private final static int[] LETTER_POINTS = new int[] { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

	/**
	 * Compute the score of a word depending on the points for each letter.
	 *
	 * @param str {@link String} -- input string to be scored
	 * @return {@link Integer} -- score for input word
	 */
	public final static int score (final String str)
	{
		int score = 0;
		assert str != null;
		for (int i = 0; i < str.toCharArray().length; i++) {
			score += LETTER_POINTS[str.charAt(i) - 'a'];
		}
		return score;
	}

	/**
	 * Returns a {@link Array} array of integers representing the number of
	 * occurrences for each letter.
	 *
	 * @param str {@link String} -- string for which spectrum is to be determined
	 * @return {@link Array} -- array of integers representing the spectrum
	 */
	public final static int[] spectrum (final String str)
	{
		assert str != null;
		final int[] spectrum = new int[26];
		str.chars().forEach(m -> spectrum[m - 'a']++);
		return spectrum;
	}

	/**
	 * Converts all of the characters in this {@link String} to lower case.
	 *
	 * @param val {@link String} -- string that should be converted to lower case
	 * @return {@link String} -- string in lower case
	 */
	public final static String toLowerCase (final String val)
	{
		return val == null ? null : val.toLowerCase();
	}
}
