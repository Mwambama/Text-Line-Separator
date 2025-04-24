import java.io.IOException;
import java.util.List;

public class WeightedAdjacencyListTest {
    public static void main(String[] args) throws IOException {

        //String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\src\\cs3110\\hw4\\all_black.bmp";
         String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\src\\cs3110\\hw4\\all_white.bmp";

        //String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\am_monologue.bmp";

       //  String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\concentric_black_squares.bmp";

        //String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\every_5th_column_white.bmp";

        //String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\every_5th_row_white.bmp";

         //String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\ex1-upload.bmp";

         //String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\gradient_columns.bmp";

        // String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\top_left_20x20_black.bmp";
        //String imagePath = "C:\\Users\\mwambama\\Downloads\\COM S311\\mwambama-3110-hw4\\hw4-sp25-cs311\\top_left_20x20_white.bmp";



        // Run CharacterSeparator
        Pair<List<Integer>, List<Integer>> result = CharacterSeparator.findSeparationWeighted(imagePath);

        if (result == null) {
            System.out.println("Error occurred during processing.");
            return;
        }

        System.out.println("Whitespace rows: " + result.getFirst());
        System.out.println("Whitespace columns: " + result.getSecond());

        // Now assume you want to visualize the graph:
        System.out.println("\n--- Debugging Graph ---");




    }
}
