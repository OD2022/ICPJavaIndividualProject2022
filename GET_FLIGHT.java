import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

/**
 * @author Ibukun-Oluwa Addy
 * 
 */
public class GET_FLIGHT {

    /**
     * This node helps to traverse the document
     */
    static class FlightNode {
        FlightNode Parent = null;
        ArrayList<String> State = new ArrayList<>();
        int PathCost;

        public FlightNode(ArrayList<String> state, FlightNode parent, int pathCost) {
            Parent = parent;
            State = state;
            PathCost = pathCost;
        }

        /**
         * Returns the solution path of a node
         * 
         * @return ArrayList<ArrayList<String>> an ArrayList containing interconnecting
         *         flights leading up to the current flight
         */
        public ArrayList<ArrayList<String>> solutionPath() {
            ArrayList<ArrayList<String>> goal_to_start = new ArrayList<ArrayList<String>>();
            FlightNode current = this;
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
     * Node Comparator compares
     * the distance property of two
     * nodes, and returns the node
     * with the shortest distance.
     * 
     */
    static class NodeComparator implements Comparator<FlightNode> {
        public int compare(FlightNode n1, FlightNode n2) {
            if (n1.PathCost > n2.PathCost)
                return 1;
            else if (n1.PathCost < n2.PathCost) {
                return -1;
            }
            return 0;
        }
    }

    /**
     * Lays out a flight path for given departure airports and arrival airports
     * 
     * @param departureAirports - an ArrayList of Strings containing departure
     *                          airports
     * @param arrivalAirports   - an ArrayList of Strings containing arrival
     *                          airports
     */
    public static void MapJournies(ArrayList<String> departureAirports, ArrayList<String> arrivalAirports)
            throws FileNotFoundException, IOException {
        for (String airport : departureAirports) {
            System.out.println("These are your departure airports" + airport);
            ArrayList<ArrayList<String>> flightPath = getFlightPath(airport, arrivalAirports);
            String filePath = "optimalFlight.txt";
            PRINT_TICKET.printTicket(flightPath, filePath);
        }
    }

    /**
     * getFlightPath
     * 
     * @param startAirport - a string representing an airport from which the journey
     *                     is started
     * @param stopAirports - an ArrayList of strings representing the possible
     *                     destination airports
     * @return ArrayList<ArrayList<String>> - a two-dimensional ArrayList of strings
     *         containing a flight path
     */
    public static ArrayList<ArrayList<String>> getFlightPath(String startAirport, ArrayList<String> stopAirports)
            throws FileNotFoundException {

        ArrayList<String> arrivalAirports = stopAirports;
        ArrayList<ArrayList<String>> departingFlights = GET_DEPARTURES.getDepartures(startAirport);
        PriorityQueue<FlightNode> FlightMatches = new PriorityQueue<FlightNode>(10, new NodeComparator());
        Set<ArrayList<String>> visited = new HashSet<ArrayList<String>>();
        ArrayList<FlightNode> neighbourhood = new ArrayList<FlightNode>();

        for (ArrayList<String> flight : departingFlights) {
            FlightNode node = new FlightNode(flight, null, 0);
            // Check if the current flight will arrive in a destination airport
            if (arrivalAirports.contains(node.State.get(4))) {
                System.out.println("FOUND A FLIGHT, ADDING TO PRIORITY QUEUE");
                FlightMatches.add(node);
            }
            neighbourhood.add(node);
        }
        while (neighbourhood.size() > 0 && FlightMatches.size() < 5) {
            FlightNode node = neighbourhood.remove(0);
            visited.add(node.State);
            ArrayList<ArrayList<String>> neighbours = GET_DEPARTURES.getDepartures(node.State.get(4));
            if (neighbours.size() != 0) {
                for (ArrayList<String> neighbour : neighbours) {
                    ArrayList<ArrayList<String>> childPath = node.solutionPath();
                    childPath.add(neighbour);
                    // Create a new child node
                    FlightNode child = new FlightNode(neighbour, node, node.PathCost + 1);
                    if (!visited.contains(child.State) && !neighbourhood.contains(child)) {
                        if (arrivalAirports.contains(child.State.get(4))) {
                            FlightMatches.add(child);
                            System.out.println("FOUND A FLIGHT, ADDING TO PRIORITY QUEUE");
                        }
                        neighbourhood.add(child);
                    }
                }
            }
        }
        String matchesPrintPath = "allFlights.txt";
        for (FlightNode node : FlightMatches) {
            try {
                PRINT_TICKET.printTicket(node.solutionPath(), matchesPrintPath);
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
        System.out.println("DONE! PLEASE CHECK FOR YOUR TICKET PRINTOUT!");
        return FlightMatches.remove().solutionPath();
    }
}
