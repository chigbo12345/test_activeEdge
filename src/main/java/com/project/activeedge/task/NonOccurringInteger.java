package com.project.activeedge.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NonOccurringInteger {
    public static int getInteger(int[] array) {
        if(array.length == 0) return 0;
        List<Integer> converted = new ArrayList<>();
        for(int e : array) {
            converted.add(e);
        }// Populate our newly declared array with data from parameter array
        Collections.sort(converted);// Arrange/sort array in ascending order
        int firstElement = converted.get(0);
        int lastElement = converted.get(converted.size() - 1);
        int x = 0;
        for(int i = firstElement; i <= lastElement; i++) {
            if(!converted.contains(i)) {// "i" is present in array...
                if(x == 0 && i > 0) {
                    x = i;
                }else {
                    if(i > 0 && i < x) {
                        x = i;
                    }
                }
            }
        }
        return x;
    }
}
