/**
 * 
 * @author Shane Zabel
 *
 */

import java.util.ArrayList;
import java.util.List;
 
public class Point {
	/*
	 * Cluster data contains 	1) the x value
	 * 				2) the y value
	 * 				3) the id of the point
	 * 				3) the id of the cluster the point is associated with
	 */

    private double x = 0;
    private double y = 0;
    private int pointId = -1;
    private int cluster_number = 0;
 
	/********************************************************************************
	 * Init methods
	 *******************************************************************************/
    public Point(double x, double y, int id)
    {
        this.setX(x);
        this.setY(y);
        this.setId(id);
    }
    //Creates List of points from an input Matrix
    protected static List<Point> createPoints(double [ ] [ ] data, int number) {
    	List<Point> points = new ArrayList<Point>(number);
    	for(int i = 0; i < number; i++) {
    		Point newPoint = new Point( data[i][1] , data[i][2], (int)Math.round(data[i][0]) );
    		points.add(newPoint);
    	}
    	return points;
    }
    
	/********************************************************************************
	 * Set methods
	 *******************************************************************************/
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setId(int ID) {
        this.pointId = ID;
    }
    public void setCluster(int n) {
        this.cluster_number = n;
    }
    
	/********************************************************************************
	 * Get methods
	 *******************************************************************************/
    public double getX()  {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public int getId() {
        return this.pointId;
    }
    public int getCluster() {
        return this.cluster_number;
    }
    
	/********************************************************************************
	 * Print methods
	 *******************************************************************************/
    public void printPoint() {
		System.out.println("x: " + this.getX() );
		System.out.println("y: " + this.getY() );
		System.out.println("Point ID: " + this.getId() );
		System.out.println("Cluster Number: " + this.getCluster() );
	}
    public void printPointId() {
		System.out.println("Point ID: " + this.getId() );
	}
    
	/********************************************************************************
	 * Support methods
	 *******************************************************************************/
    //Calculates the Euclidean distance between two points.
    protected static double distance(Point a, Point b) {
        return Math.sqrt(Math.pow((a.getY() - b.getY()), 2) + Math.pow((a.getX() - b.getX()), 2));
    }
    public String toString() {
    	return "("+x+","+y+")";
    }
  
}
