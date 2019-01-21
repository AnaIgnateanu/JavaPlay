package com.java.play;


import java.util.concurrent.RecursiveTask;

public class FindMin extends RecursiveTask<Integer> {
   private Integer[] elements;
   private int a;
   private int b;
   public FindMin(Integer[] elements, int a, int b) {
      this.elements = elements;
      this.a = a;
      this.b = b;
   }
   public Integer compute() {
      if ((b - a) < 2)
         return Math.min(elements[a], elements[b]);
      else {
         int m = a + ((b - a) / 2);
         System.out.println(a + "," + m + "," + b);
         RecursiveTask<Integer> t1 = new FindMin(elements, a, m);
         int result = t1.fork().join();
         return Math.min(new FindMin(elements, m, b).compute(), result);
      }
   }
}