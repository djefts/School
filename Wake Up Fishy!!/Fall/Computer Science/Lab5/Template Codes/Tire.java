/* Do not change this file for the lab-- you won't be submitting it! */
public class Tire {
	
	/* These are the private fields of the wheel object. 
	 * This is where the aspects of the Tire are saved. */
	private boolean popped;

	public Tire() {
		popped = false;
	}

	/* Methods of the wheel class */

	/** Returns a string value of the status of the tire */
	@Override
	public String toString() {
		/* This is commonly known as "the" ternary operator, or "?:"
		 * If the boolean before the question mark is true,
		 * the first value after it (between '?' and ':') is used,
		 * otherwise the one after the colon is used.
		 * It's a miniature if/else statement! */
		return popped ? "popped!" : "not popped.";
	}

	/** Sets the popped value to false. */
	public void patchTire() {
		popped = false;
	}

	/** Sets the popped value to true. */
	public void slashTire() {
		popped = true;
	}

	/* Getters and setters */
	public boolean isPopped() {
		return popped;
	}

}