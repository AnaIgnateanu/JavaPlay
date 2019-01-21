package com.java.play;

import java.util.function.IntUnaryOperator;

class News<News> {}


public class Transport {
	
	   static interface Vehicle {}
	   public static class Bus implements Vehicle {
		   private enum Lakes {
			   A { @Override protected int c() {return 1;}},
			   B { @Override protected int c() {return 2;}};
			   protected abstract int c();
		   }
		  Lakes getLakes() {
			   return Lakes.A;
		   }
	   }
	   static class Van extends Bus {}
	   
	   final protected class V {final public int getV() {return 7;}}
	   V vg = new V();
	 
	   public static void main(String[] args) {
		  Transport t = new Transport();
	      Bus b = new Bus();
	      System.out.println(b.getLakes());
	      Transport.Bus.Lakes lake = Transport.Bus.Lakes.A;
	      
	   // --------------------------------------/
	      
	      
	      Bus bus = new Van();
	      Van van = new Van();
	      Van[] vans = new Van[0];
	 
	      boolean b1 = bus instanceof Vehicle;
	      boolean v = van instanceof Vehicle;
	      boolean a = vans instanceof Vehicle[];
	 
	      System.out.println(b1 + " " + v + " " + a);
	      
	      
	   // --------------------------------------/
	      int w = 32;
	      final class O extends Walk {
	    	  int getO() {
	    		  return w;
	    	  }
	      }
	      System.out.println(new O().getO());
	      
	      
	   // --------------------------------------/
	      
	      System.out.println(new Transport().vg.getV());
	      
	   }
	   
	   public int v(IntUnaryOperator i, int a) {
		   double d = 5.6;
		   int r = (int) (1 + d);
		   return i.applyAsInt(a);	   
	   }
	}