/**
 * Interface for edible objects.
 */
public interface Edible {
    /**
     * Attempt to a bite of this Edible object.
     */
    public void takeBite();
    
    /**
     * Determine whether this Edible object has been completely eaten.
     */
    public boolean isEaten();
}
