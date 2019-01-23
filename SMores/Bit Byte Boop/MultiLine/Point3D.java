/**
 * @author dgb
 */
public class Point3D {
    private double x;
    private double y;
    private double z;

    public Point3D() {
        x = y = z = 0;  /* Note that I don't need to use 'this' here. */
    }

    public Point3D(double x, double y, double z) {
        //Note that I have to use 'this' here to distinguish between the 
        //instance variables and the parameters.
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double calcDistanceBetweenPoints(Point3D pt) throws Exception {
        if (pt == null)
            throw new Exception("Parameter is not allowed to be null");
        double sqr = (pt.x - x) * (pt.x - x) + (pt.y - y) * (pt.y - y)
                + (pt.z - z) * (pt.z - z);
        return Math.sqrt(sqr);
    }

    @Override  //this is an annotation. It's optional but suggested.
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    //A test main
    public static void main(String[] args) throws Exception {
        Point3D pt1 = new Point3D();
        Point3D pt2 = new Point3D(3, 4, 5);
        System.out.println("Point 1: " + pt1);
        System.out.println("Point 2: " + pt2);
        System.out.println("Distance between points: "
                + pt1.calcDistanceBetweenPoints(pt2));
    }//end main
}
