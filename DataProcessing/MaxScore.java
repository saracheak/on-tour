import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MaxScore {
    private static List<Integer> SearchArray = new ArrayList<>();

    private static  List<Vertex> vertices;



    private MaxScore(){
        Read.getVertices();
        vertices=Read.vertices;
    }

    private static void SortVertices(){
        for (int i = 0; i < vertices.size(); i++) {
            SearchArray.add(i);
        }
        
        SearchArray.sort((i1, i2) ->
            Integer.compare(vertices.get(i1).getVal(), vertices.get(i2).getVal())
        );
    }

    private static void UpdateCurrent(Vertex vertex,Hashtable<Vertex,Integer> hashvertices){
        if (vertex.numbersOfVertex==0)
        return;
        
        int weight=0;
        
        for(int i=0; i<vertex.numbersOfVertex; i++)
        {
            if (vertices.get(vertex.getNearbyVertex().get(i)-1).getVal()> vertex.getVal())
            {               
                    if (weight< hashvertices.get(vertices.get(vertex.getNearbyVertex().get(i)-1)))
                    {
                        weight=hashvertices.get(vertices.get(vertex.getNearbyVertex().get(i)-1));
                    }
            }            
        }

        weight=weight+vertex.getWeight();
        vertex.ifDiscoveried=!vertex.ifDiscoveried;
        hashvertices.put(vertex, weight);
    }

    private static int LongestTourScore(Hashtable<Vertex,Integer>HashVertices,List<Integer>SearchArray, List<Vertex>vertices){
        int max=0;
        for(int i=vertices.size()-1;i>=0;i--){
            UpdateCurrent(vertices.get(SearchArray.get(i)), HashVertices);
        }
        for(Vertex x:vertices){
            if (HashVertices.get(x)>max){
                max=HashVertices.get(x);
            }
        }
        return max;
    }
    
    public static void main(String args[]){
        Read.getVertices();
        new MaxScore();
        SortVertices();
        Hashtable<Vertex, Integer> HashVertices = new Hashtable<>();
        int max=LongestTourScore(HashVertices, SearchArray, vertices);
        System.out.println(max);
    }
    



}
