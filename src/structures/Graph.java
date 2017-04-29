package structures;

//Graph class
public class Graph {
	//Cardinality of the vertices: |V|
	public int cardV;
	
	//Adjacency list representation of the entire graph as an array of Vertices
	public Vertex[] vertices;
	
	public Graph(int size){
		this.cardV = 0;
		
		//Initialized at size size + 1 because the input file uses 1-indexing
		this.vertices = new Vertex[size + 1];
	}
	
	//Adds an edge to the graph in the form (from, to)
	public void addEdge(int from, int to){
		//If edge points to itself, does nothing
		if(from == to){
			return;
		}
		
		//If the from vertex is null, create a new vertex there
		if(vertices[from] == null){
			vertices[from] = new Vertex(from);
		}
		
		//If the to vertex is null, create a new vertex there
		if(vertices[to] == null){
			vertices[to] = new Vertex(to);
		}
		
		//Adds nodes to the new vertex's adjacency list and adds it on the other end as the input graph is undirected
		vertices[from].adjVerts.add(vertices[to]);
		vertices[to].adjVerts.add(vertices[from]);
	}
	
	//Prints the entire graph
	public void printAll(){
		for(int i = 0; i < vertices.length; i++){
			if(vertices[i] != null){
				System.out.print("Head: " + vertices[i].root + ", ");
				for(Vertex v: vertices[i].adjVerts){
					System.out.print(v.root + " ");
				}
				System.out.println();
			}
		}
 	}
}
