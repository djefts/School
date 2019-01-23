import java.util.ArrayList;
import java.util.List;

/** Structurally interesting code for the UML lab.
 * @author bassat 
 * @since Spring 2017
 */
public class ClassDiagram {

	//Note: A List is an interface for effectively a resizable array, with no hard size limit.
	//ArrayList is a real class that implements it.
	
	private List<ClassBox> classBoxes = new ArrayList<>();
	
	private List<Connector> connectors = new ArrayList<>();

	public List<ClassBox> getClassBoxes() {
		return classBoxes;
	}

	public List<Connector> getConnectors() {
		return connectors;
	}

}