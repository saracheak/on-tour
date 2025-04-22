package DataProcessing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class OnTourScorer {
    /**
     * Sorts vertices by their stored values in ascending order.
     * Populates searchArray with indices sorted by vertex values.
     * @param vertices List of all vertices in the graph
     * @param searchArray Empty list to store sorted vertex indices
     */
    private static void sortVertices(List<Vertex> vertices,List<Integer> searchArray){
        for (int i = 0; i < vertices.size(); i++) {
            searchArray.add(i);
        }      
        searchArray.sort((i1, i2) ->
        Integer.compare(vertices.get(i1).getVal(), vertices.get(i2).getVal())
        );
    }

    /**
     * Updates the current vertex's maximum score and optimal path pointer.
     * Only considers neighbors with higher values (forward direction).
     * @param vertex Current vertex being processed
     * @param hashvertices Map storing accumulated scores for each vertex
     * @param vertices Complete list of graph vertices
     */
    private static void updateCurrent(Vertex vertex,Hashtable<Vertex,Integer> hashvertices,List<Vertex> vertices){
        if (vertex.getNumbersOfVertex()==0)
        return;
        
        int weight=0;
        
        for(int i=0; i<vertex.getNumbersOfVertex(); i++)
        {
            if (vertices.get(vertex.getNearbyVertex().get(i)-1).getVal()> vertex.getVal())
            {               
                    if (weight< hashvertices.get(vertices.get(vertex.getNearbyVertex().get(i)-1)))
                    {
                        weight=hashvertices.get(vertices.get(vertex.getNearbyVertex().get(i)-1));
                        vertex.setPointTo(vertex.getNearbyVertex().get(i)-1);
                    }
            }            
        }
        weight=weight+vertex.getWeight();
        hashvertices.put(vertex, weight);
    }

    /**
     * Calculates maximum tour score using dynamic programming approach.
     * Processes vertices in reverse value order (from highest).
     * @param hashVertices Map to store accumulated scores
     * @param searchArray Sorted vertex indices by value
     * @param vertices Complete list of vertices
     * @return Vertex with highest accumulated score
     */
    private static Vertex longestTourScore(Hashtable<Vertex,Integer>hashVertices,List<Integer>searchArray, List<Vertex>vertices){
        int max=0;
        for(int i=vertices.size()-1;i>=0;i--){
            updateCurrent(vertices.get(searchArray.get(i)), hashVertices,vertices);
        }
        Vertex maxVertex=new Vertex(0, 0, 0, null);
        for(Vertex x:vertices){
            if (hashVertices.get(x)>max){
                max=hashVertices.get(x);
                maxVertex=x;
            }
        }
        return maxVertex;
    }

    /**
     * Backtracks through optimal path using stored pointers
     * @param vertices Complete list of vertices
     * @param currentVertex Starting vertex for backtracking
     * @return Next vertex in optimal path
     */
    private static Vertex trackTour(List<Vertex> vertices,Vertex currenVertex){
        return vertices.get(currenVertex.getPointTo());
    }

    /**
     * Writes results to output.txt with two lines:
     * 1. Maximum score
     * 2. Space-separated path values in reverse order (end to start)
     * @param max Starting vertex of optimal path (end of actual path)
     * @param hashVertices Map containing final scores
     * @param vertices Complete vertex list
     */
    public static void output(Vertex max,Hashtable<Vertex,Integer>hashVertices,List<Vertex>vertices){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(String.valueOf(hashVertices.get(max)));
            writer.newLine();

            List<Integer> pathValues = new ArrayList<>();
            pathValues.add(max.getVal()); 

            // Backtrack through optimal path
            while (max.getPointTo() != -1) {
                max = trackTour(vertices, max);
                pathValues.add(max.getVal());
            }


            StringBuilder pathLine = new StringBuilder();
            for (int val : pathValues) {
                pathLine.append(val).append(" ");
            }
            if (!pathValues.isEmpty()) {
                pathLine.deleteCharAt(pathLine.length() - 1); 
            }

            writer.write(pathLine.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Main execution pipeline:
     * 1. Read input data
     * 2. Sort vertices
     * 3. Calculate optimal path
     * 4. Generate output
     */
    public static void main(String args[]){
        String fileName="small-input.txt";
        Read Input=new Read(fileName);
        List<Vertex> vertices=Input.getVertices();
        List<Integer> searchArray = new ArrayList<>();
        sortVertices(vertices,searchArray);
        Hashtable<Vertex, Integer> hashVertices = new Hashtable<>();
        Vertex max=longestTourScore(hashVertices, searchArray, vertices);
        output(max, hashVertices, vertices);
    }
}