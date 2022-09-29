
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.io.FileWriter;

/**
 * @author Ibukun-Oluwa Addy
 * 
 */
public class PRINT_TICKET {
    /**
     * To write a ticket, this method accepts a two dimensional list of routes,
     * and fishes out the airline code, airportCode and
     * destination airportCode.
     * 
     * @param flightPath - a two dimensional array list of strings representing a flight path
     * @param ticketFilePath - a string to represent the filename/filepath
     * @returns void
     */
    public static void printTicket(ArrayList<ArrayList<String>> flightPath, String ticketFilePath)
            throws FileNotFoundException, IOException {
        try {
            FileWriter fw = new FileWriter(ticketFilePath, true);
            for (int i = 0; i < flightPath.size(); i++) {
                String airline = flightPath.get(i).get(0);
                String depature = flightPath.get(i).get(2);
                String arrival = flightPath.get(i).get(4);
                fw.write(airline + " FROM " + depature + " TO " + arrival + "\n");
            }
            fw.write("TOTAL FLIGHTS" + flightPath.size() + "\n" + "TOTAL ADDITIONAL STOPS: 0");
            fw.write("------------------------------" + "\n" + "\n");
            fw.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }
    }
}
