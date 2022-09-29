package helpers;

public class GET_FLIGHTS {
    /**
     * Create different flights for a given start airport and stop city
     * 
     * @param getFlightPath
     * @return ArrayList<ArrayList<String>>
     */
    public static ArrayList<ArrayList<String>> getFlightPath(String startAirport, ArrayList<String> stopAirports)
            throws FileNotFoundException {
        System.out.println("ABOUT TO DO DFS ON ROUTE PROBLEM: ");

        // Priority queue to store flight nodes
        PriorityQueue<Node> FlightMatches = new PriorityQueue<Node>(10000, new NodeComparator());
        // Get arrival airports
        ArrayList<String> arrivalAirports = stopAirports;
        ArrayList<ArrayList<String>> departingFlights = GET_DEPARTURES.getDepatures(startAirport);

        // For each flight perform a depth-first search, if a solution is found
        // add the node to the priorityQueue

        for (ArrayList<String> flight : departingFlights) {
            int depth = 0;
            Node node = new Node(flight, null, 0);
            System.out.println("CREATED THIS NODE" + node);
            // Check if the current flight will arrive in a destination airport
            if (arrivalAirports.contains(node.State.get(4))) {
                System.out.println("FOUND A FLIGHT, ADDING TO PRIORITY QUEUE" + node.solutionPath());
                FlightMatches.add(node);
            }

            ArrayList<Node> frontier = new ArrayList<Node>();
            Set<ArrayList<String>> explored = new HashSet<ArrayList<String>>();
            frontier.add(node);

            while (frontier.size() > 0 && depth < 5) {
                depth = depth + 1;
                node = frontier.remove(0);
                explored.add(node.State);
                ArrayList<ArrayList<String>> successors = GET_DEPARTURES.getDepatures(node.State.get(4));

                if (successors.size() != 0) {
                    for (int i = 0; i < successors.size(); i++) {

                        ArrayList<ArrayList<String>> childPath = node.solutionPath();
                        childPath.add(successors.get(i));

                        // Create a new child node
                        Node child = new Node(successors.get(i), node, node.PathCost + 1);
                        if (!explored.contains(child.State) && !frontier.contains(child)) {
                            if (arrivalAirports.contains(child.State.get(4))) {
                                FlightMatches.add(child);
                                System.out.println("I've added a flight to the priority queue");
                            }
                            frontier.add(child);
                        }
                    }
                }
            }
        }
        System.out.println("These are the number of flights that match so far" + FlightMatches.size());
        String matchesPrintPath = "allFlights.txt";
        for (Node node : FlightMatches) {
            PRINT_TICKET.printTicket(node.solutionPath(), matchesPrintPath);
        }
        return FlightMatches.remove().solutionPath();
    }
}