package com.java.play;

import static java.lang.System.out;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializationSample {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Path dg = Paths.get("des.data");
	//	Files.createFile(dg); -->not necessary
		
		//File ds = dg.toFile();
		File ds= new File("des.data");
		List<Bob> bb = Arrays.asList(new Bob(), new Bob());
		try (ObjectOutputStream so = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ds)))) {
			for (Bob bc : bb) {
				so.writeObject(bc);
			}
		}
		
		List<Bob> b = new ArrayList<>();
		try (ObjectInputStream si = new ObjectInputStream(new BufferedInputStream(new FileInputStream(ds)))) {
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

}

class Bob implements Serializable {
	static int ag;
	transient String s = "lo";
	String go = "go";
	
	{
		s=  "op";
	}
	Bob() {
		ag= 90;
		s = "ol";
		go = "re";
	}
	@Override
	public String toString() {
		return "Bob [s=" + s + ", go=" + go + ", ag=" + ag + "]";
	}
	
}
