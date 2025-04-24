import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *  this is the CharacterSeparator that uses a weighted graph representation of a bitmap image
 * to find separations (whitespace) between characters using Dijkstra's algorithm.
 */
public class CharacterSeparator {
    /**
     * This method uses the WeightedAdjacencyList class to identify the space between characters in an image of text.
     * For efficiency, it should only construct a single graph object and should only make a constant
     * number of calls to Dijkstra's algorithm.
     * @param path The location of the image on disk.
     * @return Two lists of Integer. The first list indicates whitespace rows. The second list indicates whitespace columns. Returns null if some error occurred loading the image.
     */
    public static Pair<List<Integer>, List<Integer>> findSeparationWeighted(String path) {
         //declaring the bitmap processsor in here
        BitmapProcessor bp;
        try {
            bp = new BitmapProcessor(path);
        } catch (IOException e) {
            System.err.println("Error loading images: " + e.getMessage());
            return null;
        }

        int[][] pixels = bp.getRGBMatrix();
        int height = pixels.length;       // Number of rows
        int width = pixels[0].length;     // Number of columns

        // Initializing the weighted graph
        WeightedAdjacencyList<String> graph = new WeightedAdjacencyList<>(new ArrayList<>());

        // Adding each pixel as a vertex in the graph
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                graph.addVertex(i + "," + j);
            }
        }

        // Defining 4-directional adjacency (up, down, left, right)
        int[][] adj = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};

        // Adding the edges between adjacent pixels with weight based on pixel color
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String u = i + "," + j;
                for (int[] neighbor : adj) {
                    int x = neighbor[0];
                    int y = neighbor[1];
                    if (0 <= i + x && i + x < height && 0 <= j + y && j + y < width) {
                        String v = (i + x) + "," + (j + y);
                        if (pixels[i + x][j + y] == 0xFFFFFFFF) {
                            graph.addEdge(u, v, 1); // only whitespace|
                        } else {
                            graph.addEdge(u, v, 100); // anything other than whitespace, Discourage dark/black pixels
                        }
                    }
                }
            }
        }

        // Detecting whitespace rows
        List<Integer> whitespaceRows = new ArrayList<>();
        String rowSrc = "-1,-1,";
        graph.addVertex(rowSrc);

        // Adding the source edges from left side for each row
        for (int i = 0; i < height; i++) {
            if (pixels[i][0] == 0xFFFFFFFF) {
                graph.addEdge(rowSrc, i + ",0", 0);
            }
        }

        Map<String, Long> distances = graph.getShortestPaths(rowSrc);

        // Checking if right-side pixel in row is reachable with expected weight
        for (int i = 0; i < height; i++) {
            if (distances.containsKey(i + "," + (width - 1)) && distances.get(i + "," + (width - 1)) == width - 1) {
                whitespaceRows.add(i);
            }
        }

        // Detecting the whitespace columns
        List<Integer> whitespaceCols = new ArrayList<>();
        String colSrc = "-2,-2,";
        graph.addVertex(colSrc);

        // Adding the source edges from top for each column
        for (int i = 0; i < width; i++) {
            if (pixels[0][i] == 0xFFFFFFFF) {
                graph.addEdge(colSrc, "0," + i, 0);
            }
        }

        distances = graph.getShortestPaths(colSrc);

        // Checking if bottom-side pixel in column is reachable with expected weight
        for (int i = 0; i < width; i++) {
            if (distances.containsKey((height - 1) + "," + i) && distances.get((height - 1) + "," + i) == height - 1) {
                whitespaceCols.add(i);
            }
        }

        // Returning the whitespace rows and columns found
        return new Pair<>(whitespaceRows, whitespaceCols);
    }
}


