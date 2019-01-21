package com.java.play;

class Ts {
	synchronized void ck(long id) {
		for (int i = 1; i < 3; i++) {
			System.out.println("id: " + id);
			Thread.yield();
		}
	}
}

public class ThreadSample implements Runnable{
	static Ts ts ;
	
	public static void main(String[] args) {
		new ThreadSample().go();
		
	}
	
	void go() {
		ts = new Ts();
		new Thread(new ThreadSample()).start();
		new Thread(new ThreadSample()).start();
	}
	
	public void run() {
		ts.ck(Thread.currentThread().getId());
	}
}
