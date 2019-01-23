/**
 * @author dgb
 */
public class Point2D extends Point3D {
    public Point2D() {
        super();
    }

    public Point2D(double x, double y) {
        super(x, y, 0);
    }

    @Override  //this is an annotation. It's optional but suggested.
    public String toString() {
        return "(" + super.getX() + ", " + super.getY() + ")";
    }

    //A test main
    public static void main(String[] args) throws Exception {
        Point2D pt1 = new Point2D();
        Point2D pt2 = new Point2D(3, 4);
        Point3D pt3 = new Point2D(1, 1);
        Point3D pt4 = new Point3D(1, 1, -1);
        System.out.println("Point 1: " + pt1);
        System.out.println("Point 2: " + pt2);
        System.out.println("Distance between points: "
                + pt1.calcDistanceBetweenPoints(pt2));

        System.out.println("Point 1: " + pt1);
        System.out.println("Point 3: " + pt3);
        System.out.println("Distance between points: "
                + pt1.calcDistanceBetweenPoints(pt3));

        System.out.println("Point 1: " + pt1);
        System.out.println("Point 4: " + pt4);
        System.out.println("Distance between points: "
                + pt1.calcDistanceBetweenPoints(pt4));

    }//end main    
}
