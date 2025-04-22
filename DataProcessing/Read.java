package DataProcessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Read {
    private String fileName;

    public void setFileName(String fileName){this.fileName=fileName;}

    public String getFileName(){return fileName;}

    private List<Vertex> vertices = new ArrayList<>(); 

    public Read(String fileName){this.fileName=fileName;}

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
    
    private boolean isDataLine(String line) {
        return !line.isEmpty();
    }

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
