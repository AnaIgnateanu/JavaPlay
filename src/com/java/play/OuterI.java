package com.java.play;

import static java.lang.System.out;

public class OuterI {
	private int f;
	int h() {
		return f;
		//return f+g; //doesn't compile because it doesn't see private variable g from Inner class
	}
	
	public static void main(String[] args) {
		new OuterI().local();
	}
	
	Inner local() {
		class Local extends Inner{
			public Local() {
				String name = "local class";
				out.println(name);
			}
		}
		new Local() {
			
		};
		Local ol = new Local();
		OuterI lp = new OuterI();
		out.println((ol instanceof O) +""+ (lp instanceof OuterI));
		return new Local();
	}
	
	public class Inner {
		private int g;
		static final int h = 5; //inner classes cannot declare static members(fields and methods),
										//only static final variables (constants)
		
		int v() {
			Inner i = local();
			return h;
		}
		int h() {
			return f+g;
		}
	}
}

interface O {
	
}
