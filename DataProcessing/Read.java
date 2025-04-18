import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Read {
    
    public static List<Vertex> vertices = new ArrayList<>(); 

    public static List<String> readLines(String FilePath) throws IOException {
        List<String> Lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FilePath))) {
            String Line;
            while ((Line = br.readLine()) != null) {
                if (isDataLine(Line)) {
                    Lines.add(Line.trim());
                }
            }
        }
        return Lines;
    }
    
    private static boolean isDataLine(String Line) {
        return !Line.isEmpty();
    }

    public static List<Vertex> parseLines(List<String> Lines) {
        List<Vertex> result = new ArrayList<>();
        for (String Line : Lines) {
            Vertex vertex = parseLine(Line);
            if (vertex != null) {
                result.add(vertex);
            }
        }
        return result;
    }


    private static Vertex parseLine(String line) {
        try {
            String[] tokens = line.split("\\s+");
            if (tokens.length < 3) {
                System.err.println("wrong numbers of data: " + line);
                return null;
            }

            int val = Integer.parseInt(tokens[0]);
            int weight = Integer.parseInt(tokens[1]);
            int numbersOfVertex = Integer.parseInt(tokens[2]);
            ArrayList<Integer> nearbyVertex = new ArrayList<>();
        if (tokens.length > 3) {
            nearbyVertex = new ArrayList<>();
            for (int i = 3; i < tokens.length; i++) {
                int num = Integer.parseInt(tokens[i]);
                nearbyVertex.add(num);
            }
        }
            return new Vertex(val, weight, numbersOfVertex, nearbyVertex);
        } catch (NumberFormatException e) {
            System.err.println("Error, skip " + line);
            return null;
        }
    }
    
        public static List<Vertex> getVertices() {
        String fileName = "example-input.txt";
        try {
            List<String> validLines = readLines(fileName);
            vertices = parseLines(validLines);
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
    
        return vertices;
    }
    
    public static void main(String args[]){
        getVertices();
        System.out.println("Numbers of vertices: " + vertices.size());
        vertices.forEach(v -> System.out.println(
            "Val: " + v.getVal() + 
            ", Weight: " + v.getWeight() + 
            ", Nearby: " + v.getNearbyVertex()
        ));
    }

}
