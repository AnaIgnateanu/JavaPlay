package com.java.play;

import java.util.concurrent.RecursiveAction;

public class CountNumbers extends RecursiveAction {
	   private int start;
	   private int end;
	   public CountNumbers(int start, int end) {
	      this.start = start;
	      this.end = end;
	   }
	   protected void compute() {
	      if (start<0) return;
	      else {
	         int middle = start + ((end - start) / 2);
	         start--;
	         invokeAll(new CountNumbers(start, middle), new CountNumbers(middle, end)); // m1
	      }
	   }
	}
