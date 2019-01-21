package com.java.play;

public class WhaleSharkException extends Exception {
	public WhaleSharkException() {
		super("Friendly shark!");
	}

	public WhaleSharkException(String message) {
		super(new Exception(new WhaleSharkException()));
	}

	public WhaleSharkException(Exception cause) {
		assert 0 == 1;
		if (true)
			if (!"cat".equals("kat"))
				new IllegalArgumentException("message");
	}
}

interface Closing {
	void close() throws Exception;
}

class Shelf implements Closing, AutoCloseable {

	@Override
	public void close() throws Exception {


	}

}

class Step {
	static {
	      try (Shelf shelf = new Shelf()) {
	         throw new IllegalArgumentException();
	      } catch (Exception e) {
	      } //catch (IllegalArgumentException e) {
	//      }
	finally {
	      //   shelf.close();
	      }
	   }
}