// Name: Arce, Sophia
// Project: 5
// Due: 12/8/2023
// Course: cs-2400-02-f23
//
// Description:
// The project involves implementing a Java program for managing airport information and connections using a graph ADT. 
// Two input files, namely `airports.csv` and `distances.csv`, contain IATA 3-letter airport codes with 
// associated information and distance data between airports, respectively. 
// The program provides a menu system with options to read and store data from the input files, 
// display airport information based on user input, find the shortest distance between two airports using cheapest path algorithm, and exit the program. 
// The implementation includes a DirectedGraph class to store airport and distance information.
// The required input/output interactions include questions pertaining to the airport information, finding minimum distances, displaying help information, 
// and handling various user inputs and potential errors such as invalid commands, unknown airport codes, and unconnected airports.
// The main goal is to create an efficient program that effectively manages and presents airport data based on user response.
//

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AirportApp {
    public static void main(String[] args) throws FileNotFoundException {

        // Initialize data structures
        DictionaryInterface<String, String> dictionaryIATA = new HashedDictionary<>(311);
        GraphInterface<String> graph = new DirectedGraph<>();

        // Scanner for reading airports.csv file
        Scanner airScan = new Scanner(new File("airports.csv"));

        // Reading and storing data from airports.csv
        while (airScan.hasNext()) {
            String line = airScan.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 2) {
                // Extracting airport code and information
                String key = parts[0];
                String value = parts[1];
                // Adding airport information to the dictionary
                dictionaryIATA.add(key, value);
                // Adding airport as a vertex in the graph
                graph.addVertex(key);
            } else {
                // Handling invalid line format
                System.out.println("Invalid line format");
            }
        }
        airScan.close();

        // Scanner for reading distances.csv file
        Scanner distScan = new Scanner(new File("distances.csv"));

        // Reading and storing data from distances.csv
        while (distScan.hasNextLine()) {
            String line = distScan.nextLine();
            String[] parts = line.split(",");
            String source = parts[0];
            String target = parts[1];
            double weight = Double.parseDouble(parts[2]);

            // Add vertices and edges to the graph
            graph.addEdge(source, target, weight);
        }
        distScan.close();

        System.out.println("Airports v0.1 by S. Arce\n");
        Scanner scnr = new Scanner(System.in);

        System.out.print("Command? ");
        String userResponse = scnr.nextLine();

        StackInterface<String> pathStack = new LinkedStack<>();

        // Main user interaction loop
        while (!userResponse.equals("E")) {
            if (userResponse.equals("H")) {
                // Display help information
                System.out.println("Q Query the airport information by entering the airport code.");
                System.out.println("D Find the minimum distance between two airports.");
                System.out.println("H Display this message.");
                System.out.println("E Exit");
                System.out.print("Command? ");
                userResponse = scnr.nextLine();
            } else if (userResponse.equals("Q")) {
                // Query airport information
                System.out.print("Airport code: ");
                String airportCode = scnr.nextLine();
                String value = dictionaryIATA.getValue(airportCode);
                if (value != null) {
                    System.out.println(value);
                } else {
                    // Handling unknown airport code
                    System.out.println("Airport code unknown");
                }
                System.out.print("Command? ");
                userResponse = scnr.nextLine();
            } else if (userResponse.equals("D")) {
                // Find minimum distance between two airports
                pathStack.clear();
                System.out.print("Airport codes from to? ");
                String input = scnr.nextLine();

                String[] airports = input.split("\\s+");

                if (airports.length == 2) {
                    String sourceAirport = airports[0];
                    String destinationAirport = airports[1];

                    if (dictionaryIATA.getValue(sourceAirport) == null
                            || dictionaryIATA.getValue(destinationAirport) == null) {
                        // Handling unknown airport codes
                        System.out.println("Airport code unknown");
                    } else {
                        double distance = graph.getCheapestPath(sourceAirport, destinationAirport, pathStack);

                        if (distance != 0.0) {
                            System.out.println("Minimum distance between " + dictionaryIATA.getValue(sourceAirport)
                                    + " and " + dictionaryIATA.getValue(destinationAirport) + " is " + distance
                                    + " through the route: ");
                            // Displaying the path
                            while (!pathStack.isEmpty()) {
                                System.out.println(pathStack.pop());
                            }
                        } else {
                            // Handling unconnected airports
                            System.out.println("Airports not connected");
                        }
                        System.out.print("Command? ");
                        userResponse = scnr.nextLine();
                    }

                } else {
                    // Handling invalid input format
                    System.out.println("Invalid input format. Please enter two airport codes separated by a space.");
                    System.out.print("Command? ");
                    userResponse = scnr.nextLine();
                }

            } else {
                // Handling invalid commands
                System.out.println("Invalid command");
                System.out.print("Command? ");
                userResponse = scnr.nextLine();
            }

        }

        if (userResponse.equals("E"))
            scnr.close();

    }

}
