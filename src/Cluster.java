/**
 * 
 * @author Shane Zabel
 *
 */

import java.util.ArrayList;
import java.util.List;
 
public class Cluster {
	/*
	 * Cluster data contains 	1) a list of points in the cluster
	 * 				2) the cluster centroid
	 * 				3) the cluster id
	 */
	public List<Point> clusterPoints;
	public Point centroid;
	public int clusterId;
	
	/********************************************************************************
	 * Init methods
	 *******************************************************************************/
	public Cluster() {
		this.clusterPoints = new ArrayList<Point>();
		this.centroid = null;
		this.clusterId = -1;
	}
 
	/********************************************************************************
	 * Set methods
	 *******************************************************************************/
	public void addPoint(Point point) {
		clusterPoints.add(point);
	}
	public void setPoints(List<Point> points) {
		this.clusterPoints = points;
	}
	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}
	public void setId(int ID) { 
		this.clusterId = ID;
	}
	public void clear() {
		clusterPoints.clear();
	}
	
	/********************************************************************************
	 * Get methods
	 *******************************************************************************/
	public List<Point> getPoints() {
		return this.clusterPoints;
	}
	public Point getCentroid() {
		return this.centroid;
	}
	public int getId() {
		return this.clusterId;
	}
	
	/********************************************************************************
	 * Print methods
	 *******************************************************************************/
	public void printCluster() {
		System.out.println("[Cluster: " + this.getId() + "]");
		System.out.println("[Centroid: " + this.getCentroid() + "]");
		System.out.println("[Points:");
		for(Point p : this.getPoints() ) {
			System.out.println( p.getId() + ":[" + p.getX() + "," + p.getY() + "]" );
		}
		System.out.println("]");
	}
 
	/********************************************************************************
	 * Support methods
	 *******************************************************************************/
	//NA
	
}//End of Cluster class
