/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

/**
 * @author Nick Jansen
 */

interface WordFrequencyAnalyzer {
    int calculateHighestFrequency(String text);
    int calculateFrequencyForWord(String text, String word);
    List<WordFrequency> calculateMostFrequentNWords (String text, int n);
}
        
/**
 *
 * @author Nick Jansen
 */
public class WordCount implements WordFrequencyAnalyzer {
    
    /**
     *
     * @param text the sentence to use
     * @return the frequency of all words in the sentence
     * in a list of WordObjects
     */
    public List<WordFrequency> textToArray(String text){
        List<WordFrequency> list = new ArrayList<>();
        text = text.replaceAll(","," "); //remove the commas
        text = text.replaceAll("\\."," "); //remove the points
        String[] done = text.split("\\s+"); //split the sentence into words
        Map<String, Integer> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for(int i = 0; i <= done.length-1; i++) {
            if (map.containsKey(done[i])) {
                map.put(done[i], map.get(done[i])+1); 
            //if word was already in the map, then plus 1 frequency
            } else {
                map.put(done[i], 1); //otherwise enter as new word
            }
        } //move all words from a TreeMap to a list
            for(Map.Entry<String, Integer> entry: map.entrySet()) {
                list.add(new WordObject(entry.getKey(), entry.getValue()));
            }
        return list;
    }

    /**
     *
     * @param text the sentence to use
     * @return the highest of all frequency of all words in the sentence
     */
    @Override
    public int calculateHighestFrequency(String text) {;
        List<WordFrequency> list = new ArrayList<>();
        list = textToArray(text);
        int currentHighestFreq = 0;
        for(int i=0;i<list.size();i++){ //go over every frequency and
            //then check what the highest frequency is
            if (list.get(i).getFrequency() > currentHighestFreq) {
                currentHighestFreq = list.get(i).getFrequency();
            }
        }
            return currentHighestFreq;  
    }

    /**
     *
     * @param text the sentence to use
     * @param word the word to use
     * @return the frequency of the word in the sentence
     */
    @Override
    public int calculateFrequencyForWord(String text, String word) {
        List<WordFrequency> list = new ArrayList<>();
        list = textToArray(text);    
        for(int i=0;i<list.size();i++) {
            if (word.equals(list.get(i).getWord())) {//check if word was in text
                return list.get(i).getFrequency();
            }
        }
            return 0; 
    }

    /**
     *
     * @param text the sentence to use
     * @param n the amount of words in the list
     * @pre {@code n >= 0}
     * @return the most frequent words, it must be @code n words
     * @throws IllegalArgumentException if precondition is violated
     */
    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        if (n<0) { //throw exception if number of words is smaller than zero
            throw new IllegalArgumentException(
                    "asked for a negative amount of words"
            );
        }
        List<WordFrequency> list = new ArrayList<>();
        list = textToArray(text);
        Collections.reverse(list); //since a TreeMap was used,
        //the words with highest frequency will be first after reversing
        List<WordFrequency> outputlist = new ArrayList<>();
        for (int i = 0; i <= n-1; i++) { //output the first n words
        outputlist.add(list.get(i));
        }
        return outputlist;
    }    
}