import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** A class that demands the use of debugging tools to fully analyse. 
 * @author Thomas R. Bassa
 * @date Spring 2016 */
public class DebuggingLab {
	
	/** Scanner to read this file */
	private static Scanner scan;
	
	/** Random number generator */
	private static Random r = new Random();
	
	/** Line number... sort of */
	private static int line = 0;
	
	/** Winning numbers array */
	private static int[] winNums = {1, 2, 4, 5, 8, 9};
	
	public static void main(String[] args) {
		//We're going to read this very source code file. Don't panic.
		try {
			File f = new File("DebuggingLab.java");
			if(f.exists()) {
				scan = new Scanner(f);
			} else {
				scan = new Scanner(new File("src/DebuggingLab.java"));
			}
		} catch (FileNotFoundException e) {
			//Alright, panic. Except don't, because things are still OK.
			System.err.println("Something is wrong with your project setup.");
			System.err.println("Please ensure that your source code is named \"DebuggingLab.java\"");
			System.err.println("and that it is inside a \\src\\ folder, if present in your Eclipse project.");
			System.exit(1);
		}

		//Confusing console flooding ensues, with side effects!
		gottaPrintFast();
		for(int i = line / 10; i >= 0; i--) {
			scan.nextLine(); //How many lines are thrown out? Eh, who cares.
		}
		
		System.out.println(scan.nextLine().trim());
		scan.close(); //Don't leave the fridge door open... or your file scanners.
		
		gottaPrintFast();
		System.out.println();
		
		//Let's play the Lotto, except for zero cost and zero winnings...
		int[] luckyNums = new int[6];
		
		System.out.println("This run's lucky numbers are...");
		System.out.println("-------------------------------");
		pickLuckyNums(luckyNums);
		
		System.out.println(Arrays.toString(luckyNums));
		System.out.println();
		
		if(Arrays.equals(luckyNums, winNums)) {
			System.out.println("You win the code lottery!");
		} else {
			System.out.println("Aww, better luck next time.");
		}
		System.out.println();
		
		System.out.println("And now for some mathematics:");
		
		AdditionTechnique[] statements = {new Recursive(), new Base_ic(), new Bitwise()};
		for(AdditionTechnique go : statements) {
			go.addAndPrint(2, 5);
			System.out.println();
		}
		
	}
	
	private static boolean giveWarning = true;

	/** Print psuedorandom garbage to the console;
	 * enough to exceed the size of most console buffers
	 * (to prevent scrolling for the answer easily).
	 * Also change the value of {@link #line} to prevent just commenting its calls. */
	private static void gottaPrintFast() {
		if(giveWarning) {
			System.out.println("The console is about to be flooded. Don't panic.");
			try {
				//We're going to stall the entire program here. It's fine.
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				//Do nothing.
			}
			giveWarning = false;
		}
		
		for(int i = 0; i < 5000; i++) {
			line += i  / 4000;
			int n = r.nextInt(10) + 1;
			for(int j = 0; j < n; j++) {
				int c = r.nextInt(100);
				if(c < 10) System.out.printf("Date! %s ", new java.util.Date(r.nextLong()));
				else if(c < 30) System.out.printf("Gaussian! %f ", r.nextGaussian());
				else System.out.printf("Integer! %d ", r.nextInt());
			}
			System.out.println();
		}
		line /= 2;
	}
	
	/** Pick exactly 6 numbers, valid by FL Lotto standards...
	 * @param luckyNums an array of exactly 6 integers, to overwrite
	 * @return nothing, but the parameter luckyNums will be overwritten
	 * with 6 numbers that would correspond to valid Lotto picks because why not */
	private static void pickLuckyNums(int[] luckyNums) {
		List<Integer> opts = IntStream.range(1, 54).boxed().collect(Collectors.toList());
		Collections.shuffle(opts, r);
		int start = r.nextInt(opts.size() - 6);
		for(int i = 0; i < 6; i++) {
			luckyNums[i] = opts.get(start + i);
		}
		Arrays.sort(luckyNums);
	}
}

interface AdditionTechnique {
	/** Add two numbers and immediately display the result somehow. */
	public void addAndPrint(int first, int second);
}

class Base_ic implements AdditionTechnique {
	@Override
	public void addAndPrint(int first, int fir5t) {
		System.out.printf("%d plus %d equals %s... IN BASE FOUR I'M FINE",
				first, fir5t, Integer.toString(first + fir5t, 4));
	}
}

class Recursive implements AdditionTechnique {
	@Override
	public void addAndPrint(int first, int second) {
		int res;
		if(first > second) {
			res = add(second, first);
		} else {
			res = add(first, second);
		}
		System.out.printf("(%d + %d) = %d", first, second, res);
	}
	
	/* Warning: this method is not very well written;
	 * notably, it does not handle "second" being smaller than "first" very well.
	 * Large numbers are also problematic, but this wasn't intended to be efficient. */
	private int add(int first, int second) {
		if(first == 0) return second;
		else if(second == 0) return first;
		else return add(first + 1, second - 1);
	}
}

class Bitwise implements AdditionTechnique {
	@Override
	public void addAndPrint(int first, int second) {
		System.out.printf("%d + %d = ", first, second);
		int power = 1;
		do {
			if((second & 1) == 1) {
				first += power;
			}
			power <<= 1;
			//That unsigned right shift though (try negatives...)
			second >>>= 1;
		} while (second > 0);
		System.out.printf("%d", first);
	}
}
 