package com.java.play;

import static java.lang.System.out;

interface Birdie {
	void fly();
}

interface Biped {
	void walk();
}

abstract class Mammal {
	public void walk() {out.println("walking");}
	static int i = 9;
}

public class InhSample extends Mammal implements Biped, Birdie {
	public void fly() {
		if (i < 8) {
			
		}
		out.println("flying");}
	
	public static void main(String[] args) {
		out.println(new Outer.Inner().val);
		out.println(new Outer().new Inn().fed);
	}
}


class Outer {
	static class Inner {
		public int val = 76;
	}
	class Inn {
		public int fed = 865;
	}
}