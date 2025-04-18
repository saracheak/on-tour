import java.util.ArrayList;
import java.util.Hashtable;

public class Arraylisttohashtable {

    Hashtable<Integer,Vertex> getHashVertex;
    
    public void vertexToHash(ArrayList<Vertex> vertices){
        for(int i=0;i<vertices.size();i++){
            getHashVertex.put(vertices.get(i).getVal(), vertices.get(i));
        }
    }


}

