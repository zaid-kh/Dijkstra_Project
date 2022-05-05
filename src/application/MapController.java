package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextArea;

import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class MapController implements Initializable {
	@FXML
	private AnchorPane mapPane;
	@FXML
	private TextArea pathArea;
	@FXML
	private ComboBox<String> sourceBox;
	@FXML
	private ComboBox<String> destBox;
	@FXML
	private Button findButton;
	@FXML
	private Button resetButton;

	List<String> itemsList = new ArrayList<String>(); // ArrayList to contain the options for menuItem
	public static ArrayList<Data> cities = Main.cities;
	double xRatio = 110 / (34.941411 - 34.218792);
	double yRatio = 357 / (31.348141 - 33.271233);
	List<Line> lines = new ArrayList<Line>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Adding to combobox list");
		for (int i = 0; i < cities.size(); i++) {

			itemsList.add("" + cities.get(i).getCityname());
		}
		Collections.sort(itemsList);
		sourceBox.getItems().setAll(itemsList);
		destBox.getItems().setAll(itemsList);

		System.out.println(yRatio);
		System.out.println(xRatio);

		for (int i = 0; i < cities.size(); i++) {
			Circle jenin = new Circle();
			jenin.setRadius(3);
			jenin.setLayoutX((cities.get(i).getYcon() - 34.218792) * xRatio);
			jenin.setLayoutY((cities.get(i).getXcon() - 33.271233) * yRatio);
			mapPane.getChildren().add(jenin);
		}
		findButton.setOnAction(e -> findPath(e));
//		Circle testCity = new Circle();
//		testCity.setRadius(5);
//		testCity.setLayoutX((34.989571 - 34.218792) * xRatio);
//		testCity.setLayoutY((32.794046 - 33.271233) * yRatio);
//		System.out.println(testCity.getLayoutX());
//		System.out.println(testCity.getLayoutY());
//		mapPane.getChildren().add(testCity);

	}

	void findPath(ActionEvent event) {
		mapPane.getChildren().removeAll(lines);

		Graph g = new Graph(cities);
		if (sourceBox.getValue() == null || destBox.getValue() == null) {
			pathArea.setText("Please select two cities");
			return;
		}

		String source = sourceBox.getValue().toString();
		String dest = destBox.getValue().toString();

		if (source == dest) {
			pathArea.setText("Destination city is the same as source = " + dest);
			return;
		}
		getPath(g, source, dest);

	}

	public void getPath(Graph g, String srcName, String destName) {
		ArrayList<String> pathCities = new ArrayList<>();
		g.dijkstra(srcName);
		System.out.println("Printing Cities");
		Data dest = g.getHash().get(destName);

		if (dest.getDijkdistance() == 0)
			System.out.println("Destination city is the same as source = " + dest.getCityname());

		else if (dest.getPrevpath() == null)
			pathArea.setText("City " + dest.getCityname() + " unreachable :( ");

		else {
			Data temp = g.getHash().get(destName);
			while (temp != null && !temp.getCityname().equalsIgnoreCase(srcName)) {
				pathCities.add(temp.getCityname());
				Line line = new Line();
				line.setStartX(temp.getYcon());
				line.setStartY(temp.getXcon());
				line.setEndX(temp.getPrevpath().getYcon());
				line.setEndY(temp.getPrevpath().getXcon());
				mapPane.getChildren().add(line);
				temp = temp.getPrevpath();
			}
			if (temp != null && temp.getCityname().equalsIgnoreCase(srcName))
				pathCities.add(temp.getCityname());

		}

		Collections.reverse(pathCities);

		pathArea.setText("");
		for (int i = 0; i < pathCities.size(); i++) {
			pathArea.appendText((pathCities.get(i) + "\n"));
		}
		Data temp = g.getHash().get(destName);

		for (int i = 0; i < pathCities.size() - 1; i++) {
			System.out.println("here?????");
			Line line = new Line();
			line.setStartX((temp.getYcon() - 34.218792) * xRatio);
			line.setStartY((temp.getXcon() - 33.271233) * yRatio);
			line.setEndX((temp.getPrevpath().getYcon() - 34.218792) * xRatio);
			line.setEndY((temp.getPrevpath().getXcon() - 33.271233) * yRatio);
			lines.add(line);
			System.out.println(temp);
			temp = temp.getPrevpath();
			System.out.println(temp);
			mapPane.getChildren().add(line);
		}
		pathArea.appendText("Total ditance = " + dest.getDijkdistance() + "KMs");
		System.out.println("Done 2");
	}

}
