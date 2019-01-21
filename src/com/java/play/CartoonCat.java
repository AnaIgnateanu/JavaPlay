package com.java.play;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.LinkedBlockingDeque;

public class CartoonCat {
	   private void await(CyclicBarrier c) {
	      try {
	         c.await();
	      } catch (Exception e) {}
	   }
	   public void march(CyclicBarrier c) {
	      ExecutorService s = Executors.newSingleThreadExecutor();
	      for(int i=0; i<12; i++)
	         s.execute(() -> await(c));
	      s.shutdown();
	   }
	   public static void main(String[] strings) {
	      new CartoonCat().march(new CyclicBarrier(4,
	            () -> System.out.println("Ready")));
	      System.out.println("Done");
	      ExecutorService es = Executors.newCachedThreadPool();
	      BlockingDeque bq = new LinkedBlockingDeque();
	      
	      Callable c = () -> s();
	      Collection<Callable<Integer>> coll = Arrays.asList(c, c, c);
	      
	      ForkJoinTask fj = new CountNumbers(1,1);
	      ForkJoinPool p = new ForkJoinPool();
	      p.invoke(fj);
	      p.shutdown();
	   }
	   
	   public static int s() {
		   return 1;
	   }
	}
