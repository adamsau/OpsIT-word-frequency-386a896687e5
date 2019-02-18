package com.ubs.opsit.interviews.wordfrequency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  This class counts and accumulate the number of occurrences of words in given Strings. This class is thread-safe.
 */
public class DefaultWordFrequency implements WordFrequency {
    private final Map<String, Integer> frequencyByWordMap = new HashMap<>();
    private final Lock frequencyByWordMapLock = new ReentrantLock();

    /**
     * counts and accumulate the number of occurrences of words in given Strings, ignore any characters which is non-printable (any control/whitespace/) or punctuation.
     * @param stringToEvaluate
     * @return a map contains the frequency of words, null if the input string is invalid
     */
    @Override
    public Map<String, Integer> countOccurrencesOfWordsWithin(String stringToEvaluate) {
        if(stringToEvaluate == null || stringToEvaluate.isEmpty()) {
            return null;
        }

        String[] splits = stringToEvaluate.split("[\\p{Cntrl}\\p{Blank}\\p{Punct}]+");
        frequencyByWordMapLock.lock();
        try {
            for (String split : splits) {
                Integer val = frequencyByWordMap.getOrDefault(split, 0);
                frequencyByWordMap.put(split, val + 1);
            }
            return new HashMap<>(frequencyByWordMap);
        }
        finally {
            frequencyByWordMapLock.unlock();
        }
    }
}
