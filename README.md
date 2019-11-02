# ABOUT
In this program I implement the k-means algorithm using Euclidean distance on a dataset with two attributes.  

The input parameter to the program is the number of clusters (k).  

Initialization:  
Based on the input parameter(k), I randomly select k points as centroids.  

Termination Condition:  
The usual termination condition in k-means is when the centroids no longer move.  
I also limit the update step to a maximum of 25 iterations.  

Input:  
The input file is specified by the parameter (input-file-name).  

Output:  
The code also outputs to a file called specified by the parameter (output-file-name)  
in the format:  
(cluster-id) (List of points ids separated by comma)  

For example,  
1                2, 4, 7, 10  

Validation:  
The usual method of evaluating the goodness of clustering is be used.  
It is the Sum of Squared Error function, defined as:

SSE = ∑ ∑ dist^2 ( mi , x )  
i =1 to K  
x ∈ Ci  
where mi is the centroid of the ith cluster.  

I run the code at least 5 times with different values of k and include the details of SSE in the RESULTS file.

Program was written in Java  

# COMPILING, INSTALLATION AND RUNNING  
Program files are Kmeans_main.java, Kmeans.java, Cluster.java and Point.java 

## Compiling:  
Can compile with the following command:  
javac -g Cluster.java Kmeans.java Point.java Kmeans_main.java  
 
Or by running the compile.script.bash  
./compile_script.bash  

## How to run the code and a genric run command statement along with an example  
You can run the compiled code via the command in the unpacked directory:  
java Kmeans_main 25 ../data/test_data.txt output.txt  

Or you can run the code using the script: run_script.bash  

Generic run command: ./run_script.bash  

Example using directory structure as unpacked:  
$ ./run_script.bash  

## RESULTS  

Results are shown in the RESULTS.pdf file  

## LICENSE  
[MIT License](https://github.com/shoeloh/k-means/blob/master/LICENSE)  

