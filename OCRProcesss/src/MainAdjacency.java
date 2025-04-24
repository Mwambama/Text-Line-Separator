import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Alisala Mwamba
 */
public class MainAdjacency<T> {

    /**
     * Notes:
     * <p>
     * These tests only cover the WeightedAdjacencyList class, and there are no tests
     * for CharacterSeparator. This is because CharacterSeparator is only for images,
     * and I don't know what images you have on your computer.
     * <p>
     * The WeightedAdjacencyList class NEEDS to work with generic types. I went to
     * office hours and was told this directly, so ignoring it and making your program
     * work with only images is wrong.
     * <p>
     * There is more than one way to implement the adjacency list, so this class will
     * assume you have all your methods done and simply call the methods given in
     * the skeleton code.
     * <p>
     * By default, every test is annotated with @Test and @Ignore. As you implement your
     * class, remove the @Ignore tags that correspond to the tests you want to run. I've
     * Javadoc-ed every test so you have an idea of what might be useful to you. They're
     * also loosely ordered on the order I tested my own code in.
     * <p>
     * Some tests will track the amount of time your algorithm takes to run. Mine takes
     * around 5ms give or take for each of them, and my implementation is pretty simple
     * and unoptimized, so you can use that as a baseline. Either way, these aren't great
     * indicators of how fast your program actually is, since in reality most graphs
     * (including the images we can run through this thing) are going to contain hundreds
     * to thousands of nodes and edges, far outpacing what I can reasonably write by hand.
     * <p>
     * IF YOUR CODE DOES NOT PASS THESE TESTS IT IS NOT NECESSARILY WRONG!!!
     * There is more than one way to do this assignment, and you should modify the tests
     * as needed to suit your own code.
     * <p>
     * Good luck!
     */

    @Test
    //@Ignore
    /**
     * Test 1 - getVertexCount(), hasVertex(), getVertices()
     *
     * Graph used for this test:
     *
     *    [A]     [B]
     *
     *
     *    [C]     [D]
     */
    public void test1() {
        List<T> vertices = List.of((T[]) new String[]{"A", "B", "C", "D"});
        WeightedAdjacencyList<T> W = new WeightedAdjacencyList<>(vertices);

        assertEquals("W should have 4 vertices.", 4, W.getVertexCount());
        assertTrue("W should contain 'A'.", W.hasVertex((T) "A"));
    }


    @Test
   // @Ignore
    /**
     * Test 2 - addEdge(), getEdgeCount(), hasEdge(), getNeighbors(), areNeighbors()
     *
     * Graph used for this test: (all weights 0)
     *
     *    [A] --- [B]
     *     |
     *     |
     *    [C] --- [D]
     */


    public void test2() {
        List<T> vertices = List.of((T[]) new String[]{"A", "B", "C", "D"});
        WeightedAdjacencyList<T> W = new WeightedAdjacencyList<>(vertices);

        assertTrue("addEdge() should return 'true'.", W.addEdge((T) "A", (T) "B", 0));
        assertFalse("addEdge() should return 'false' because the edge A-B already exists.", W.addEdge((T) "A", (T) "B", 0));
        W.addEdge((T) "C", (T) "D", 0);
        W.addEdge((T) "A", (T) "C", 0);

        assertFalse("addEdge() should return 'false' because the graph does not contain 'E'.", W.addEdge((T) "A", (T) "E", 0));
        // NOTE: In my implementation, edges are technically one-way, so A-B and B-A are different, and both get added when calling addEdge()
        assertEquals("W should have 6 edges.", 6, W.getEdgeCount());
        assertTrue("The edge A-B should be in W.", W.hasEdge((T) "A", (T) "B"));
        assertFalse("The edge A-D should not be in W.", W.hasEdge((T) "A", (T) "D"));
                  // they are expecting to return the count for an ArrayList  but I am using a hashmap
        //        ArrayList<T> neighbors = (ArrayList) W.getNeighbors((T) "A");
        // Use Iterable and count neighbors
        Iterable<T> neighbors = W.getNeighbors((T) "A");
        int neighborCount = 0;
        for (T neighbor : neighbors) {
            neighborCount++;
        }
        assertEquals("A should have 2 neighbors.", 2, neighborCount);

        assertTrue("C should be a neighbor of D.", W.areNeighbors((T) "C", (T) "D"));
        assertFalse("B should not be a neighbor of D.", W.areNeighbors((T) "B", (T) "D"));
//        assertEquals("A should have 2 neighbors.", 2, neighbors.size());
//        assertTrue("C should be a neighbor of D.", W.areNeighbors((T) "C", (T) "D"));
//        assertFalse("B should not be a neighbor of D.", W.areNeighbors((T) "B", (T) "D"));
    }


