package com.epam.tdd_word_chain_puzzle;

public class WordIsNotInDictionaryException extends RuntimeException {
  public WordIsNotInDictionaryException(String message) {
    super(message);
  }
}
