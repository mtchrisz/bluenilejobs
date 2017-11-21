package com.bluenile.scrabble;

import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ScrabbleController {

  @Autowired
  private ScrabbleService scrabbleService;

  @ResponseBody
  @RequestMapping(value = "/words/{letters}", produces = "application/json")
  public SortedSet<String> getWords(@PathVariable("letters") String letters) {
    SortedSet<String> words = scrabbleService.getWords(letters);
    return words;
  }
}
