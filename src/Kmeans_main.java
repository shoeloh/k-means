/**
 * 
 * @author Shane Zabel
 *
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Kmeans_main {
	//Main
	public static void main(String[] args){
		//Print out command line args 
		System.out.print("Arg 0 = ");
		System.out.println(args[0]);
		System.out.print("Arg 1 = ");
		System.out.println(args[1]);
		System.out.print("Arg 2 = ");
		System.out.println(args[2]);
		
    	Kmeans kmeans = new Kmeans();
    	double [ ] [ ] data = new double [ 100 ] [ 3 ] ; 
    	
    	data=readData(args[1]);
    	int rows=data.length;
    	kmeans.setNumPoints(rows);
    	
    	kmeans.init(args[0],data);
    	kmeans.calculate();
    	kmeans.SSE();
    	System.out.println("");
    	System.out.print("Final SSE: ");
    	System.out.println( kmeans.getSSE() );
    	outputToFile(kmeans,args[2]);
    	
	}//End of main class
	
	/********************************************************************************
	 * Support methods
	 *******************************************************************************/
    //readData reads data from a file into a double matrix
	public static double[][] readData(String inputFile) {
	//Initialize file and data objects
			File inFile = null;
			FileReader fr = null;
			BufferedReader br = null;
			String line = null;
			double [ ] [ ] data = new double [ 100 ] [ 3 ] ; 
			
			//Create File named inFile based on inputFile=arg[1]
			if (inputFile!=null) {
			      inFile = new File(inputFile);
			} else {
				   System.err.println("Invalid input file argument.");
				   System.exit(1);
			}
			
			//Read inFile
			try{
				fr = new FileReader(inFile);
			}catch(FileNotFoundException fnfe) {
				System.out.println(inFile.getAbsolutePath());
				System.out.println(inFile.exists());
				System.out.println(inFile.canRead());
	            fnfe.printStackTrace();
			}
				
			//Parse inFile with " " separators into a matrix
		    try{
				br = new BufferedReader(fr);
				int i=0;
		    	while ((line = br.readLine()) != null) {
		    		String[] values = line.split("\t",-1);
		    		//Don't parse header
		    		if(i!=0){
			    		data[i-1][0]=Double.parseDouble(values[0]);
			    		data[i-1][1]=Double.parseDouble(values[1]);
			    		data[i-1][2]=Double.parseDouble(values[2]);
		    		}
		    		i++;
		    	} 
		    	br.close();
		    }catch(IOException ioe) {
	            ioe.printStackTrace();
		    }
	return data;
	}
	
	//outputToFile prints results to file specified by outFile
	public static void outputToFile(Kmeans kmeans, String outFileName) {
		//Initialize file and data objects
		File outFile = null;
		FileWriter fw = null;
		BufferedWriter bw = null;

		//Create File named outFile based on outFileName
		if (outFileName!=null) {
			outFile = new File(outFileName);
		} else {
			System.err.println("Invalid output file argument.");
			System.exit(1);
		}
					
		//Create FileWriter for outFile
		try{
			fw = new FileWriter(outFile);
		}catch(IOException ioe) {
			System.out.println(outFile.getAbsolutePath());
			System.out.println(outFile.exists());
			System.out.println(outFile.canWrite());
			ioe.printStackTrace();
		}
			
		//Write to outFile 
		try{
			bw = new BufferedWriter(fw);
			bw.write("<cluster-id>	 <List of points ids separated by comma>");
	        bw.write("\n");
	        int counter = 0;
		    for(int i = 0; i < kmeans.getNumClusters(); i++) {
		        String line = "";
		        Cluster c = kmeans.getClusters().get(i);
		        line += c.getId() + 1;
		        line += "\t\t";
		        for( Point point : kmeans.getPoints() ) {
		    	    if(point.getCluster()==c.getId() ){
		    	        if(counter == 0){
		    	        line += " ";	
		    	        }else{
		    	        line += ", ";
		    	        }
		    	        line += point.getId();
		    	        counter++;
		    	    }
		    	}
		        bw.write(line);
		        bw.write("\n");
		        counter = 0;
		    }
		    bw.close();
		}catch(IOException ioe) {
	        ioe.printStackTrace();
		}
	}//End of the outputToFile class
		
}//End of the Kmeans_main class
