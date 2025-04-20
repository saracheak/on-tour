package DataProcessing;

import java.util.ArrayList;
public class Vertex{
    int val;
    int weight;
    int numbersOfVertex;
    ArrayList<Integer> nearbyVertex;
    ArrayList<Integer> nearbyVertexValues;

    public Vertex(int val, int weight, int numbersOfVertex, ArrayList<Integer> nearbyVertex, ArrayList<Integer> nearbyVertexValues) {
        this.val = val;
        this.weight = weight;
        this.numbersOfVertex = numbersOfVertex;
        this.nearbyVertex = nearbyVertex != null ? nearbyVertex : new ArrayList<>();
        this.nearbyVertexValues = new ArrayList<>();
    }


    public int getVal() { return val; }
    public int getWeight() { return weight; }
    public ArrayList<Integer> getNearbyVertex() { return nearbyVertex; }

    public ArrayList<Integer> getNearbyVertexValues(){
        return nearbyVertexValues;
    }

}