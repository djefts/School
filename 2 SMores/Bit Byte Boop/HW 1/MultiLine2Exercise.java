/**
 * David Jefts
 */
public class MultiLine2Exercise {
    private Point3D [] points;
    private int numInArray; //Keeps track of the number of points currently in the array of points.

    public double calcDistanceBetweenPoints(Point3D pt) {return 0;}

    public double calcDistanceBetweenFirstAndLast() {
        if(numInArray==0) { //make sure there is at least one point in the line
            return 0;
        }

        try {
            return points[0].calcDistanceBetweenPoints(points[numInArray - 1]);
        } catch(Exception e) {
            return 0;
        }
    } //end calcDistanceBetweenFirstAndLast
}
