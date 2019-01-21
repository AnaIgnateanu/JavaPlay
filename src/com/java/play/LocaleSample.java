package com.java.play;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TooManyListenersException;
import java.util.TreeMap;

public class LocaleSample extends ListResourceBundle {
	private int count = 0;

	@Override
	protected Object[][] getContents() {
		class Cl { // no access modifiers are permitted, only abstract or final are permitted

		}
		return new Object[][] { { "count", count++ } };

	}

	public static void main(String[] args) {
		ResourceBundle rb = ResourceBundle.getBundle("LocaleSample");
		System.out.println(rb.getObject("count") + " " + rb.getObject("count"));

		Properties props = new Properties();
		TreeMap<String, String> tm = new TreeMap<>();
		tm.put("a", "1");
		tm.put("b", "2");
		tm.forEach((k, v) -> props.put(k, v));
		String s = (String) props.get("a");
		String st = props.getProperty("ab", "default");
		System.out.println(st);
		System.out.println(props.get("2"));

		Locale loc = new Locale("hi_IN");
		System.out.println(loc);

		ZonedDateTime zd = ZonedDateTime.parse("2020-05-04T08:05:00Z");
		System.out.println(zd.getMonth() + " " + zd.getDayOfMonth());

		Duration d = Duration.ofMinutes(65);

		LocalDate ld = LocalDate.of(2016, 4, 23);
		LocalDate kl = LocalDate.of(2018, 7, 8);
		Period pf = Period.between(ld, kl);
		System.out.println(pf.getMonths() + " " + pf.getYears());
		// Duration pt = Duration.between(ld, kl); //Compilation works
		// UnsupportedTemporalTypeException: Unsupported unit: Seconds

		ZoneId id = ZoneId.systemDefault();
		ZonedDateTime zsd = ZonedDateTime.now(id);
		System.out.println("now: " + zsd);
		ZonedDateTime dif = ZonedDateTime.ofInstant(zsd.toInstant(), ZoneId.of("Europe/Berlin"));
		System.out.println("dif: " + dif);

		ZonedDateTime diff = zsd.withZoneSameInstant(ZoneId.of("Europe/Berlin"));
		System.out.println("diff: " + diff);

		ZonedDateTime dift = ZonedDateTime.from(zsd);
		System.out.println("dift: " + dift);

		LocalDateTime ldd = LocalDateTime.of(2016, 4, 13, 23, 12, 10);
		ldd = ldd.plusDays(40).with(TemporalAdjusters.lastDayOfMonth()).with(TemporalAdjusters.firstDayOfNextMonth());
		System.out.println("ldd: " + ldd);

		LocalDate gg = LocalDate.of(2018, 12, 31);

		LocalDate ltt = LocalDate.of(2014, 5, 21);
		System.out.println("first day of next year: "+ltt.minusMonths(5).with(TemporalAdjusters.firstDayOfNextYear()));
		ltt = ltt.ofEpochDay(gg.plusDays(32).toEpochDay());
		LocalDate ll = LocalDate.ofEpochDay(gg.toEpochDay());
		System.out.println("ll: "+ll);
		System.out.println("ltt: " + ltt);
		ltt = ltt.minus(Period.ofDays(5));
		System.out.println("ltt: " + ltt);
		
		System.out.println("28.11.2017: "+LocalDate.of(2017, 11, 28).plusDays(3).with(TemporalAdjusters.firstDayOfNextMonth()));

		List<String> ls = (List) new ArrayList<>();
		ls.size();
		String a = "";
		a.length();
		Object[] o = new Object[] {};
		int i = o.length;
	}

	private class Cls {

	}

	protected class Ls {

	}

}

class Asd { // only public, abstract & final are permitted

}
