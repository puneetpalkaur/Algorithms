package com.algo.jkadane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/*************************************************************************

*  References: https://en.wikipedia.org/wiki/Maximum_subarray_problem   TOPIC : Kadan's Algorithm
*
*  Problem: FIND MAXIMUM SUBARRAY USING KADANE'S DYNAMIC PROGRAMMING ALGORITHM
*  Description: This code generates random array of integers based on the size of vector entered by the user
*  and calculates the maximum sub-array as well as running time for the algorithm.
*
*  Visible methods:
*  main(String[] args)
*  generateRandomNumbers(int n)
*  jKadaneAlgo(int A[], int n)
*
*   Remarks
*   -------
*	1)Kadane's algorithm has a linear running time and executes faster than 
*	Divide and Conquer Algorithm
*	2)Running time grows real slow with respect to the input size
*   
*
*************************************************************************/
public class JKadaneAlgo 
{

	public static void main(String[] args) 
	{
				// ask user to enter size of vector
				System.out.println("Enter size of vector");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				int n;
				try 
				{
					// fetch size of vector, the input entered by the user
					n = Integer.parseInt(br.readLine());
					// call method to generate n random numbers
					if(n>0)
					{
						generateRandomNumbers(n);
					}
				} 
				catch (NumberFormatException ex) 
				{
					System.out.println(" Number Format Exception Occurred: "+ex.getMessage());
				}
				catch (IOException e) 
				{
					System.out.println("Input Output Exception: "+e.getMessage());
				}
				catch (Exception exp) 
				{
					System.out.println(" Exception Occurred: "+exp.getMessage());
				}

	}
	/*************************************************************************
	 * METHOD NAME: generateRandomNumbers
	 * DESCRIPTION: method to generate random integers using java.util.Random
	 * PARAMETERS:
	 * 	NAME	TYPE	INPUT/RETURN	 	DESCRIPTION
	 *  n		int		IN					size of vector entered by user
	 *************************************************************************/
	public static void generateRandomNumbers(int n)
	{
			long startTime = 0;
			long endTime = 0;
			long timeTaken = 0;
			int minimum = -20;
			int maximum = 20;
			//maximum subarray
			int maximumSum=0;
			// initialize length of array of integers to n
		 	int A [] = new int[n];
		 	// get instance of Random() to generate random numbers between -20 and 20
		 	Random rand = new Random();
	        for(int k =0;k<n;k++)
	        {
	        	int randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
	     		A [k]= randomNum;
	     	}
	         startTime = System.currentTimeMillis();
	         maximumSum = jKadaneAlgo(A,n);
	        // System.out.println("Maximum Subarray: "+maximumSum);
	        // time in milliseconds after completion of J Kadane Algorithm logic
	        endTime = System.currentTimeMillis();
	        // calculate total time taken
	        timeTaken = endTime-startTime;
	        System.out.println("Total time - J Kadane dynamic programming algo: "+timeTaken+"ms");
	        // time in milliseconds before calling J Kadane Algorithm logic
	        startTime = System.currentTimeMillis();
	       
	}
	/*************************************************************************
	 * METHOD NAME: jKadaneAlgo			
	 * DESCRIPTION: method to calculate the maximum subarray using J Kanade dynamic programming algo
	 * PARAMETERS:
	 * 	NAME		TYPE	INPUT/RETURN	 	DESCRIPTION
	 *  A			int[]	IN					array of random integers
	 *  n			int		IN					length of array
	 *  maxSubarray double	RET					maximum sub array
	 *************************************************************************/
	public static int jKadaneAlgo(int A[], int n)
	{
		 // initialize variables as first value in array
		 int currentMax=A[0]; 
		 int maxSubarray=A[0]; 
		 for(int i=1; i<n; i++) 
		 { 
			 //compare the first element of array with the sum of first element of array and the iterating value of array
			 currentMax=Math.max(A[i],(currentMax+A[i])); 
			 //keep updating the maxSubarray with highest value by comparing currentMax and maxSubArray
			 maxSubarray=Math.max(maxSubarray,currentMax); 
		 }
		 return maxSubarray;
	}
}
