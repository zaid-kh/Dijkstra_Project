package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Graph {
	private ArrayList<Data> list = new ArrayList<>();
	private PriorityQueue<Data> dataPQ = new PriorityQueue<Data>();
	private HashMap<String, Data> hash;

	/*
	 * constructor
	 */
	public Graph(ArrayList<Data> list) {
		this.list = list;
		this.hash = new HashMap<>();

		// Initializing the hash
		for (int i = 0; i < list.size(); i++)
			hash.put(list.get(i).getCityname(), list.get(i)); // hash is used to get faster to cities

		// Initialize the list
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setKnown(false);
			list.get(i).setDijkdistance(Integer.MAX_VALUE); // infinity
			list.get(i).setPrevpath(null);
		}
	}

	/*
	 * The algorithmic part
	 */
	public void dijkstra(String src) {
		Data srcData = hash.get(src);
		srcData.setDijkdistance(0);

		for (int i = 0; i < list.size(); i++) { // V
			dataPQ.add(list.get(i));
		}

		dataPQ.poll(); // srcData.dijkDistance ==0 ;; others are max integer

		for (int j = 1; j < list.size(); j++) { // V +
			srcData.setKnown(true);
			System.out.println("Main City==> " + srcData);
			System.out.println("Adjacentssss:");
			for (int i = 0; i < srcData.getCityadj().size(); i++) { // E + logE
				Data n = srcData.getCityadj().get(i);
				Data node = hash.get(n.getCityname());
				if (!node.isKnown()) {
					double newdist = srcData.getDijkdistance() + n.getDistance();
					if (newdist < node.getDijkdistance()) {

						node.setPrevpath(srcData);
						node.setDijkdistance(newdist);
						System.out.println("" + node);
						dataPQ.add(node); // log E

					}
				}
			}

			do { // V
				srcData = hash.get(dataPQ.peek().getCityname());
				dataPQ.poll(); // 1
			} while (srcData.isKnown());

		}
	}

	// print shortest path from s to d (interchange s and d to print in right order)
	public void showPath(String srcName, String destName) {
		ArrayList<String> pathCities = new ArrayList<>();
		dijkstra(srcName);
		System.out.println("Printing Cities");
		Data dest = hash.get(destName);

		if (dest.getDijkdistance() == 0)
			System.out.println("Destination city is the same as source = " + dest.getCityname());

		else if (dest.getPrevpath() == null)
			System.out.println("City " + dest.getCityname() + " unreachable :( ");

		else {
			Data temp = hash.get(destName);
			while (temp != null && !temp.getCityname().equalsIgnoreCase(srcName)) {
				pathCities.add(temp.getCityname());
				temp = temp.getPrevpath();

			}
			if (temp != null && temp.getCityname().equalsIgnoreCase(srcName))
				pathCities.add(temp.getCityname());

		}

		Collections.reverse(pathCities);
		// Printing them in reverse order -->
		// System.out.println(s);
//		for (int i = pathCities.size() - 1; i >= 0; i--) {
//			System.out.println(pathCities.get(i));
//		}

		for (int i = 0; i < pathCities.size(); i++) {
			System.out.println(pathCities.get(i));
		}
		System.out.println("Total ditance = " + dest.getDijkdistance() + "KMs");
		System.out.println("Done 2");
	}

	/*
	 * setters + getters
	 */
	public ArrayList<Data> getList() {
		return list;
	}

	public void setList(ArrayList<Data> list) {
		this.list = list;
	}

	public PriorityQueue<Data> getDataPQ() {
		return dataPQ;
	}

	public void setDataPQ(PriorityQueue<Data> dataPQ) {
		this.dataPQ = dataPQ;
	}

	public HashMap<String, Data> getHash() {
		return hash;
	}

	public void setHash(HashMap<String, Data> hash) {
		this.hash = hash;
	}

}
