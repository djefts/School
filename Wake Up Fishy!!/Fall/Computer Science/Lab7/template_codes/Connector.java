/**
 * Structurally interesting code for the UML lab.
 * These classes DO NOT exhibit good design,
 * but should be relatively easy to draw in a UML class diagram.
 *
 * @author bassat
 * @since Spring 2017
 */
public class Connector {
    public ClassBox startClass;
    
    public ClassBox endClass;
    
    protected int connectorType;
    
    public Connector(ClassBox startClass, ClassBox endClass, int connectorType) {
        this.startClass = startClass;
        this.endClass = endClass;
        this.connectorType = connectorType;
    }
    
    public void reverse() {
        ClassBox tmp = startClass;
        startClass = endClass;
        endClass = tmp;
    }
    
    public ClassBox getStartClass() {
        return startClass;
    }
    
    public ClassBox getEndClass() {
        return endClass;
    }
    
    public String getConnectorType() {
        //This would be soooo much less painful with an enum...
        if (connectorType == ConnectorConstants.DEPENDENCY) {
            return "Dependency";
        } else if (connectorType == ConnectorConstants.ASSOCIATION) {
            return "Association";
        } else if (connectorType == ConnectorConstants.REALIZATION) {
            return "Realization";
        } else if (connectorType == ConnectorConstants.GENERALIZATION) {
            return "Generalization";
        } else if (connectorType == ConnectorConstants.AGGREGATION) {
            return "Aggregation";
        } else if (connectorType == ConnectorConstants.COMPOSITION) {
            return "Composition";
        } else {
            return "Unknown";
        }
    }
    
}