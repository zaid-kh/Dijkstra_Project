package application;

import java.util.ArrayList;

public class Data implements Comparable<Data> {
	private String cityname;
	private double xcon;
	private double ycon;
	private double distance;
	private boolean known;
	private double dijkdistance;
	private Data prevpath;
	private ArrayList<Data> cityadj = new ArrayList<Data>();

	public Data() {

	}

	public Data(String cityname, double xcon, double ycon) {
		super();
		this.cityname = cityname;
		this.xcon = xcon;
		this.ycon = ycon;
	}

	public Data(String cityname, double xcon, double ycon, double distance) {
		super();
		this.cityname = cityname;
		this.xcon = xcon;
		this.ycon = ycon;
		this.distance = distance;

	}

	public Data(String cityname, boolean know, double dijkdistance, Data prevpath) {
		super();
		this.cityname = cityname;
		this.dijkdistance = dijkdistance;
		this.known = know;
		this.prevpath = prevpath;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public double getXcon() {
		return xcon;
	}

	public void setXcon(double xcon) {
		this.xcon = xcon;
	}

	public double getYcon() {
		return ycon;
	}

	public void setYcon(double ycon) {
		this.ycon = ycon;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public ArrayList<Data> getCityadj() {
		return cityadj;
	}

	public void setCityadj(ArrayList<Data> cityadj) {
		this.cityadj = cityadj;
	}

	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean know) {
		this.known = know;
	}

	public double getDijkdistance() {
		return dijkdistance;
	}

	public void setDijkdistance(double dijkdistance) {
		this.dijkdistance = dijkdistance;
	}

	public Data getPrevpath() {
		return prevpath;
	}

	public void setPrevpath(Data prevpath) {
		this.prevpath = prevpath;
	}

	@Override
	public String toString() {
		return "Data [cityname=" + cityname + ", xcon=" + xcon + ", ycon=" + ycon + ", know=" + known
				+ ", dijkdistance=" + dijkdistance + ", distance = [" + distance + "]]";
	}

	@Override
	public int compareTo(Data d) {
		return (int) (this.getDijkdistance() - d.getDijkdistance());
	}

}
