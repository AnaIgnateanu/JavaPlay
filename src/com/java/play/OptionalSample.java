package com.java.play;

import java.util.Optional;
import java.util.stream.Stream;
import static java.lang.System.out;

public class OptionalSample {
	public static void main(String[] args) {
		Optional<String> op = Optional.of("ge");
		out.println("optional map: "+op.map(x->x+" //").get());
		
		Stream<String> sd = Stream.of("ab", "cd", "fd", "eg");
		Stream<String> ss = Stream.of("ab", "cd", "fd", "eg");
		Optional<Stream<String>> oss = Optional.of(ss);
	//	Optional<Stream<String>> osd = Optional.of(ss); //java.lang.IllegalStateException: 
		
		out.println("---------------------");
		//stream has already been operated upon or closed
		Optional<Stream<String>> osd = Optional.of(sd);
		out.println("optional flat map find any: "+oss.flatMap(x->x.parallel().findAny()).get());
		out.println("optional flat map fin first: "+osd.flatMap(d->d.findFirst()).get());
		
		
		out.println("---------------------");
		Optional<String> dd = Optional.of("rer");
		out.println("optional filter or else: "+dd.filter(x->x.startsWith("re")).orElse("reo"));
		out.println("optional filter or else get: "+dd.filter(x->x.contains("f")).orElseGet(()->new String("pop")));
		out.println("optional filter or else throw: "+dd.filter(x->x.contains("e")).orElseThrow(()->new RuntimeException("pop")));
		out.println("optional is present: "+dd.isPresent()+" ");
		dd.ifPresent(b->out.println("optional  if present: "+b.concat("#"))); //takes a Consumer as an argument
		
		out.println("---------------------");
		Optional<String> ops = Optional.empty();
	//	out.println("empty optional: "+ops.get()); // throws  java.util.NoSuchElementException: No value present
		out.println("empty optional or else: "+ops.orElse("m"));
		out.println("empty optional or else get: "+ops.orElseGet(()->"ju".concat("m")));
		out.println("optional is present: "+ops.isPresent());
		ops.ifPresent(b->out.println("optional  if present: "+b.concat("*")));//does nothing
		
		
		Optional<String> no = Optional.ofNullable(null);
		out.println("optional of nullable: "+no); //----->Optional.empty;
	//	no = Optional.of(null); //throws NullPointerException
		no = Optional.ofNullable("fr");
		out.println("optional of nullable with value: "+no);//------------>Optional[fr]
		
		
		out.println("---------------------");
		Stream<String> fsf = Stream.empty();
		out.println("empty stream find any: : "+fsf.findAny()); //-->Optional.empty -->.get()---> NoSuchElementException
		fsf = Stream.empty();
		out.println("empty stream all match: "+fsf.allMatch(x->x.startsWith("op")));
		
		
		
	}
}
