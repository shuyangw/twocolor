package structures;

import java.util.ArrayList;
import java.util.List;

//Vertex class
public class Vertex {
	//Value of the current vertex
	public int root;
	//Adjacency list 
	public List<Vertex> adjVerts;
	
	//Pointer to the previous vertex in the BFS
	public Vertex parent;
	
	//String denoting the current coloring. Either RED or BLUE
	public String rb;

	
	//Constructors
	public Vertex (){
		this.root = -1;
		this.adjVerts = new ArrayList<Vertex>();
		this.setupRest();
	}
	public Vertex (int root){
		this.root = root;
		this.adjVerts = new ArrayList<Vertex>();
		this.setupRest();
	}
	
	//Sets up the rest of the fields because having lots of this.stuff is messy
	private void setupRest(){
		this.parent = null;
		this.rb = "";
	}
	
	//Returns if the current vertex has a root node or not
	public boolean isDeclared(){
		return root != -1;
	}
}
