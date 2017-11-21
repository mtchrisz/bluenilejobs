package com.bluenile.scrabble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This application starts a web service that returns Scrabble suggestions for
 * a given set of letters. The highest-scoring words are listed first. For
 * example, an HTTP GET request to http://local.bluenile.com:18080/words/hat
 * returns:
 *
 * <pre>
 * [
 *   "hat",
 *   "ah",
 *   "ha",
 *   "th",
 *   "at",
 *   "a"
 * ]
 * </pre>
 **/
@SpringBootApplication
public class ScrabbleMain {

  public static void main(String[] args) {
    SpringApplication.run(ScrabbleMain.class, args);
  }
}
