package com.java.play;

class TimeException extends Exception {}
class TimeMachine implements AutoCloseable {
   int v;
   public TimeMachine(int v) {this.v = v;}
   public void close() throws Exception {
      System.out.print(v);
   }
}
public class TimeTraveler {
   public static void main(String[] twelve) throws Exception {
      try (TimeMachine timeSled = new TimeMachine(1);
    		  //TimeMachine timeSled = null; //compiles
            TimeMachine delorean = new TimeMachine(2);
            TimeMachine tardis = new TimeMachine(3)) {
      } catch (TimeException e) {
         System.out.print(4);
      } finally {
         System.out.print(5);
      }
      
      
      try (TimeMachine tm = new TimeMachine(9)) {
    	  
      } catch (Exception e) {
    	  throw e;
    	  
      } catch (Error t) {
    	  
      }
   }
}
