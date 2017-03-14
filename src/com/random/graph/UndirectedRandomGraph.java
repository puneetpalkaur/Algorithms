package com.random.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*************************************************************************
*  Problem: Program to estimate f(n) for a given value of n. Track the connected
*   		components of the graph as you add edges until the graph becomes connected.
*   		You should try n = 100, 200, 300, 400, 500, 600, 700, 800, 900, and 1000
*  Description: Let f(n) be the expected number of random edges that must be added
*  				before an empty undirected graph with n vertices becomes connected.
*  				(A graph is connected if there is a path of one or more edges connecting
*  				each pair of nodes.) That is, suppose that we start with a graph on
*  				n vertices with zero edges and then repeatedly add an edge, chosen
*  				uniformly at random from all edges not currently in the graph, until
*  				the graph becomes connected. 
*
*
*  Visible methods:
*  main(String[] args)
*  addEdges (int edgeOne, int edgeTwo, Map  <Integer,List<Integer>> map,int v)
*  generateRandomNumbers(int n)
*  checkIfGraphIsConnected(int edgeOne,int edgeTwo, int v)
*
*   Remarks
*   -------

*  f(n) that I suggest for this program is O (n log n)
*  Because with the increase in number of vertices the random edges getting generated are increasing
*  but not in a constant manner, so it is not linear.
*  When table output is plotted on graph, it gives graph of nlogn 
********************************************************************************************************************************/

public class UndirectedRandomGraph 
{
	public static Set<Integer> trackingSet = new HashSet<Integer>();
	public static final String FALSE = "False";
	public static final String TRUE = "True";
	public static final String COMPLETE = "Complete";
	public static final String SKIP = "Skip";
	public static int count = 0;
	
