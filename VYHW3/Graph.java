public class Graph<T extends Comparable<T>> {
    private class Vertex {
        T vertexID;
        Edge edgeLink;
        Vertex nextVertex;

        public Vertex(T vertexID) {
            this.vertexID = vertexID;
            this.edgeLink = null;
            this.nextVertex = null;
        }
    }

    private class Edge {
        T vertexID;
        Edge nextEdge;

        public Edge(T vertexID) {
            this.vertexID = vertexID;
            this.nextEdge = null;
        }
    }

    private Vertex graphHead;// pribvate Vertex

    public void addVertex(T id) {
        Vertex newVertex = new Vertex(id);
        if (graphHead == null) {
            graphHead = newVertex;
        } else {
            Vertex iterator = graphHead;
            while (iterator.nextVertex != null) {
                iterator = iterator.nextVertex;
            }
            iterator.nextVertex = newVertex;
        }
    }
    public Vertex findVertex(T id) {
        Vertex iterator = graphHead;
        while (iterator != null) {
            if (iterator.vertexID.compareTo(id) == 0) {
                return iterator;
            }
            iterator = iterator.nextVertex;
        }
        return null;
    }

    public void addEdge(T startingVertexID, T endingVertexID) throws Exception {
        Vertex startVertex = findVertex(startingVertexID);
        if (startVertex == null)
            throw new Exception("Vertex bulunamadı");
        Edge newEdge = new Edge(endingVertexID);
        if (startVertex.edgeLink == null)
            startVertex.edgeLink = newEdge;
        else {
            Edge iterator = startVertex.edgeLink;
            while (iterator.nextEdge != null) {
                iterator = iterator.nextEdge;
            }
            iterator.nextEdge = newEdge;
        }
    }

    // Kenarın var olup olmadığını kontrol eden blok
    public boolean hasEdge(T startingVertexID, T endingVertexID) {
        Vertex startVertex = findVertex(startingVertexID);
        if (startVertex != null) {
            Edge iterator = startVertex.edgeLink;
            while (iterator != null) {
                if (iterator.vertexID.compareTo(endingVertexID) == 0) {
                    return true;
                }
                iterator = iterator.nextEdge;
            }
        }
        return false;
    }

    // display methodu
    public void display() {
        Vertex iterator = graphHead;
        while (iterator != null) {
            System.out.print(iterator.vertexID);
            Edge iteratorEdge = iterator.edgeLink;
            while (iteratorEdge != null) {
                System.out.print("--->" + iteratorEdge.vertexID);
                iteratorEdge = iteratorEdge.nextEdge;
            }
            iterator = iterator.nextVertex;
            System.out.println();
        }
    }
}
