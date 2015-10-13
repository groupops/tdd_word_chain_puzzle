package com.epam.word_chains;

/**
 * Created by Adam_Bronowicki on 10/13/2015.
 */
public class WordIsNotInDictionaryException extends RuntimeException {
  public WordIsNotInDictionaryException(String message) {
    super(message);
  }
}
