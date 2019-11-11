import java.util.ArrayList;
import java.util.List;

/** Structurally interesting code for the UML lab.
 * @author bassat 
 * @since Spring 2017
 */
public class ClassBox {
	//In the interest of not recreating the java.lang.reflect package,
	//we're not going to model fields & methods in the detail
	//that they probably deserve.
	
	protected String className;
	
	protected List<String> fields = new ArrayList<>();
	
	protected List<String> methods = new ArrayList<>();
	

	public ClassBox(String className) {
		this.className = className;
	}


	public String getClassName() {
		return className;
	}

	
	public void setClassName(String className) {
		this.className = className;
	}

	
	public List<String> getFields() {
		return fields;
	}

	
	public List<String> getMethods() {
		return methods;
	}
}
