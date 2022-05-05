package application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MapTest {
	public static ArrayList<Data> cities = new ArrayList<Data>();

	public static void main(String[] args) {

		try {

			HashMap<String, Data> hash = new HashMap<String, Data>();

			File file = new File("cityfile.txt");
			Scanner sc = new Scanner(file);
			String line = sc.nextLine();
			String[] token = line.split(" ");
			int numCities = Integer.parseInt(token[0]);
			int numEdges = Integer.parseInt(token[1]);
			System.out.println("number of cities = " + numCities);
			System.out.println("number of edges= " + numEdges);

			// *** read cities -->
			for (int i = 0; i < numCities; i++) {
				line = sc.nextLine();
				token = line.split(";;");
				Data cityData = new Data(token[0].trim(), Double.parseDouble(token[1].trim()),
						Double.parseDouble(token[2].trim()));
				cities.add(cityData);
			}

			for (int i = 0; i < cities.size(); i++)
				hash.put(cities.get(i).getCityname(), cities.get(i)); // hash is used to find cities faster

			// *** read and fill adjacency list ***//
			for (int i = 0; i < numEdges; i++) {
				line = sc.nextLine();
				token = line.split(";;");
				Data src = hash.get(token[0]);
				// ** retrieves data object (city +its data) of edge source from hashMap
				Data dest = hash.get(token[1]);
				// ** retrieves data object (city +its data) of edge destination from hashMap

//				Data source = new Data(src.getCityname(), src.getXcon(), src.getYcon(),
//						Integer.parseInt(token[2].trim()));
				Distance d = new Distance(src.getYcon(), src.getXcon(), dest.getYcon(), dest.getXcon());
				Data destination = new Data(dest.getCityname(), dest.getXcon(), dest.getYcon(), d.getResult());

				src.getCityadj().add(destination);
				// dest.getCityadj().add(source);

			}
			sc.close();
			// all city data is is ready and cities are connected via adjacencies
			System.out.println("****file read****");

			System.out.println("number of cities = " + cities.size());
//			for (int i = 0; i < cities.size(); i++) {
//				System.out.println(cities.get(i));
//			}
			for (int i = 0; i < numCities; i++) {
				System.out.println(cities.get(i).getCityname() + " =====");
				for (int u = 0; u < cities.get(i).getCityadj().size(); u++) {
					System.out.println("\t" + cities.get(i).getCityadj().get(u));
				}
			}

			System.out.println("\n-------------------------------------------\nTest case start ");
			Graph g = new Graph(cities);
			g.showPath("Nables", "Gaza");
			System.out.println("End of test case\n*******************\n");

			Distance d = new Distance(35.235772, 31.776082, 35.203418, 31.903764);
			System.out.println("Distance between Ramallah and Jerusalem = " + d.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
