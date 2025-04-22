package OnTourScore;

import java.util.ArrayList;
public class Vertex{
    private int val;//The numeric value stored in this vertex
    private int weight;//The weight/score associated with this vertex
    private int numbersOfVertex;// Number of adjacent vertices
    public ArrayList<Integer> nearbyVertex;//List of adjacent vertices
    private boolean ifDiscoveried;//Discovery state flag for graph traversal algorithm
    private int pointTo;//Index of the next vertex in the optimal path

    public Vertex(int val, int weight, int numbersOfVertex, ArrayList<Integer> nearbyVertex) {
        this.val = val;
        this.weight = weight;
        this.numbersOfVertex = numbersOfVertex;
        this.nearbyVertex = nearbyVertex != null ? nearbyVertex : new ArrayList<>();
        this.ifDiscoveried=false;
        this.pointTo=-1;
    }

    /** @param val New numeric value for the vertex */
    public void setVal (int val){this.val=val;}

    /** @param weight New scoring weight for the vertex */
    public void setWeight(int weight){this.weight=weight;}

    /** 
     * @param numbersOfVertex New count of adjacent vertices.
     * Note: Should match the size of nearbyVertex list.
     */
    public void setNumbersOfVertex(int numbersOfVertex){this.numbersOfVertex=numbersOfVertex;}

    /** Toggles the discovery state of the vertex */
    public void setIfDiscoveried(){this.ifDiscoveried=!this.ifDiscoveried;}

    /** 
     * @param val Index of the next vertex in the optimal path.
     * Use -1 to indicate path termination.
     */
    public void setPointTo(int val){this.pointTo=val;}

    /**@return Return variable */
    public int getVal() { return val; }
    public int getWeight() { return weight; }
    public int getNumbersOfVertex() { return numbersOfVertex; }
    public ArrayList<Integer> getNearbyVertex() { return nearbyVertex; }
    public boolean getIfDiscoveried(){return ifDiscoveried;}
    public int getPointTo(){return pointTo;}
}
