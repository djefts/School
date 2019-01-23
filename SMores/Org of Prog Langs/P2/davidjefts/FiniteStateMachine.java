import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author davidjefts
 * <p>
 * All the instructions for creating your finite state machine can be found in FiniteStateMachine.txt
 * Please refer to that file on how to set up the machine.
 * <p>
 * Once the finite state machine is created, this class will run
 */
class FiniteStateMachine {
    private char[] language;
    private List<String> testStrings;
    private final ArrayList<String[]> states = new ArrayList<>();
    
    public static void main(String[] args) {
        FiniteStateMachine fsm = new FiniteStateMachine();
        String fsmFile = "davidjefts/FiniteStateMachine.txt";
        File file = new File(fsmFile);
        List<String> fsm_raw = new ArrayList<>();
        Path path = Paths.get(file.getAbsolutePath());
        try {
            Files.lines(path).forEachOrdered(fsm_raw::add);
            System.out.println("Parsing " + fsmFile);
            fsm.parseFile(fsm_raw);
        } catch (IOException FourOhFour) {
            String out = "Error finding FiniteStateMachine.txt";
            out += "\nPlease make sure there is a file called 'FiniteStateMachine.txt in the folder 'davidjefts/'.";
            out += "\nI think the file path is: " + path;
            exceptionThrown(out, FourOhFour);
        }
        
        //run the finite state machine
        System.out.println("Running your finite state machine: " + fsm_raw.get(10).substring(fsm_raw.get(10).indexOf(">") + 1));
        fsm.manVSmachine();
    }
    
    private void parseFile(List<String> file) {
        //get the language
        try {
            String lang = file.get(0);
            lang = lang.substring(lang.indexOf("=") + 1, lang.indexOf("#"));
            List<String> input = Arrays.asList(lang.split(","));
            language = verifyLanguage(input);
        } catch (UnsupportedOperationException e) {
            String out = "Error in language. ";
            out += "Please do not change lines you were not supposed to.\n";
            out += "This program only currently supports a language using 'a' and 'b'.";
            exceptionThrown(out, e);
        }
        System.out.println("\n" + Arrays.toString(language));
        System.out.println("Received the language.\n");
        
        //get the test strings
        String strings = file.get(1);
        strings = strings.substring(strings.indexOf("=") + 1, strings.indexOf("#"));
        testStrings = Arrays.asList(strings.split(","));
        System.out.println(testStrings);
        System.out.println("Received the test strings.\n");
        
        //get the states
        int line = 11;
        while(line < file.size()) { //loop through the file
            //System.out.println("Reading state " + file.get(line));
            String init = file.get(line).substring(0, 1); //first character of the line
            if(init.equals("!") || init.equals("*")) { //new state
                line++;
                String[] state = new String[language.length + 1]; //spot 0 is type, rest matches language
                state[0] = init; //state type
                //set the grammar
                for(int spot = 1; spot < state.length; spot++) { //loop through next states
                    try {
                        String gram = file.get(line).substring(1); //grammar rule
                        String nextState = gram.substring(gram.indexOf(">") + 1);
                        state[spot] = nextState;
                    } catch (IndexOutOfBoundsException i) {
                        exceptionThrown("Error reading State " + (spot - 1) + "\nDid you forget to define a state?", i);
                    }
                    /* TODO: Add capability to default undefined states to % */
                    line++;
                }
                states.add(state);
            } else if(init.equals("@")) {
                exceptionThrown("Error reading State " + (line - 11) + "\nDid you forget to define a state?", new Exception());
            } else {
                String s = "Invalid character (" + init + ") found while parsing states.\nDid you define a state incorrectly?";
                exceptionThrown(s, new Exception());
            }
        }
        printStates();
    }
    
    private void printStates() {
        System.out.println("Here are my states:");
        System.out.print(" \t");
        for(char l : language) {
            System.out.print(l + "\t");
        }
        System.out.println();
        for(int s = 0; s < states.size(); s++) {
            String[] state = states.get(s);
            System.out.print(state[0] + s + "\t");
            for(int i = 1; i < state.length; i++) {
                System.out.print(state[i] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
    
    private char[] verifyLanguage(List<String> language) throws UnsupportedOperationException {
        char[] output = new char[language.size()];
        for(int i = 0; i < language.size(); i++) {
            char let = language.get(i).charAt(0);
            if(let != 'a' && let != 'b') {
                throw new UnsupportedOperationException();
            }
            output[i] = let;
        }
        return output;
    }
    
    private static void exceptionThrown(String message, Exception e) {
        System.out.println(message);
        e.printStackTrace();
        System.exit(1);
    }
    
    private void manVSmachine() {
        int string = 0;
        for(String test : testStrings) { //each test string
            int state = 0;
            boolean valid = true;
            while(test.length() > 0) { //each character in the test string
                for(int i = 0; i < language.length; i++) { //each character in the language- allows for any length of language
                    //make sure the character is in the language
                    String let = test.substring(0, 1);
                    if(new String(language).contains(let)) { //check character is in the language
                        if(let.charAt(0) == language[i]) {
                            String s = "";
                            try {
                                s = states.get(state)[i + 1]; //value of next state
                            } catch (IndexOutOfBoundsException e) {
                                exceptionThrown("Error- State" + state + " is undefined.\nPlease define all of your states.", e);
                            }
                            if(s.equals("%")) { //next state is trash
                                valid = false;
                            } else {
                                try {
                                    state = Integer.parseInt(s); //set the new state
                                } catch (NumberFormatException e) {
                                    String out = "Error processing State" + state;
                                    out += ".\nPlease make sure all of your states are defined properly.";
                                    exceptionThrown(out, e);
                                }
                            }
                        }
                    } else valid = false;
                }
                if(!valid) break;
                
                //trim the string
                test = test.substring(1);
            }
            //make sure we are in valid final state
            if(states.get(state)[0].equals("*") && valid) {
                System.out.println("\"" + testStrings.get(string) + "\"\tis a valid string.");
            } else {
                System.out.println("\"" + testStrings.get(string) + "\"\tis an invalid string.");
            }
            string++;
        }
    }
}
