package com.java.play;

import java.io.*;

class SerialSample extends IOStreamSample implements Serializable {
		int ratings;
		transient int id = 87437;
		String description;
		static String place = "pl";
		SerialSample(int ratings, String description) {
			super("set");
			this.ratings = ratings;
			this.description = description;
		}
		
	
		@Override
		public String toString() {
			return "SerialSample [ratings=" + ratings + ", description=" + description + ", name=" + name
					+  ", place=" + place + ", id=" + id +"]" ;
		}
}

public class IOStreamSample {
	String name = "ged";
	IOStreamSample(String name) {
		this.name = name;
	}
	IOStreamSample() {
		name = "empty";
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File file = new File("serial.txt");
		file.createNewFile();
		PrintStream w = new PrintStream(file);
		ObjectOutputStream s = new ObjectOutputStream(w);
		SerialSample as = new SerialSample(1, "des");
		as.place = "ret";
		as.id = 536;
		s.writeObject(as);
		s.close();
		
		
		FileInputStream f = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(f);
		IOStreamSample read = (IOStreamSample) in.readObject();
		in.close();
		System.out.println("read: "+read);
		SerialSample made = new SerialSample(4, "gub");
		made.place = "meda";
		System.out.println("made: "+made);
	}
}
