package com.lib.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

/*************************************************************************
*
*  Problem: Program to evaluate experimentally Library Sort. 
*  			Try n = 100, 200, 300, 400, 500, 600, 700, 800, 900,and 1000, 
*  			producing the numbers to sort at random.
*  Description: Library Sort is an Insertion Sort algorithm that runs in O(n log n)
*  				steps with high probability , which is optimal for comparison-based 
*  				sorting. This optimal running time is achieved at the expense of 
*               using some extra space epsilon.

*  Visible methods:
*  main(String[] args)
*  runLibrarySort(int n)
*  modifiedInsertionSort(Integer array[], int n)
*  binarySearch(Integer array[], int element, int low, int high)
*  generateRandomNumbers(int n)
*
*   Remarks
*   -------
*   Function that I suggest, based on average running time is O(n log n).
*   Looking at the table data , it can be easily concluded that with constant rise in input size,
*   the running time does not increase in O(n) order. 
*   For input size increase from 100 to 1000, adding up by 100 constantly i.e 100,200,300 etc.
*   Output for each n:
*   0 to 1, increase by  1; 
*   1 to 2, increase by  1; 
*   2 to 4 increase by   2; 
*   4 to 6 increase by   2;
*   6 to 9 increase by   3;
*   9 to 10 increase by  1;
*   13 to 16 increase by 3;
*   16 to 21 increase by 5;
*   21 to 25 increase by 4;
*   The differences between output are not equal.
*   when above data is plotted on a graph it gives n log n graph, instead of a straight line for n or graph for n^2
*   Thus the code runs with an average running time of O(n logn);
*   
************************************************************************/
public class LibrarySort 
{
	public static long steps = 0;
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
		System.out.println("Enter size of array to sort");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n;
		try 
		{
			// fetch size of vector, the input entered by the user
			n = Integer.parseInt(br.readLine());
			//initialize variables for running time 
			long startTime = 0;
			long endTime = 0;
			long timeTaken = 0;
			// call method to generate n random numbers
			if(n>0)
			{
				//Run each experiment 100 times to get average number of steps
				for(int run = 0 ; run<100 ;run++)
				{
					Integer[] sortedArray = new Integer [n];
					// time in milliseconds before calling Library Sort
			        startTime = System.currentTimeMillis();
			        // call runLibrarySort method to sort n numbers
					sortedArray = runLibrarySort(n);
					sortedArray = Arrays.copyOf(sortedArray, n);
					//Uncomment below to see the output i.e. sorted array of n numbers 
					//System.out.println("Sorted array: "+Arrays.toString(sortedArray));
					// time in milliseconds after completion of Library Sort
			        endTime = System.currentTimeMillis();
			        // calculate total time taken in milliseconds
			        timeTaken = endTime-startTime;
			        steps = timeTaken + steps;
				}
				System.out.println("Average Number of Steps:  "+steps/100+" ms");
				
				
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
	 * METHOD NAME: runLibrarySort
	 * DESCRIPTION: main method to sort array of n numbers
	 * PARAMETERS:
	 * 	NAME	TYPE	 INPUT/RETURN	 	DESCRIPTION
	 *  n		int		 IN					size of Array
	 *  array	int[]	 RET				sorted Array
	 * @return array
	 *************************************************************************/
	public static Integer[] runLibrarySort(int n)
	{
		//Initialize array size
		int size = 3;
		Integer [] array = new Integer [3] ;
		for (int i = 0;i<n;i++)
		{
			//During first run, insert number at position 0
			if(i ==0)
			{
				array[i] = generateRandomNumbers(n);
			}
			else
			{
				//Expand the array
				array = Arrays.copyOf(array, size);
				//Fetch next random number
				array[size-1] = generateRandomNumbers(n);
				//Call insertion sort to insert the new value
				modifiedInsertionSort(array, array.length);
			}
			// increase array size as the number of elements increase
			size= size+2;
		}
		return array;
	}

	/*************************************************************************
	 * METHOD NAME: modifiedInsertionSort
	 * DESCRIPTION: Function to sort an array a[] of size 'n'
	 * PARAMETERS:
	 * 	NAME	TYPE	 INPUT/RETURN	 	DESCRIPTION
	 *  n		int		 IN					size of Array
	 *  array	int[]	 IN					array to sort
	 *************************************************************************/
	public static void modifiedInsertionSort(Integer array[], int n)
	{
		int locationToAdd, m, current = 0;
		for (int i = 1; i < n; ++i)
		{
			m = i - 1;
			if(array[i]!=null)
			{
				current = array[i];
				// find position where new element should be inserted
				locationToAdd = binarySearch(array, current, 0, m);
				// Move all elements after location to create space
				while (m >= locationToAdd)
				{
					array[m+1] = array[m];
					m--;
				}
				array[m+1] = current;
			 }
         }
	 }
	/*************************************************************************
	 * METHOD NAME: binarySearch
	 * DESCRIPTION: Function to find position , at which the new element should be 
	 * 				inserted in array, so that the final Array is sorted
	 * PARAMETERS:
	 * 	NAME	 TYPE	 INPUT/RETURN	 	DESCRIPTION
	 *  element	 int	 IN					new number to be added to existing Array
	 *  array	 int[]	 IN					Array to search position within
	 *  low      int     IN					first element of Array
	 *  high     int	 IN 				last element of Array
	 *************************************************************************/
	 static int binarySearch(Integer array[], int element, int low, int high)
	  {
		 
		 //below check to skip the blanks or null or empty spaces in an Array
		 if(array[low]!=null)
		 {
			//when no element found return low accordingly, as an inserting position in Array
		  	if (high <= low)
		  	{
		  	    return (element > array[low])?  (low + 1): low;
		  	}
		 }
		 // find mid 
		 int mid = (low + high)/2;
		 //below check to skip the blanks or null or empty spaces in an Array
		 if(array[mid]!=null)
		   {
		   	  if(element == array[mid])
		   	  {
		   		return mid+1;
		   	  }
		   	  if(element > array[mid])
		   	  {
		   		 return binarySearch(array, element, mid+1, high);
		   	  }
		   }
		
		  return binarySearch(array, element, low, mid-1);
	  }
	 /*************************************************************************
		 * METHOD NAME: generateRandomNumbers
		 * DESCRIPTION: method to generate random integers using java.util.Random
		 * PARAMETERS:
		 * 	NAME	TYPE	INPUT/RETURN	 	DESCRIPTION
		 *  n		int		IN					size of vector entered by user
		 *************************************************************************/
		public static int generateRandomNumbers(int maximum)
		 {
			// set maximum and minimum limit for integer generation, can be set to any value
			int minimum = 1;
			// initialize length of array of integers to n
			Random rand = new Random();
		    int randomNum = minimum + rand.nextInt((maximum - minimum) + 1);
		    return randomNum;
		}			 
	  
}
