package com.bluenile.scrabble;

import java.util.Objects;
import java.util.SortedSet;

import org.junit.Assert;
import org.junit.Test;

public class ScrabbleServiceTest {

	@Test
	public void testGetWordsReturnsAListOfWords()
	{
		final ScrabbleService scrabbleService = new ScrabbleService();

		// test getWords on random string
		final SortedSet<String> words1 = scrabbleService.getWords("abc");
		Assert.assertNotNull(words1);
		Assert.assertEquals(words1.size(), 6);

		// test getWords on permutation
		final SortedSet<String> words2 = scrabbleService.getWords("cba");
		Assert.assertTrue(Objects.equals(words1, words2));

		// test case insensitivity
		final SortedSet<String> words3 = scrabbleService.getWords("ABc");
		Assert.assertTrue(Objects.equals(words1, words3));

		// test invalid case
		final SortedSet<String> words4 = scrabbleService.getWords("zzz");
		Assert.assertTrue(words4.isEmpty());

		// test invalid corner case(s)
		final SortedSet<String> words5 = scrabbleService.getWords("");
		Assert.assertTrue(words5.isEmpty());

		final SortedSet<String> words6 = scrabbleService.getWords(null);
		Assert.assertTrue(words6.isEmpty());
	}
	
	@Test
	public void testScrabbleScores ()
	{
		Assert.assertEquals(Utils.score("hat"), 6);
		Assert.assertEquals(Utils.score("code"), 7);
		Assert.assertEquals(Utils.score("antidisestablishmenatarianism"), 39);
	}
	
	public void testSpectrum ()
	{
		int[] spectrum = Utils.spectrum("hat");
		Assert.assertEquals(spectrum['h' - 'a'], 1);
		Assert.assertEquals(spectrum['a' - 'a'], 1);
		Assert.assertEquals(spectrum['t' - 'a'], 1);
		Assert.assertEquals(spectrum['s' - 'a'], 0);
	}
}
