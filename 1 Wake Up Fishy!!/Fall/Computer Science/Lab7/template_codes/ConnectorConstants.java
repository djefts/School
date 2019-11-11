/** Structurally interesting code for the UML lab.
 * This particular class is a bundle of bad ideas.
 * The best way to do this sort of thing would be an enum.
 * A step up would be to have these all be final,
 * and make the class an interface (without any methods) instead.
 * 
 * To reiterate: THIS CLASS HAS POOR DESIGN but makes for easier UML.
 *   
 * @author bassat 
 * @since Spring 2017
 */
public class ConnectorConstants {
	//Constants. Would be final, but adding <<final>> to all of these would be annoying...
	//Actually would be better expressed as an enumeration,
	//but that's outside of the course scope.
	//Go to https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
	//if you're thinking of doing something like this in your project.
	public static int DEPENDENCY = 0;
	public static int ASSOCIATION = 1;
	public static int REALIZATION = 2;
	public static int GENERALIZATION = 3;
	public static int AGGREGATION = 4;
	public static int COMPOSITION = 5;
	
	//Are you angry from writing in all caps yet?

}