	/*************************************************************************
	 * METHOD NAME: main
	 * DESCRIPTION: main method to run the code
	 * PARAMETERS:
	 * 	NAME	TYPE	 INPUT/RETURN	 	DESCRIPTION
	 *  args	String[] IN					array of String
	 *************************************************************************/
	public static void main(String[] args) 
	{
		generateRandomEdges();
	}
	/*************************************************************************
	 * METHOD NAME: generateRandomEdges
	 * DESCRIPTION: generate random edges to be added to a graph
	 * PARAMETERS:
	 * 	NAME	TYPE	 INPUT/RETURN	 	DESCRIPTION
	 *  none
	 *************************************************************************/
	public static void generateRandomEdges() 
	{
		// ask user to enter size of vector
		System.out.println("Enter number of vertices");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int v ;
		//declaring and initializing variables 
		int edgeOne ;
		int edgeTwo ;
		//Graph Edges are tracked using Hash Map
		Map <Integer,List<Integer>> mp = new HashMap<Integer,List<Integer>>();
		
		try 
		{
			// fetch number of vertices, the input entered by the user
			v = Integer.parseInt(br.readLine());
			// call method to generate n random numbers
			if(v>0)
			{
					String checkIfEdgeAdded = "";
					//for first run
					List <Integer> initialRun = new ArrayList<Integer>();
					// minimum number of edges required for a graph with v vertices to get connected
					int edges = v-1;
					// run the for loop, AT LEAST as a number, that equals minimum number of edges
					for(int n = 0 ; n <=edges; n ++)
					{
						//count total random edges generated
						count = count+1;
						//generate edge at random
						edgeOne = generateRandomNumbers(v);
						edgeTwo = generateRandomNumbers(v);
						//to avoid cycle, make sure edge one and edge two represent two different nodes
						if(edgeOne !=edgeTwo)
						{
							
							//for the first run edge map will be blank so add edges without any check
							if(n ==0)
							{
								initialRun.add(edgeTwo);
								mp.put(edgeOne, initialRun);
								List<Integer> myL = new ArrayList<Integer>();
								myL.add(edgeOne);
								myL.add(edgeTwo);
								//add same data to a Set to keep a check on edges
								trackingSet.add(edgeOne);
								trackingSet.add(edgeTwo);
							}
							else
							{
								/*call addEdges method to check if edge already connected
								  if not then add edges to graph*/
								checkIfEdgeAdded = addEdges(edgeOne, edgeTwo, mp,v);
								if(checkIfEdgeAdded.equals(FALSE))
								{
									//graph need more edges, generate numbers again
									if(n>1)
									{
										n=n-1;	
									}
								}
								//Graph is complete, exit!
								else if(checkIfEdgeAdded.equals(COMPLETE))
								{
									break;
								}
							}
						}
						else
						{
							//generate numbers again, if both numbers are equal
							if(n>0)
							{
								n=n-1;	
							}
						}
					}
					System.out.println("Count "+count);
					/*Uncomment below to print edges in the format 
					Example: 2=[3,4] -->>  [2,3],[2,4] gives two edges , between 2 and 3; 2 and 4*/
					System.out.println("Graph [n1=n2,n3] implies n1-->n2 and n1-->n3 ");
					System.out.println(mp);
				}
		} 
		catch (NumberFormatException ex) 
		{
			System.out.println(" Number Format Exception Occurred: "+ex.getMessage());
		}
		catch (Exception exp) 
		{
			System.out.println(" Exception Occurred: "+exp.getMessage());
		}
	}
	/*************************************************************************
	 * METHOD NAME: addEdges
	 * DESCRIPTION: method to add Edges between Graph nodes
	 * PARAMETERS: 
	 * 	NAME						TYPE	 INPUT/RETURN	 	DESCRIPTION
	 *  edgeOne						int		 IN					starting node to add edge (node1)
	 *  edgeTwo						int 	 IN					ending node to add edge (node2)
	 *  Map<Integer, List<Integer>> Map		 IN					contains connected edges of graph
	 *  v							int		 IN					number of vertices
	 *************************************************************************/
	public static String addEdges (int edgeOne, int edgeTwo, Map  <Integer,List<Integer>> map,int v)
	{
		String tempString = "";
		List<Integer> temp = new ArrayList<Integer>();
		String success = FALSE;
		if(map.size()>0)
		{
			tempString = checkIfGraphIsConnected(edgeOne, edgeTwo,v);
			if(tempString.equals(COMPLETE))
			{
				success = tempString;
				return success;
			}
		}
	    if(!tempString.equals(SKIP))
	    {
		  //check if map already contains key
		  if(map.containsKey(edgeOne))
			{
				temp = map.get(edgeOne);
				// if map already contains key, then check if it has same value
				if(!temp.contains(edgeTwo))
				{
					temp.add(edgeTwo);
					map.put(edgeOne, temp);
					success = TRUE;
				}
			}
			else
			{
			    temp.add(edgeTwo);
				map.put(edgeOne, temp);
				success = TRUE;
			}
		 }
		 return success;
	 }
	/*************************************************************************
	 * METHOD NAME: generateRandomNumbers
	 * DESCRIPTION: method to generate random integers using java.util.Random
	 * PARAMETERS:
	 * 	NAME	TYPE	INPUT/RETURN	 	DESCRIPTION
	 *  n		int		IN					size of vector entered by user
	 *************************************************************************/
	public static int generateRandomNumbers(int n)
	{
			// set maximum and minimum limit for integer generation, can be set to any value
			int minimum = 1;
			int maximum = n;
		 	// get instance of Random() to generate random numbers between -20 and 20
		 	Random rand = new Random();
	        int randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
	     	return randomNum;
	}
	/****************************************************************************
	 * METHOD NAME: checkIfGraphIsConnected
	 * DESCRIPTION: method to check if graph is connected, after adding each edge,
	 * 				if yes, then exit, no more edges needs to be added
	 * PARAMETERS:
	 * 	NAME	TYPE	INPUT/RETURN	DESCRIPTION
	 *  v		int		IN				number of vertices of graph entered by user
	 *  edgeOne int		IN				first node to start edge
	 *  edgeTwo int		IN				second node to connect to first node,
	 *  								to form an edge
	 ******************************************************************************/
	public static String checkIfGraphIsConnected(int edgeOne,int edgeTwo, int v)
	{
		String isGraphConnected = "";
		List<Integer> tempList = new ArrayList<Integer>();
		tempList.add(edgeOne);
		tempList.add(edgeTwo);
		//trackingSet tracks if all the nodes in map(Graph) are connected
		//if edges are already there, skip adding to Graph
		if(trackingSet.containsAll(tempList))
		{
			isGraphConnected = SKIP;
			//check if n vertices are connected
			for(int k = 1 ;k<=v;k++)
			{
				if(!trackingSet.contains(k))
				{
					isGraphConnected = SKIP;
					return isGraphConnected;
				}
			}
			isGraphConnected = COMPLETE;
			return isGraphConnected;
		}
		//check if there is one common node existing in graph
		else if (trackingSet.removeAll(tempList))
		{
			trackingSet.addAll(tempList);
		}
		else
		{
			isGraphConnected = SKIP;
			return isGraphConnected;
		}
		return isGraphConnected;
	}
}
