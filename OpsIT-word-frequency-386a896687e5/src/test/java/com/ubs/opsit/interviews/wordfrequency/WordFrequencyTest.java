package com.ubs.opsit.interviews.wordfrequency;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class WordFrequencyTest {
    WordFrequency wordFrequency;

    @Before
    public void setup() {
        wordFrequency = new DefaultWordFrequency();
    }

    @Test
    public void testCountOccurrencesOfWords() {
        Map<String, Integer> result = wordFrequency.countOccurrencesOfWordsWithin("the man in the moon");
        assertEquals(Integer.valueOf(2), result.get("the"));
        assertEquals(Integer.valueOf(1), result.get("man"));
        assertEquals(Integer.valueOf(1), result.get("in"));
        assertEquals(Integer.valueOf(1), result.get("moon"));

        result = wordFrequency.countOccurrencesOfWordsWithin("the man on the moon");
        assertEquals(Integer.valueOf(4), result.get("the"));
        assertEquals(Integer.valueOf(2), result.get("man"));
        assertEquals(Integer.valueOf(1), result.get("in"));
        assertEquals(Integer.valueOf(1), result.get("on"));
        assertEquals(Integer.valueOf(2), result.get("moon"));

        result = wordFrequency.countOccurrencesOfWordsWithin(" the,man\u0000\u0001on..!,the\n\t ,,\u0003\u0004 moon 123 ");
        assertEquals(Integer.valueOf(6), result.get("the"));
        assertEquals(Integer.valueOf(3), result.get("man"));
        assertEquals(Integer.valueOf(1), result.get("in"));
        assertEquals(Integer.valueOf(2), result.get("on"));
        assertEquals(Integer.valueOf(3), result.get("moon"));
        assertEquals(Integer.valueOf(1), result.get("123"));
    }

    @Test
    public void testReturnNullIfStringToEvaluateIsInvalid() {
        Map<String, Integer> result = wordFrequency.countOccurrencesOfWordsWithin(null);
        assertEquals(null, result);

        result = wordFrequency.countOccurrencesOfWordsWithin("");
        assertEquals(null, result);
    }
}
