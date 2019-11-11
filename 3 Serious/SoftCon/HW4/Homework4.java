import java.util.GregorianCalendar;

public class Homework4 {
    private double radius = 1;
    
    public static void main(String[] args) {
        //problem 1
        GregorianCalendar calendar = new GregorianCalendar();
        displayDate(calendar);
        calendar.setTimeInMillis(1234567898765L);
        displayDate(calendar);
        
        //problem 3
        Homework4 myCircle = new Homework4();
        System.out.println("Radius is " + myCircle.radius);
    }
    
    public static void displayDate(GregorianCalendar c) {
        int year = c.get(GregorianCalendar.YEAR);
        int month = c.get(GregorianCalendar.MONTH);
        int day = c.get(GregorianCalendar.DAY_OF_MONTH);
        System.out.println("Date:"+month+"/"+day+"/"+year);
    }
    
    /**
     * Find the area of this circle
     */
    public double getArea() {
        return radius * radius * Math.PI;
    }
}
