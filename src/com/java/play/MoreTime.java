package com.java.play;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class MoreTime {
	public static void main(String[] args) {
		out.println("duration of 22 days: "+Duration.ofDays(22));
		out.println("duration of 0 days: "+Duration.ofDays(0));
		out.println("chained duration: "+Duration.ofNanos(23).of(45, ChronoUnit.MINUTES));
		
		
	}
}
