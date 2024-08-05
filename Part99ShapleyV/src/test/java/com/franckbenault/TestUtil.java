package com.franckbenault;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TestUtil {
	
	@Test
	public void factorialTest() {
		
		assertEquals(Util.factorial(0), 1);
		assertEquals(Util.factorial(1), 1);
		assertEquals(Util.factorial(2), 2);
		assertEquals(Util.factorial(3), 6);
		assertEquals(Util.factorial(4), 24);
	}
	
	@Test
	public void powerSetTest() {
		
		Set<String> empty = new HashSet<String>();
		assertEquals(Util.powerSet(empty).size(),1);
		assertTrue(Util.powerSet(empty).contains(empty));
		
		Set<String> one = new HashSet<String>(Arrays.asList("One"));
		assertEquals(Util.powerSet(one).size(),2);
		assertTrue(Util.powerSet(one).contains(empty));
		assertTrue(Util.powerSet(one).contains(one));

	}

}
