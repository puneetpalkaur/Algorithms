package com.algo;
import java.lang.Math;
/*************************************************************************

*  Problem: FIND MAXIMUM SUBARRAY USING BRUTE FORCE ALGORITHM O(n^3)
*  Description: This code generates random array of integers based on the size of vector entered by the user
*  and calculates the maximum sub-array as well as running time for the algorithm.
*
*  Visible methods:
*  bruteForceAlgo(int n, int[] arrayOfInt)
*
*
*************************************************************************/

public class BruteForce 
{
	/*************************************************************************
	 * METHOD NAME: bruteForceAlgo
	 * DESCRIPTION: method to calculate the maximum sub array among array of 
	 * random integers of length n
	 * PARAMETERS:
	 * 	NAME		TYPE	INPUT/RETURN	 	DESCRIPTION
	 *  n			int		IN					size of vector entered by user
	 *  arrayOfInt	int[]	IN					array of random integers
	 *  sMax		double	RET					returns maximum subarray
	 *************************************************************************/
	public double bruteForceAlgo(int n, int[] arrayOfInt)
	{
		int numArray[] = arrayOfInt;
		//initialize sMax to minimum negative value
		double sMax = Double.NEGATIVE_INFINITY;
		for(int i=0;i<n;i++)
		{
			for(int j=i;j<n;j++)
			{
				int sum = 0;
				for(int k =i;k<=j;k++)
				{
					sum = sum + numArray[k];
				}
				//find maximum among sMax and sum using java.lang.Math
				sMax = Math.max(sMax, sum);
			}
		}
		return sMax;
	}
}