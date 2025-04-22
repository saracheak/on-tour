### OnTourScorer.java (includes main class)


### Vertex.java
Vertex.java is our blueprint class where we create vertex objects from. Each vertex has int val (vertex value), int weight (score), ArrayList<Integer> numbersOfVertex (arraylist of neighbour vertices' indices), ArrayList<Integer> nearbyvertex (arraylist of neighbour vertices' values). Includes getter functions for each of these fields. 

### Read.java
Read.java reads the input file, creates Vertex objects using values from the file and stores Vertexes in List<Vertex> vertices for easier searching. NOTICE: the example-input file do not have line 0, but vertices do have 0 number as the first vertices. It really important when we need find the nearby vertex, we subtract by 1.
  
  public static List<Vertex> vertices = new ArrayList<>(); 
  store the vertices from input
      
  public static List<String> readLines
  Input file name, read files and return data in List<String> structure call Lines. 
  Using BufferReader, storing in String called Line, and string Line in lines

  private static boolean isDataLine
  Input line, make sure the line is not empty return true and false
  isempty()

  private static Vertex parseLine
  Input line, parse information in line and store in vertex structure, return vertex
  Using tokens and split to gain each augment

  public static List<Vertex> parseLines
  Input lines, using parseLines and store in ArrayList<Vertex>

  public static List<Vertex> getVertices
  using the functions former function to get vertices 

  public static void main
  just displays to check



### Arraylisttohashtable.java
Arraylisttohashtable.java transforms data in ArrayList<Vertex> vertices into a sorted version in a hash table, storing each vertex according to their val as their key.

  Hashtable<Integer,Vertex> getHashVertex;
  declare hashtable

  public void vertexToHash
  Input Vertex(as ArrayList<vertex>), store in hashtable
  put in according to their value




  NOTICE: there might be a way to directly change their nearby vertex number into their value and then sorted. It might be easier to directly store vertex in hashtable instead of needing a List<vertex> as a transfer
