
public class Vehicle {
    
    /* These are the private fields of the Vehicle object.
     * This is where the components of the car are saved. */
    private Motor motor;
    
    private Tire frontLeftTire, frontRightTire, backLeftTire, backRightTire;
    
    private Chassis chassis;
    
    /**
     * Construct a Vehicle... mostly.
     */
    public Vehicle(Tire frontLeftTire, Tire frontRightTire, Tire backRightTire, Chassis chassis) {
        this.frontLeftTire = frontLeftTire;
        this.frontRightTire = frontRightTire;
        this.backRightTire = backRightTire;
        this.chassis = chassis;
    }

	/* These methods perform an action on the car. */
    
    /**
     * Increase the speed of the motor
     */
    public void pressGas() {
        if (motor != null) {
            motor.increaseSpeed();
        }
    }
    
    /**
     * Decrease the speed of the motor
     */
    public void pressBrake() {
        if (motor != null) {
            motor.decreaseSpeed();
        }
    }
    
    /**
     * Check the speed of the car
     *
     * @return Integer value of the speed
     */
    public int getSpeed() {
        if (motor != null) {
            return motor.getSpeed();
        } else {
            return 0;
        }
    }
    
    /**
     * This method returns an integer for what part of the car is missing.
     * It also prints the missing piece, if any.
     * 0 - Everything is good.
     * 1 - Motor, 2 - Front left wheel, 3 - Front right wheel, 4 - Back right wheel, 5 - Back left wheel, 6 - Chassis
     *
     * @return Integer value representing missing part
     */
    public int checkStatus() {
        int status = 0;
        
        //Check each part until a part is missing.
        if (motor == null) {
            System.out.println("The motor is missing!");
            status = 1;
        } else if (frontLeftTire == null) {
            System.out.println("The front left tire is missing!");
            status = 2;
        } else if (frontRightTire == null) {
            System.out.println("The front right tire is missing!");
            status = 3;
        } else if (backLeftTire == null) {
            System.out.println("The back left tire is missing!");
            status = 4;
        } else if (backRightTire == null) {
            System.out.println("The back right tire is missing!");
            status = 5;
        } else if (chassis == null) {
            System.out.println("The chassis is missing!");
            status = 6;
        } else {
            System.out.println("This vehicle is ready to go!");
            //status == 0 at the start of this method
        }
        
        return status;
    }
    
    /**
     * Returns a string representation of the vehicle object
     *
     * @return List of the parts and their status.
     * Ex: Left wheel is not popped. Chassis is a Ford. etc
     */
    @Override
    public String toString() {
        String output="The vehicle's motor is moving us at "+getMotor();
        output+="\nThe back left tire is "+getBackLeftTire()+"\nThe back right tire is "+getBackRightTire();
        output+="\nThe front left tire is "+getFrontLeftTire()+"\nThe front right tire is "+getFrontRightTire();
        output+="\nThe chassis is a "+getChassis();
        
        return output;
    }
    
    
    /* These are the getters and setters for the private aspects.
     * These are used in place of the variables themselves, as they are set to private.
     * If you want to use the motor from outside the class, you need to call vehicle.getMotor(); */
    public Motor getMotor() {
        return motor;
    }
    
    public void setMotor(Motor motor) {
        this.motor = motor;
    }
    
    public Tire getFrontLeftTire() {
        return frontLeftTire;
    }
    
    public void setFrontLeftTire(Tire frontLeftTire) {
        this.frontLeftTire = frontLeftTire;
    }
    
    public Tire getFrontRightTire() {
        return frontRightTire;
    }
    
    public void setFrontRightTire(Tire frontRightTire) {
        this.frontRightTire = frontRightTire;
    }
    
    public Tire getBackLeftTire() {
        return backLeftTire;
    }
    
    public void setBackLeftTire(Tire backLeftTire) {
        this.backLeftTire = backLeftTire;
    }
    
    public Tire getBackRightTire() {
        return backRightTire;
    }
    
    public void setBackRightTire(Tire backRightTire) {
        this.backRightTire = backRightTire;
    }
    
    public Chassis getChassis() {
        return chassis;
    }
    
    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }
    
}
