package com.java.play;

import static java.lang.System.out;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateTimeSample {

	public static void main(String[] args) {
		LocalDate ld = LocalDate.of(2017, Month.NOVEMBER, 23);
		Period pd = Period.of(10, 9, 12);
		Period pdd = Period.ofMonths(2);
		Period pddd = Period.ofDays(2);
	
		LocalTime lt = LocalTime.of(12, 10, 13);
		Duration pt = Duration.of(1, ChronoUnit.HALF_DAYS);
		Duration ptt = Duration.ofDays(2);
		System.out.println("ptt.getSeconds(): "+ptt.getSeconds());

		System.out.println("ld.minus(pd): " + ld.minus(pd));
		System.out.println("ld.plus(pdd): " + ld.plus(pdd));
		System.out.println("lt.minus(pd): "); // +lt.minus(pd)); //UnsupportedTemporalTypeException: UnsupportedUnit:
												// Months
		System.out.println("lt.plus(pdd): ");// +lt.plus(pdd)); //UnsupportedTemporalTypeException: UnsupportedUnit:
												// Months

		System.out.println("ld.minus(pt): ");// +ld.minus(pt)); //UnsupportedTemporalTypeException: UnsupportedUnit:
												// Seconds
		System.out.println("ld.plus(ptt): ");// +ld.plus(ptt)); //UnsupportedTemporalTypeException: UnsupportedUnit:
												// Seconds
		System.out.println("lt.minus(pt): " + lt.minus(pt));
		System.out.println("lt.plus(ptt): " + lt.plus(ptt));

		// LocalDateTime ldt = LocalDateTime.of(2015, Month.FEBRUARY, 30, 10, 10);
		// //DateTimeException: Invalid date 'FEBRUARY 30'
		LocalDateTime ldt = LocalDateTime.of(2015, Month.FEBRUARY, 20, 10, 10);
		System.out.println("ldt: " + ldt);

		System.out.println("ldt.minus(pd): " + ldt.minus(pd));
		System.out.println("ldt.plus(pdd): " + ldt.plus(pdd));
		System.out.println("ldt.minus(pt): " + lt.minus(pt));
		System.out.println("ldt.plus(ptt): " + lt.plus(ptt));

		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		ZonedDateTime zddt = ZonedDateTime.of(ldt, ZoneId.of("GMT"));
		System.out.println("systemDefault - zdt: " + zdt);
		System.out.println("GMT - zddt: " + zddt);
		
		ZonedDateTime ins = ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
		ZonedDateTime ins2 = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("GMT"));
		System.out.println("systemDefault - ins: " + ins);
		System.out.println("GMT - ins2: " + ins2);
		
	//	ZonedDateTime zdtt = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("hi")); //ZoneRulesException:  Unknown time-zone ID: hi
		ZonedDateTime zdtt = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("US/Eastern")); 
		System.out.println("zdtt: " + zdtt);
		
		ZonedDateTime zdtd = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Europe/Stockholm")); 
		System.out.println("zdtd: " + zdtd);
		
		NumberFormat nf = NumberFormat.getInstance(Locale.CHINESE);
		NumberFormat nf2 = NumberFormat.getInstance();
		String n = "232546.45.212lfdew546";
		String n2 = "232546.45.212lfdew546";
		try {
			Number d = nf.parse(n);
			Number d2 = nf2.parse(n2);
			System.out.println("n: "+d);
			System.out.println("n2: "+d2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
		DateTimeFormatter dt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		System.out.println("dtf: "+dtf.format(zdtt));
		System.out.println("dt: "+dt.format(zdtt));
		
		LocalDate lc = LocalDate.of(2018, Month.JULY, 3);
		LocalDate lft = LocalDate.of(2018, Month.JULY, 4);
		System.out.println("lc is before lft: "+lc.isBefore(lft));
		System.out.println("lc is before lc: "+lc.isBefore(lc));
		
	      LocalDate polarBearDay = LocalDate.of(2017, 2, 27);
	    //  LocalDateTime ll = LocalDateTime.of(polarBearDay,  ); of(LocalDate, LocalTime)
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy dd MMM");
	      System.out.println(polarBearDay.format(formatter));
		
	      
	      Instant instant = zdt.toInstant();
	      instant = instant.plus(1, ChronoUnit.DAYS);
	      System.out.println("instant: "+instant);
	      System.out.println("zonedDateTime month value: "+zdt.getMonthValue());
	      
	      System.out.println("chaining: "+polarBearDay.plusDays(2).minusDays(3).minusDays(20));
	      
	      
	      MonthDay md = MonthDay.of(zdt.getMonth(), zdt.getDayOfMonth());
	      out.println("zdt: "+zdt);
	      out.println("month day: "+md);
	      
	      
	      Period pp = Period.ofDays(56);
	      out.println("period of zero days: "+pp);
	      Period mpd = Period.ofMonths(0);
	      out.println("period of zero months: "+mpd);
	      Duration dtp = Duration.ofDays(0);
	      out.println("duration of zero days: "+dtp);
	      Duration dpd = Duration.ofMillis(2434534);
	      out.println("duration of 243453954 millis: "+dpd);
	}

}
