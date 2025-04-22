package DataProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Read {
    private String fileName;//Path to the input file containing graph data
    private List<Vertex> vertices = new ArrayList<>(); //Cached list of parsed Vertex objects

    /** 
     * Initialize a Read instance
     * @param fileName Path to graph data file 
     */
    public Read(String fileName){this.fileName=fileName;}

    /**@param fileName New file path for data source */
    public void setFileName(String fileName){this.fileName=fileName;}

    /**@return Current configured file path*/
    public String getFileName(){return fileName;}

    /** 
     * Reads and filters valid data lines from specified file
     * @param filePath Path to data file
     * @return List of non-empty trimmed lines 
     * */
    public List<String> readLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isDataLine(line)) {
                    lines.add(line.trim());
                }
            }
        }
        return lines;
    }
    
    /**
     * Validates if a line contains processable data
     * @param line Raw input line from file
     * @return true if line is non-empty after trimming
     */
    private boolean isDataLine(String line) {
        return !line.isEmpty();
    }

    /**
     * Converts raw lines to Vertex objects
     * @param lines Validated data lines from input file
     * @return List of successfully parsed Vertex objects
     */
    public List<Vertex> parseLines(List<String> lines) {
        List<Vertex> result = new ArrayList<>();
        for (String line : lines) {
            Vertex vertex = parseLine(line);
            if (vertex != null) {
                result.add(vertex);
            }
        }
        return result;
    }

    /**
     * Parses single line into Vertex object
     * @param line Data line to parse
     * @return Vertex object or null for invalid lines
     */
    private Vertex parseLine(String line) {
        try {
            String[] tokens = line.split("\\s+");
            if (tokens.length < 3) {
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
    
    /**
     * Main entry point for data processing pipeline
     * @return List of parsed Vertex objects. 
     */
    public List<Vertex> getVertices() {
        try {
            List<String> validLines = readLines(fileName);
            vertices = parseLines(validLines);
        } catch (IOException e) {
            System.err.println("Failed to read file: " + e.getMessage());
        }
        return vertices;
    }
    

}
