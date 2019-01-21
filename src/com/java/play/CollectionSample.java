package com.java.play;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.IntStream;

public class CollectionSample {
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.offer(2);
		pq.offer(84);
		pq.offer(45);
		out.println("peek: " + pq.peek());
		pq.add(32);
		out.print("pq: ");
		for (int e : pq) {
			out.print(e + " ");
		}
		out.println();
		out.println("remove(): " + pq.remove());
		out.println("remove(Object o): " + pq.remove(34));
		out.println("remove(Object o): " + pq.remove(45));

		ArrayDeque<Integer> ad = new ArrayDeque<>();
		ad.add(3);
		ad.add(98);
		ad.add(65);
		ad.offer(765);
		out.print("ad: ");
		for (int r : ad) {
			out.print(r + " ");
		}
		out.println();
		out.println("ad peek: " + ad.peek());
		ad.addFirst(312);
		ad.addLast(32);
		out.print("ad: ");
		for (int r : ad) {
			out.print(r + " ");
		}
		out.println();

		List l = new ArrayList();
		l.add("nvf");
		l.add(32);
		for (Object o : l) {
			out.println("o: " + o);
		}

		// Set s =new TreeSet(); //ClassCastException because it compares the elements
		// at insertion
		// s.add("12");
		// s.add("21");
		// s.add(4);
		// Iterator it = s.iterator();
		// while (it.hasNext()) {
		// out.print(it.next()+" ");
		// }
		out.println();

		Set<Turtle> st = new LinkedHashSet<Turtle>();
		st.add(new Turtle(2, 6));
		st.add(new Turtle(5, 7));
		st.add(new Turtle(2, 8));
		out.println("turtle set size: " + st.size());
		out.print("turtles: ");
		for (Turtle e : st) {
			out.print(e.size + " " + e.length + " | ");
		}
		out.println();

		LinkedList<Integer> li = new LinkedList<>();
		li.add(34);
		li.add(321);
		li.add(843);
		out.println("element: " + li.element());
		Iterator<Integer> its = li.iterator();
		while (its.hasNext())
			out.print(its.next() + "|");
		li.push(3455);
		li.addFirst(5647);
		li.addLast(45632);
		out.println();
		out.println("peek: " + li.peek());
		Iterator<Integer> it = li.iterator();
		li.remove(Integer.valueOf(34));
		// while (it.hasNext()) {
		// out.print(it.next()); // java.util.ConcurrentModificationException
		// }
		// out.println();

		its = li.iterator();
		while (its.hasNext())
			out.print(its.next() + "|");
		out.println();

		Iterator<Integer> desc = li.descendingIterator();
		while (desc.hasNext())
			out.print(desc.next() + "|");

		out.println();

		List<String> asd = new ArrayList<>();
		asd.add("er");
		asd.add("orew");
		asd.add("logf");
		asd.add("gfa");
		Collections.sort(asd);
		out.println("binary search: " + Collections.binarySearch(asd, "ap"));
		
		String res = asd.stream().reduce((s1, s2)->s1+" | "+s2).get();
		out.println("res: "+res);
		String resu = asd.stream().parallel().reduce("*", (s1, s2)->s1+" | "+s2);
		out.println("resu: "+resu);
		String resul = asd.stream().parallel().reduce("#", (s1,s2)->s1+" | "+s2, (s, s3)->s+s3);
		out.println("resul: "+resul);
		
		
		IntStream in = IntStream.of(1,2,7,5,65);
		out.println("intstream average: "+in.average());
		in = IntStream.empty();
		out.println("empty intstream average: "+in.average()); //--->OptionalDouble.empty
	}
}

class Turtle {
	int size;
	int length;

	Turtle(int s, int l) {
		size = s;
		length = l;
	}

	public boolean equals(Object o) {
		return (this.size == ((Turtle) o).size);
	}

	// @Override
	// public int hashCode() {
	// return size/5;
	// }
}
