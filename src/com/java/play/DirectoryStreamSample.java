package com.java.play;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.function.Supplier;

public class DirectoryStreamSample {
	public static void main(String[] args) {
		Path p = Paths.get("./bin/com/ocp");
		System.out.println(p.toAbsolutePath().normalize());
		try (DirectoryStream<Path> ds = Files.newDirectoryStream(p, "*.{class}")) {
			for(Path d:ds) {
				System.out.println("d: "+d);
			}
		} catch (IOException e) {
			System.err.println("IO error");
		}
		
		Supplier<LocalDate> now  = LocalDate::now;
		Supplier<LocalDate> now1 = ()-> LocalDate.now();
	}
}
