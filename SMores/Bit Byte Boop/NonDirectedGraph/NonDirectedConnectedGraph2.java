import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dgb
 * @date 11/27/2018
 * This has a breadth-first traversal.
 */
public class NonDirectedConnectedGraph2<T> {
    private ArrayList<T> vertices;
    private ArrayList<ArrayList<T>> adjacencyLists;
    
    public NonDirectedConnectedGraph2() {
        vertices = new ArrayList<>();
        adjacencyLists = new ArrayList<>();
    }
    
    public void addVertex(T vertex) {
        if(vertex == null) return;
        if(vertices.contains(vertex)) return;
        vertices.add(vertex);
        adjacencyLists.add(new ArrayList<>());
    }
    
    public void addEdge(T vertex, T adjacent) {
        if(vertex == null || adjacent == null) return;
        if(vertex.equals(adjacent)) return;
        if(!vertices.contains(vertex)) return;
        if(!vertices.contains(adjacent)) return;
        int posOfVertex = getVertexLocation(vertex);
        adjacencyLists.get(posOfVertex).add(adjacent);
    }
    
    public int getVertexLocation(T vertex) {
        if(vertex == null) return -1;
        return vertices.indexOf(vertex);
    }
    
    public T getVertex(int pos) {
        if(pos < 0 || pos >= vertices.size()) return null;
        return vertices.get(pos);
    }
    
    
    public ArrayList<T> getVertexList() {
        return vertices;
    }
    
    public ArrayList<T> getVertexAdjacencyList(T vertex) {
        if(vertex == null) return null;
        if(!vertices.contains(vertex)) return null;
        int posOfVertex = getVertexLocation(vertex);
        return adjacencyLists.get(posOfVertex);
    }
    
    public ArrayList<ArrayList<T>> breadthFirst(T start) {
        System.out.println("Breadth-first Search");
        if(start == null) return null;
        ArrayList<T> queue = new ArrayList<>();
        ArrayList<T> visitedList = new ArrayList<>();
        ArrayList<ArrayList<T>> result = new ArrayList<>();
        queue.add(start);
        visitedList.add(start);
        while(!queue.isEmpty()) {
            T current = queue.remove(0);
            ArrayList<T> adjList = getVertexAdjacencyList(current);
            for(int i = 0; i < adjList.size(); i++) {
                T vertex = adjList.get(i);
                if(!visitedList.contains(vertex)) {
                    ArrayList<T> edge = new ArrayList<T>() {{
                        add(current);
                        add(vertex);
                    }};
                    result.add(edge);
                    queue.add(vertex);
                    visitedList.add(vertex);
                }
            }
        }
        return result;
    }
    
    public ArrayList<ArrayList<T>> depthFirst(T start) {
        System.out.println("Depth-first Search");
        if(start == null) return null;
        ArrayList<T> visitedList = new ArrayList<>();
        visitedList.add(start);
        return depthFirst(start, visitedList);
    }
    
    public ArrayList<ArrayList<T>> depthFirst(T start, ArrayList<T> visitedList) {
        ArrayList<T> adjList = getVertexAdjacencyList(start);
        ArrayList<ArrayList<T>> result = new ArrayList<>();
        if(adjList.isEmpty()) return null;
        for(T vertex : adjList) {
            if(!visitedList.contains(vertex)) {
                ArrayList<T> edge = new ArrayList<T>() {{
                    add(start);
                    add(vertex);
                }};
                result.add(edge);
                visitedList.add(vertex);
                result.addAll(depthFirst(vertex, visitedList));
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        NonDirectedConnectedGraph2<String> graph = new NonDirectedConnectedGraph2<>();
        
        String input = "NonDirectedGraph/graphInput.txt";
        File file = new File(input);
        List<String> input_raw = new ArrayList<>();
        Path path = Paths.get(file.getAbsolutePath());
        try {
            Files.lines(path).forEachOrdered(input_raw::add);
        } catch (IOException FourOhFour) {
            FourOhFour.printStackTrace();
        }
        
        //Add all of the vertices
        String vertexes = input_raw.get(0);
        String[] vertices = vertexes.split(" ");
        for(String vertex : vertices) {
            graph.addVertex(vertex);
        }
        
        //Add all of the edges
        for(int i = 1; i < input_raw.size(); i++) {
            String[] edges = input_raw.get(i).split(" ");
            for(int j = 1; j < edges.length; j++) {
                graph.addEdge(edges[0], edges[j]);
            }
        }
        
        dump(graph);
        
        ArrayList<ArrayList<String>> breadthFirst = graph.breadthFirst("A");
        for(ArrayList<String> edge : breadthFirst) {
            System.out.println(edge.get(0) + "->" + edge.get(1));
        }
        System.out.println("*************");
        ArrayList<ArrayList<String>> depthFirst = graph.depthFirst("D");
        for(ArrayList<String> edge : depthFirst) {
            System.out.println(edge.get(0) + "->" + edge.get(1));
        }
    }//end main
    
    public static void dump(NonDirectedConnectedGraph2<String> graph) {
        ArrayList<String> vertices = graph.getVertexList();
        
        for(String vertex : vertices) {
            System.out.print(vertex + " is connected to ");
            ArrayList<String> adjacencyList
                    = graph.getVertexAdjacencyList(vertex);
            System.out.print("[");
            for(int i = 0; i < adjacencyList.size(); i++) {
                System.out.print(adjacencyList.get(i));
                if(i < adjacencyList.size() - 1)
                    System.out.print(", ");
            }
            System.out.println("]");
        }
    }
    
}//end class
