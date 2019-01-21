package com.java.play;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class IOSample {
	public static void main(String[] args) throws IOException {
		Path pt = Paths.get("C:/IOSample/ex");
		Files.createDirectories(pt);
		out.println("pt elem count: "+pt.getNameCount());
		out.println("pt root: "+pt.getRoot());
		Path made = pt.subpath(0, pt.getNameCount() - 1);
		
		Path pp = Paths.get("moreIO");
		Path rp = made.resolve(pp);
		//out.println("rp: "+rp.toAbsolutePath()+" .... made: "+made.toAbsolutePath());
		rp = pp.resolve(made);
		out.println("rp: "+rp.toAbsolutePath()+" .... made: "+made.toAbsolutePath());
		out.println("made relative to rp: "+made.relativize(rp));
		out.println("rp relative to made: "+rp.relativize(made));
		out.println("rp: "+rp.toAbsolutePath()+" .... made: "+made.toAbsolutePath());
		out.println("made exists: "+Files.exists(made.toAbsolutePath()));
		out.println("made: "+made);
		out.println("pt root: "+pt.getRoot());
		
	//	FileVisitResult.TERMINATE;
		String glob = "glob:**";
		PathMatcher matcher = FileSystems.getDefault().getPathMatcher(glob);
		Path p = Paths.get("IOSample");
		boolean matched = matcher.matches(p);
		System.out.println("patch matcher matches: "+matched);
		glob.matches("");
		
//		Files.walkFileTree(made, new SimpleFileVisitor<Path>() {
//			@Override
//			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//					Files.delete(file);
//				return FileVisitResult.CONTINUE;
//			}
//		});
		
//		out.println("count: "+Files.walk(made, Integer.MAX_VALUE, FileVisitOption.FOLLOW_LINKS).count());
//		Files.createDirectories(made);

		Path file = pt.subpath(pt.getNameCount() - 1, pt.getNameCount());
		if (!Files.exists(file))
			Files.createFile(file);

		out.println("file exists: "+Files.exists(file));
		out.println("file is regular file: "+Files.isRegularFile(file));

		Path fl = Paths.get("fd", "oi.txt");
		out.println("fl exists: "+Files.exists(fl));
		out.println("fl: "+fl.toAbsolutePath());
		if (!Files.exists(fl.getParent()))
			Files.createDirectory(fl.getParent());
		if (!Files.exists(fl))
			Files.createFile(fl);
		out.println("Paths: " + fl + " exists: " + Files.exists(fl));

		BasicFileAttributes bs = Files.readAttributes(fl, BasicFileAttributes.class);
		out.println("fl is hidden: "+Files.isHidden(fl));
		DateTimeFormatter frt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		DateTimeFormatter fg = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		// out.println("creation time: "+ fg.format(bs.creationTime().toInstant()));
		// //UnsupportedTemporalTypeException: Unsupported field: Year
		out.println("creation time GMT: "
				+ frt.format(ZonedDateTime.ofInstant(bs.creationTime().toInstant(), ZoneId.of("GMT")))); // UnsupportedTemporalTypeException:
																										// Unsupported
																										// field:
																										// MonthOfYear
		out.println("this time zone: "
				+ frt.format(ZonedDateTime.ofInstant(bs.creationTime().toInstant(), ZoneId.systemDefault())));
		
		BasicFileAttributeView bv = Files.getFileAttributeView(fl, BasicFileAttributeView.class);
		out.println("attributes last modif time: "+bv.readAttributes().lastModifiedTime());
		bv.setTimes(FileTime.from(Instant.now()), FileTime.from(Instant.now()), 
				FileTime.from(Instant.now().minus(1, ChronoUnit.HOURS)));
		out.println("attributes last modif time: "+bv.readAttributes().lastModifiedTime());
		
		
		FileOutputStream as = new FileOutputStream(fl.toString());
		while (true) {
			byte[] b = new byte[] {104, 67, 87, 97, 123};
			as.write(b);
			if (Files.size(fl) > 10)
				break;
		}
		
		FileInputStream si  = new FileInputStream(fl.toString());
		out.println("read all lines from fl");
		Files.readAllLines(fl, StandardCharsets.US_ASCII).forEach(out::print);
		out.println();
		out.println("read lines from fl");
		 char[] ch = Files.lines(fl).collect(Collectors.toList()).get(0).toCharArray();
		for (char c:ch) {
			out.print((int)c+" ");
		}
			
	}
}
