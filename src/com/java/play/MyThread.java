package com.java.play;

public class MyThread implements Runnable {

	@Override
	public void run() {

		MyThread t2 = new MyThread();
		System.out.println("t2");
		System.out.println("MyThread getId: " + Thread.currentThread().getId());
		Thread t1 = new Thread(t2);
		// t1.start();
		System.out.println("t1");
	}

	public static void main(String[] args) throws InterruptedException {

		MyThread t1 = new MyThread();
		Thread t = new Thread(t1);
		t.start();

		MyThread t2 = new MyThread() {
			public void run() {
				System.out.println("tt");
				System.out.println("AnoThread getId: " + Thread.currentThread().getId());
			}
		};
		Thread tt = new Thread(t2);
		tt.start();

		synchronized (MyThread.class) {
			t.join();
			System.out.println("Thread name: " + Thread.currentThread().getName());
			
		}

		System.out.println("1");
		synchronized (args) {
			System.out.println("2");
			try {
				args.wait(500);
			} catch (InterruptedException e) {

			}
			System.out.println("3");
		}

		System.out.println("Last Thread: " + Thread.currentThread().getId()+" "+ Thread.currentThread().getName());
	}

}
