package com.java.play;

import static java.lang.System.out;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoreStreams {
	public static void main(String[] args) throws IOException, SQLException {
		//--------------Different ways to determine the max number in a collection----------------
		
		List<Integer> lst = Arrays.asList(12, 45, 67, 32, 213, 89);
		int i = lst.stream().reduce((a, b)->a>b?a:b).get();
		out.println("get max with reduce: "+i);
		
		i = lst.stream().reduce(Integer.MIN_VALUE, (a,b)->a>b?a:b);
		out.println("get max with reduce&identity: "+i);
		
		i = lst.stream().max(Comparator.comparing(x->x)).get();
		out.println("get max with max(Comparator): "+i);
		
		i = lst.stream().mapToInt(x->x).max().getAsInt();
		out.println("get max with max on intstream: "+i);
		
		i = lst.stream().max(Comparator.naturalOrder()).get();
		out.println("get max with max & Comparator: "+i);
		
		List<Student> st=  Arrays.asList(new Student("S1", "C"), new Student("S2", "A"), new Student("S3", "B"), new Student("S4", "A"),
				new Student("S5", "C"), new Student("S6", "B"));
		
		Map<String, Map<String, List<Student>>> dc = st.stream()
				.collect(Collectors.groupingBy(Student::getGrade, Collectors.groupingBy(Student::getName, Collectors.toList())));
		dc.entrySet().stream().map(c->c.toString()+", ").forEach(out::print);
		
		out.println();
		

		Map<String, List<String>> dv = st.stream()
				.collect(Collectors.groupingBy(Student::getGrade, Collectors.mapping(Student::getName, Collectors.toList())));
		dv.entrySet().stream().map(g->g.toString()+", ").forEach(out::print);
		out.println();
		
		Map<String, List<Student>> df =  st.stream()
				.collect(Collectors.groupingBy(Student::getGrade, Collectors.toList()));
		df.entrySet().stream().map(fg->fg.toString()+", ").forEach(out::print);
		
		out.println();
		new A();
		new TestClass().new A();
		
		
		List<Character> ch = Arrays.asList('n', 'y', 'k', 't', 'p');
		
		
		ch.stream().map(x->x.charValue()).mapToInt(x->x).forEach(cv->out.print((char)cv+", "));
		out.println();
		ch.stream().flatMap(as->Stream.of(as.charValue())).forEach(bn->out.print(bn+", "));
		out.println();
		out.print("partitioning by: ");
		Map<Boolean, List<Character>> lbc = ch.stream().collect(Collectors.partitioningBy(de->de.equals('w')));
		lbc.entrySet().forEach(out::print);
		out.println();
		out.print("partitioning by: ");
		Map<Boolean, Set<Character>> sb = ch.stream().collect(Collectors.partitioningBy(y->y.equals('f'), Collectors.toSet()));
		sb.entrySet().forEach(out::print);
		out.println();
		
		out.print("grouping by: ");
		ch.stream().collect(Collectors.groupingBy(hg->hg.charValue()=='w'?true:false, Collectors.toList())).forEach((op, bn)->out.print(bn+" "+op+", "));
		out.println();
		ch.stream().collect(()->'f', (tr, uy)->out.print((char)tr+"\\"+(char)uy), (nh, bf)->out.print((char)nh+"!"+(char)bf));
		out.println();
		out.println(ch.stream().map(vb->vb.toString()).collect(Collectors.joining()));
		out.println(ch.stream().map(mj->mj+"").collect(Collectors.joining("\\")));
		
		
		
		//-------------------getting the max of a stream of chars------------------------------
		out.println(" max by with natural ordering: "+ch.stream().mapToInt(x->(int)x).peek(o->out.print(""))
				.mapToObj(x->(char)x).collect(Collectors.maxBy(Comparator.naturalOrder())).get());
		
		out.println(" max by with comparing: "+ch.stream()
				.collect(Collectors.maxBy(Comparator.comparing(x->x))).get());
		
		
		out.println("max with summary statistics: "+(char)ch.stream().mapToInt(x->(int)x).summaryStatistics().getMax());
		out.println("max with summarizing int: "+(char)ch.stream().collect(Collectors.summarizingInt(x->(int)x)).getMax());
		
		
		
		//-------------------operations on stream of ints
		out.println("summing int: "+(char)(ch.stream().collect(Collectors.summingInt(x->(int)x))/ch.stream().count()));
		out.println("averaging int: "+(char)(ch.stream().collect(Collectors.averagingInt(x->(int)x)).doubleValue()));
		
		new Student("", "").g();
	}
}

class Student {
	String grade;
	String name;
	
	public String getGrade() {
		return grade;
	}
	
	public void g() throws IOException, SQLException {
		try {
			if (this.grade.equals("1"))
				throw new IOException();
			else if (this.grade.equals(""))
				throw new SQLException();
		} catch (IOException | SQLException e) {
			out.println("e : ");
			//e.printStackTrace();
			//throw e;
		}
	}

	@Override
	public String toString() {
		return "St["+name+"-" + grade+"]";
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	Student(String name, String grade) {
		this.grade = grade;
		this.name = name;
	}
}


class A
{
	A() {out.println("m");}
}
class TestClass
{
   public class A
   {
	   A() {super();out.println("ov");}
      public void m() {TestClass.this.new B(); }
      
   }
   class B extends A
   {
	   
   }
   void f() {
	   this.new B();
	   new B();
   }
   public static void main(String args[])
   {
	  new com.java.play.A();
      new TestClass().new A() {   public void m() { } };
      
      new TestClass().new A().m();
      new TestClass().f();
   }
}
