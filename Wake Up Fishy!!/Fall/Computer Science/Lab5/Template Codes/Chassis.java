/* Do not change this file for the lab-- you won't be submitting it! */
public class Chassis {

	/* These are the private fields of the Chassis object. 
	 * This is where the aspects of the Chassis are saved. */
	private String model;

	public Chassis(String model) {
		this.model = model;
	}

	/** Returns a string representation of the chassis class
	 * @return String of the model */
	@Override
	public String toString() {
		return model;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}