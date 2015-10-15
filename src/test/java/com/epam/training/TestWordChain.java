package com.epam.training;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Dmytro_Ulanovych on 10/13/2015.
 */
public class TestWordChain {
    private static final int WORDS_COUNT = 99171;
    private static final String DICTIONARY_PATH = "/words";
    private static final String WRONG_DICTIONARY_PATH = "/wrong/dictionary/path";
    private static final String UNEXISTING_WORD_IN_DICTIONARY = "unexist_word_in_dictionary";

    private static WordChain wordChain;


    @BeforeClass
    public static void setup() {
        wordChain = new WordChain(DICTIONARY_PATH);
    }

    @Test
    public void checkIfFileExist() throws Exception {
        assertTrue(wordChain.fileExist(DICTIONARY_PATH));
    }

    @Test
    public void checkIfFileNotExist() throws Exception {
        assertFalse(wordChain.fileExist(WRONG_DICTIONARY_PATH));
    }

    @Test
    public void checkIfAllWordsWereLoaded() throws Exception {
        assertEquals(WORDS_COUNT, wordChain.getWords().size());
    }

    @Test(expected = WordChainException.class)
    public void exceptionWhenInputWordNotExistInDictionary() throws Exception {
        wordChain.getChain(UNEXISTING_WORD_IN_DICTIONARY, UNEXISTING_WORD_IN_DICTIONARY);
    }

    @Test(expected = WordChainException.class)
    public void exceptionWhenPassNull() throws Exception {
        wordChain.getChain(null, null);
    }

    @Test
    public void checkIfGetChainOfSameParamsGivesTwoWords() throws Exception {
        List<String> words = wordChain.getChain("dog", "dog");
        assertEquals(2, words.size());
    }

    @Test(expected = WordChainException.class)
    public void checkIfExceptionThrownForDifferentWordLength() throws Exception {
        wordChain.getChain("dog", "crocodile");
    }

    @Test
    public void checkIfOneLetterChanged() throws Exception {
        List<String> words = wordChain.getChain("dog", "cat");
        String word = words.get(1);
        assertTrue(('d' != word.charAt(0) || 'o' != word.charAt(1) || 'g' != word.charAt(2)));
    }

}
