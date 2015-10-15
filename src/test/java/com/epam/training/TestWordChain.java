package com.epam.training;

import com.epam.traning.WordChain;
import com.epam.traning.WordChainException;
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
    private static WordChain wordChain;

    @BeforeClass
    public static void setup() {
        wordChain = new WordChain("D:\\words");
    }

    @Test
    public void checkIfFileExist() throws Exception {
        assertTrue(wordChain.fileExist("D:\\words"));
    }

    @Test
    public void checkIfFileNotExist() throws Exception {
        assertFalse(wordChain.fileExist("D:\\words\\word"));
    }

    @Test
    public void checkIfFileIsLoadedIntoSet() throws Exception {
        assertFalse(wordChain.getWords().isEmpty());
    }

    @Test
    public void checkIfAllWordsWereLoaded() throws Exception {
        assertEquals(WORDS_COUNT, wordChain.getWords().size());
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
        Assert.assertTrue(('d' != word.charAt(0)
                || 'o' != word.charAt(1)
                || 'g' != word.charAt(2)
        ));
    }
}
