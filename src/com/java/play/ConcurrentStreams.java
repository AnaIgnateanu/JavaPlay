package com.java.play;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConcurrentStreams {
	public static void main(String[] args) {
		Stream<Integer> s = Stream.of(12, 34, 76, 21, 31);
		Optional<Integer> gf = s.parallel()
				.map(m->m*10)
				.reduce((a, b)->a-b);
		out.println(gf.get());
		
		
		Stream<Integer> si = Stream.empty();
		out.println("empty stream: "+si.reduce(5, (c, f)->c -f));
		
		IntStream is  =IntStream.empty();
		out.println("empty stream: "+is.reduce(5, (c, f)->c -f));
		
		IntStream ts  = IntStream.range(23, 120);
		out.println("int stream: "+ts.reduce(0, (c, f)->c+f));
		
		
		Stream<List<String>> sls = Stream.generate(()->Arrays.asList("B", "A", "F", "G"))
				.limit(2).unordered();
		sls.parallel()
		.flatMap(p->p.stream())
		.map(k->k.toLowerCase())
		.forEach(out::println);
		
	}
}
