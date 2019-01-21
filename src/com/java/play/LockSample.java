package com.java.play;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.IntFunction;
import java.util.function.UnaryOperator;

public class LockSample {
	private ReentrantReadWriteLock rl = new ReentrantReadWriteLock();
	private List<Integer> highScores = new ArrayList<>();
	
	private ReentrantLock ll = new ReentrantLock();

	public void addScores(Integer score) {
		Lock lock = rl.writeLock(); // only one thread can enter the write lock
		try {
			lock.lock();
			if (highScores.size() < 10) {
				highScores.add(score);

			} else if (highScores.get(highScores.size() - 1) < score) {
				highScores.add(highScores.size() - 1, score);
			} else {
				return;
			}
			Collections.sort(highScores, Collections.reverseOrder());
		} finally {
			lock.unlock();
		}
	}

	public List<Integer> getHighScores() {
		Lock lock = rl.readLock(); // multiple threads can enter the read lock
		try {
			lock.lock();
			return Collections.unmodifiableList(highScores);
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		LockSample l = new LockSample();
		ExecutorService s = null;
		try {
			s = Executors.newFixedThreadPool(4);
			s.submit(() -> l.addScores(23));
			s.submit(() -> l.addScores(45));
			s.submit(() -> l.addScores(7658));
			s.submit(() -> l.addScores(422));
			s.submit(() -> l.addScores(7686));
			s.submit(() -> l.addScores(382));
			s.submit(() -> l.addScores(908));
			
			s.submit(() -> l.getHighScores().stream().peek((jh) -> out.print(" ")).forEach(out::print));
			s.submit(() -> out.println());
			s.submit(() -> l.getHighScores().stream().peek((jh) -> out.print(" ")).forEach(out::print));
			s.submit(() -> out.println());
			s.submit(() -> l.getHighScores().stream().peek((jh) -> out.print(" ")).forEach(out::print));
			s.submit(() -> out.println());
			s.submit(() -> l.getHighScores().stream().peek((jh) -> out.print(" ")).forEach(out::print));
			s.submit(() -> out.println());
		} finally {
			s.shutdown();
		}
		
		IntFunction<UnaryOperator<Integer>> func = i -> j -> i * j;
		out.println("func: "+func.apply(10).apply(20));
		
		
//		ReentrantReadWriteLock rf = new ReentrantReadWriteLock();
//		rf.readLock().unlock(); // java.lang.IllegalMonitorStateException: 
//		out.println("Lock 1"); 		//attempt to unlock read lock, not locked by current thread
//		rf.readLock().lock();
//		out.println("Lock 2");
//		rf.readLock().lock();
//		out.println("Lock 3");
//		rf.readLock().unlock();
//		out.println("Lock 4");
//		rf.writeLock().lock();
//		out.println("Lock 5");
//		rf.writeLock().unlock();
//		out.println("Lock 6");
		
	}
}
