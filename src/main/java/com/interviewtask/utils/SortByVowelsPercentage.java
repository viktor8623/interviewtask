package com.interviewtask.utils;

import java.util.Comparator;
import java.util.function.IntPredicate;

public class SortByVowelsPercentage implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        IntPredicate vowel = t -> t == 'a' || t == 'e' || t == 'i' || t == 'o' || t == 'u'
                || t == 'A' || t == 'E' || t == 'I' || t == 'O'
                || t == 'U';

        long percentage1 = (long)((o1.chars().filter(vowel).count() / (double)o1.length()) * 100);
        long percentage2 = (long)((o2.chars().filter(vowel).count() / (double)o2.length()) * 100);

        return Long.compare(percentage2, percentage1);
    }
}
