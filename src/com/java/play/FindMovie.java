package com.java.play;

import java.util.List;
import java.util.function.Function;

public class FindMovie {
	private Function<String, String> printer;
	protected FindMovie() {
		printer = s -> { System.out.println(s); return s;};
	}
	
	public void printMovie(List<String> movies) {
		
	}

}
 