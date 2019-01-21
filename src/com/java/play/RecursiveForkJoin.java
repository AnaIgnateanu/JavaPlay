package com.java.play;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class RecursiveForkJoin extends RecursiveAction{
	private int start;
	private int end;
	private int[] data;
	
	public RecursiveForkJoin(int start, int end, int[] data) {
		this.start = start;
		this.end = end;
		this.data = data;
	}

	@Override
	protected void compute() {
		if (end - start < 10) {
			for(int i = start;i<end;i++) {
				data[i] = ThreadLocalRandom.current().nextInt();
			}
		}
		else {
			int mid = ((end - start)/ 2) + start;
			RecursiveForkJoin r1 = new RecursiveForkJoin(start, mid, data);
			r1.fork();
			RecursiveForkJoin r2 = new RecursiveForkJoin(mid, end, data);
			r2.compute();
			r1.join();
			//invokeAll(new RecursiveForkJoin(start, mid, data), new RecursiveForkJoin(mid, end, data));
		}
		
	}
	
	public static void main(String[] args) {
		int[] data =  new int[1500];
		RecursiveForkJoin r = new RecursiveForkJoin(0, 1000,data);
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(r);
		Arrays.stream(data).forEach((l)->System.out.print(l+" "));
		
	}

}
