package com.epam.tdd_word_chain_puzzle;

import java.util.ArrayList;
import java.util.List;

public class WordChainPuzzleSolver {

  public List<String> getChain(String startWord, String endWord) {
    validateInputWords(startWord, endWord);
    List<String> chain = new ArrayList<>();
    String currentWord = startWord;

    while (true) {
      chain.add(currentWord);
      String nextWord = nextWord(currentWord, endWord);
      ///TODO throw exception if there is no chain from startWOrd to endWord
      if (nextWord == null || nextWord.equals(endWord))
        break;
      currentWord = nextWord;
    }
    ///TODO add endWord to the chain
    return chain;
  }

  private String nextWord(String currentWord, String endWord) {
    for (int i = 0; i < currentWord.length(); i++) {
      StringBuilder currentWordMutable = new StringBuilder(currentWord);
      if (currentWord.charAt(i) != endWord.charAt(i)) {
        String nextWord = currentWordMutable
            .replace(i, i + 1, String.valueOf(endWord.charAt(i))).toString();
        if (isWordInDictionary(nextWord)) {
          return nextWord;
        }
      }
    }
    return null;
  }

  private void validateInputWords(String startWord, String endWord) {
    if (startWord.equals(endWord))
      throw new IllegalArgumentException(
          "The start and end words are the same word");
    if (startWord.length() != endWord.length())
      throw new IllegalArgumentException(
          "The start and end word do not have the same length");

    if (!isWordInDictionary(startWord))
      throw new WordIsNotInDictionaryException(
          "The start word is not in the dictionary");

    if (!isWordInDictionary(endWord))
      throw new WordIsNotInDictionaryException(
          "The end word is not in the dictionary");

  }

  private boolean isWordInDictionary(String word) {
    WordDictionary dictionary = new FileWordDictionary();
    return dictionary.containsWord(word);
  }
}
