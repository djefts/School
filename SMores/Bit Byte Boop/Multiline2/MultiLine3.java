
import java.util.ArrayList;

/**
 *
 * @author dgb
 */
/*
    This version uses an ArrayList instead of an array to store the Point3D 
    objects.
*/
public class MultiLine3 {
    private ArrayList<Point3D> points;
    public static final int DEFAULT_AMOUNT = 10;
    
    public MultiLine3()
    {
        this(DEFAULT_AMOUNT);
    }

    public MultiLine3(int amount)
    {
        if (amount <= 0)
            amount = DEFAULT_AMOUNT;
        
        points = new ArrayList<Point3D>(amount);
    }
    
    private Point3D getPoint(int pos)
    {
        if (pos < 0 || pos >= points.size())
            return null;
        
        return points.get(pos);
    }
    
    public int getNumInList(){ return points.size(); }
       
    public Point3D add(Point3D pt)
    {
        //Attempts to add point to array and returns point added
        //or null
        if (pt == null )  //null parameter
            return null;
        points.add(pt);
        return pt;
    }
    
    public double lengthOfMultiLine() throws Exception
    {
        double sum = 0;
        for (int i = 0; i < points.size()-1; i++)
        {
            Point3D pt1 = points.get(i);
            Point3D pt2 = points.get(i+1);
            sum += pt1.calcDistanceBetweenPoints(pt2);
        }
        return sum;
    }
    
    @Override
    public String toString()
    {
        String str = "";
        for (int i = 0; i < points.size(); i++)
        {
            str += points.get(i) + "\n";
        }
        
        return str;
    }
    
    //test main
    public static void main(String[] args) throws Exception
    {
        MultiLine3 multiLine = new MultiLine3(3);
        Point3D pt1 = new Point3D();
        Point3D pt2 = new Point3D(1, 1, 0);
        Point2D pt3 = new Point2D(2, 2);
        Point2D pt4 = new Point2D(-2.5, 3.8);
        
        multiLine.add(pt1);
        multiLine.add(pt2);
        System.out.println(multiLine);
        System.out.println("Length of multi line: " 
                + multiLine.lengthOfMultiLine());

        multiLine.add(pt3);
        System.out.println(multiLine);
        System.out.println("Length of multi line: " 
                + multiLine.lengthOfMultiLine());

        System.out.println("Added point? " + multiLine.add(pt4));
        System.out.println(multiLine);
        System.out.println("Length of multi line: " 
                + multiLine.lengthOfMultiLine());
    }
}
