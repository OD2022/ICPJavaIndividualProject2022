
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

/**
 * @author Ibukun-Oluwa Addy
 * 
 */

public class GET_DEPARTURES {
    /**
     * Given a city, this method retreives departing flights from 'routes CSV.'
     * 
     * @param airportCode - a string representing the airport IATA code
     * @return ArrayList<ArrayList<String>> - ArrayList of ArrayList's containing departing flight details
     */
    public static ArrayList<ArrayList<String>> getDepartures(String airportCode) throws FileNotFoundException {
        ArrayList<ArrayList<String>> departures = new ArrayList<ArrayList<String>>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("routes.csv"));
            String line = "";
            String splitBy = ",";
            while ((line = br.readLine()) != null) {
                String[] record = line.split(splitBy);
                if (airportCode.equalsIgnoreCase(record[2])) {
                    ArrayList<String> copier = new ArrayList<String>();
                    Collections.addAll(copier, record);
                    departures.add(copier);
                }
            }
            br.close();
        } catch (FileNotFoundException fe) {
            fe.getMessage();
        } catch (IOException ie) {
            ie.getMessage();
        }
        return departures;
    }
}