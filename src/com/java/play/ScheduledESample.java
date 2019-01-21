package com.java.play;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledESample {
	static ScheduledExecutorService ses = null;
	static CyclicBarrier cb= new CyclicBarrier(2, ()->out.println("Done"));
	public static void main(String[] args) {
		List<Future<String>> lst = new ArrayList<>();
		ArrayBlockingQueue<Future<String>> ab = new ArrayBlockingQueue<Future<String>>(20);
		try {
		ses = Executors.newScheduledThreadPool(4);
		
		lst.add(ses.submit(()->{out.println("1");out.println(Thread.currentThread().getName());return "des";}));
		out.println(Thread.currentThread().getName());
		ses.scheduleAtFixedRate(()->
		{	cb(cb);
		out.println(Thread.currentThread().getName());
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		out.println(Thread.currentThread().getName());
			out.println("2");},0, 10,TimeUnit.SECONDS);
		
		ses.scheduleWithFixedDelay(()->{cb(cb);out.println("3");},0, 10, TimeUnit.SECONDS);
		try {
			out.println(Thread.currentThread().getName());
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		} finally {
			if (ses != null) {
				ses.shutdown();
			}
		}
	}
	
	static void cb(CyclicBarrier c) {
		try {
			c.await();
		} catch (InterruptedException |BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
