Analysis of the memory usage and time complexity

Prerequisites of the application:

- All words are lower case consisting only of alpha characters.
- The dictionary contains many words with the same stem (prefix)

Given the requirements of the application I have decided to implement the project using a prefix tree.
A prefix tree is a data structure that efficiently stores strings with common prefixes. Each node
contains a maximum of n (26 in the English alphabet) children. A path exists between two nodes if
they share the same prefix,

for example:

	   Root
	 /     \
	 C     C
	/|\    |
	A      O
   /|\     |
  R* T*    T*
  |
  S*


What makes the prefix tree perform well in these situations is that the cost of looking up a word
or prefix is fixed and dependent only on the number of characters in the word and not on the size
of the vocabulary.

In this specific example, most words in the dictionary are less than 20 characters, so it would
take a maximum of 20 steps to find a word that is in the vocabulary, while any negative answer,
i.e. the word or prefix that is not in the tree, can be obtained in at most 20 steps as well.

Given the characteristics of a prefix tree the following complexities can be determined. 

Time complexity:

A single tree can can be traversed in O(n) time, where n is the length of the longest word in the
dictionary (20), repeat that m times for the number of distinct letters, hence O(m*n). 

Memory complexity:

O(m*n) with n being the number of distinct letters in all dictionary words and m being the length
of the longest word. Additional memory is used by my algorithm to store the word on the terminal
node itself plus memory for holding the scrabble score per word.

The runtime and memory complexities very much depend on the words in the dictionary. In a typical
dictionary many words share common prefixes making a prefix tree the optimal choice for storing
such words. However, if a large number of words do not share common prefixes the prefix tree ends
up a very expensive structure as the following example illustrates.

Shared prefixes:
    C
    |
    A
    |
    R*
    |
    S*
No shared prefixes:
    A
  / | \
 B  C  D
/|\
XYZ

The worst case complexity if no words share any prefixes is O(n^m). However, given all the short circuit
conditions in the implementation and the fact that a lot of English word share a common prefix the worst
case complexity is only theoretical.
