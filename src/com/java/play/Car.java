package com.java.play;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import static java.lang.System.out;

public class Car {
	private String model;
	private int year;

	public Car(String name, int year) {
		this.model = name;
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return model;
	}

	public static void main(String... make) {
		List<Car> cars = new ArrayList<>();
		cars.add(new Car("Mustang", 1967));
		cars.add(new Car("Thunderbird", 1967));
		cars.add(new Car("Escort", 1975));
		ConcurrentMap<Integer, List<Car>> map = cars.stream().collect(Collectors.groupingByConcurrent(Car::getYear));
		System.out.println("map:" + map);

		List<String> names = new ArrayList<>();
		names.add("A");
		names.add("B");
		names.add("C");

		String d = names.parallelStream().reduce("a", (x, y) -> x + y, String::concat);
		System.out.println("d: " + d);

		String w = names.parallelStream().reduce((v, g) -> v + g).get();
		System.out.println("w: " + w);

		ExecutorService es = Executors.newSingleThreadExecutor();
		double ret = 6.7;
		Future<Object> o = es.submit(() -> ret);
		Future<?> ev = es.submit(() -> System.out.println("e "));
		try {
			System.out.println("o: " + o.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		ScheduledExecutorService esr = Executors.newSingleThreadScheduledExecutor();

		ScheduledFuture<?> y = esr.scheduleAtFixedRate(() -> out.println("y"), 10, 10, TimeUnit.MILLISECONDS);
		y.cancel(true);


		Optional<String> op = Optional.empty();
		Optional<String> op1 = Optional.ofNullable(null);

		System.out.println("empty(): " + op);
		System.out.println("ofNullable: " + op1);
		System.out.println("empty() is present: " + op.isPresent());
		System.out.println("ofNullable is present: " + op1.isPresent());

		IntStream ints = IntStream.empty();
		IntStream moreInts = IntStream.of(66, 77, 88);
		Stream.of(ints, moreInts).flatMapToInt(x -> x).forEach(System.out::println);

		LongStream stream = LongStream.of(9);
		stream.mapToDouble(p -> p).forEach(System.out::println);

		Set<String> set = new HashSet<>();
		set.add("tire-");
		List<String> list = new LinkedList<>();
		Deque<String> queue = new ArrayDeque<>();
		queue.push("wheel-");
		Stream.of(set, list, queue).map(x -> x).forEach(System.out::print);

		System.out.println();

		List<String> ls = new ArrayList<>();
		Deque<String> dq = new ArrayDeque<>();

		System.out.println("deque");
		dq.push("76");
		dq.push("879");
		Stream.of(dq).flatMap(x -> x.stream()).forEach(System.out::print);

		System.out.println();

		Stream.of(dq).filter(x -> !x.isEmpty()).map(x -> x).forEach(System.out::print);
		System.out.println();

		System.out.println("list");
		Stream.of(ls).flatMap(x -> x.stream()).forEach(System.out::print);

		synchronized (Car.class) {
			AtomicInteger aix = new AtomicInteger(45);

		}
	}
}
