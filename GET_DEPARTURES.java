
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.io.FileWriter;

public class GET_DEPARTURES {
    /**
     * getDepartures retreives flights from routes csv leaving from given city.
     * 
     * @param getDepartures
     * @return ArrayList<ArrayList<String>>
     */
    public static ArrayList<ArrayList<String>> getDepatures(String airportCode) throws FileNotFoundException {
        System.out.println("Retreiving Depatures");
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
                    for (String i : record) {
                        copier.add(i);
                    }
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
