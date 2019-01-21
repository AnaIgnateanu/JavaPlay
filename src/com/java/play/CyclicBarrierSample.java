package com.java.play;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CyclicBarrierSample {
	ExecutorService s = null;
	CyclicBarrier cb;
	List<Future<?>> lf = new ArrayList<>();

	public static void main(String[] args) {
		new CyclicBarrierSample().go();
	}

	void go() {
		try {
			s = Executors.newFixedThreadPool(4);
			cb = new CyclicBarrier(4, () -> 
			{
				out.println("Done");
				out.println(Thread.currentThread().getName());
				out.println("-------");	
				});
			lf.add(s.submit(() ->{out.println("1");performTask(cb); return "1";}));
			s.execute(() -> {out.println("2");performTask(cb);});
			lf.add(s.submit(() -> {
				out.println("3");
				performTask(cb);
				return "3";
			}));
			lf.add(s.submit(() -> {
				Thread.currentThread().setName("Tread4");
				out.println("4");
				performTask(cb);
			}));

			
			lf.stream().map(a -> {
				try {
					return a.get();
				} catch (InterruptedException | ExecutionException e) { //InterruptedException is thrown 												
					e.printStackTrace();								//when the thread calling the Future's get() is interrupted before returning the result
				}
				return null;											//ExecutionException is thrown
			}).forEach(out::println);									//when a exception was thrown during the execution of Callable's call() method.
			// lf.add(s.submit(()->{out.println("5");return "5";}));
			
			Runtime r = Runtime.getRuntime();
			int cpus = r.availableProcessors();
			out.println("cpus: "+cpus);
		} finally {
			if (s != null)
				s.shutdown();
		}
	}

	void performTask(CyclicBarrier cb) {
		try {
			cb.await();		
		} catch (InterruptedException | BrokenBarrierException e) {
			
		}
	}

}
