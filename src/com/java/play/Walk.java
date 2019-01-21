package com.java.play;

import java.util.concurrent.CyclicBarrier;

interface CanHop {
	abstract void i();

}

public class Walk implements AutoCloseable, CanHop {
	protected String step;
	int id;
	int stepLength;

	public boolean equals(Walk obj) {
		return this.id == obj.id;
	}

	@Override
	public int hashCode() {
		return id;
	}
	
	void m() {
		CanHop jump = new Walk();
	}
	
	public void i() {
	}

	public void close() {
		throw new RuntimeException("snow");
	}

	class Inner {
		class Innerinner {

		}

	}

}

class Jump implements CanHop {

	@Override
	public void i() {
		CyclicBarrier cb = new CyclicBarrier(4);
}
	
}