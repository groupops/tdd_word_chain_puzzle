package com.epam.traning;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmytro_Ulanovych on 10/13/2015.
 */
public class WordChain {
    private final String dictionaryPath;
    private Set<String> words;

    public WordChain(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
        loadFile(dictionaryPath);
    }

    public List<String> getChain(String start, String end) throws WordChainException {
        if (start.length() != end.length())
            throw new WordChainException("Number of letters should be equal");
        List<String> chain = new ArrayList<String>();
        chain.add(start);
        String currentWord = start;
        while (!currentWord.equals(end)) {
            String nextWord = getNextMatchWord(currentWord, end);
            if (nextWord != null) {
                chain.add(nextWord);
            }
            currentWord = nextWord;
        }

        chain.add(end);
        return chain;
    }

    private String getNextMatchWord(String currentWord, String endWord) {
        char[] currentWordChars = currentWord.toCharArray();
        char[] endWordChars = endWord.toCharArray();
        char[] currentWordsCharsCopy = currentWord.toCharArray();
        for (int i = 0; i < currentWordChars.length; i++) {
            if (currentWordChars[i] != endWordChars[i]) {
                currentWordChars[i] = endWordChars[i];
            } else {
                continue;
            }
            if (words.contains(String.valueOf(currentWordChars))) {
                return String.valueOf(currentWordChars);
            } else {
                currentWordChars = currentWordsCharsCopy;
            }
        }
        return null;
    }

    private void loadFile(String dictionaryPath) {
        if (fileExist(dictionaryPath)) {
            try {
                this.words = new HashSet<String>(Files.readAllLines(Paths.get(dictionaryPath)));
            } catch (IOException e) {
                //EATING IT OMONONONO
            }
        }
    }


    public boolean fileExist(String fileName) {
        return Files.exists(Paths.get(fileName));
    }


    public Set<String> getWords() {
        return this.words;
    }
}
