package com.epam.word_chains;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adam_Bronowicki on 10/13/2015.
 */
public class FileWordDictionary implements WordDictionary {
  private static final String DICTIONARY_FILE_PATH = "src/main/resources/words";

  private Set<String> dictionary;
  public FileWordDictionary() {
    // open the dictionary file and load it in a hash set
    dictionary = new HashSet<>();
    File dictionary_file = new File(DICTIONARY_FILE_PATH);
    try {
      BufferedReader buffered_reader =
          new BufferedReader(
              new InputStreamReader(
                  new FileInputStream(
                      dictionary_file
                  )
              )
          );
      String line = null;

      while((line = buffered_reader.readLine()) != null) {
        dictionary.add(line);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean containsWord(String word) {
    return dictionary.contains(word);
  }
}
