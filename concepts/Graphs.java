import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

interface Graph {

    public Integer numVertices(); // i.e. |V|
    public Integer numEdges();    // i.e. |E|
    public List<Integer> neighbors(Integer v);
}

class AdjacencyMatrix implements Graph {
    // a graph class that is represented by an adjacecy matrix

    private Integer[][] edges;

    public Integer numVertices() {
        return edges.length;
    }

    public Integer numEdges() {
        Integer result = 0;
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] != 0) {
                    result++;
                }
            }
        }
        return result;
    }

    public List<Integer> neighbors(Integer v) {
        List<Integer> result = new ArrayList<Integer>(); 
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] != 0) {
                    result.add(i);
                }
            }
        }
        return result;
    }
}

class AdjacencyList implements Graph {
    // a graph class that is represented by an adjacecy list 

    private Map<Integer, ArrayList<Integer> > mp;

    public Integer numVertices() {
        return 0;
    }

    public Integer numEdges() {
        return 0;
    }

    public List<Integer> neighbors(Integer v) {
        List<Integer> result = new ArrayList<Integer>(mp.get(v)); // create a copy of the neighbor list 
        return result;
    }
}

public class Graphs {
    public static void main(String[] args) {
        System.out.println("hello"); 
    }
}
