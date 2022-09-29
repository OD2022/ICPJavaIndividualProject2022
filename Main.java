import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
import java.io.FileWriter;
import helpers.*;

/** Main Class to process an oder */
public class Main {

    /** This node helps to traverse the document */
    static class Node {
        Node Parent = null;
        ArrayList<String> State = new ArrayList<>();
        int PathCost;

        public Node(ArrayList<String> state, Node parent, int pathCost) {
            Parent = parent;
            State = state;
            PathCost = pathCost;
        }

        /**
         * @param solutionPath
         * @return ArrayList<ArrayList<String>>
         */
        public ArrayList<ArrayList<String>> solutionPath() {
            ArrayList<ArrayList<String>> goal_to_start = new ArrayList<ArrayList<String>>();
            Node current = this;
            goal_to_start.add(current.State);
            while (current.Parent != null) {
                goal_to_start.add(current.Parent.State);
                current = current.Parent;
            }
            Collections.reverse(goal_to_start);
            return goal_to_start;
        }

        @Override
        public String toString() {
            return "Flight with state " + this.State + " and parent " + this.Parent + "with number of stops"
                    + this.PathCost;
        }
    }

    /**
     * Node Comparator compares the distance property of two
     * nodes and returns the node
     * with the shortest distance
     */
    static class NodeComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            if (n1.PathCost > n2.PathCost)
                return 1;
            else if (n1.PathCost < n2.PathCost) {
                return -1;
            }
            return 0;
        }
    }

    public static void getOrder() throws FileNotFoundException, IOException {
        Scanner stream = new Scanner(System.in);
        System.out.println("Enter Depature City and Country in this format: State, Country");
        String start = stream.nextLine();
        System.out.println("Enter Arrival City and Country in this format: State, Country");
        String stop = stream.nextLine();
        stream.close();

        String startCity = start.split(", ")[0];
        String startCountry = start.split(", ")[1];

        String stopCity = stop.split(", ")[0];
        String stopCountry = stop.split(", ")[1];

        ArrayList<String> depatureAirports = GET_AIRPORTS.getAirports(startCity, startCountry);
        ArrayList<String> arrivalAirports = GET_AIRPORTS.getAirports(stopCity, stopCountry);
        MapJournies(depatureAirports, arrivalAirports);
    }

    /** MAPS A GIVEN JOURNEY */
    public static void MapJournies(ArrayList<String> departureAirports, ArrayList<String> arrivalAirports)
            throws FileNotFoundException, IOException {
        System.out.println("I was called, I am Map Journies");

        for (String airport : departureAirports) {
            System.out.println("These are your departure airports" + airport);
            ArrayList<ArrayList<String>> flightPath = GET_FLIGHTS.getFlightPath(airport, arrivalAirports);
            String filePath = "optimalFlight.txt";
            PRINT_TICKET.printTicket(flightPath, filePath);
        }
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
