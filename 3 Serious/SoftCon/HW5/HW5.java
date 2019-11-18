import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HW5 {
    /**
     * 3. (2 points) Write a prettyPrintApple method that takes a List of Apples and that can be parameterized with
     * multiple ways to generate a String output from an apple (a bit like multiple customized toString methods). For
     * example, you could tell your prettyPrintApple method to print only the weight of each apple. In addition, you
     * could tell your prettyPrintApple method to print each apple individually and mention whether it’s heavy or light.
     * The solution is similar to the filtering examples we’ve explored in class. To help you get started, here is a
     * rough skeleton of the prettyPrintApple method:
     */
    public static void prettyPrintApple(List<Apple> inventory, List<ApplePredicate> applePredicates) {
        for (Apple apple : inventory) {
            String output = "";
            for (ApplePredicate p : applePredicates) {
                output += p.output(apple);
            }
            System.out.println(output);
        }
    }
}

interface ApplePredicate {
    String output(Apple apple);
}

class InfoPredicate implements ApplePredicate {
    @Override
    public String output(Apple apple) {
        return apple.toString();
    }
}

class ColorPredicate implements ApplePredicate {
    @Override
    public String output(Apple apple) {
        return " This apple is " + apple.color;
    }
}

class WeightPredicate implements ApplePredicate {
    @Override
    public String output(Apple apple) {
        return " This apple weighs " + apple.weight + " pounds. ";
    }
}

class HeavinessPredicate implements ApplePredicate {
    private int weight;

    public HeavinessPredicate(int weight) {
        this.weight = weight;
    }

    @Override
    public String output(Apple apple) {
        if (apple.weight>weight) {
            return " This apple is heavy. ";
        } else {
            return " This Apple is light. ";
        }
    }
}
