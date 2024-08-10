package com.franckbenault.randomsampling;

import java.util.ArrayList;
import java.util.List;

public class RandomPermutationUtil {
    // Function to return the next random number 
    private static int getNum(ArrayList<Integer> v) 
    { 
        int n = v.size(); 
     
        // Make sure the number is within 
        // the index range 
        int index = (int)(Math.random() * n); 
     
        // Get random number from the vector 
        int num = v.get(index); 
     
        // Remove the number from the vector 
        v.set(index, v.get(n - 1));
        v.remove(n - 1); 
     
        // Return the removed number 
        return num; 
    } 
     
    // Function to generate n 
    // non-repeating random numbers 
    public static List<String> generateRandomPermutation(List<String> nToList) 
    { 
    	List<String> output = new ArrayList<>();
    	int nSize =nToList.size();
        ArrayList<Integer> v = new ArrayList<>(nSize); 
     
        // Fill the vector with the values 
        // 1, 2, 3, ..., n 
        for (int i = 0; i < nSize; i++) 
            v.add(i + 1); 
     
        // While vector has elements 
        // get a random number from the vector 
        while (v.size() > 0) 
        { 
            //System.out.print(getNum(v) + " "); 
            output.add(nToList.get(getNum(v)-1));
        } 
        return output;
    } 
}
