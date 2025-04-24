import java.io.IOException;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        // Create a graph
//        WeightedAdjacencyList<String> graph = new WeightedAdjacencyList<>();
//
//        // Add vertices
//        graph.addVertex("A");
//        graph.addVertex("B");
//        graph.addVertex("C");
//
//        // Add edges
//        graph.addEdge("A", "B", 2);
//        graph.addEdge("B", "C", 3);
//        graph.addEdge("A", "C", 6);
//
//        // Test basic methods
//        System.out.println("Vertex count: " + graph.getVertexCount()); // Should be 3
//        System.out.println("Edge count: " + graph.getEdgeCount()); // Should be 3
//        System.out.println("Has edge A->B: " + graph.hasEdge("A", "B")); // true
//        System.out.println("Has edge B->A: " + graph.hasEdge("B", "A")); // false
//
//        // Test neighbors
//        System.out.print("Neighbors of A: ");
//        for (String neighbor : graph.getNeighbors("A")) {
//            System.out.print(neighbor + " ");
//        }
//        System.out.println(); // Should print B C
//
//        // Test shortest paths
//        Map<String, Long> distances = graph.getShortestPaths("A");
//        System.out.println("Shortest paths from A: " + distances);
//        // Should be {A=0, B=2, C=5}


//        BitmapProcessor p = new BitmapProcessor("cs3110/hw4/concentric_black_squares.bmp");
//
//        int[][] pixels = p.getRGBMatrix();
////        int height = pixels.length;
////        int width = pixels[0].length;
//
//        // Step 2: Build graph
//        // Add vertices
//        for (int i = 0; i < pixels.length; i++) {
//            for (int j = 0; j < pixels.length; j++) {
//                //graph.addVertex(i + "," + j);
//                System.out.println(pixels[i][j]);
//            }
//        }


   //-------------------------------------------------------------------------------
        // to read/load the image.

      //String path = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\src\\cs3110\\hw4\\concentric_black_squares.bmp";
        String path = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\src\\cs3110\\hw4\\ex1-upload.bmp";
        Pair<List<Integer>, List<Integer>> result = CharacterSeparator.findSeparationWeighted(path);
        if (result == null) {
            System.out.println("Error loading image");
            return;
        }
        System.out.println("\nrows: " + result.getFirst());
        System.out.println("columns: " + result.getSecond());
     }
//------------------------------------------------------------------------------------

}