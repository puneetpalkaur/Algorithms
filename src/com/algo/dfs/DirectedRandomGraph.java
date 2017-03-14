package com.algo.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*************************************************************************
*

*
*  Problem: Program to Generate Directed Random Graph with number of nodes and edges entered by user
*  Description: Generate Directed Random Graph with number of nodes and edges entered by user
*
*
*  Visible methods:
*  generateRandomEdges(int v,int edges) 
*  addEdges (int edgeOne, int edgeTwo, Map  <Integer,List<Integer>> map,int v)
*  generateRandomNumbers(int n)
*
*   Remarks
*   -------
*   Code to generate directed random graph
********************************************************************************************************************************/

public class DirectedRandomGraph 
{
	public static Set<Integer> trackingSet = new HashSet<Integer>();
	public static final String FALSE = "False";
	public static final String TRUE = "True";
	
	/*************************************************************************
	 * METHOD NAME: generateRandomEdges
	 * DESCRIPTION: generate random edges to be added to a graph
	 * PARAMETERS:
	 * 	NAME	TYPE	 INPUT/RETURN	 	DESCRIPTION
	 *  none
	 * @return 
	 *************************************************************************/
	public Map<Integer, List<Integer>> generateRandomEdges(int v,int edges) 
	{
		// ask user to enter size of vector
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//declaring and initializing variables 
		int edgeOne ;
		int edgeTwo ;
		//Graph Edges are tracked using Hash Map
		Map <Integer,List<Integer>> mp = new HashMap<Integer,List<Integer>>();
		
		try 
		{
					//for first run
					List <Integer> initialRun = new ArrayList<Integer>();
					// run the for loop, for number of edges entered by user
					for(int n = 0 ; n <edges; n ++)
					{
						//count total random edges generated
						
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
								boolean checkIfEdgeAdded = addEdges(edgeOne, edgeTwo, mp,v);
								if(!checkIfEdgeAdded)
								{
									//graph need more edges, generate numbers again
									if(n>1)
									{
										n=n-1;	
									}
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
					
					/*Uncomment below to print edges in the format 
					Example: 2=[3,4] -->>  [2,3],[2,4] gives two edges , between 2 and 3; 2 and 4*/
					/*System.out.println("Graph [n1=n2,n3] implies n1-->n2 and n1-->n3 ");
					System.out.println(mp);*/
				
		} 
		catch (NumberFormatException ex) 
		{
			System.out.println(" Number Format Exception Occurred: "+ex.getMessage());
		}
		catch (Exception exp) 
		{
			System.out.println(" Exception Occurred: "+exp.getMessage());
		}
		return mp;
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
	public static boolean addEdges (int edgeOne, int edgeTwo, Map  <Integer,List<Integer>> map,int v)
	{
		List<Integer> temp = new ArrayList<Integer>();
		boolean success = false;
	    //check if map already contains key
		  if(map.containsKey(edgeOne))
			{
				temp = map.get(edgeOne);
				// if map already contains key, then check if it has same value
				if(!temp.contains(edgeTwo))
				{
					temp.add(edgeTwo);
					map.put(edgeOne, temp);
					success = true;
				}
			}
			else
			{
			    temp.add(edgeTwo);
				map.put(edgeOne, temp);
				success = true;
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
}
