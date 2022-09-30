# ICPJavaIndividualProject2022
ICP PROJECT INDIVIDUAL PROJECT 2022

To run this programme, make sure all the java classes/source files such as GET_DEPARTURE, GET_FLIGHT, GET_AIRPORT, PRINT_TICKET are all open in the same folder/directory at the same time. You can then run Main.Java in the same folder with the previously mentioned classes. The programme will write to two text files, allFlights.txt and optimalFlight.txt which should also be in the same folder.

This project is intended to help a user who has a start city and stop city, to find an optimal flight from a database of airports, routes and airline CSV files. 
It uses an iterative depth-first search approach to find an optimal flight based on the number of flight stops. 
The programme also returns a minimum of five other possible flight combinations. 
The programme has a total of six classes, but the HAVERSINE OPTIMIZER class is subject to review.
When up and running, the HAVERSINE OPTIMIZER can be combined with the flight node class, to get the total distance covered by a flight as an attribute of that flight. 
Haversine Distance can serve as the basis of comparison in the flight matches priority queue.

