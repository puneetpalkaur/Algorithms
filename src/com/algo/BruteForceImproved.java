package com.algo;

/*************************************************************************
*
*  Problem: FIND MAXIMUM SUBARRAY USING BRUTE FORCE IMPROVED ALGORITHM O(n^2)
*  Description: This code generates random array of integers based on the size of vector entered by the user
*  and calculates the maximum sub array as well as running time for the algorithm.
*
*
*  Visible methods:
*  bruteForceImprovedAlgo(int n, int[] arrayOfInt)
*
*
*
*************************************************************************/
public class BruteForceImproved 
{
	/*************************************************************************
	 * METHOD NAME: bruteForceImprovedAlgo
	 * DESCRIPTION: method to calculate the maximum sub array among array of 
	 * random integers of length n,using improved Brute Force Algorithm 
	 * PARAMETERS:
	 * 	NAME		TYPE	INPUT/RETURN	 	DESCRIPTION
	 *  n			int		IN					size of vector entered by user
	 *  arrayOfInt	int[]	IN					array of random integers
	 *  sMax		double	RET					returns maximum subarray
	 *************************************************************************/
	public double bruteForceImprovedAlgo(int n, int[]arrayOfInt)
	{
		//initialize sMax to minimum negative value
		double sMax = Double.NEGATIVE_INFINITY;
		for(int i =0;i<n;i++)
		{
			int sum = 0;
			for(int j=i;j<n;j++)
			{
				sum = sum + arrayOfInt[j];
				sMax = Math.max(sMax, sum);
			}
		}
		return sMax;
	}

}
