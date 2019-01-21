package com.java.play;

import static java.lang.System.out;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class ObjectStreamSample {
	public static List<Pet> getAnimals(File dataFile) throws IOException, ClassNotFoundException {
		List<Pet> animals = new ArrayList<Pet>();
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {
			while (true) {
				Object object = in.readObject();
				if (object instanceof Pet)
					animals.add((Pet) object);
			}
		} catch (EOFException e) {
			// File end reached
		}
		return animals;
	}

	public static void createAnimalsFile(List<Pet> animals, File dataFile) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(dataFile)))) {
			for (Pet animal : animals)
				out.writeObject(animal);
		}
	}

	private static String formatMe(ZonedDateTime obj) {
		DateTimeFormatter f = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
		return f.format(obj);
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		List<Pet> animals = new ArrayList<Pet>();
		animals.add(new Pet("Tommy Tiger", 5, 'T'));
		animals.add(new Pet("Peter Penguin", 8, 'P'));
		File dataFile = new File("animal.data");
		createAnimalsFile(animals, dataFile);
		System.out.println(getAnimals(dataFile));

		Console console = System.console();
		if (console != null) {
			console.writer().printf("%s%f", "a", 3.4f);
			console.writer().format("s%d", "f", 4);
		}

		Path p = Paths.get("fe");
		//Stream<Path> sl = Files.walk(p);
	//	Stream<Path> sp = Files.find(p, 10, (a, b) -> a.endsWith("e") && b.isRegularFile());
	//	Stream<Path> sm = Files.list(p);

	/*	try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(new File("text.txt")))) {

		} catch (EOFException e) {

		}*/

		// try (InputStream ps = new BufferedInputStream(new FileReader("")));
		// //BufferedInputStream takes a InputStream
		// as a parameter to the constructor, not a Reader

		byte[] data = new byte[] { 1, 2, 3, 4 };
		try (InputStream is = new ByteArrayInputStream(data)) {
			is.read(new byte[2]); // reads from the input stream and writes them to buffer passed as parameter
		}
		
		
		File ds= new File("des.data");

		List<Bob> b = new ArrayList<>();
		try (ObjectInputStream si = new ObjectInputStream(new FileInputStream(ds))) {
			while (true) {
				Object obj = si.readObject();
				if (obj instanceof Bob) {
					b.add((Bob)obj);
				}
			}
		} catch (EOFException e) {
			
		}
		
		b.forEach(d->out.print(d+"|"));

	}

	// File file = new File("/Earth");
	// System.out.print(file.getParent()
	// +" - "
	// +file.getParent().getParent()); } } // method getParent() is undefined for
	// object type String

}

class Pet implements Serializable {
	String name;
	int age;
	char acronym;

	public Pet(String name, int age, char acronym) {
		this.name = name;
		this.age = age;
		this.acronym = acronym;
	}
}
