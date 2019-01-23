/**
 * A class representing a submarine sandwich.
 */
public class SubmarineSandwich extends Sandwich {
    private int length; // stands for the length of current submarine sandwich instance
    
    //default constructor. default value for length is 12 (because I am a pig)
    public SubmarineSandwich() {
        super();
        this.length = 12;
    }
    
    //if a length is specified
    public SubmarineSandwich(int length) {
        super();
        this.length = length;
    }
    
    // if length and bread type are specified
    public SubmarineSandwich(int length, String breadType) {
        super(breadType);
        this.length = length;
    }
    
    //FULL BLOWN CONSTRUCTOR
    public SubmarineSandwich(int length, String breadType, String components) {
        super(breadType, components);
        this.length = length;
    }
    
    /**
     * return a integer type value standing for length of current sandwich, for example, 6 inch or 12 inch.
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Overrides the Sandwich.java getBitesLeft() method
     * Subs require 2 bites per inch. They are fat.
     * return a integer type value standing for bites left to finish the sandwich.
     */
    @Override
    public int getBitesLeft() {
        return (length * 2) - getNumBites();
    }
    
    /**
     * Overrides the Sandwich.java toString() method
     * a better way to do this would be to have the Sandwich class include the type of sandwich in its own toString
     * return a string including the details of the sandwich
     */
    @Override
    public String toString() {
        return String.format("A Submarine Sandwich is %d inches long made with %s bread and %s on it.", length, breadType, components);
    }
}
