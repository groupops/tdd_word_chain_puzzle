package com.epam.word_chains;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import static org.junit.Assert.*;
public class WordChainsTest {

  /*
    0- Make sure that the start and end words are not the same
    1- Make sure that the start and end word are the same length
    2- Make sure that start word is in dictionary
    3- Make sure that end/final word is in dictionary
    4- when call nextWord then next word must have 1 letter different than prev word
    5- when call nextWord then next word changed letter must be in final word
    6- when call nextWord then it must be in the dictionary
    7- When get the final word with the algorithm, then make sure that it is equal to the final word defined in the input
   */

  private WordChainPuzzleSolver word_solver;

  @Before
  public void setUp() {
    word_solver = new WordChainPuzzleSolver();
  }

  @Test(expected = IllegalArgumentException.class)
  public void startAndEndWordsAreNotTheSame() {

    word_solver.getChain("cat", "cat");
  }

  @Test(expected = IllegalArgumentException.class)
  public void checkIfStartAndEndWordsAreTheSameLength() {
    word_solver.getChain("cat", "cats");
  }

  @Test(expected = WordIsNotInDictionaryException.class)
  public void startWordIsInDictionary() {
    word_solver.getChain("abcdefgh", "daughter");
  }

  @Test(expected = WordIsNotInDictionaryException.class)
  public void endWordIsInDictionary() {
    word_solver.getChain("daughter", "abcdefgh");
  }

  private boolean doWordsDifferInOneLetter(String word1, String word2) {
      int diff_count = 0;
      for (int i = 0; i < word1.length(); i++) {
          if (word1.charAt(i) != word2.charAt(i)) diff_count++;
      }
    return (diff_count == 1);
  }

  @Test
  public void everyWordInTheChainMustChangeOneLetterInPreviousWord() {
    List<String> chain = word_solver.getChain("cat", "dog");
    boolean wordsAreNotCorrectlySet = false;
    for (int i = 0; i < chain.size() - 1; i++) {
      if (!doWordsDifferInOneLetter(chain.get(i), chain.get(i + 1)))
        wordsAreNotCorrectlySet = true;
    }
    assertFalse("A word or more were not found to be different than the previous word by one letter", wordsAreNotCorrectlySet);
  }

  @Test
  public void chainMustNotBeNull() {
    List<String> chain = word_solver.getChain("cat", "dog");
    assertFalse("The chain must not be empty" ,chain.isEmpty());
  }
}
