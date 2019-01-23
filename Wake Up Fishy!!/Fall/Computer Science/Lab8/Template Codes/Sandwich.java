/**
 * A class representing a basic sandwich.
 */
public class Sandwich implements Edible {
    
    /**
     * Constant: How many bites can be taken from a Sandwich
     * before it's considered fully consumed?
     */
    public static final int MAX_BITES = 8;
    
    //Number of bites taken from this Sandwich
    private int numBites = 0;
    
    //Type of bread used to make this Sandwich
    protected String breadType;
    
    //Ingredients used to make this Sandwich=
    protected String components;
    
    //Create a plain Sandwich with nothing on it.
    public Sandwich() {
        this("Plain");
    }
    //Create a Sandwich with the specified bread type and nothing on it.
    public Sandwich(String breadType) {
        this(breadType, "nothing");
    }
    //Create a Sandwich with the specified bread type and components.
    public Sandwich(String breadType, String components) {
        this.breadType = breadType;
        this.components = components;
    }
    
    //@return the number of bites taken from this Sandwich
    public int getNumBites() {
        return numBites;
    }
    
    /**
     * @return the number of bites left in this Sandwich before it is considered eaten
     */
    public int getBitesLeft() {
        return MAX_BITES - numBites;
    }
    
    //See javadoc comments in Edible for takeBite & isEaten
    @Override
    public void takeBite() {
        if (isEaten()) {
            System.out.println("Sandwich is competely eaten!");
        } else {
            numBites++;
        }
    }
    
    @Override
    public boolean isEaten() {
        return getBitesLeft() <= 0;
    }
    
    //See the official Javadoc for Object#toString() by mousing over toString below in Eclipse
    @Override
    public String toString() {
        return String.format("A Sandwich made with %s bread and %s on it.", breadType, components);
    }
    
    //Basic getters & setters for breadType, components
    //(Probably should have made those private instead of protected...)
    public String getBreadType() {
        return breadType;
    }
    
    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }
    
    public String getComponents() {
        return components;
    }
    
    public void setComponents(String components) {
        this.components = components;
    }
}
