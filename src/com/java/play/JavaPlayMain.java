package com.java.play;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaPlayMain {

	private static AtomicInteger sheepCount1 = new AtomicInteger(0); // w1
	private static int sheepCount2 = 0;
	static int counter = 0;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		AtomicLong value1 = new AtomicLong(0);
		final long[] value2 = { 0 };
		IntStream.iterate(1, i -> 1).limit(100).parallel().forEach(i -> value1.incrementAndGet());
		IntStream.iterate(1, i -> 1).limit(100).parallel().forEach(i -> ++value2[0]);
		System.out.println(value1 + " " + value2[0]);

		// --------------------------------------/

		List<Integer> l1 = Arrays.asList(1, 2, 3);
		List<Integer> l2 = new CopyOnWriteArrayList<>(l1);
		Set<Integer> s3 = new ConcurrentSkipListSet<>();
		s3.addAll(l1);

		for (Integer item : l2)
			l2.add(4); // x1
		for (Integer item : s3)
			s3.add(5); // x2
		System.out.println("sizes: " + l1.size() + " " + l2.size() + " " + s3.size());

		// --------------------------------------/

		Integer i1 = Arrays.asList(1, 2, 3, 4, 5).stream().findAny().get();
		synchronized (i1) { // y1
			Integer i2 = Arrays.asList(6, 7, 8, 9, 10).parallelStream().sorted() // y2
					.findAny().get(); // y3
			System.out.println("i: " + i1 + " " + i2);
		}

		Object o1 = new Object();
		Object o2 = new Object();
		ExecutorService service = Executors.newFixedThreadPool(2);
		Future<?> f1 = service.submit(() -> {
			synchronized (o1) {
				synchronized (o2) {
					System.out.println("Tortoise");
				} // t1
			}
		});
		Future<?> f2 = service.submit(() -> {
			synchronized (o2) {
				synchronized (o1) {
					System.out.println("Hare");
				} // t2
			}
		});
		try {
			f1.get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// --------------------------------------/

		// ForkJoinTask<?> task = new CountNumbers(0, 4); // m2
		// ForkJoinPool pool = new ForkJoinPool();
		// Object result = pool.invoke(task); // m3

		// --------------------------------------/

		Stream<String> cats = Stream.of("leopard", "lynx", "ocelot", "puma").parallel();
		Stream<String> bears = Stream.of("panda", "grizzly", "polar").parallel();
		ConcurrentMap<Boolean, List<String>> data = Stream.of(cats, bears).flatMap(s -> s)
				.collect(Collectors.groupingByConcurrent(s -> !s.startsWith("p")));
		System.out.println(data.get(false).toString() + " " + data.get(true).toString());
		BlockingDeque<Integer> deque = new LinkedBlockingDeque<>();
		addAndPrintItems(deque);

		// --------------------------------------/

		ExecutorService service1 = null;
		try {
			service1 = Executors.newSingleThreadExecutor(); // w2
			for (int i = 0; i < 100; i++)
				service1.execute(() -> {
					sheepCount1.getAndIncrement();
					sheepCount2++;
				}); // w3
			Thread.sleep(100);
			System.out.println(sheepCount1 + " " + sheepCount2);
		} finally {
			if (service1 != null)
				service1.shutdown();
		}

		// --------------------------------------/

		ExecutorService service2 = Executors.newSingleThreadExecutor();
		final List<Future<?>> results = new ArrayList<>();
		IntStream.range(0, 10).forEach(i -> results.add(service2.submit(() -> performCount(i)))); // o2
		results.stream().forEach(f -> printResults(f));
		service2.shutdown();

		// --------------------------------------/

		ExecutorService service3 = Executors.newSingleThreadExecutor();
		List<Future<?>> results3 = new ArrayList<>();
		IntStream.iterate(0, i -> i + 1).limit(5).forEach(
				// i -> results3.add(service3.execute(() -> counter++)) //execute returns void,
				// submit returns future
				i -> results3.add(service3.submit(() -> counter++)));
		for (Future<?> result : results3) {
			System.out.println(result.get() + " "); // n2
		}
		service3.shutdown();
		
		// --------------------------------------/
		
		System.out.println("Seasons autumn ordinal: "+Seasons.AUTUMN.ordinal());
//		System.out.println(Seasons.valueOf(Seasons.class, "summer"));
		System.out.println("Seasons compare: "+ Seasons.WINTER.compareTo(Seasons.SUMMER));
		
		List<Seasons> seasons = new ArrayList<>();
		for (Seasons s: Seasons.values()) {
			seasons.add(s);
		}
		System.out.println(seasons.toString());
		Collections.sort(seasons);
		System.out.println(seasons.toString());
		Collections.sort(seasons, (a, b)->a.name().compareTo(b.name()));
		System.out.println(seasons.toString());
		
		// --------------------------------------/
		
		Seasons.AUTUMN.printHours();
		System.out.println("declaring class: "+Seasons.SPRING.getDeclaringClass());
		System.out.println("first char: "+Seasons.WINTER.getFirstChar());
		System.out.println("first char: "+Seasons.SUMMER.getFirstChar());
		
		
		// --------------------------------------/
		
		Walk walk = new Walk();
		Walk.Inner inner = walk.new Inner(); //equivalent to Walk.Inner if not using import
		Walk.Inner.Innerinner i = inner.new Innerinner(); // equivalent to Walk.Inner.Innerinner if not using import
		
		// --------------------------------------/
		
		Walk walk1 = new Walk();
		walk1.id = 23;
		Walk walk2 = new Walk();
		walk2.id = 45;
		if (!walk1.equals(walk2))
			System.out.println("walk equals: "+ walk1.equals(walk2));
		
		// --------------------------------------/
		
		if (!(null instanceof Walk)) {
			System.out.println("null instanceof");
		}
		
		// --------------------------------------/
		
		System.out.println(Animal.CAT.name() + Animal.DOG.name());
		
		// --------------------------------------/
		
		
		Map<String, String> map = new HashMap<>();
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		BiFunction<String, String, String> mapper = (s1, s2) -> s1+s2;
		map.merge("a", "z", mapper);
		map.merge("b", "x", mapper);
		map.merge("c", "y", mapper);
		map.merge("d", "w", mapper);
		map.entrySet().forEach(System.out::println);
		
		// --------------------------------------/
		
		Map<Integer, Integer> map1 = new HashMap<>();
		for(int j =1; j <= 10;j++) {
			map1.put(j,  j * j);
		}
		System.out.println(map1.get(4));
		
		// --------------------------------------/
		
		Comparator<Integer> c = (g1, g2) -> g2 - g1;
		List<Integer> list = Arrays.asList(5, 4, 7, 1);
		Collections.sort(list, c);
		list.forEach(System.out::println);
		if (Collections.binarySearch(list, 1) < 0)
			System.out.println("undefined");
		
		
		// --------------------------------------/
		
		List<Integer> list1 = new LinkedList<>();
		list1.add(10);
		list1.add(12);
		list1.remove(1);
		System.out.println("list: ");
		list1.forEach(System.out::println);
		
		Queue<Integer> q = new LinkedList<>();
		q.add(10);
		q.add(12);
		q.remove(1);
		System.out.println("queue: ");
		q.forEach(System.out::println);
		
		
		// --------------------------------------/
		
		
		Map<Integer, Integer> map2 = new HashMap<>();
		map2.put(1, 10);
		map2.put(2, 20);
		map2.put(3, null);

		map2.merge(1, 3, (a,b) -> a + b);
		map2.merge(3, 3, (a,b) -> a + b);

		System.out.println("map2: ");
		System.out.println(map2);
		
		// --------------------------------------/
		
		boolean b = null instanceof Walk;
		System.out.println(b);
		
		// --------------------------------------/
		
		System.out.println("enum: "+Enum.valueOf(Seasons.class, "SPRING"));
		System.out.println(Seasons.valueOf("SUMMER"));
		
		
		// --------------------------------------/ 
		
		
		List<String> list3 = new ArrayList<>();
		list3.add("Atlanta");
		list3.add("Chicago");
		list3.add("New York");
		 
		list3.stream().filter(String::isEmpty).forEach(System.out::println);
		
		
		// --------------------------------------/ 
		
		
		ArrayDeque<Integer> dice = new ArrayDeque<>();
		dice.offer(3);
		dice.offer(2);
		dice.offer(4);
		//System.out.print(dice.stream().filter(n -> n != 4));
		
		
		// --------------------------------------/ 
		
		List<Integer> original = new ArrayList<>(Arrays.asList(1,2,3,4,5));
	
		List<Integer> copy1 = new CopyOnWriteArrayList<>(original);
		for(Integer w : copy1)
		   copy1.remove(w);
		System.out.println("copy1 is empty: "+copy1.isEmpty());
		
		 
		List<Integer> copy2 = Collections.synchronizedList(original);
//		for(Integer w : copy2) //ConcurrentModificationException
//		   copy2.remove(w);
		 
		List<Integer> copy3 = new ArrayList<>(original);
//		for(Integer w : copy3) //ConcurrentModificationException
//		   copy3.remove(w);
		 
		Queue<Integer> copy4 = new ConcurrentLinkedQueue<>(original);
		for(Integer w : copy4)
		   copy4.remove(w);
		System.out.println("copy4 is empty: "+copy4.isEmpty());
		
		// --------------------------------------/ 
		
	    ExecutorService serv = Executors.newCachedThreadPool();
	    
	    
	 // --------------------------------------/ 
	    
	    
	    List<Integer> ints = new ArrayList<>();
	    ints.add(2);
	    ints.add(3);
	    ints.add(1);
	    ints.add(1);
	   Integer strIn = ints.stream().reduce(1, (ik,  ikj)-> ik*ikj, (in, inj)->in*inj);
	   Integer strInParallel = ints.parallelStream().reduce(1, (ik,  ikj)-> ik*ikj, (in, inj)->in*inj);
	   System.out.println("strIn: "+strIn);
	   System.out.println("strIn: "+strInParallel);
	}
	
	public static <String> Integer f(Integer k) {
		return k;
	}
 
	// --------------------------------------/

	public static void addAndPrintItems(BlockingDeque<Integer> deque) throws InterruptedException {
		deque.offer(103);
		try {
			deque.offerFirst(20, 1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			deque.offerLast(85, 7, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(deque.pollFirst(200, TimeUnit.NANOSECONDS));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(" " + deque.pollLast(1, TimeUnit.MINUTES));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// --------------------------------------/

		ExecutorService service = Executors.newScheduledThreadPool(10);
		Future<?> f = service.submit(()->System.out.println(""));
		try {
			System.out.println(f.get());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DoubleStream.of(3.14159, 2.71828) // b1
				.forEach(c -> service.submit( // b2
						() -> System.out.println(10 * c)));
		service.execute(() -> System.out.println("Printed")); // b4
		if (service != null)
			service.shutdown();

		
		
		Function<Integer, Integer> ff = a -> {int s;System.out.println("a: "+a);return a;};
		BiFunction<Integer, Integer, Integer> bf = (Integer a, Integer b) -> a*b - a/b;
		System.out.println("function: "+ff.apply(20));
		
		
		List<String> sl = new ArrayList<>();
		sl.add("abc");
		sl.add("ab");
		sl.add("ac");
		Consumer<List<String>> cl = l -> l.forEach(System.out::println);
		
		sl.add("324");
		cl.accept(sl);
	}
	
	public static Integer performCount(int exhibitNumber) {
		// IMPLEMENTATION OMITTED
		return 1;
	}

	public static void printResults(Future<?> f) {
		try {
			System.out.println(f.get()); // o1
		} catch (Exception e) {
			System.out.println("Exception!");
		}
		
		Walk walk = new Walk();
		System.out.println(walk.stepLength);
		
	}
}


enum Animal implements Alive {
	CAT(Boolean.FALSE),
	DOG(true);
	boolean b;
	Animal (boolean b) {
		this.b = b;
	}
	public boolean isAlive() {
		return b;
	}
}

interface Alive {
	boolean isAlive();
}


enum Seasons {
	SUMMER("") {
		public void printHours() {System.out.println("09:00 - 20:00");}
		void printName() {}
		@Override
		char getFirstChar() {return name().charAt(name().length() - 1);}
},
	AUTUMN(""){
		public void printHours() {System.out.println("09:00 - 20:00");
	}
		void printName() {}
},
	WINTER(""){
		public void printHours() {System.out.println("09:00 - 20:00");
	}
		void printName() {}
},
	SPRING(""){
		public void printHours() {System.out.println("09:00 - 20:00");
	}
		void printName() {}
};
	
	String s;
	
	Seasons(String s) { //only private is allowed
		this.s = s;
	}
	
	public String getS() {
		return this.s;
	}
	
	public abstract void printHours();
	public static void p() {};
	abstract void printName(); // access modifier can be one of public or protected
	char getFirstChar() {return name().charAt(0);}
}


class Point {
    private int x = 0, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Point() {
    	this(0, 0);
    }
    
    
}

