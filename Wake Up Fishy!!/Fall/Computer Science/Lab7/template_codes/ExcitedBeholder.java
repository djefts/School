import java.util.Random;

/** Structurally interesting code for the UML lab.
 * @author bassat 
 * @since Spring 2017
 */
public class ExcitedBeholder extends Beholder {
	private static Random rng = new Random();
	
	@Override
	public void readDiagram(ClassDiagram diag) {
		final double positivityChance = 0.33;
		final String baseDesc = analyzeDiagram(diag);
		
		String[] lines = baseDesc.split(System.lineSeparator());
		for(int i = 0; i < lines.length; i++) {
			if(rng.nextDouble() < positivityChance) {
				lines[i] += generatePositivity();
			}
		}		
		System.out.println(String.join(System.lineSeparator(), lines));
	}
	
	private static String generatePositivity() {
		String[] happyLittleStrings = {
				"... wow!",
				"-- isn't that interesting?",
				" (Amazing.)",
				" (Neato!)"
		};
		
		return happyLittleStrings[rng.nextInt(happyLittleStrings.length)];
	}
}
