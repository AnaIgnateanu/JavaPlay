package com.java.play;

import static java.lang.System.out;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayDeque;


class Place {
	int rating;
	String description;
	
	Place(int rating, String description) {
		this.rating = rating;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Place [rating=" + rating + ", description=" + description + "]";
	}
	
}
public class StackSort {
	ArrayDeque<Integer> ints = new ArrayDeque<>();
	ArrayDeque<String> strings = new ArrayDeque<>();
	ArrayDeque<Place> places = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		StackSort s = new StackSort();
		s.ints.push(1);
		s.ints.push(435);
		s.ints.addLast(3243);
		s.ints.add(9483);
		s.ints.addLast(7653);
		s.ints.add(642);
		s.ints.push(567);
		s.ints.addFirst(8547);
		out.print("original stack of ints: ");
		s.ints.stream().map(i->i+", ").forEach(out::print);
		out.println();
		out.print("sorted stack of ints using stream: ");
		s.ints.stream().sorted().map(i->i+", ").forEach(out::print);
		out.println();
		
		ArrayDeque<Integer> tempI = new ArrayDeque<>();
		tempI.push(1);
		tempI.push(2);
		tempI.push(3);
		tempI.stream().map(i->i+", ").forEach(out::print);
		out.println();
		tempI.stream().sorted().map(i->i+", ").forEach(out::print);
		tempI.clear();
		while (!s.ints.isEmpty()) {
			int top = s.ints.pop();
			while (!tempI.isEmpty() && tempI.peek() > top)  {
				s.ints.push(tempI.pop());
			}
			tempI.push(top);
		}
		while (!tempI.isEmpty()) {
			s.ints.push(tempI.pop());
		}
		out.println();
		out.print("sorted ints using algo: ");
		s.ints.stream().map(i->i+", ").forEach(out::print);
		out.println();
		
		
		s.strings.add("Asd");
		s.strings.addLast("pfejn");
		s.strings.push("pcdv");
		s.strings.push("12124dsf");
		s.strings.addFirst("HFBV");
		s.strings.push("ZZsa");
		s.strings.addFirst("cdmsvmdca");
		out.print("sorted strings using stream: ");
		s.strings.stream().sorted().map(i->i+", ").forEach(out::print);
		out.println();
		out.print("alternative sort: ");
		s.strings.stream().sorted((s1, s2)->s1.compareToIgnoreCase(s2)).map(i->i+", ").forEach(out::print);
		out.println();
		out.print("parallel stream: ");
		s.strings.stream().sorted((s1, s2)->s1.compareToIgnoreCase(s2)).parallel().map(i->i+", ").forEach(out::print);
		out.println();
		
		
		s.places.push(new Place(1, "des"));
		s.places.addLast(new Place(3, "dec"));
		s.places.push(new Place(10, "vrb"));
		s.places.push(new Place(9, "cdn"));
		s.places.addFirst(new Place(6, "hed"));
		s.places.stream().sorted((c1, c2)->(c1.rating - c2.rating)).map(i->i+", ").forEach(out::print);
		out.println();
		
		File file = new File("serial.txt");
		FileInputStream f = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(f);
		IOStreamSample read = (IOStreamSample) in.readObject();
		System.out.println("read: "+read);
		
	}
}
