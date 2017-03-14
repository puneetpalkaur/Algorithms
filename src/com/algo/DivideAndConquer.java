package com.algo;

/*************************************************************************
*
*  Problem: FIND MAXIMUM SUBARRAY USING DIVIDE AND CONQUER ALGORITHM O(n logn)
*  Description: This code generates random array of integers based on the size of vector entered by the user
*  and calculates the maximum sub array as well as running time for the algorithm.
*
*
*  Visible methods:
*  maxSubarray(int arrayOfInt[], int low, int high)
*  maxCrossingSubArray(int A[],int low, int mid, int high)
*
*
*
*************************************************************************/
public class DivideAndConquer 
{
	/*************************************************************************
	 * METHOD NAME: maxSubarray
	 * DESCRIPTION: method to calculate the maximum sub array among array of 
	 * random integers of length n,using Divide and Conquer
	 * PARAMETERS:
	 * 	NAME		TYPE	INPUT/RETURN	 	DESCRIPTION
	 *  arrayOfInt	int[]	IN					array of random integers
	 *  low			int		IN					lowest array index
	 *  high		int		IN					highest array index
	 *  Math.max()	double	RET					returns maximum subarray
	 *************************************************************************/
	public double maxSubarray(int arrayOfInt[], int low, int high)
	{
		if(high==low)
		{
			return arrayOfInt[low];
		}
		else
		{
			int mid = (low+high)/2;
			//recursive call to maxSubarray
			double leftSum = maxSubarray(arrayOfInt, low, mid);
			double rightSum = maxSubarray(arrayOfInt,mid+1,high);
			//call maxCrossingSubArray to find max from middle, where left and right arrays cross
			double crossSum = maxCrossingSubArray(arrayOfInt,low, mid, high);
			return Math.max(Math.max(leftSum,crossSum),rightSum);
		}
	}
	/*************************************************************************
	 * METHOD NAME: maxCrossingSubArray
	 * DESCRIPTION: method to calculate the maximum subarray from middle, where left and right arrays cross
	 * PARAMETERS:
	 * 	NAME		TYPE	INPUT/RETURN	 	DESCRIPTION
	 *  A			int[]	IN					array of random integers
	 *  low			int		IN					lowest array index
	 *  mid			int		IN					middle index of array
	 *  high		int		IN					highest array index
	 *  Math.max()	double	RET					returns maximum subarray
	 *************************************************************************/
	public double maxCrossingSubArray(int A[],int low, int mid, int high)
	{
		double leftSum = Double.NEGATIVE_INFINITY;
		double sum = 0;
		for(int i = mid;i>=low;i--)
		{
			sum = sum + A[i];
			if(sum>leftSum)
			{
				leftSum = sum ;
			}
		}
		double rightSum = Double.NEGATIVE_INFINITY;
		sum =0;
		for(int j = mid+1;j<=high;j++)
		{
			sum = sum +A[j];
			if(sum>rightSum)
			{
				rightSum = sum;
			}
		}
		return leftSum+rightSum;
	}
}





