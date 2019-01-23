/**
 *
 * @author dgb
 */
/*
    This version increases the size of the array every time it fills up.
*/
public class MultiLine2 {
    private Point3D [] points;
    private int numInArray;
    public static final int DEFAULT_AMOUNT = 10;
    private final int INCREMENT = 20;
    
    public MultiLine2()
    {
        this(DEFAULT_AMOUNT);
    }

    public MultiLine2(int amount)
    {
        if (amount <= 0)
            amount = DEFAULT_AMOUNT;
        
        points = new Point3D[amount];
        numInArray = 0;
        init();
    }
    
    private void init()
    {
        for(int i = 0; i < points.length; i++)
            points[i] = null;
    }
    
    private Point3D getPoint(int pos)
    {
        if (pos < 0 || pos >= numInArray)
            return null;
        
        return points[pos];
    }
    
    public int getNumInArray(){ return numInArray; }
    
    public int size(){ return points.length; }
    
    public Point3D add(Point3D pt)
    {
        //Attempts to add point to array and returns point added
        //or null if no null was passed
        if (pt == null )  //null parameter
            return null;
        if (numInArray >= points.length)
            increaseSize();
        points[numInArray] = pt;
        numInArray++;
        return pt;
    }
    
    private void increaseSize()
    {
        //Make room
        Point3D [] copy = new Point3D[points.length + INCREMENT];
        //Copy the elements into the new array
        //There is a better way to do this using a system call but this is
        //just done for learning purposes.
        for(int i = 0; i < numInArray; i++)
            copy[i] = points[i];
        //replace the original
        points = copy;
    }
    
    public double lengthOfMultiLine() throws Exception
    {
        double sum = 0;
        for (int i = 0; i < numInArray-1; i++)
        {
            Point3D pt1 = points[i];
            Point3D pt2 = points[i+1];
            sum += pt1.calcDistanceBetweenPoints(pt2);
        }
        return sum;
    }
    
    @Override
    public String toString()
    {
        String str = "";
        for (int i = 0; i < numInArray; i++)
        {
            str += points[i] + "\n";
        }
        
        return str;
    }
    
    //test main
    public static void main(String[] args) throws Exception
    {
        MultiLine2 multiLine = new MultiLine2(3);
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
