Feel Free to change anything! More efficient or creative is great!




Vertex.java 
It is main structure I use to store each vertex's information, including its value called val as int, its weight call weight as int, its number of nearby vertex called numbersOfVertex as int and the nearby vertexs store in ArrayList<int> called nearbyvertex.
It also contains functions like getVal or getWeight but I'm not sure if we need to make it more formal. For example, declear two functions about val, one is getVal and other one is changeVal to make private int val.


Read.java
This file's main purpose is to read the input file and store the data in actual data structure. Using "List<Vertex> vertices " to store each vertex information for easier searching. NOTICE: the example-input file do not have line 0, but vertices do have 0 number as the first vertices. It really important when we need find the nearby vertex, we may subtracts 1.
  
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



Arraylisttohashtable.java
This file is to transform the data in ArrayList<vextex> into a sorted version in a hash map(store each vertex according to their val as their key)

  Hashtable<Integer,Vertex> getHashVertex;
  declear hashtable

  public void vertexToHash
  Input vertice(as ArrayList<vertex>), store in hashtable
  put in according to their value




  NOTICE: there might be a way to directly change their nearby vertex number into their value and then sorted. It might be easier to directly store vertex in hashtable instead of needing a List<vertex> as a transfer
