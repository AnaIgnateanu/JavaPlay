package com.java.play;

import java.util.function.Predicate;

class Lifeguard {
	void save(Predicate<Tourist> p, Tourist t) {
		System.out.println(p.test(t) ? "Saved" : "Too far");
	}
}
public class Tourist {
	double distance;
	public Tourist(double distance) {
		this.distance = distance;
	}
	
	
	
	public static void main(String ... sand) {
		new Lifeguard().save(t->t.distance < 4, new Tourist(2));
	}
	
}
