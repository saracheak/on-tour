import java.util.ArrayList;
public class Vertex{
    int val;
    int weight;
    int numbersOfVertex;
    ArrayList<Integer> nearbyVertex;

    public Vertex(int val, int weight, int numbersOfVertex, ArrayList<Integer> nearbyVertex) {
        this.val = val;
        this.weight = weight;
        this.numbersOfVertex = numbersOfVertex;
        this.nearbyVertex = nearbyVertex != null ? nearbyVertex : new ArrayList<>();
    }


    public int getVal() { return val; }
    public int getWeight() { return weight; }
    public ArrayList<Integer> getNearbyVertex() { return nearbyVertex; }
}