package com.java.play;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.System.out;

public class ConcHashMapS {
	private static ConcurrentHashMap<Integer, String> chm = new ConcurrentHashMap<>();
	private static HashMap<Integer, String> hm = new HashMap<>();
	
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(6);
		
		String s = "reel";
		String gh = "ndu";
		String nb = "buvcf";
		
		es.submit(()->chm.put(s.length(), s));
		es.submit(()->chm.putIfAbsent(gh.length(), gh));
		es.submit(()->chm.computeIfAbsent(gh.length()+6, a->a+""+gh));
		//chm.put(45, null); //NullPointerException
		Collection<String> vals = chm.values();
		es.submit(()->chm.put(nb.length(), nb));
		out.println("vals: ");
		vals.forEach(out::println);
		es.submit(()->chm.forEach((k, v)->out.println("k: "+k+" v: "+v)));
		
		out.println("-------------------");
		
		es.submit(()->hm.put(s.length(), s));
		es.submit(()->hm.putIfAbsent(gh.length(), gh));
		es.submit(()->hm.computeIfAbsent(gh.length()+6, a->a+""+gh));
		hm.put(45, null); //doesn't throw NullPointerException
		vals = hm.values();
		es.submit(()->hm.put(nb.length(), nb));
		out.println("vals: ");
		vals.forEach(out::println);
		es.submit(()->hm.forEach((k, v)->out.println("k: "+k+" v: "+v)));
		
		double fr= 5.4;
		if (fr > 5) {
			
		}
		
//		for(;;) {
//			out.println("here");
			if (es != null) es.shutdown();
	//	}
		
		ConcHashMapS sc = new ConcHashMapS();
		ConcHashMapS sdc = new ConcHashMapS();
		out.println("sc equals sdc: "+sc.equals(sdc));
		out.println("sc equals obj: "+sc.equals(new Object()));
	}
	
//	boolean equals(ConcHashMapS sdcx) {
//		return false;
//	}
}
