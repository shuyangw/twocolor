import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import structures.Graph;
import structures.Vertex;

/*
 * 	Main class
 * 	Takes two command line arguments, input file and output file
 * 
 */
public class TwoColor {
	//Stores the graph
	public static Graph graph;
	
	public static void main(String[] args) throws IOException {
		//Start timer
		final long startTime = System.currentTimeMillis();
		
		String fileName = args[0];
		String outputName = args[1];
		
		//Parses the file into the graph format
		parseFile(fileName);
		//NOTE: SKIP INDEX 0 BECAUSE GRAPH VERTICES START AT 1
		
		//Initializes the breadth first search engine
		BFSEngine bfs = new BFSEngine(graph);
		bfs.BFS();

		//If there does not exist a valid coloring of the graph, this gets the violating cycle
		List<Integer> answer = bfs.getAnswer();
		
		//If graph is 2-Colorable
		if(answer == null){
			for(Vertex v: graph.vertices){
				if(v != null){
					//Prints the valid coloring
//					System.out.println(v.root + " " + v.rb);
				}
			}
			//Outputs the appropriate coloring to a file
			outputCanColor(fileName, outputName);
		}
		//If graph is not 2-Colorable
		else{
			//Prints the violating cycle
//			for(Integer x: answer){
//				System.out.println(x);
//			}
			//Outputs the cycle
			outputCannotColor(answer, fileName, outputName);
		}
		
		//Ends timer and prints it
		final long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(totalTime/1000.0 + " secs");
	}
	
	//Outputs the violating cycle if there is one
	public static void outputCannotColor(List<Integer> ans, String inFilename, String outFilename) throws IOException{
		File file = new File(outFilename);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("no");
//		bw.write("No, " + inFileName + " is not 2-Colorable. The following denotes an odd cycle");
		bw.newLine();
		for(Integer x: ans){
			bw.write(x.toString());
			bw.newLine();
		}
		bw.close();
	}
	
	//Outputs a valid coloring if there is one
	public static void outputCanColor(String inFilename, String outFilename) throws IOException{
		File file = new File(outFilename);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
//		bw.write("Yes, " + inFilename + " is 2-Colorable. The following is the appropriate coloring");
		bw.write("yes");
		bw.newLine();
		for(Vertex v: graph.vertices){
			if(v != null){
				bw.write(v.root + " " + v.rb.toLowerCase() + "\n");
			}
		}
		bw.close();
	}
	
	//Given a list of Integers denoting vertices of the graph, determines if this list represents a cycle within the graph
	public static boolean determineIfCycle(List<Integer> ans){
		for(int i = 0; i < ans.size() - 1; i++){
			//Checks if the next vertex exists in the current vertex's adjacency list
			List<Vertex> currAdjList = graph.vertices[ans.get(i)].adjVerts;
			boolean cont = false;
			for(Vertex v: currAdjList){
				if(v.root == ans.get(i + 1)){
					cont = true;
				}
			}
			if(cont){
				continue;
			}
			System.out.println(ans.get(i) + " not included in " + ans.get(i) + "'s adjList, " + "iteration: " + i);
			return false;
		}
		return true;
	}
	
	//Parses the input file into a Graph object
	public static void parseFile(String fileName) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(fileName));
		int cardV = Integer.parseInt(scan.next());
		graph = new Graph(cardV);
		while(scan.hasNext()){
			int from = Integer.parseInt(scan.next());
			int to = Integer.parseInt(scan.next());
			graph.addEdge(from, to);
		}
		scan.close();
	}
}
