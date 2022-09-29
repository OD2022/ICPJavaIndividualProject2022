import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.io.FileWriter;

public class GET_AIRPORTS {
    /**
     * Gets the airports available in a city
     * 
     * @param getAirports
     * @return ArrayList<String>
     */
    public static ArrayList<String> getAirports(String city, String country) {
        getCurrentDirectory();
        System.out.println("Retreiving airport codes");
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
