package com.epam.word_chains;

import java.io.*;

public class Dictionary {

  public boolean containsWord(String word) {
    boolean containsWord = false;
    File dictionary = new File("src/main/resources/words");
    try (BufferedReader br = new BufferedReader(new FileReader(dictionary))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.equals(word)) {
          containsWord = true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return containsWord;
  }

}
