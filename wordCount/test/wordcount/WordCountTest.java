/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordcount;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 20172654
 */
public class WordCountTest {
    
    /**
     * It is difficult to test textToArray, because unique WordObjects
     * are created. This is also the case for calculateMostFrequentNWords, 
     * the word and the frequency may be the same, the WordObject is not.
     */
    
    
    private void checkcalculateHighestFrequency(String test, int expResult) {
        WordCount instance = new WordCount();
        int result = instance.calculateHighestFrequency(test);
        assertEquals("result", expResult, result);
    }
    
    @Test
    public void testTextA() {
        checkcalculateHighestFrequency("The fox ran on a street.", 1);
    }
    
    @Test
    public void testTextB() {
        checkcalculateHighestFrequency("The fox ran on the street.", 2);
    }
    
    @Test
    public void testTextC() {
        checkcalculateHighestFrequency("a a a a bb b a", 5);
    }
    
    @Test
    public void testTextEmpty() {
        checkcalculateHighestFrequency(" ", 0);
    }
    
    private void checkcalculateFrequencyForWord(
            String test, String word, int expResult) {
        WordCount instance = new WordCount();
        int result = instance.calculateFrequencyForWord(test, word);
        assertEquals("result", expResult, result);
    }
    
    @Test
    public void testWordA() {
        checkcalculateFrequencyForWord("The fox ran on a street.", "axe", 0);
    }
    
    @Test
    public void testWordB() {
        checkcalculateFrequencyForWord("The fox ran on the street.", "The", 2);
    }
    
    @Test
    public void testWordC() {
        checkcalculateFrequencyForWord("a a a a bb b a", "bb", 1);
    }
    
    @Test
    public void testWordD() {
        checkcalculateFrequencyForWord("a a a a bb b a", "a", 5);
    }
    
    @Test
    public void testWordEmpty() {
        checkcalculateFrequencyForWord(" ", " ",0);
    }
    
    private void checkcalculateMostFrequentNWordsException(
            String test, int n, Class expected) {
        WordCount instance = new WordCount();
        try {
            instance.calculateMostFrequentNWords(test, n);
            fail( "should have thrown " + expected);
        } catch (Exception e) {
            assertTrue("type: " + e.getClass().getName()
            + " should be instance of " + expected,
                    expected.isInstance(e));
            assertNotNull("message should not be null",
                    e.getMessage());
        }
    }
    
    @Test(timeout = 100)
    public void testNWordExceptionA() {
        checkcalculateMostFrequentNWordsException("the the", -1,
                IllegalArgumentException.class);
    }
    
    @Test(timeout = 100)
    public void testNWordExceptionB() {
        checkcalculateMostFrequentNWordsException("the the", -5,
                IllegalArgumentException.class);
    }
}
