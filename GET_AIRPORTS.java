
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

/**
 * @author Ibukun-Oluwa Addy
 * 
 */

public class GET_AIRPORTS {
    /**
     * This method gets the airports available in a city
     * 
     * @param city -a string representing the city
     * @param country - a string representing the country of a chosen city
     * @return ArrayList<String> - an Array List containing airports for the given city.
     */
    public static ArrayList<String> getAirports(String city, String country) {
        System.out.println("Getting airport codes");
        ArrayList<String> airportCodes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("airports.csv"));
            String line = "";
            String splitBy = ",";
            while ((line = br.readLine()) != null) {
                String[] record = line.split(splitBy);
                if (city.equalsIgnoreCase(record[2]) && country.equalsIgnoreCase(record[3])) {
                    airportCodes.add(record[4]);
                }
            }
            br.close();
            System.out.println(airportCodes);
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return airportCodes;
    }
}