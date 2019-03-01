import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class IBMSolution {
    public static void main(String[] args) {
    
    }
    
    // Complete the oddNumbers function below.
    static List<Integer> oddNumbers(int l, int r) {
        List<Integer> output = new ArrayList<>();
        for(int i = l; i <= r; i++) {
            if(i % 2 == 1) {
                output.add(i);
            }
        }
        return output;
    }
    
    static int getCountries(String s, int p) {
        org.json.JSONObject jObject = null;
        try {
            jObject = new org.json.JSONObject(IBMSolution.getJson(s));
            org.json.JSONObject results = jObject.getJSONObject("page");
            
            String geoId = results.getString("id");
            System.out.println(geoId);
            
            String name = results.getString("name");
            System.out.println(name);
            
            String gender = results.getString("gender");
            System.out.println(gender);
            
            String lat = results.getString("latitude");
            System.out.println(lat);
            
            String longit = results.getString("longitude");
            System.out.println(longit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static String getJson(String s) throws Exception {
        String query = "https://jsonmock.hackerrank.com/api/countries/search?name=" + s;
        
        StringBuilder result = new StringBuilder();
        URL url = new URL(query);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
    
    /*
     * Complete the 'budgetShopping' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n -- Number of dollars in Helen's notebook budget
     *  2. INTEGER_ARRAY bundleQuantities -- the number of notebooks in a bundle at shop[i]
     *  3. INTEGER_ARRAY bundleCosts -- the cost of a bundle of notebooks at shop[i]
     */
    public static int budgetShopping(int n, List<Integer> bundleQuantities, List<Integer> bundleCosts) {
        bundleCosts.sort(Comparator.naturalOrder());
        // Write your code here
        int maxBooks = 0;
        int smallestPrice = 300;
        //find the cheapest bundle
        for(int i : bundleCosts) {
            if(i < smallestPrice) {
                smallestPrice = i;
            }
        }
        
        //loop while we still have a bundle to buy
        while(n >= smallestPrice) {
            int currentBundle = 0;
            //find highest purchasable bundle
            for(int i = 0; i < bundleQuantities.size(); i++) {
                if(bundleQuantities.get(i) > bundleQuantities.get(i) && bundleCosts.get(i) <= n) {
                    currentBundle = i;
                }
            }
            
            //buy the max bundles possible
            int numBundles = n / bundleCosts.get(currentBundle); //integer division
            maxBooks += bundleQuantities.get(currentBundle) * numBundles;
            n = n - (bundleCosts.get(currentBundle) * numBundles);
        }
        
        return maxBooks;
    }
}
