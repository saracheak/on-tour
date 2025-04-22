import java.util.ArrayList;
public class Vertex{
    private int val;
    private int weight;
    private int numbersOfVertex;
    public ArrayList<Integer> nearbyVertex;
    private boolean ifDiscoveried;
    private int pointTo;

    public Vertex(int val, int weight, int numbersOfVertex, ArrayList<Integer> nearbyVertex) {
        this.val = val;
        this.weight = weight;
        this.numbersOfVertex = numbersOfVertex;
        this.nearbyVertex = nearbyVertex != null ? nearbyVertex : new ArrayList<>();
        this.ifDiscoveried=false;
        this.pointTo=-1;
    }

    public void setVal (int val){this.val=val;}
    public void setWeight(int weight){this.weight=weight;}
    public void setNumbersOfVertex(int numbersOfVertex){this.numbersOfVertex=numbersOfVertex;}
    public void setIfDiscoveried(){this.ifDiscoveried=!this.ifDiscoveried;}
    public void setPointTo(int val){this.pointTo=val;}

    public int getVal() { return val; }
    public int getWeight() { return weight; }
    public int getNumbersOfVertex() { return numbersOfVertex; }
    public ArrayList<Integer> getNearbyVertex() { return nearbyVertex; }
    public boolean getIfDiscoveried(){return ifDiscoveried;}
    public int getPointTo(){return pointTo;}
}
