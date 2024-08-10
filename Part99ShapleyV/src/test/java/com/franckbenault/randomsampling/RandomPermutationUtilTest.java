package com.franckbenault.randomsampling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

class RandomPermutationUtilTest extends RandomPermutationUtil {

	@Test
	public void test() {
    	Set<String> n = new HashSet<>(Arrays.asList("A01", "A02","A03","A04","A05"));
    	List<String> temp = new ArrayList<>(n);
        
    	System.out.println(generateRandomPermutation(temp)); 
    	System.out.println(generateRandomPermutation(temp)); 
    	System.out.println(generateRandomPermutation(temp)); 
    	System.out.println(generateRandomPermutation(temp)); 
    	System.out.println(generateRandomPermutation(temp)); 
	}

}
