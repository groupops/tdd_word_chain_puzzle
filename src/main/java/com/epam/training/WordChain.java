package com.epam.training;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created by Dmytro_Ulanovych on 10/13/2015.
 */
public class WordChain {
    private final String dictionaryPath;
    private Set<String> words;

    public WordChain(String dictionaryPath) {
        this.dictionaryPath = dictionaryPath;
    }

    public List<String> getChain(String start, String end) throws WordChainException, IOException, URISyntaxException {
        loadFile(dictionaryPath);
        validateInput(start, end);

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

    private void validateInput(String start, String end) throws WordChainException {
        if (isBlank(start) || isBlank(end)) {
            throw new WordChainException("Input parameters can't be null or empty!");
        }
        if (start.length() != end.length()) {
            throw new WordChainException("Number of letters should be equal");
        }
        if (!words.contains(start) || !words.contains(end)) {
            throw new WordChainException("One of words doesn't exit in dictionary");
        }
    }

    private String getNextMatchWord(String start, String end) {
        StringBuilder startWord = new StringBuilder(start);
        StringBuilder endWord = new StringBuilder(end);

        for (int i = 0; i < startWord.length(); i++) {
            if (startWord.charAt(i) != endWord.charAt(i)) {
                char charToChange = startWord.charAt(i);
                startWord.setCharAt(i, endWord.charAt(i));
                if (words.contains(startWord.toString())) {
                    return startWord.toString();
                } else {
                    startWord.setCharAt(i, charToChange);
                }
            }
        }
        return null;
    }

    private void loadFile(String dictionaryPath) throws IOException, URISyntaxException {
        if (words == null && fileExist(dictionaryPath)) {
            Path path = Paths.get(getClass().getResource(dictionaryPath).toURI());
            this.words = new HashSet<String>(Files.readAllLines(path));
        }
    }

    boolean fileExist(String fileName) throws URISyntaxException {
        return fileName != null && getClass().getResource(fileName) != null;
    }

    Set<String> getWords() {
        return words;
    }
}
