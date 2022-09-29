import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

/**
 * @author Ibukun-Oluwa Addy
 * 
 */
public class Main {

    /** This method uses a scanner to collect input from a user
     */

    public static void getOrder() throws FileNotFoundException, IOException {
        Scanner stream = new Scanner(System.in);
        System.out.println("Enter Departure City and Country in this format: State, Country");
        String start = stream.nextLine();
        System.out.println("Enter Arrival City and Country in this format: State, Country");
        String stop = stream.nextLine();
        stream.close();

        String startCity = start.split(", ")[0];
        String startCountry = start.split(", ")[1];

        String stopCity = stop.split(", ")[0];
        String stopCountry = stop.split(", ")[1];

        ArrayList<String> departureAirports = GET_AIRPORTS.getAirports(startCity, startCountry);
        ArrayList<String> arrivalAirports = GET_AIRPORTS.getAirports(stopCity, stopCountry);
        GET_FLIGHT.MapJournies(departureAirports, arrivalAirports);
    }

    public static void main(String[] args) {
        try {
            getOrder();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
