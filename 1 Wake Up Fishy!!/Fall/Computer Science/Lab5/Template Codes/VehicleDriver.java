
public class VehicleDriver {

	public static void main(String[] args) {
		Motor motor = new Motor();
		Tire frontLeftTire = new Tire();
		Tire frontRightTire = new Tire();
		Tire backLeftTire = new Tire();
		Tire backRightTire = new Tire();
		Chassis chassis = new Chassis("Pontiac Sunfire");
		
		Vehicle vehicleObj = new Vehicle(frontLeftTire, frontRightTire, backRightTire, chassis);
  
		
		//1. Check the status of the vehicle and find out what's wrong with the vehicle
        System.out.println();
        vehicleObj.checkStatus();
        
        
        
        //2. "Repair" the vehicle
        vehicleObj.setMotor(motor);
        vehicleObj.checkStatus();
        
        //3. Check the status again and if you see "This vehicle is ready to go!", you can go ahead; otherwise please continue to "repair" the vehicle
        vehicleObj.setBackLeftTire(backLeftTire);
        vehicleObj.checkStatus();
		
		//4. Loop through 5 times and use random number generation to change the speed of the vehicle. Use an if statement utilizing a random number to
		//   decide whether to increase or decrease the speed of the car. Print out the speed of the vehicle in each loop.
        System.out.println();
        int rand;
        for(int i=0; i<5; i++) {
             rand = (int)(Math.random()*20);
             do {
                 if (rand > vehicleObj.getSpeed()) {
                     vehicleObj.pressGas();
                 } else if (rand < vehicleObj.getSpeed()) {
                     vehicleObj.pressBrake();
                 }
             } while(rand!=vehicleObj.getSpeed());
             System.out.println("Current Speed of this vehicle is: "+vehicleObj.getMotor());
        }

		//5. Call some method and metaphorically "kick" the back left tire of the vehicle
        System.out.println();
        vehicleObj.getBackLeftTire().slashTire();
        System.out.println("The back left tire is "+vehicleObj.getBackLeftTire());
		
		//6. Implement the toString() method in the Vehicle class and print the status of all parts of the vehicle here
        System.out.println("\n"+vehicleObj+"\n");
        
	}
}
