import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Hw4Tests {
    public static void main(String[] args) throws IOException {
//        System.out.println("Running all tests...\n");

            Pair<List<Integer>, List<Integer>> result = CharacterSeparator.findSeparationWeighted("C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\concentric_black_squares.bmp");
            System.out.print("Rows: ");
            for(Integer i: result.getFirst()){
                System.out.print("\t" + i);
            }
        System.out.println();
        System.out.print("Cols: ");
        for(Integer i: result.getSecond()){
            System.out.print("\t" + i);
        }
        testWeightedGraphBasicOperations();
        testDijkstraShortestPath();
        testCharacterSeparatorSimpleImage();
        testInvalidInputs();
        testComplexGraph();
        
        System.out.println("\nAll tests completed.");

        System.out.println("\nCreating visualization...");
       // CharacterSeparator.visualizeSeparations("./ex1-upload.bmp");
       // Hw4Tests.visualizeSeparations("C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\src\\cs3110\\hw4\\concentric_black_squares.bmp");
       //  visualizeSeparations("./ex1-upload.bmp");
       //visualizeSeparations("C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\concentric_black_squares.bmp");
        visualizeSeparations("C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\top_left_20x20_black.bmp");

    }

    public static void testWeightedGraphBasicOperations() {
        System.out.println("\nTesting Basic Graph Operations:");
        try {
            List<String> vertices = Arrays.asList("A", "B", "C", "D");
            WeightedAdjacencyList<String> graph = new WeightedAdjacencyList<>(vertices);
            
            System.out.println("- Vertex count: " + 
                (graph.getVertexCount() == 4 ? "PASS" : "FAIL (expected 4, got " + graph.getVertexCount() + ")"));
            
            boolean edgesAdded = graph.addEdge("A", "B", 5) &&
                               graph.addEdge("B", "C", 3) &&
                               graph.addEdge("C", "D", 2) &&
                               graph.addEdge("D", "A", 4);
            System.out.println("- Adding edges: " + (edgesAdded ? "PASS" : "FAIL"));
            
            System.out.println("- Edge count: " + 
                (graph.getEdgeCount() == 4 ? "PASS" : "FAIL (expected 4, got " + graph.getEdgeCount() + ")"));
            
            boolean edgeTests = graph.hasEdge("A", "B") && !graph.hasEdge("A", "C");
            System.out.println("- Edge existence check: " + (edgeTests ? "PASS" : "FAIL"));
            
            List<String> aNeighbors = new ArrayList<>();
            graph.getNeighbors("A").forEach(aNeighbors::add);
            boolean neighborTest = aNeighbors.size() == 1 && aNeighbors.contains("B");
            System.out.println("- Neighbor check: " + (neighborTest ? "PASS" : "FAIL"));
            
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
        }
        System.out.println();
    }

    public static void testDijkstraShortestPath() {
        System.out.println("Testing Dijkstra's Shortest Path:");
        try {
            List<String> vertices = Arrays.asList("A", "B", "C", "D");
            WeightedAdjacencyList<String> graph = new WeightedAdjacencyList<>(vertices);
            
            graph.addEdge("A", "B", 1);
            graph.addEdge("B", "C", 2);
            graph.addEdge("C", "D", 3);
            graph.addEdge("A", "D", 10);
            
            Map<String, Long> paths = graph.getShortestPaths("A");
            
            System.out.println("- Distance to A: " + 
                (paths.get("A") == 0L ? "PASS" : "FAIL (expected 0, got " + paths.get("A") + ")"));
            System.out.println("- Distance to B: " + 
                (paths.get("B") == 1L ? "PASS" : "FAIL (expected 1, got " + paths.get("B") + ")"));
            System.out.println("- Distance to C: " + 
                (paths.get("C") == 3L ? "PASS" : "FAIL (expected 3, got " + paths.get("C") + ")"));
            System.out.println("- Distance to D: " + 
                (paths.get("D") == 6L ? "PASS" : "FAIL (expected 6, got " + paths.get("D") + ")"));
            
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
        }
        System.out.println();
    }

    public static void testCharacterSeparatorSimpleImage() {
        System.out.println("Testing Character Separator:");
        try {
//            String testImagePath = "./ex1-upload.bmp";
            //String testImagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\ex1-upload.bmp";
            //String testImagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\concentric_black_squares.bmp";
            String testImagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\top_left_20x20_black.bmp";
            Pair<List<Integer>, List<Integer>> separations =
                CharacterSeparator.findSeparationWeighted(testImagePath);
            
            List<Integer> rowSeps = separations.getFirst();
            List<Integer> colSeps = separations.getSecond();
            
            System.out.println("- Row separations found: " + 
                (!rowSeps.isEmpty() ? "PASS" : "FAIL (no separations found)"));
            System.out.println("- Column separations found: " + 
                (!colSeps.isEmpty() ? "PASS" : "FAIL (no separations found)"));
            
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
        }
        System.out.println();
    }

    public static void testInvalidInputs() {
        System.out.println("Testing Invalid Inputs:");
        try {
            WeightedAdjacencyList<String> graph = new WeightedAdjacencyList<>(new ArrayList<>());
            
            System.out.println("- Null vertex: " + 
                (!graph.addVertex(null) ? "PASS" : "FAIL"));
            
            System.out.println("- Null edge endpoints: " + 
                (!graph.addEdge(null, "A", 1) && !graph.addEdge("A", null, 1) ? "PASS" : "FAIL"));
            
            graph.addVertex("A");
            graph.addVertex("B");
            System.out.println("- Negative weight: " + 
                (!graph.addEdge("A", "B", -1) ? "PASS" : "FAIL"));
            
            Pair<List<Integer>, List<Integer>> result = 
                CharacterSeparator.findSeparationWeighted("nonexistent.bmp");
            System.out.println("- Non-existent file: " + 
//                (result.getFirst().isEmpty() && result.getSecond().isEmpty() ? "PASS" : "FAIL"));
                    (result == null ? "PASS" : "FAIL"));
            
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
        }
        System.out.println();
    }

    private static boolean isSorted(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i-1)) {
                return false;
            }
        }
        return true;
    }

    public static void testComplexGraph() {
        System.out.println("Testing Complex Graph:");
        try {
            List<String> vertices = Arrays.asList("A", "B", "C", "D", "E", "F");
            WeightedAdjacencyList<String> graph = new WeightedAdjacencyList<>(vertices);
            
            graph.addEdge("A", "B", 4);
            graph.addEdge("A", "C", 2);
            graph.addEdge("B", "C", 1);
            graph.addEdge("B", "D", 5);
            graph.addEdge("C", "D", 8);
            graph.addEdge("C", "E", 10);
            graph.addEdge("D", "E", 2);
            graph.addEdge("D", "F", 6);
            graph.addEdge("E", "F", 3);
            
            Map<String, Long> paths = graph.getShortestPaths("A");
            
            System.out.println("- Distance to A: " + 
                (paths.get("A") == 0L ? "PASS" : "FAIL (expected 0, got " + paths.get("A") + ")"));
            System.out.println("- Distance to B: " + 
                (paths.get("B") == 4L ? "PASS" : "FAIL (expected 4, got " + paths.get("B") + ")"));
            System.out.println("- Distance to C: " + 
                (paths.get("C") == 2L ? "PASS" : "FAIL (expected 2, got " + paths.get("C") + ")"));
            System.out.println("- Distance to D: " + 
                (paths.get("D") == 9L ? "PASS" : "FAIL (expected 9, got " + paths.get("D") + ")"));
            System.out.println("- Distance to E: " + 
                (paths.get("E") == 11L ? "PASS" : "FAIL (expected 11, got " + paths.get("E") + ")"));
            System.out.println("- Distance to F: " + 
                (paths.get("F") == 14L ? "PASS" : "FAIL (expected 14, got " + paths.get("F") + ")"));
            
        } catch (Exception e) {
            System.out.println("Test failed with exception: " + e.getMessage());
        }
        System.out.println();
    }

    public static void visualizeSeparations(String inputPath) {
        try {
            System.out.println("Loading image from: " + inputPath);

            Pair<List<Integer>, List<Integer>> separations = CharacterSeparator.findSeparationWeighted(inputPath);
            List<Integer> rowSeps = separations.getFirst();
            List<Integer> colSeps = separations.getSecond();

            BitmapProcessor processor = new BitmapProcessor(inputPath);
            BufferedImage image = processor.bi;
            int width = image.getWidth();
            int height = image.getHeight();

            System.out.println("Found " + rowSeps.size() + " row separations");
            System.out.println("Found " + colSeps.size() + " column separations");

            for (Integer row : rowSeps) {
                for (int x = 0; x < width; x++) {
                    image.setRGB(x, row, Color.RED.getRGB());
                }
            }

            for (Integer col : colSeps) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(col, y, Color.GREEN.getRGB());
                }
            }

            processor.writeToFile(); // Make sure this writes to a file like "inputPath + .new.bmp"
            System.out.println("Saving processed image as: " + inputPath + ".new.bmp");

        } catch (IOException e) {
            System.out.println("Error processing image: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}
