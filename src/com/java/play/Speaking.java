package com.java.play;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Ballot {
	   private String name;
	   private int judgeNumber;
	   private int score;
	 
	   public Ballot(String name, int judgeNumber, int score) {
	      this.setName(name);
	      this.judgeNumber = judgeNumber;
	      this.setScore(score);
	   }
	   // all getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	}
	 
	public class Speaking {
	   public static void main(String[] args) throws SQLException {
	      Stream<Ballot> ballots = Stream.of(
	         new Ballot("Mario", 1, 10),
	         new Ballot("Christina", 1, 8),
	         new Ballot("Mario", 2, 9),
	         new Ballot("Christina", 2, 8)
	      );
	 
	      Map<String, Integer> scores = ballots.collect
	    		  (Collectors.groupingBy(Ballot::getName, Collectors.summingInt(Ballot::getScore)));
	      System.out.println(scores.get("Mario"));
	      
	      try (Connection conn = DriverManager.getConnection("jdbc:mysql:library");
	    		  Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	    		  ResultSet rs = st.executeQuery("select * from books")) {
	    	  rs.afterLast();
	    	  rs.updateInt(1, 1); //int columnIndex, int value
	    	  rs.updateRow();
	    	  rs.next();
	      }
	      System.out.printf(null);
//	      Connection conn = DriverManager.getConnection("");
//	      
//	      DatabaseMetaData dbm = conn.getMetaData();
	    		  
	  }
	}
