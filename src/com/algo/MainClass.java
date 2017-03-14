package com.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/*************************************************************************
*
*  Problem: FIND MAXIMUM SUBARRAY USING BRUTE FORCE ALGORITHM,BRUTE FORCE IMPROVED, DIVIDE AND CONQUER ALGORITHM
*  Description: This code generates random array of integers based on the size of vector entered by the user
*  and calculates the maximum sub-array as well as running time for the algorithm.
*
*  Input: Please refer below table
*  Output: Please refer below table
*  |	Table1			  |n = 10^3 |n = 10^4  |n = 11111  |n = 15555  |n = 20000   |
*  |______________________|_________|__________|___________|___________|____________|
*  |Brute Force Algorithm |  218ms  | 167691ms | 195379ms  | 541099ms  | 1149775ms  |
*  |Brute Force Improved  |    7ms	|	  72ms |	 88ms  |	184ms  |	 283ms	|
*  |Divide and Conquer    |    0ms	|	   4ms |	  5ms  |	  6ms  |	   7ms  |
*  |________________________________________________________________________________|
*
*  |	Table2  		  |n = 10^3 |n = 10^4  |n = 10^5   |n = 10^6 |n = 1111111 |
*  |______________________|_________|__________|___________|_________|____________|
*  |Brute Force Improved  |    7ms	|	  72ms |	6738ms | 682706ms|884658ms    |
*  |Divide and Conquer    |    0ms	|	   4ms |	  15ms |	 95ms|   129ms    |
*  |______________________________________________________________________________|
*  
*  Visible data fields:
*  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
*
*  Visible methods:
*  main(String[] args)
*  generateRandomNumbers(int n)
*
*   Remarks
*   -------
*	1)Running time for all three algorithms differs completely.
*	2)Algorithm 1 - Brute Force Algorithm running time increases sharply, 
*	  as input size grows,as compared to other two algorithms.
* 	  For an input size increase from 10000 to 11111 (1.1 times), the running time increase by n^3 (i.e 1.3 times), 
* 	  that is 167691 * 1.3 = 217998 ~ 19537 (from above table1)
* 	  for input size increase from 11111 to 15555 (1.4 times), running time increase by n^3 (i.e 2.7 times)
* 	  that is 195379 * 2.7 = 527523 ~ 541099 (from above table1).
* 	  Therefore, the Brute Force Algorithm complies with the theoretical running time O(n^3).
* 
*   3)Algorithm 2 - Brute Force Improved Algorithm is much faster than Brute Force Algorithm
*     and running time grows gradually as the input size grows.
* 	  For an input size increase from 10000 to 11111 (1.1 times), the running time increase by n^2 (i.e 1.2 times), 
* 	  that is 72 * 1.2 = 86.4 ~ 88  (from above table1)
* 	  for input size increase from 11111 to 15555 (1.4 times), running time increase by n^2 (i.e 1.96 times)
* 	  that is 88 * 1.96  = 172.48 ~  184 (from above table1).  
* 	  for input size increase from 15555 to 20000 (1.28 times), running time increase by n^2 (i.e 1.6 times)
* 	  that is 184 * 1.6  = 294 ~  283 (from above table1).  
* 	  Hence, the running time is increasing quadratically with the increase in input size and
*     complies with theoretical running time O(n^2)
*     
*   4)Algorithm 3 - Divide and Conquer Algorithm is the fastest among three and running time
*     grows very slow as the input size increase.This algorithm seems  better
*     approach to find maximum subarray, as it can complete 10^7 input size in just 2449ms,
*     which is quite faster than the remaining two algorithms.
*     From table 1 and table 2, the values for this algorithm are increasing in a linear manner [4,5,6,7]for small input size
*     from 10^3 to 2*10^4. But as the n size grows big like 10^5, 10^6, the output is not linear anymore but it is not quadratic 
*     as 200000 to 1000000 by 5 times but the output does not grow 25 times ( i.e 7*25 = 175, but actual value is 15 instead of 175 from table2),
*     hence it is increasing by O(nlogn)
*   
*
*************************************************************************/
public class MainClass
{
	/*************************************************************************
	 * METHOD NAME: main
	 * DESCRIPTION: main method to run the code
	 * PARAMETERS:
	 * 	NAME	TYPE	 INPUT/RETURN	 	DESCRIPTION
	 *  args	String[] IN					array of String
	 *************************************************************************/
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
			// set maximum and minimum limit for integer generation, can be set to any value
			int minimum = -20;
			int maximum = 20;
			//maximum subarray
			double maximumSum=0;
			int low = 0;
			int high = n-1;
			// initialize length of array of integers to n
		 	int A [] = new int[n];
		 	// get instance of Random() to generate random numbers between -20 and 20
		 	Random rand = new Random();
	        for(int k =0;k<n;k++)
	        {
	        	int randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
	     		A [k]= randomNum;
	     	}
	        // instantiate classes to call methods for Brute Force Algo, Brute Force Improved Algo , Divide and Conquer Algo
	        BruteForce bruteForce = new BruteForce();
	        BruteForceImproved bruteForceImprove = new BruteForceImproved();
	        DivideAndConquer dividenConquer = new DivideAndConquer();
	        // time in milliseconds before calling Brute Force Algorithm logic
	        startTime = System.currentTimeMillis();
	        /*________________________________________
	        
	         	**** call BRUTE FORCE ALGORITHM ****
	          ________________________________________
	        */
	        maximumSum = bruteForce.bruteForceAlgo(n, A);
	        /*
	         * Uncomment below print statement to print maximum sub array
	         */
	        //System.out.println("Maximum Subarray: "+maximumSum);
	        // time in milliseconds after completion of Brute Force Algorithm logic
	        endTime = System.currentTimeMillis();
	        // calculate total time taken
	        timeTaken = endTime-startTime;
	        System.out.println("Total time - Brute Force: "+timeTaken+"ms");
	        // time in milliseconds before calling Improved Brute Force Algorithm logic
	        startTime = System.currentTimeMillis();
	        /*_____________________________________________
	        
              **** call BRUTE FORCE IMPROVED ALGORITHM ****
              _____________________________________________*/
            maximumSum = bruteForceImprove.bruteForceImprovedAlgo(n, A);
	        /*
	         * Uncomment below print statement to print maximum sub array
	         */
	        // System.out.println("Maximum Subarray: "+maximumSum);
	        // time in milliseconds after completion of Improved Brute Force Algorithm logic
	        endTime = System.currentTimeMillis();
	        // calculate total time taken
	        timeTaken = endTime-startTime;
	        System.out.println("Total time - Brute Force Improved "+timeTaken+"ms");
	        // time in milliseconds before calling Divide and Conquer Algorithm logic
	        startTime = System.currentTimeMillis();
	        /*_____________________________________________
	        
             **** call DIVIDE AND CONQUER ALGORITHM ****
             _____________________________________________*/
            maximumSum = dividenConquer.maxSubarray( A,  low,  high);
	        /*
	         * Uncomment below print statement to print maximum sub array
	         */
	        //System.out.println("Maximum Subarray: "+maximumSum);
	        // time in milliseconds after completion of Divide and Conquer Algorithm  logic
	        endTime = System.currentTimeMillis();
	        // calculate total time taken
	        timeTaken = endTime-startTime;
	        System.out.println("Total time - Divide and Conquer "+timeTaken+"ms");
	}
}
