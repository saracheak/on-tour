package DataProcessing;

import java.util.ArrayList;
import java.util.Hashtable;

public class ArrayListToHashTable {
    static Hashtable<Integer,Integer> vertexHashTable = new Hashtable<>();
    
    static public Hashtable<Integer,Integer> vertexToHash(ArrayList<Vertex> vertices){
        for (Vertex vertex : vertices) {
            vertexHashTable.put(vertex.getVal(), 0);
        }
        return vertexHashTable;
    }


}

