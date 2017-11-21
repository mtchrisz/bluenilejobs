package com.bluenile.scrabble;
import java.sql.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Dictionary class implemented as a Trie with children representing words
 * starting with a specific letter.
 *
 * @author Christian Zou
 * @since Nov 20, 2017 9:54:03 PM
 */
public final class Dictionary
{
	/**
	 * Root of the Trie
	 */
	private Node root = new Node();

	/**
	 * Map of words to score
	 */
	private final Map<String, Integer> scoreMap = new HashMap<>();

	/**
	 * Constructor
	 */
    public Dictionary()
    {
        super();
    }

    /**
     * Adds a new {@link String} word to the dictionary
     *
     * @param word {@link String} -- new word to be added to dictionary
     */
    public final void add (final String word)
    {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            int currentChar = word.charAt(i) -'a';
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new Node());
            }
            node = node.get(currentChar);
        }
        node.setWord(word);

        // compute the score for the word and store it in a map
        scoreMap.put(word, Utils.score(word));
    }

    /**
     * Return the {@link List} list of words that can be spelled with given
     * spectrum of letters starting from the specified {@link Node}.
     *
     * @param spectrum {@link Array} --
     *   spectrum, representing the frequency of each letter in the word
     * @param nd {@link Node} --
     *   staring node from where to search for words given the spectrum
     * @return {@link List} --
     *   list of words that can be spelled with given spectrum of letters
     *   starting from the specified {@link Node}
     */
    private final SortedSet<String> search (final int[] spectrum, final Node nd)
    {
    	final SortedSet<String> retval = new TreeSet<>(new Comparator<String>() {

			@Override
			public final int compare (final String o1, final String o2)
			{
				final int score1 = scoreMap.get(o1).intValue();
				final int score2 = scoreMap.get(o2).intValue();
				int delta = score2 - score1;
				
				if (delta == 0) {
					// same score, sort alphabetically
					return o1.compareTo(o2);
				}
				return delta;
			}
		});

    	// found a terminal leaf...
    	if (nd.getWord() != null) {

    		// .. add the word
    		retval.add(nd.getWord());
    	}

        Node node = nd;
        for (int i = 0; i < spectrum.length; i++) {
        	if (spectrum[i] > 0) {

        		// search if there's a word starting with the current letter
        		final Node child = node.get(i);
        		if (child != null) {

        			// found a word starting with letter
        			final int[] s = Arrays.copyOf(spectrum, spectrum.length);

        			// reduce letter count for letter in spectrum
            		s[i]--;

            		// depth-first search the subtree for reduced spectrum
            		retval.addAll(search(s, child));
        		}
        	}
        }

    	return retval;
    }

    /**
     * Return the {@link List} list of words that can be spelled with the
     * letters from the given word.
     *
     * @return {@link List} --
     *   list of words that can be spelled with the letters from
     *   the given word
     */
    public final SortedSet<String> search (final String letters)
    {
    	return search(Utils.spectrum(letters), this.root);
    }

    /**
     * Single node in a dictionary trie
     *
     * @author Christian Zou
     * @since Nov 20, 2017 9:54:03 PM
     */
    private final class Node
    {
    	/**
    	 * Links to children for words starting with letter a-z
    	 */
        private Node[] links = new Node[26];

        /**
         * Word for terminal leaves
         */
        private String word = null;

        /**
         * Constructor
         */
        public Node()
        {
            super();
        }

        /**
         * Returns <code>true</code> if node has a child for the specified letter.
         *
         * @param ch {@link Integer} --
         *   letter for which the existence of children should be determined
         * @return {@link Boolean} --
         *   returns <code>true</code> if node has a child for the specified
         *   letter, <code>false</code> otherwise
         */
        public final boolean containsKey (final int ch)
        {
            return links[ch] != null;
        }

        /**
         * Returns the {@link Node} child for the given letter.
         *
         * @param ch {@link Integer} --
         *   letter for which the children should be returned
         * @return {@link Node} -- child for the given letter
         */
        public final Node get (final int ch)
        {
            return links[ch];
        }

        /**
         * Adds a new {@link Node} child for the given letter.
         *
         * @param ch {@link Integer} -- letter for which child is to be added
         * @param node {@link Node} -- child for the given letter
         */
        public final void put (final int ch, final Node node)
        {
            links[ch] = node;
        }

        /**
         * Sets the {@link String} word on the terminal node
         *
         * @param word {@link String} -- word to be set on the terminal node
         */
        public final void setWord (final String word)
        {
            this.word = word;
        }

        /**
         * Returns the {@link String} word set on the terminal node
         *
         * @return {@link String} -- word set on the terminal node
         */
        public final String getWord ()
        {
        	return this.word;
        }
    }
}
