
import java.util.ArrayList;

/**
 *
 * @author dgb
 */
/*
    n-dimensional point
*/
public class PointnD {
    private ArrayList<Double> coords;

    public PointnD(double... coords) throws Exception  //variadic array)
    {
        this.coords = new ArrayList();
        if (coords.length == 0)
            throw new Exception("Must have at least 1 coordinate");
        for (int i = 0; i < coords.length; i++)
        {
            this.coords.add(coords[i]);
        }
    }
    
    public double getCoord(int which)
    {
        if (which < 0 || which >= coords.size())
            throw new IndexOutOfBoundsException("No such coordinate.");
        return coords.get(which); 
    }
    
    public int numCoords(){ return coords.size(); }
    
    public double calcDistanceBetweenPoints(PointnD pt) throws Exception
    {
        if (pt == null) 
            throw new Exception("Parameter is not allowed to be null");
        if (pt.numCoords() != numCoords())
            throw new Exception("Points don't match in dimension.");
            
        double sqr = 0;
        for(int i = 0; i < coords.size(); i++)
        {
            sqr += ((pt.getCoord(i) - getCoord(i)) 
                    * (pt.getCoord(i) - getCoord(i)));
        }
        return Math.sqrt(sqr);
    }
    
    public PointnD calcMidPoint(PointnD pt) throws Exception
    {
        if (pt == null) 
            throw new Exception("Parameter is not allowed to be null");
        if (pt.numCoords() != numCoords())
            throw new Exception("Points don't match in dimension.");
        
        double [] midCoords = new double[numCoords()];
        for(int i = 0; i < numCoords(); i++)
        {
            midCoords[i] = (getCoord(i) + pt.getCoord(i)) / 2;
        }
        PointnD midPoint = new PointnD(midCoords);
        
        return midPoint;
    }
    
    @Override  //this is an annotation. It's optional but suggested.
    public String toString()
    {
        String str = "(";
        
        for(int i = 0; i < numCoords(); i++)
        {
            str += getCoord(i);
            if (i == numCoords() - 1)
                str += ")";
            else
                str += ", ";
        }
        return str;
    }
    
    //A test main
    public static void main(String [] args) throws Exception
    {
        PointnD pt1 = new PointnD(1, 0, 0);
        PointnD pt2 = new PointnD(0, 1, 0);
        PointnD pt3 = new PointnD(-2.5, 0, 1.8, 11);
        System.out.println("Point 1: " + pt1);
        System.out.println("Point 2: " + pt2);
        System.out.println("Point 3: " + pt3);
        System.out.println("Distance between points: " + pt1 + " and " + pt2
                + " is " + pt1.calcDistanceBetweenPoints(pt2));    
        System.out.println("Midpoint between " + pt1 + " and " + pt2 + ": " 
                + pt1.calcMidPoint(pt2));    
    }//end main
}