    @Test
  //  @Ignore
/**
     * Test 3 - getShortestPaths(). Also tests time taken.
     *
     * Graph used for this test:
     *
     *    [A] --- [B]
     *     |       |
     *     |       |
     *    [C] --- [D]
     *
     *    A-B = 2
     *    A-C = 1
     *    B-D = 3
     *    C-D = 0
     */


    public void test3() {
        long startTime = System.currentTimeMillis();
        List<T> vertices = List.of((T[]) new String[]{"A", "B", "C", "D"});
        WeightedAdjacencyList<T> W = new WeightedAdjacencyList<>(vertices);

        W.addEdge((T) "A", (T) "B", 2);
        W.addEdge((T) "A", (T) "C", 1);
        W.addEdge((T) "B", (T) "D", 3);
        W.addEdge((T) "C", (T) "D", 0);

        Map<T, Long> shortestPaths = W.getShortestPaths((T) "A");
        Long aa = 0L;
        Long ab = 2L;
        Long ac = 1L;
        Long ad = 1L;
        assertEquals("A -> A should be 0.", aa, shortestPaths.get((T) "A"));
        assertEquals("A -> B should be 2.", ab, shortestPaths.get((T) "B"));
        assertEquals("A -> C should be 1.", ac, shortestPaths.get((T) "C"));
        assertEquals("A -> D should be 1.", ad, shortestPaths.get((T) "D"));

        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed time for test 3: " + (endTime - startTime) + "ms");
    }


    @Test
    //@Ignore
/**
     * Test 4 - getShortestPaths(). Also tests time taken.
     *
     * Graph used for this test:
     *
     *    [A] --- [B]
     *     |       |
     *     |       |
     *    [C] --- [D]
     *     |       |
     *     |       |
     *    [E] --- [F]
     *
     *    A-B = 1
     *    A-C = 5
     *    B-D = 1
     *    C-D = 3
     *    C-E = 10
     *    D-F = 1
     *    E-F = 2
     */


    public void test4() {
        long startTime = System.currentTimeMillis();
        List<T> vertices = List.of((T[]) new String[]{"A", "B", "C", "D", "E", "F"});
        WeightedAdjacencyList<T> W = new WeightedAdjacencyList<>(vertices);

        W.addEdge((T) "A", (T) "B", 1);
        W.addEdge((T) "A", (T) "C", 5);
        W.addEdge((T) "B", (T) "D", 1);
        W.addEdge((T) "C", (T) "D", 3);
        W.addEdge((T) "C", (T) "E", 10);
        W.addEdge((T) "D", (T) "F", 1);
        W.addEdge((T) "E", (T) "F", 2);

        Map<T, Long> shortestPaths = W.getShortestPaths((T) "A");
        Long aa = 0L;
        Long ab = 1L;
        Long ac = 5L;
        Long ad = 2L;
        Long ae = 5L;
        Long af = 3L;
        assertEquals("A -> A should be 0.", aa, shortestPaths.get((T) "A"));
        assertEquals("A -> B should be 1.", ab, shortestPaths.get((T) "B"));
        assertEquals("A -> C should be 5.", ac, shortestPaths.get((T) "C"));
        assertEquals("A -> D should be 2.", ad, shortestPaths.get((T) "D"));
        assertEquals("A -> E should be 2.", ae, shortestPaths.get((T) "E"));
        assertEquals("A -> F should be 2.", af, shortestPaths.get((T) "F"));

        shortestPaths = W.getShortestPaths((T) "C");
        Long ca = 5L;
        Long cb = 4L;
        Long cc = 0L;
        Long cd = 3L;
        Long ce = 6L;
        Long cf = 4L;
        assertEquals("C -> A should be 5.", ca, shortestPaths.get((T) "A"));
        assertEquals("C -> B should be 4.", cb, shortestPaths.get((T) "B"));
        assertEquals("C -> C should be 0.", cc, shortestPaths.get((T) "C"));
        assertEquals("C -> D should be 3.", cd, shortestPaths.get((T) "D"));
        assertEquals("C -> E should be 6.", ce, shortestPaths.get((T) "E"));
        assertEquals("C -> F should be 4.", cf, shortestPaths.get((T) "F"));

        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed time for test 4: " + (endTime - startTime) + "ms");
    }



    /**
     * Makes the following graph:
     *
     *    [A] --- [B] --- [C] --- [D]
     *     |       |       |       |
     *     |       |       |       |
     *    [E]     [F] --- [G]     [H]
     *     |       |               |
     *     |       |               |
     *    [I] --- [J] --- [K]     [L]
     *             |       |       |
     *             |       |       |
     *    [M] --- [N]     [O] --- [P]
     *
     *    A-B = 2
     *    A-E = 10
     *    B-C = 0
     *    B-F = 7
     *    C-D = 7
     *    C-G = 6
     *    D-H = 1
     *    E-I = 3
     *    F-G = 11
     *    F-J = 9
     *    H-L = 2
     *    I-J = 1
     *    J-K = 5
     *    J-N = 0
     *    K-O = 12
     *    L-P = 2
     *    M-N = 4
     *    O-P = 0
     */

