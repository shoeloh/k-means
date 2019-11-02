/**
 * 
 * @author Shane Zabel
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Kmeans {
 
	/*
	 * Kmeans data contains 	1) a list of points in the data
	 * 				2) a list of clusters
	 * 				3) the number of clusters
	 * 				4) the number of points
	 * 				5) the SSE (sum squared error) for the final clustering
	 */

    private List<Point> points;
    private List<Cluster> clusters;
    private int numClusters;
    private int numPoints;
    private double SSE;
	
	/********************************************************************************
	 * Init methods
	 *******************************************************************************/
    //Kmeans is the default initializer
	public Kmeans() {
		this.points = new ArrayList<Point>();
	    this.clusters = new ArrayList<Cluster>();   
	    this.numClusters = 0;
	    this.numPoints = 0;
	    this.SSE = 0.0;
	}//End of the Kmeans class
	
    //init initializes Kmeans data based on input of the k number of clusters and file location of the data
	public void init(String k, double[][] data) {
		//Set number of clusters based on input
	    this.numClusters = Integer.parseInt(k);   
	    
	    //Create Points
	    this.points = Point.createPoints(data,this.numPoints);		
		
	    //Create Clusters with Random Centroids
    	List<Integer> selectedIDs = new ArrayList<Integer>();

	    for (int i = 0; i < this.getNumClusters() ; i++) {
	    	Cluster cluster = new Cluster();
	    	cluster.setId(i);
	    	Point centroid = new Point(0.0,0.0,-1);
    		centroid = selectRandomPoint();
	    	while( selectedIDs.contains( centroid.getId() )){
	    		centroid = selectRandomPoint();
	    	}
			selectedIDs.add( centroid.getId() );
	    	cluster.setCentroid(centroid);
	    	this.clusters.add(cluster);
	    }
        this.assignCluster();  
	    //Print Initial state
      //Print cluster info
        System.out.println("#################");
        System.out.println("Initial Assignments with Random Cluster Centroids:");
        plotClusters();
        this.SSE();
    	System.out.print("SSE: ");
    	System.out.println( this.getSSE() );
    	
	}//End of the init class
	
	/********************************************************************************
	 * Set methods
	 *******************************************************************************/
	//Standard setters
	public void setNumClusters(int numClusters) {
		this.numClusters = numClusters;
	}
	public void setNumPoints(int numPoints) {
		this.numPoints = numPoints;
	}
	public void setSSE(double sse) {
		this.SSE = sse;
	}
	//clearClusters clears all data in all clusters
	private void clearClusters() {
	    for(Cluster cluster : clusters) {
	    	cluster.clear();
	    }
	}//End of the clearClusters class

	/********************************************************************************
	 * Get methods
	 *******************************************************************************/
	//Standard getters
	public List<Point> getPoints() {
		return(this.points);
	}
	public List<Cluster> getClusters() {
		return(this.clusters);
	}
	public int getNumClusters() {
		return(this.numClusters);
	}
	public int getNumPoints() {
		return(this.numPoints);
	}
	public double getSSE() {
		return(this.SSE);
	}
	//getCentroids gets all centroids in the cluster
	public List<Point> getCentroids() {
	    List<Point> centroids = new ArrayList<Point>(this.numClusters);
	    for(Cluster cluster : clusters) {
	    	Point aux = cluster.getCentroid();
	    	Point point = new Point(aux.getX(),aux.getY(),-1);
	    	centroids.add(point);
	    }
	    return centroids;
	}//End of the getCentroids class
		
	/********************************************************************************
	 * Print methods
	 *******************************************************************************/
	//plotClusters prints out cluster data
	private void plotClusters() {
	    for (int i = 0; i < this.getNumClusters() ; i++) {
	    	Cluster c = this.getClusters().get(i);
	    	c.printCluster();
	    }
	}//End of the plotClusters class
	
	/********************************************************************************
	 * Support methods
	 *******************************************************************************/
	//calculate performs the iterated KMeans calculation on the data
	public void calculate() {
		boolean finish = false;
	    int iteration = 0;
	        
	    // Add in new data, one at a time, recalculating centroids with each new one. 
	    while(!finish) {
	    	//Clear cluster state
	        clearClusters();
	        //Get old centroids	
	        List<Point> lastCentroids = getCentroids();
	        //Assign points to the closest cluster
	        this.assignCluster();  
	        //Calculate new centroids.
	        calculateCentroids();
	        //Store new centroids	
	        List<Point> currentCentroids = this.getCentroids();
	        	
	        //Calculates total distance between new and old centroids
	        double distance = 0;
	        for(int i = 0; i < lastCentroids.size(); i++) {
	        	distance += Point.distance(lastCentroids.get(i),currentCentroids.get(i));
	        }
	        
	        //Print cluster info
	        System.out.println("#################");
	        System.out.println("Iteration: " + (iteration+1) );
	        System.out.println("Distance centroid moved: " + distance);
	        plotClusters();
	        this.SSE();
	    	System.out.print("SSE: ");
	    	System.out.println( this.getSSE() );
	    	
	        //Stop if centroid does not move enough
	        if(distance < 0.00001) {
	        	finish = true;
	        }
	        //Or stop after 25 iterations
	        iteration++;
	        if(iteration == 26) {
	        	finish = true;
	        }
	    }
	}//End of the calculate class
	
	//assignCluster assigns the data points to the cluster whose centroid it is nearest to
	private void assignCluster() {
		//Initialize variables
		double minX,minY,maxX,maxY;
		minX=minY=maxX=maxY=0.0;
		double a[] = new double[points.size()];
		double b[] = new double[points.size()];
	    int cluster = 0;                 
	    double distance = 0.0; 
	    
		//Calculate maximum distance between any two points		
		for(int i=0;i<points.size();i++){
			Point currentPoint=points.get(i);
			a[i]=currentPoint.getX();
			b[i]=currentPoint.getY();
		}
		Arrays.sort(a);
		Arrays.sort(b);
		minX=a[0];
		maxX=a[a.length-1];
		minY=b[0];
		maxY=b[a.length-1];
    	double maxDist=Math.sqrt( Math.pow(minX-minY,2) + Math.pow(maxX+maxY,2) );
    	
    	//Set minimum distance to maximum distance
	    double min = maxDist; 

	    //For each point, find closest cluster centroid
	    for(Point point : points) {
	        min = maxDist;
	        for(int i = 0; i < this.getNumClusters() ; i++) {
	            Cluster c = clusters.get(i);
	            distance = Point.distance(point, c.getCentroid() );
	            if(distance < min){
	                min = distance;
	                cluster = i;
	            }
	        }
	        //Assign closest cluster centroid to each point
	        point.setCluster(cluster);
	        //Add the point to the cluster list
	        clusters.get(cluster).addPoint(point);
	    }
	}//End assignCluster class
	
	//calculateCentroids sets the centroid location for a cluster based on the points assigned to it
	private void calculateCentroids() {
	    for(Cluster cluster : clusters) {
	        double sumX = 0;
	        double sumY = 0;
	        List<Point> list = cluster.getPoints();
	        int n_points = list.size();
	            
	        for(Point point : list) {
	            sumX += point.getX();
	            sumY += point.getY();
	        }
	            
	        Point centroid = cluster.getCentroid();
	        if(n_points > 0) {
	            double newX = sumX / n_points;
	            double newY = sumY / n_points;
	            centroid.setX(newX);
	            centroid.setY(newY);
	        }
	    }
	}//End of calculateCentroids class
	
	//Selects a randomly chosen point from the list of points
    public Point selectRandomPoint() {
    	//Create random number of size points.size()
    	Random  seed = new Random();
    	double rndDouble=seed.nextDouble();
    	double r=rndDouble*points.size();
    	//Select random point using random number
    	Point rndmPoint = points.get( (int)r );
    	//Return random point
    	return rndmPoint;
    }//End of the selectRandomPoints class
   
	//SSE calculates the SSE for all the data given its clustering
	public void SSE() {         
	    double distanceSquared = 0.0; 
	    double sserror=0.0;
	        
	    for(Point point : points) {
	        for(int i = 0; i < this.getNumClusters() ; i++) {
	        	if(point.getCluster()==i){
	        		Cluster c = clusters.get(i);
	        		distanceSquared = Math.pow( Point.distance(point, c.getCentroid() ) , 2);
	        		sserror+=distanceSquared;
	        	}
	        }
	    }
	    this.setSSE(sserror);
	}//End of the SSE class
	
}//End of the Kmeans class
