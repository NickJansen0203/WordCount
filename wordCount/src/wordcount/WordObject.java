/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

/**
 * @author Nick Jansen
 */
interface WordFrequency {
        String getWord();
        int getFrequency();
    }

/**
 * @author Nick Jansen
 */
public class WordObject implements WordFrequency {
    private final String word;
    private final int frequency;
    
    WordObject(String word, int frequency){
        this.word = word;
        this.frequency = frequency;
    }
    
    /**
     *
     * @return the word of the WordObject
     */
    @Override
    public String getWord() {
        return this.word;
    }

    /**
     *
     * @return the frequency of the WordObject
     */
    @Override
    public int getFrequency() {
        return this.frequency;
    }
}

//There is no test class for this Class