    public WeightedAdjacencyList<T> makeBigOlGraph() {
        List<T> vertices = List.of((T[]) new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"});
        WeightedAdjacencyList<T> W = new WeightedAdjacencyList<>(vertices);

        W.addEdge((T) "A", (T) "B", 2);
        W.addEdge((T) "A", (T) "E", 10);
        W.addEdge((T) "B", (T) "C", 0);
        W.addEdge((T) "B", (T) "F", 7);
        W.addEdge((T) "C", (T) "D", 7);
        W.addEdge((T) "C", (T) "G", 6);
        W.addEdge((T) "D", (T) "H", 1);
        W.addEdge((T) "E", (T) "I", 3);
        W.addEdge((T) "F", (T) "G", 11);
        W.addEdge((T) "F", (T) "J", 9);
        W.addEdge((T) "H", (T) "L", 2);
        W.addEdge((T) "I", (T) "J", 1);
        W.addEdge((T) "J", (T) "K", 5);
        W.addEdge((T) "J", (T) "N", 0);
        W.addEdge((T) "K", (T) "O", 12);
        W.addEdge((T) "L", (T) "P", 2);
        W.addEdge((T) "M", (T) "N", 4);
        W.addEdge((T) "O", (T) "P", 0);

        return W;
    }

    @Test
   // @Ignore
    /**
     * Test 5 - getShortestPaths(). Also tests time taken.
     *
     * Graph used for this test:
     *    Big ol' Graph
     */


    public void test5() {
        long startTime = System.currentTimeMillis();
        WeightedAdjacencyList<T> W = makeBigOlGraph();
        Map<T, Long> shortestPaths = W.getShortestPaths((T) "A");
        assertEquals("A -> A should be 0.", Long.valueOf(0), shortestPaths.get((T) "A"));
        assertEquals("A -> B should be 2.", Long.valueOf(2), shortestPaths.get((T) "B"));
        assertEquals("A -> C should be 2.", Long.valueOf(2), shortestPaths.get((T) "C"));
        assertEquals("A -> D should be 9.", Long.valueOf(9), shortestPaths.get((T) "D"));
        assertEquals("A -> E should be 10.", Long.valueOf(10), shortestPaths.get((T) "E"));
        assertEquals("A -> F should be 9.", Long.valueOf(9), shortestPaths.get((T) "F"));
        assertEquals("A -> G should be 8.", Long.valueOf(8), shortestPaths.get((T) "G"));
        assertEquals("A -> H should be 10.", Long.valueOf(10), shortestPaths.get((T) "H"));
        assertEquals("A -> I should be 13.", Long.valueOf(13), shortestPaths.get((T) "I"));
        assertEquals("A -> J should be 14.", Long.valueOf(14), shortestPaths.get((T) "J"));
        assertEquals("A -> K should be 19.", Long.valueOf(19), shortestPaths.get((T) "K"));
        assertEquals("A -> L should be 12.", Long.valueOf(12), shortestPaths.get((T) "L"));
        assertEquals("A -> M should be 18.", Long.valueOf(18), shortestPaths.get((T) "M"));
        assertEquals("A -> N should be 14.", Long.valueOf(14), shortestPaths.get((T) "N"));
        assertEquals("A -> O should be 14.", Long.valueOf(14), shortestPaths.get((T) "O"));
        assertEquals("A -> P should be 14.", Long.valueOf(14), shortestPaths.get((T) "P"));
        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed time for test 5: " + (endTime - startTime) + "ms");
    }

    @Test
   // @Ignore
    /**
     * Test 6 - getShortestPaths(). Also tests time taken.
     *
     * Graph used for this test:
     *    Big ol' Graph
     */

    public void test6() {
        long startTime = System.currentTimeMillis();
        WeightedAdjacencyList<T> W = makeBigOlGraph();
        Map<T, Long> shortestPaths = W.getShortestPaths((T) "F");
        assertEquals("F -> A should be 9.", Long.valueOf(9), shortestPaths.get((T) "A"));
        assertEquals("F -> B should be 7.", Long.valueOf(7), shortestPaths.get((T) "B"));
        assertEquals("F -> C should be 7.", Long.valueOf(7), shortestPaths.get((T) "C"));
        assertEquals("F -> D should be 14.", Long.valueOf(14), shortestPaths.get((T) "D"));
        assertEquals("F -> E should be 13.", Long.valueOf(13), shortestPaths.get((T) "E"));
        assertEquals("F -> F should be 0.", Long.valueOf(0), shortestPaths.get((T) "F"));
        assertEquals("F -> G should be 11.", Long.valueOf(11), shortestPaths.get((T) "G"));
        assertEquals("F -> H should be 15.", Long.valueOf(15), shortestPaths.get((T) "H"));
        assertEquals("F -> I should be 10.", Long.valueOf(10), shortestPaths.get((T) "I"));
        assertEquals("F -> J should be 9.", Long.valueOf(9), shortestPaths.get((T) "J"));
        assertEquals("F -> K should be 14.", Long.valueOf(14), shortestPaths.get((T) "K"));
        assertEquals("F -> L should be 17.", Long.valueOf(17), shortestPaths.get((T) "L"));
        assertEquals("F -> M should be 13.", Long.valueOf(13), shortestPaths.get((T) "M"));
        assertEquals("F -> N should be 9.", Long.valueOf(9), shortestPaths.get((T) "N"));
        assertEquals("F -> O should be 19.", Long.valueOf(19), shortestPaths.get((T) "O"));
        assertEquals("F -> P should be 19.", Long.valueOf(19), shortestPaths.get((T) "P"));
        shortestPaths = W.getShortestPaths((T) "K");
        assertEquals("K -> A should be 19.", Long.valueOf(19), shortestPaths.get((T) "A"));
        assertEquals("K -> B should be 21.", Long.valueOf(21), shortestPaths.get((T) "B"));
        assertEquals("K -> C should be 21.", Long.valueOf(21), shortestPaths.get((T) "C"));
        assertEquals("K -> D should be 17.", Long.valueOf(17), shortestPaths.get((T) "D"));
        assertEquals("K -> E should be 9.", Long.valueOf(9), shortestPaths.get((T) "E"));
        assertEquals("K -> F should be 14.", Long.valueOf(14), shortestPaths.get((T) "F"));
        assertEquals("K -> G should be 25.", Long.valueOf(25), shortestPaths.get((T) "G"));
        assertEquals("K -> H should be 16.", Long.valueOf(16), shortestPaths.get((T) "H"));
        assertEquals("K -> I should be 6.", Long.valueOf(6), shortestPaths.get((T) "I"));
        assertEquals("K -> J should be 5.", Long.valueOf(5), shortestPaths.get((T) "J"));
        assertEquals("K -> K should be 0.", Long.valueOf(0), shortestPaths.get((T) "K"));
        assertEquals("K -> L should be 14.", Long.valueOf(14), shortestPaths.get((T) "L"));
        assertEquals("K -> M should be 9.", Long.valueOf(9), shortestPaths.get((T) "M"));
        assertEquals("K -> N should be 5.", Long.valueOf(5), shortestPaths.get((T) "N"));
        assertEquals("K -> O should be 12.", Long.valueOf(12), shortestPaths.get((T) "O"));
        assertEquals("K -> P should be 12.", Long.valueOf(12), shortestPaths.get((T) "P"));
        shortestPaths = W.getShortestPaths((T) "C");
        assertEquals("C -> A should be 2.", Long.valueOf(2), shortestPaths.get((T) "A"));
        assertEquals("C -> B should be 0.", Long.valueOf(0), shortestPaths.get((T) "B"));
        assertEquals("C -> C should be 0.", Long.valueOf(0), shortestPaths.get((T) "C"));
        assertEquals("C -> D should be 7.", Long.valueOf(7), shortestPaths.get((T) "D"));
        assertEquals("C -> E should be 12.", Long.valueOf(12), shortestPaths.get((T) "E"));
        assertEquals("C -> F should be 7.", Long.valueOf(7), shortestPaths.get((T) "F"));
        assertEquals("C -> G should be 6.", Long.valueOf(6), shortestPaths.get((T) "G"));
        assertEquals("C -> H should be 8.", Long.valueOf(8), shortestPaths.get((T) "H"));
        assertEquals("C -> I should be 15.", Long.valueOf(15), shortestPaths.get((T) "I"));
        assertEquals("C -> J should be 16.", Long.valueOf(16), shortestPaths.get((T) "J"));
        assertEquals("C -> K should be 21.", Long.valueOf(21), shortestPaths.get((T) "K"));
        assertEquals("C -> L should be 10.", Long.valueOf(10), shortestPaths.get((T) "L"));
        assertEquals("C -> M should be 20.", Long.valueOf(20), shortestPaths.get((T) "M"));
        assertEquals("C -> N should be 16.", Long.valueOf(16), shortestPaths.get((T) "N"));
        assertEquals("C -> O should be 12.", Long.valueOf(12), shortestPaths.get((T) "O"));
        assertEquals("C -> P should be 12.", Long.valueOf(12), shortestPaths.get((T) "P"));
        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed time for test 5: " + (endTime - startTime) + "ms");

  }
}



