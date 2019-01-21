package com.java.play;

import java.io.IOException;

public class OverloadSample extends Sample {
	@Override
	void doWork() {
		System.out.println(doL());
	}
	
//	public OverloadSample(String s) { //Implicit super constructor Sample() is undefined. Must explicitly invoke another constructor
//		
//	}
	
	public int doWork(int i) throws IOException {
		return i*i;
	}
	static String doL() {
		return "Overload";
	}
	public static void main(String [] args) throws IOException {
		OverloadSample os = new OverloadSample();
		Sample s = new Sample();
		Sample so = new OverloadSample();
		os.doWork();
		os.doWork(8);
		s.doWork();
		so.doWork();
	}
}

class Sample {
	void doWork() {
		System.out.println(doL());
	}
	
//	public Sample(String s) {
//		
//	}
	
	static String doL() {
		return "Sample";
	}
}
