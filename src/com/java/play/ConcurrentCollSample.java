package com.java.play;

import java.util.ArrayList;
import static java.lang.System.out;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ConcurrentCollSample {
	public static void main(String[] args) {
		CopyOnWriteArrayList<Integer> ic = new CopyOnWriteArrayList<>();
		ic.add(23);
		ic.add(43);
		ic.add(232);
		Iterator<Integer> fn = ic.iterator();
		ic.add(321);
		ic.remove(2);
		ic.stream().forEach((l)->System.out.print(l+" "));
		System.out.println();
		while (fn.hasNext()) {
			System.out.print(fn.next()+" ");
		}
		
		
		ArrayList<Integer> al = new ArrayList<>();
		al.add(43);
		al.add(4342);
		al.add(7654);
		Iterator<Integer> dw = al.iterator();
		System.out.println();
		while (dw.hasNext()) {
			System.out.print(dw.next()+" ");
		}
		al.add(3232);
		System.out.println();
		al.stream().forEach((l)->System.out.print(l+" "));
		al.remove(1);
		System.out.println();
		al.stream().forEach((l)->System.out.print(l+" "));
		
		
		List<Tree> t = new CopyOnWriteArrayList<>();
		t.add(new Tree(3, "elm"));
		t.add(new Tree(4, "linden"));
		t.add(new Tree(5, "sequoia"));
		out.println();
		
		double size = t.parallelStream().collect(Collectors.averagingInt(Tree::getSize));
		out.println("averaging int: "+size);
		long si = t.parallelStream().collect(Collectors.counting());
		out.println("counting: "+si);
		
		
		Optional<Tree> ot = t.parallelStream().max((t1, t2)->t1.getSize()-t2.getSize());
		out.println("optional<tree>: "+ot+" biggest tree: "+ot.get());
		
		Optional<Tree> ott = t.parallelStream().collect(Collectors.maxBy((t1, t2)->t1.getSize()-t2.getSize()));
		out.println("optional<tree>: "+ott+" biggest tree: "+ott.get());
		
		List<Integer> lt = t.parallelStream().collect(Collectors
				.mapping(Tree::getSize, Collectors.toList()));
		out.println("list: "+lt);
		lt = t.parallelStream().collect(Collectors
				.mapping(th->th.getSize(), Collectors.toList()));
		out.println("list: "+lt);
		
		
		TreeMap<Integer, List<Tree>> tm = t.parallelStream()
				.collect(Collectors.groupingBy(Tree::getSize, TreeMap::new, Collectors.toList()));
		out.println("tree map: "+tm);
		
	}
	
}

class Tree {
	int size;
	String species;
	
	Tree(int size, String species) {
		this.size = size;
		this.species = species;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	@Override
	public String toString() {
		return "Tree [size=" + size + ", species=" + species + "]";
	}
}
