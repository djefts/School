import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * "Driver" class for Lab 8. Works with sandwiches.
 */
public class Connoisseur {
    
    public static void main(String[] args) {
        //Indirect constructor test.
        Sandwich[] allTheSandwiches = {
                new Sandwich(), new Sandwich("Two"), new Sandwich("Three", "3"),
                new SubmarineSandwich(), new SubmarineSandwich(5),
                new SubmarineSandwich(7, "Six"), new SubmarineSandwich(9, "Seven", "7")
        };
        
        //Store the results of calling the getters...
        List<String> breads = new ArrayList<>();
        List<String> comps = new ArrayList<>();
        
        for (Sandwich sandwich : allTheSandwiches) {
            //Test getters
            breads.add(sandwich.getBreadType());
            comps.add(sandwich.getComponents());
            
            //Test setters
            sandwich.setBreadType(randBread());
            sandwich.setComponents(randComps());
            
            //Test toString
            System.out.println(sandwich);
        }
        System.out.println();
        
        //And recall the results of the getters
        System.out.println("Orig Breads: " + breads);
        System.out.println("Orig Components: " + comps);
        System.out.println();
        
        System.out.println("Sandwich checks:");
        //Eat all but one bite of the first 3 sandwiches.
        for (int i = 0; i < Sandwich.MAX_BITES - 1; i++) {
            allTheSandwiches[0].takeBite();
            allTheSandwiches[1].takeBite();
            allTheSandwiches[2].takeBite();
        }
        //Check the first.
        checkSandwichEaten(allTheSandwiches[0]);
        //Finish the second & check it
        allTheSandwiches[1].takeBite();
        checkSandwichEaten(allTheSandwiches[1]);
        
        //Go too far with the third, then check.
        allTheSandwiches[2].takeBite();
        allTheSandwiches[2].takeBite();
        checkSandwichEaten(allTheSandwiches[2]);
        System.out.println();
        
        System.out.println("Sub checks:");
        //5th (skip fourth) sandwich (sub) should have 10 bites left.
        //Take 11, checking after each step.
        for (int i = 0; i < 11; i++) {
            allTheSandwiches[4].takeBite();
            checkSandwichEaten(allTheSandwiches[4]);
        }
        System.out.println();
        
        //getLength test for Sub
        System.out.println("Ever had a sandwich " + new SubmarineSandwich(1337).getLength() + " inches long?");
    }
    
    /**
     * Utility method for printing the "eaten" state of a Sandwich
     */
    private static void checkSandwichEaten(Sandwich s) {
        System.out.printf("Took %d bites. ", s.getNumBites());
        if (s.isEaten()) {
            if (s.getBitesLeft() > 0) {
                System.out.println();
                System.err.println("isEaten was true when there were bitesLeft!");
            } else {
                System.out.println("All gone.");
            }
        } else {
            if (s.getBitesLeft() <= 0) {
                System.out.println();
                System.err.println("isEaten was false when there were no bitesLeft!");
            } else {
                int bLeft = s.getBitesLeft();
                System.out.printf("%d bite%s left.%n", bLeft, bLeft > 1 ? "s" : "");
            }
        }
    }
    
    //The rest of the class contains methods to randomly come up with sandwich aspects
    //If you're bored, add strings to the arrays following...
    private static Random gen = new Random();
    
    private static String randBread() {
        String[] opts = {"Original", "Wonderful", "Italian", "Rye", "Glorious", "moldy", "bagel", "Banana", "Cuban", "corn"};
        return opts[gen.nextInt(opts.length)];
    }
    
    private static String randComps() {
        String[] opts = {"all o' 'em", "nothing", "EVERYTHING", "air", "only what is needed", "just the basics"};
        return opts[gen.nextInt(opts.length)];
    }
}
