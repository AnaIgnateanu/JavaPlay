package com.java.play;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class SynchCol {
	static void bkCol() {
		List<String> l =new CopyOnWriteArrayList<>(); //thread safe variant of ArrayList
		l.add("34");
		l.add(0, "lob");
		l.add("fi");
		l.add("fo");
		
		ExecutorService es = Executors.newFixedThreadPool(5);
		es.submit(()->l.add("re"));
		es.submit(()->l.remove(3));
		es.submit(()->l.remove(2));
		es.submit(()->l.add("fe"));
		l.stream().map(x->x+", ").forEach(out::print);
		out.println();
		es.submit(()->{
			while (l.iterator().hasNext()) {
				l.iterator().next();
			}
		});
		es.submit(()->{
			while (l.iterator().hasNext()) {
				l.remove(l.iterator().next());
			}
		});
		if (es != null)
			es.shutdown();
	}
	
	public static void main(String[] args) {
		List<String> lst = new ArrayList<>();
		lst.add("34");
		lst.add(1, "lob");
		lst.add("fi");
		lst.add("fo");
		List<String> ls = Collections.synchronizedList(lst);

		CyclicBarrier cb = new CyclicBarrier(11, ()->out.println("barrier reached"));
		ExecutorService es = Executors.newFixedThreadPool(10);

		lst.stream().map(x -> x + ", ").forEach(out::print);
		out.println();
		ls.stream().map(x -> x + ", ").forEach(out::print);
		out.println();

		es.execute(() -> {
			lst.add("fim");
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		});
		
		es.submit(() -> {
			lst.add("fe");
			Stream.of(ls, lst).map(x->x+"m ").forEach(out::print);
			out.println();
			Stream.of(ls, lst).flatMap(x->x.stream()).map(x->x+"f ").forEach(out::print);
			out.println();
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		});
		
		

		lst.stream().map(x -> x + ", ").forEach(out::print);
		out.println();
		ls.stream().map(x -> x + ", ").forEach(out::print);
		out.println();

		es.submit(() -> {
			lst.remove(2);
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}

		});
		es.submit(() -> {
			ls.add("54");
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}

		});
		es.submit(() -> {
			ls.add("mem");
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}

		});
		es.submit(() -> {
			ls.add("mo");
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}

		});

		lst.stream().map(x -> x + ", ").forEach(out::print); // ConcurrentModificationException
		out.println();
		ls.stream().map(x -> x + ", ").forEach(out::print);
		out.println();
		
		//ls.stream().sorted(Comparator::reverseOrder).count(); //doesn't compile because of the method reference
		ls.stream().sorted(Comparator.reverseOrder()).count();

		es.submit(() -> {
			ls.remove(3);
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}

		});
		es.submit(() -> {
			ls.remove(2);
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}

		});

		lst.stream().map(x -> x + ", ").forEach(out::print); // ConcurrentModificationException
		out.println();
		ls.stream().map(x -> x + ", ").forEach(out::print);
		es.submit(() -> {
			while (ls.iterator().hasNext()) {
				ls.remove(ls.iterator().next());
			}
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}

		});
		es.submit(() -> {
			while (lst.iterator().hasNext()) {
				lst.remove(lst.iterator().next());
			}
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		});

		out.println();
		try {
			cb.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		out.println("threads waiting: "+cb.getNumberWaiting());
		out.println("lst is empty: " + lst.isEmpty());
		lst.stream().map(x -> x + "\\").forEach(out::print); //ConcurrentModificationException
		out.println();
		out.println("ls is empty: " + ls.isEmpty());
		ls.stream().map(x -> x + "\\").forEach(out::print); // ConcurrentModificationException

		if (es != null)
			es.shutdown();
		bkCol();
	}
}
