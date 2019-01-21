package com.java.play;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.*;
import static java.lang.System.out;

@FunctionalInterface
interface Corner {
	abstract void poke();
}

@FunctionalInterface
interface Pin extends Corner {
	boolean equals(Object o);
}


public class FunctionalSample {
	public static void main(String[] args) {
		Corner cr = ()->System.out.println("corner");
		Pin pp = ()->System.out.println("pin");
		cr.poke();
		pp.poke();
		
		
		Consumer<Food> cf = f ->f.descr = "new description";
		Food f = new Food("w", "w");
		cf.accept(f);
		Consumer<Food> fg = cf.andThen(g->g.ingr = "new ingredient");
		fg.accept(f);
		System.out.println(f);	
		
		List<Food> lf = new ArrayList<>();
		lf.add(new Food("s", "s"));
		lf.add(new Food("e", "e"));
		
		lf.stream().collect(Collectors.groupingBy(lv->lv.ingr)).forEach((gd, hg)-> {System.out.println(gd);
		hg.forEach(System.out::println);});
		Map<Boolean, List<String>> lkj = lf.stream().collect(Collectors
				.partitioningBy(lb->lb.descr.equals("s"), Collectors.mapping(t->t.ingr, Collectors.toList())));
		lkj.forEach((r, gh)->System.out.println(r+" "+gh));
		lf.forEach(cf.andThen(fg).andThen(System.out::println));
		
		Stream<Integer> is = Stream.of(1, 2, 4, 55, 87); //never IntStream
		lf.stream().collect(Collectors.groupingBy(lv->lv.ingr)).forEach((gd, hg)-> {System.out.println(gd);
		hg.forEach(System.out::println);});
		
		Map<String, List<Food>> md=lf.stream().collect(Collectors.groupingBy(j->j.descr, Collectors.toList()));
		
		int []ns = {43,43,54,42,87,534,211};
		int dss = Arrays.stream(ns)
				.reduce(86, (a,b)->a-b);
		System.out.println("arrays reduce with identity: "+dss);
		OptionalInt opi = Arrays.stream(ns)
				.reduce((a, b)->a-b);
		System.out.println("arrays reduce w/o identity: "+opi.getAsInt());
		
		
		List<Map<List<Integer>, List<String>>> list = new ArrayList<>(); // ADD_MAP
		Map<List<Integer>, List<String>> map = new HashMap<>();
		list.add(null);                                       // ADD_NULL
		list.add(map);
		list.add(new HashMap<List<Integer>, List<String>>()); // ADD_HASHMAP
		list.forEach(e ->  System.out.print(e + " ")); 
		System.out.println();
		
		
		Stream<Integer> ints = Stream.of(1, 2, 3, 4);
		boolean result = ints.parallel().map(Function.identity()).isParallel();
		System.out.println("result: " + result);
		
		
		List<Integer> inf = Arrays.asList(12, 45, 65, 2,4,78, 543);
		int max = inf.stream().max((a, b)->(a-b)).get();
		System.out.println("max: "+max);
		
		Optional<String> op = Optional.ofNullable(null);
		System.out.println(op.orElse("abc")); // orElse(T) returns T     ---> cannot be chained with ifPresent
		op.ifPresent(System.out::println); // ifPresent(Consumer) returns void
		
		
		
		List<String> lst = Arrays.asList("fsd;fdb;grd;igb", "", "opg;cbv");
		lst.stream()
		.flatMap(s->Arrays.stream(s.split(";")))
		.forEach(s->System.out.print(s+":"));
		System.out.println();
		
		
		Stream<String>sdf = Stream.of("erd", "nf", "md", "ofr");
		System.out.println(sdf.collect(Collectors.joining(",", "[", "]")));
														// delimiter, prefix, suffix
																//at the beginning of the joint result
																//at the end of the joint result
		
		
		out.println("-------------");
		String bob = "blofd";
		String val  = null;
		Supplier<String> ss = bob::toUpperCase;
		val = ss.get();
		out.println("val: "+val);
		
		
		IntStream s1 = IntStream.range(1,3);
		IntStream s2 = IntStream.rangeClosed(1, 3);
		IntStream scc = IntStream.concat(s1, s2);
		Object vll = scc.boxed().collect(Collectors.groupingBy(k->k)).get(3);
		out.println("vall: "+vll);
		
		
		
		DoubleStream dfc = new Random().doubles();
		
		
		List<Food> ngc = Arrays.asList(new Food("ab", "ed"), new Food("ve", "op"), new Food("oe", "ua"));
		List<String> des = ngc.stream().collect(Collectors.mapping(Food::getDescr, Collectors.toList()));
		des.forEach(x->out.print(x+"|"));
		
		
	
		
	}
}

class Food implements Comparable<Food> {
	String ingr;
	String descr;
	
	public String getIngr() {
		return ingr;
	}

	public void setIngr(String ingr) {
		this.ingr = ingr;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	Food(String ingr, String descr) {
		this.ingr = ingr;
		this.descr = descr;
	}

	@Override
	public String toString() {
		return "Food [ingr=" + ingr + ", descr=" + descr + "]";
	}

	@Override
	public int compareTo(Food o) {
		return o.descr.compareTo(this.descr);
	}
	
	
}