package com.java.play;

import static java.lang.System.out;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class NIOSample {
	public static void main(String[] args) throws IOException {
		Path p = Paths.get("C://IOSample/NIO/N");
		Path rp = Paths.get("ex");
		Path pr = Paths.get("te");
		Path r = Paths.get("C://IOSample/NI");
		Path empty = Paths.get("");
		
		//out.println("resolve: "+p.resolve(rp)); //throws IllegalArgumentException
		//out.println("resolve: "+rp.resolve(p)); //throws IllegalArgumentException - paths must be either both relative,
												//or both absolute

		out.println("resolve p r: "+r.resolve(p)); //if the argument is an absolute path it is returned
		out.println("resolve rp pr: "+rp.resolve(pr)); //concatenates the paths
		out.println("resolve empty r:"+empty.resolve(r)); //returns the argument
		out.println("resolve r empty:"+r.resolve(empty));//the path on which it is called is returned
		
		out.println("resolve empty rp:"+empty.resolve(rp));//returns the argument
		out.println("resolve rp empty:"+rp.resolve(empty));//the path on which it is called is returned
		
		out.println("relativize r p:"+r.relativize(p));
		out.println("relativize p r:"+p.relativize(r));
		
		out.println("relativize rp pr: "+rp.relativize(pr));
		out.println("relativize pr rp: "+pr.relativize(rp));
		
	//	out.println("relativize r pr:"+ r.relativize(pr)); //IllegalArgumentException
		
		//Path dp = Paths.get("//def"); //java.nio.file.InvalidPathException: UNC path is missing sharename: //def
		Path dp = Paths.get("\\def"); 
		Path dc = Paths.get("\\dep/des");
		Path dfv = Paths.get("def//dmi");
		
		
		out.println("dp relativize dc: "+dp.relativize(dc));
		out.println("dfv resolve dp: "+dfv.resolve(rp));
		
	}
	
	public static void copy(String records1, String records2) throws IOException {
        try (
                InputStream is = new FileInputStream(records1);
                OutputStream os = new FileOutputStream(records2);) {
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

        } catch (FileNotFoundException| SocketException |java.io.InvalidClassException e) {
            e.printStackTrace();
        }
    }
}
