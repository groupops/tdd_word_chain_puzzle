package com.epam.word_chains;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DictionaryTest {

  @Test
  public void shouldFindWordInDictionary(){
    Dictionary dictionary = new Dictionary();
    boolean isWordPresent = dictionary.containsWord("Adam");

    assertThat(isWordPresent, is(true));
  }

}
