package helpers;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.io.FileWriter;

public class PRINT_TICKET {
    /**
     * Accepts a 2D list of routes, and fishes out the airline code, airportCode and
     * destination airportCode
     */
    public static void printTicket(ArrayList<ArrayList<String>> flightPath, String filepath)
            throws FileNotFoundException, IOException {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            for (int i = 0; i < flightPath.size(); i++) {
                String airline = flightPath.get(i).get(0);
                String depature = flightPath.get(i).get(2);
                String arrival = flightPath.get(i).get(4);
                fw.write(airline + " FROM " + depature + " TO " + arrival + "\n");
            }
            fw.write("TOTAL FLIGHTS" + flightPath.size() + "\n" + "TOTAL ADDITIONAL STOPS: 0");
            fw.write("------------------------------");
            fw.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }
    }
}