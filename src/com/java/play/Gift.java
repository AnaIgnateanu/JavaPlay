package com.java.play;

interface Toy { String play(); }
public class Gift {
   public static void main(String[] matrix) {
      abstract class Robot {}
      class Transformer extends Robot implements Toy {
         public String name = "GiantRobot";
         public String play() {return "DinosaurRobot";}
      }
      Transformer prime = new Transformer () {
         public String play() {return name;}  // y1
      };
    //  System.out.print(prime.play()+" "+name);
   }
}


class Leader {}
class Follower extends Leader {}
abstract class Dancer {
   public Leader getPartner() { return new Leader(); }
   abstract public Leader getPartner(int count);  // u1
}
 
abstract class SwingDancer extends Dancer {
   public Follower getPartner() {  // u2
      return new Follower();  // u3
   }
}
