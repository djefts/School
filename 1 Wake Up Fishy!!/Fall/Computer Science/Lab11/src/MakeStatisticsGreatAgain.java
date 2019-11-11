import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class MakeStatisticsGreatAgain extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        File theChosenOne = pickFile();
        processFile(theChosenOne);
        Platform.exit();
    }
    
    public static File pickFile() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("For the Emperor!");
        //Only choose csv's
        chooser.getExtensionFilters().add(new ExtensionFilter("Comma Separated Awesome", "*.csv"));
        
        File theChosenOne = chooser.showOpenDialog(null);
        return theChosenOne;
    }
    
    public static void processFile(File csvFile) {
        if (csvFile != null) {
            List<String[]> data = new ArrayList<>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(csvFile));
                String curLine;
                
                while ((curLine = reader.readLine()) != null) {
                    try {
                        String[] curData = curLine.split(",");
                        Double.parseDouble(curData[1]); //converts string to double
                        data.add(curData);
                    } catch (NumberFormatException e) {
                        //ignore invalid rows
                    }
                }
                //Done reading the file
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
            for (String[] strArr : data) {
                stats.accept(Double.parseDouble(strArr[1]));
            }
            
            //Print count, sum, avg
            System.out.printf("Count: %d\tSum: %.2f\tAvg: %.2f%n", stats.getCount(), stats.getSum(), stats.getAverage());
            
            //Sort min, max, final print
            data.sort((a, b) -> {
                double ad = Double.parseDouble(a[1]);
                double bd = Double.parseDouble(b[1]);
                return Double.compare(ad, bd);
            });
            
            System.out.printf("Min: %s - %s%nMax: %s - %s%n",
                    data.get(0)[0], data.get(0)[1],
                    data.get(data.size() - 1)[0],
                    data.get(data.size() - 1)[1]);
            //And print all the data in sorted order
            for (String[] curStringArr : data) { //for each loop
                //System.out.println(Arrays.toString(curStringArr));
                System.out.printf("%s - %s%n", curStringArr[0], curStringArr[1]);
            }
        } else {
            System.out.println("No file was selected.");
        }
        //Temporary print of file
        //System.out.println(csvFile);
    }
}