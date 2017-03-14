package com.algo.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*************************************************************************
*
* 
*  Problem: Compute DFS on Adjacency List of a Directed Random Graph
*  Description:
*  Program to compute DFS efficiently. Your program should do the following.
*  (a) Prompt the user to input the number of nodes and the number of
*  	   edges.
*  (b) Create an adjacency list choosing the edges at random (do not
*  	   forget that it is a directed graph).
*  (c) Compute DFS (including discovery and finishing times).
*  (d) Measure and display the running time
*  
*  Visible methods:
*  main(String[] args)
*  dfs(List<ArrayList<Integer>> adjLists)
*  dfsVisit(ArrayList<Integer> temp,List<ArrayList<Integer>> adjLists)
*  finishingTime(ArrayList<Integer> temp,List<ArrayList<Integer>> adjLists)
*
********************************************************************************************************************************/

public class MainClassDFS 
{
	public static int time = 0;
	public static boolean[] visited ;
	public static Set <Integer> set = new HashSet<Integer>();
	/*************************************************************************
	 * METHOD NAME: main
	 * DESCRIPTION: main method to run the DFS code
	 * PARAMETERS:
	 * 	NAME	TYPE	 INPUT/RETURN	 	DESCRIPTION
	 *  args	String[] IN					array of String
	 *************************************************************************/
	public static void main(String[] args) 
	{
	   long startTime = 0;
	   long endTime = 0;
	   long runningTime = 0;
	   System.out.println("Enter number of vertices");
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  // fetch number of vertices, the input entered by the user
	  int v;
	  try
	  {
		v = Integer.parseInt(br.readLine());
		System.out.println("Enter number of edges");
		int numOfEdges = Integer.parseInt(br.readLine()); 
		if(v>0 && numOfEdges>= v-1)
		{
			 DirectedRandomGraph dr = new DirectedRandomGraph();
			 Map<Integer, List<Integer>> mp=  dr.generateRandomEdges(v, numOfEdges);
			 visited = new boolean[v+1];
			 //System.out.println(" map "+mp);
			 List<ArrayList<Integer>> adjLists = new ArrayList<ArrayList<Integer>>();
			 for (Entry<Integer, List<Integer>> entry : mp.entrySet())
			  {
				 List<Integer> we = entry.getValue();
			     for(int s=0;s<we.size();s++)
			     {
			    	 ArrayList<Integer> am = new ArrayList<Integer>();
					 am.add( entry.getKey());
			    	 am.add(we.get(s)); 
			    	 adjLists.add(am);
			     }
			  }
			  System.out.println(" Graph "+adjLists);
			  System.out.println(" G size "+adjLists.size());
			  startTime = System.currentTimeMillis();
			  //Traverse Graph using DFS
		      dfs(adjLists);
		      // time in milliseconds after completion of Brute Force Algorithm logic
		      endTime = System.currentTimeMillis();
		       // calculate total time taken
		      runningTime = endTime-startTime;
		      System.out.println("Running Time: "+runningTime+" ms");
		    }
		 else
		  {
			System.out.println(" Incorrect input : Enter Vertices >0 and Edges > Vertices-1");
		  }
		} 
		catch (NumberFormatException | IOException e)
		{
			System.out.println(" Exception Occurred "+e.getMessage());
		}
		//clear set
	  	set.clear();
	}
	/*************************************************************************
	 * METHOD NAME: dfs
	 * DESCRIPTION: this method iterates over Adjacency List and 
	 * 				makes a recursive call to DFS-Visit
	 * PARAMETERS:
	 * 	NAME		TYPE	 					  INPUT	 DESCRIPTION
	 *  adjLists	ArrayList<ArrayList<Integer>> IN	 adjacencyList that 
	 *  												 contains all the edges 
	 *  												 of Graph
	 *************************************************************************/
	public static void dfs(List<ArrayList<Integer>> adjLists)
	{
		
		for( int q = 0 ; q<adjLists.size();q++)
		{
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp = adjLists.get(q);
			/*call method DFS Visit to mark visited nodes and 
			compute discovery and finishing time*/
			dfsVisit(temp,adjLists);
		}
	}
	/*************************************************************************
	 * METHOD NAME: dfsVisit
	 * DESCRIPTION: this method iterates over Adjacency List and 
	 * 				computes discovery time for each node in Graph
	 * PARAMETERS:
	 * 	NAME		TYPE	 					  INPUT	 DESCRIPTION
	 *  temp		ArrayList<Integer> 			  IN	 list of one set of edges
	 *  adjLists	ArrayList<ArrayList<Integer>> IN	 adjacencyList that 
	 *  												 contains all the edges 
	 *  												 of Graph
	 *************************************************************************/
	public static void dfsVisit(ArrayList<Integer> temp,List<ArrayList<Integer>> adjLists)
	{
		for(int m = 0 ; m <temp.size();m++)
		{
			if(!visited[(int)temp.get(m)])
			{
				time = time+1;
				System.out.println("\nDiscovery Time : For node "+temp.get(m)+"  discovery time is "+time);
				//Mark the visited node as gray / true
				visited[(int)temp.get(m)] = true;
				//recursive call to dfsVisit
				dfsVisit(temp,adjLists);
			}
		}
		if(adjLists.size()>0)
		{
			//find finishing time and discovery time for adjacent edges if any
		    finishingTime(temp, adjLists);
		}	
	}
	/*************************************************************************
	 * METHOD NAME: finishingTime
	 * DESCRIPTION: this method computes the finishing time and 
	 * 				calls dfsVisit() when an adjacent node is found
	 * PARAMETERS:
	 * 	NAME		TYPE	 					  INPUT	 DESCRIPTION
	 *  temp		ArrayList<Integer> 			  IN	 list of one set of edges
	 *  adjLists	ArrayList<ArrayList<Integer>> IN	 adjacencyList that 
	 *  												 contains all the edges 
	 *  												 of Graph
	 *************************************************************************/
	public static void finishingTime(ArrayList<Integer> temp,List<ArrayList<Integer>> adjLists)
	{
		ArrayList <Integer> revL = new ArrayList <Integer>();
		revL.addAll(temp);
		Collections.reverse(revL);
		/*When last node is reached for a connected component, 
		check for adjacent nodes that are not visited and 
		call DFS again to find discovery time for each*/
		for(int x = 0 ; x<revL.size();x++)
		{
			for(int g = 0 ; g<adjLists.size();g++)
			{
				//check if adjacent node exists
				if(revL.get(x) == adjLists.get(g).get(0))
				{
					if( adjLists.get(g).size()>=1)
					{
						//check if node is already visited or not
						if(!visited[(int)adjLists.get(g).get(1)])
						{
							dfsVisit(adjLists.get(g), adjLists);
						}
					}
				}
			}
			/*	mark finishing time for nodes that are done.
			Put them in a set (in other words mark them black)*/
			if( !set.contains(revL.get(x)))
			{
				set.add(revL.get(x));
				time = time+1;
				//System.out.println("Nodes marked as Black :"+set);
				System.out.println("\nFinishing Time: For node "+revL.get(x)+" finishing time is "+time);
			}
		}
	}
	
}
