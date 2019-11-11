/* Do not change this file for the lab-- you won't be submitting it! */
public class Motor {

	/* These are the private fields of the Motor object.
	 * This is where the aspects of the Motor are saved. */
	private int speed = 0;

	public Motor() {
		/* This constructor does not need to do anything,
		 * since the starting value for speed is already set on line 6 */
	}

	/** Increase the speed of the motor */
	public void increaseSpeed() {
		speed++;
	}

	/** Decrease the speed of the motor */
	public void decreaseSpeed() {
		if(speed > 0) {
			speed--;
		}
	}

	/** Return a String representation of the motor.
	 * @return String as the speed, e.g. "5 MPH" */
	@Override
	public String toString() {
		return speed + " MPH";
	}

	/** Get the speed of the motor
	 * @return Integer value of the speed */
	public int getSpeed() {
		return speed;
	}
	
}