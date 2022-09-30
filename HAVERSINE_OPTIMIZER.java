
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

/**
 * @author Ibukun-Oluwa Addy
 * 
 */
public class HAVERSINE_OPTIMIZER {

    /**
     * Given start and end latitude and longitudes(In Decimal Minutes Seconds),
     * this method gets the Haversine distance between the two points.
     * 
     * @param startLat - a latitude value for departure airport
     * @param endLat - a latitude value for arrival airport
     * @param startLong - a longitude value for departure airport
     * @param endLong - a longitude value for arrival airport
     * @return double - the haversine distance between two airports
     */
    public static double getHaversineDistance(double startLat, double endLat, double startLong, double endLong) {
        double radius = 6371.00;
        double partA = Math.pow(Math.sin((Math.toRadians(endLat) - Math.toRadians(startLat)) / 2.0), 2);
        double partB = Math.cos(Math.toRadians(startLat));
        double partC = Math.cos(Math.toRadians(endLat));
        double partD = Math.pow(Math.sin((Math.toDegrees(endLong) - Math.toRadians(startLong)) / 2.0), 2);

        double stage1 = Math.sqrt(partA + (partB * partC * partD));
        double distance = 2 * radius * Math.asin(stage1);
        return distance;
    }

    /**
     * Given a flight path calculates the flight Distance
     * 
     * @param path - a two dimensional ArrayList of Strings representing a flight path
     * @return double - the total distance of a flight path
     */
    public static double calculateFlightPathDistance(ArrayList<ArrayList<String>> path) {
        ArrayList<String> AIRPORTS = new ArrayList<String>();
        ArrayList<Double> LatValues = new ArrayList<Double>();
        ArrayList<Double> LongValues = new ArrayList<Double>();
        double distance = 0.0;

        for (ArrayList<String> flight : path) {
            AIRPORTS.add(flight.get(2));
            AIRPORTS.add(flight.get(4));
        }

        for (String airport : AIRPORTS) {
            LatValues.add(getLatandLong(airport).get(0));
            System.out.println("THESE ARE LAT VALUES" + LatValues);
            LongValues.add(getLatandLong(airport).get(1));
        }
        int iterations = 0;
        int j = 0;
        int k = j + 1;
        while (iterations < LatValues.size() / 2) {
            distance = distance
                    + getHaversineDistance(LatValues.get(j), LatValues.get(k), LongValues.get(j), LongValues.get(k));
            j = j + 2;
            iterations = iterations + 1;
        }
        return distance;
    }

    /**
     * Given a flight path calculates the flight Distance
     * 
     * @param path - an Array List of strings representing a flight path
     * @return double - the total distance of a flight path
     */
    public static double calculateFlightPathDistance2(ArrayList<String> path) {
        double distance;
        String departure = path.get(2);
        String arrival = path.get(4);

        // Retreiving the latitudes
        double latDeparture = getLatandLong(departure).get(0);
        double latArrival = getLatandLong(arrival).get(0);

        // Retreiving the longitudes
        double longDeparture = getLatandLong(departure).get(1);
        double longArrival = getLatandLong(arrival).get(1);
        distance = getHaversineDistance(latDeparture, latArrival, longDeparture, longArrival);
        return distance;
    }

    /**
     * This method, given an airport code, get's the longitude and latitude values
     * 
     * @param airport - a string representing an airport IATA code
     * @return ArrayList<Double> - an ArrayList of latitude and longitude values
     */
    public static ArrayList<Double> getLatandLong(String airport) {
        ArrayList<Double> LatandLong = new ArrayList<Double>();
        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("C:/Users/Ibukun-Oluwa/Desktop/ICPPROJECT/airports.csv"));
            String line = "";
            String splitBy = ",";
            while ((line = br.readLine()) != null) {
                String[] record = line.split(splitBy);
                if (record[4].equalsIgnoreCase(airport)) {
                    LatandLong.add(Double.parseDouble(record[6]));
                    LatandLong.add(Double.parseDouble(record[7]));
                    break;
                }
            }
            br.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return LatandLong;
    }
}
