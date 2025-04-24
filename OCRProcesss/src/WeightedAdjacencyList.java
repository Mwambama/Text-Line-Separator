import java.util.*;

public class WeightedAdjacencyList<T> implements WeightedGraph<T> {
    /**
     * Adds the directed edge (u,v) to the graph. If the edge is already present, it should not be modified.
     * @param u The source vertex.
     * @param v The target vertex.
     * @param weight The weight of the edge (u,v).
     * @return True if the edge was added to the graph, false if 1) either u or v are not in the graph 2) the edge was already present.
     */
    private Map<T, Map<T, Integer>> adjList;
    private Set<T> vertices;

    public WeightedAdjacencyList(List<T> vertices) {
        this.adjList = new HashMap<>();
        this.vertices = new HashSet<>();
        if (vertices != null) {    // I am using this to debug with the graph
            for (T vertex : vertices) {
                addVertex(vertex);
            }
        }
    }
    @Override
    public boolean addEdge(T u, T v, int weight) {

        if (!vertices.contains(u) || !vertices.contains(v) || adjList.get(u).containsKey(v) || weight < 0)  {
            return false;
        }
        adjList.get(u).put(v, weight);
        return true;
   }

    /**
     * @param vertex A vertex to add to the graph.
     * @return False vertex was already in the graph, true otherwise.
     */
    @Override
    public boolean addVertex(T vertex) {

          if ( vertex == null){
                return false;
          }
        if (vertices.contains(vertex)) {
            return false;
        }
        vertices.add(vertex);
        adjList.put(vertex, new HashMap<>());
        return true;
    }

    /**
     * @return |V|
     */
    @Override
    public int getVertexCount() {

        return vertices.size();
    }

    /**
     * @param v The name of a vertex.
     * @return True if v is in the graph, false otherwise.
     */
    @Override
    public boolean hasVertex(T v) {

        return vertices.contains(v);
    }

    /**
     * @return An Iterable of V.
     */
    @Override
    public Iterable<T> getVertices() {

        return vertices;
    }

    /**
     * @return |E|
     */
    @Override
    public int getEdgeCount() {

        int count = 0;
        for (Map<T, Integer> neighbors : adjList.values()) {
            count += neighbors.size();
        }
        return count;
    }

    /**
     * @param u The source of the edge.
     * @param v The target of the edge.
     * @return True if (u,v) is in the graph, false otherwise.
     */
    @Override
    public boolean hasEdge(T u, T v) {

        return adjList.containsKey(u) && adjList.get(u).containsKey(v);
    }

    /**
     * @param u A vertex.
     * @return The neighbors of u in the weighted graph.
     */
    @Override
    public Iterable<T> getNeighbors(T u) {

        return adjList.getOrDefault(u, new HashMap<>()).keySet();
    }

    /**
     * @param u
     * @param v
     * @return
     */
    @Override
    public boolean areNeighbors(T u, T v) {

        return hasEdge(u,v);
    }

    /**
     * Uses Dijkstra's algorithm to find the (length of the) shortest path from s to all other reachable vertices in the graph.
     * If the graph contains negative edge weights, the algorithm should terminate, but the return value is undefined.
     * @param s The source vertex.
     * @return A Mapping from all reachable vertices to their distance from s. Unreachable vertices should NOT be included in the Map.
     */
    @Override
    public Map<T, Long> getShortestPaths(T s) {

        if (!vertices.contains(s)) {
            return new HashMap<>();
        }
        Map<T, Long> distances = new HashMap<>();

        // Priority queue for selecting the next closest node
        PriorityQueue<T> pq = new PriorityQueue<>(Comparator.comparingLong(distances::get));
        // Distance to source is 0
        distances.put(s, 0L);
        pq.offer(s);

        while (!pq.isEmpty()) {
            T current = pq.poll();

            // Explore all neighbors of the current node
            for (T neighbor: getNeighbors(current)) {

                int weight = adjList.get(current).get(neighbor);

                // If a negative weight is found, return an empty map
                if (weight < 0) {
                    return new HashMap<>();
                }

                // Calculate new possible distance to neighbor

                long newDist = distances.get(current) + weight;

                // If this path is shorter, update distance and add to queue
                if (!distances.containsKey(neighbor) || newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    pq.offer(neighbor);
                }
            }
        }
        return distances;
    }

}
