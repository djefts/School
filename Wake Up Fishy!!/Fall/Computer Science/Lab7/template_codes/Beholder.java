import java.util.List;

/** Structurally interesting code for the UML lab.
 * @author bassat 
 * @since Spring 2017
 */
public class Beholder {
	
	public void readDiagram(ClassDiagram diag) {
		System.out.println(analyzeDiagram(diag));
	}
	
	protected String analyzeDiagram(ClassDiagram diag) {
		StringBuilder thoughts = new StringBuilder();
		thoughts.append("This class diagram contains the classes:");
		thoughts.append(System.lineSeparator());
		
		for(ClassBox cl : diag.getClassBoxes()) {
			thoughts.append(cl.getClassName());
			thoughts.append(System.lineSeparator());
			
			thoughts.append("Fields:");
			thoughts.append(System.lineSeparator());
			
			for(String field : cl.getFields()) {
				thoughts.append('\t');
				thoughts.append(field);
				thoughts.append(System.lineSeparator());
			}
			
			thoughts.append("Methods:");
			thoughts.append(System.lineSeparator());
			
			for(String method : cl.getMethods()) {
				thoughts.append('\t');
				thoughts.append(method);
				thoughts.append(System.lineSeparator());
			}
			thoughts.append(System.lineSeparator());
		}
		
		thoughts.append("The relationships:");
		thoughts.append(System.lineSeparator());
		
		for(Connector cn : diag.getConnectors()) {
			String a = cn.startClass.getClassName();
			String b = cn.endClass.getClassName();
			String t = cn.getConnectorType();
			thoughts.append('\t');
			thoughts.append(a + " has a(n) " + t + " with/on " + b + ".");
			thoughts.append(System.lineSeparator());
		}
		
		return thoughts.toString();
	}

	public static void main(String[] args) {
		//Remind you of anything?
		ClassBox vehDr = new ClassBox("VehicleDriver");
		vehDr.getFields().add("(None)");
		vehDr.getMethods().add("+main(args: String[]) <<static>>");
		
		ClassBox veh = new ClassBox("Vehicle");
		List<String> list = veh.getFields();
		list.add("-motor : Motor");
		list.add("-frontLeftTire : Tire");
		list.add("-frontRightTire : Tire");
		list.add("-backLeftTire : Tire");
		list.add("-backRightTire : Tire");
		list.add("-chassis : Chassis");
		
		list = veh.getMethods();
		list.add("+Vehicle(Tire, Tire, Tire, Chassis)");
		list.add("+pressGas() : void");
		list.add("+pressBrake() : void");
		list.add("+getSpeed() : int");
		list.add("+checkStatus() : int");
		list.add("+toString() : String");
		list.add("+getMotor() : Motor");
		list.add("+setMotor(Motor) : void");
		list.add("+getFrontLeftTire() : Tire");
		list.add("+setFrontLeftTire(Tire) : void");
		list.add("+getFrontRightTire() : Tire");
		list.add("+setFrontRightTire(Tire) : void");
		list.add("+getBackLeftTire() : Tire");
		list.add("+setBackLeftTire(Tire) : void");
		list.add("+getBackRightTire() : Tire");
		list.add("+setBackRightTire(Tire) : void");
		list.add("+getChassis() : Chassis");
		list.add("+setChassis(Chassis) : void");
		
		ClassDiagram diagram = new ClassDiagram();
		diagram.getClassBoxes().add(vehDr);
		diagram.getClassBoxes().add(veh);
		
		diagram.getConnectors().add(new Connector(vehDr, veh, ConnectorConstants.ASSOCIATION));
		
		System.out.println("Normal:");
		new Beholder().readDiagram(diagram);
		System.out.println();
		
		System.out.println("HYPE!");
		new ExcitedBeholder().readDiagram(diagram);
	}
}
