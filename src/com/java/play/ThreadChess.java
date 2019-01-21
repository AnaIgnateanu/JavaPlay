package com.java.play;

public class ThreadChess implements Runnable{
	public void run() {
		move(Thread.currentThread().getId());
	}
	
	synchronized void move(long id) {
		System.out.println("id: "+id);
		System.out.println("id: "+id);	
	}
	
	public static void main(String[] args) {
		ThreadChess tc = new ThreadChess();
		new Thread(tc).start();
	//	new Thread(tc).start();
		new Thread(new ThreadChess()).start();
	}
}
