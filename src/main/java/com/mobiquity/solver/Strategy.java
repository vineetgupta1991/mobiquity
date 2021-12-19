package com.mobiquity.solver;

import com.mobiquity.model.Item;
import com.mobiquity.model.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Strategy {

	public Solution solve(int capacity, List<Item> items) {
		items.sort(Comparator.comparing(Item::getWeight));

		Double[] wt = items.stream().map(Item::getWeight).toArray(Double[]::new);
		Integer[] val = items.stream().map(Item::getValue).toArray(Integer[]::new);
		Boolean[] visited = new Boolean[items.size()];
		Arrays.fill(visited, Boolean.FALSE);

		Integer maxValue = findMaxValue(capacity, wt, val, items.size(), visited);
		
		List<Item> solutionItems = new ArrayList<>();
		for ( int i = 0; i < items.size(); i++ ) {
			 if ( visited[i] ) {
				 solutionItems.add(items.get(i));
			 }
		}

		solutionItems.sort(Comparator.comparing(Item::getIndex));

		return new Solution(solutionItems, maxValue);
	}

	/**
	 * @param W the maximum capacity of the package
	 * @param wt array of items weights
	 * @param val array of items values
	 * @param N total number of items
	 * @param visited
	 * @return maximum value for the package
	 *
	 * This is the recursive strategy used to find max value for the package
	 */

	/**
	 * Thought process : Get the value from array and add it's value
	 * on each iteration recursively. Also, one thing to make sure that
	 * the total weight shouldn't exceed max capacity.
	 * The other recursive function is used to explore the possibility of finding the next
	 * element skipping the first one.
	 * On the other hand, we are finding the visited elements
	 * which are put into array as part of included items
	 *
	 *
	 *
 	 */
	private Integer findMaxValue(Integer W, Double[] wt, Integer[] val, Integer N, Boolean[] visited) {

		if (N == 0 || W == 0) {
			return 0;
		}
		if ( wt[N-1] > W ) {
			return findMaxValue(W, wt, val, N-1,visited);
		}  else {
			
			Boolean[] v1 = new Boolean[visited.length];
			Boolean[] v2 = new Boolean[visited.length];
       
			System.arraycopy(visited, 0, v1, 0, v1.length);
			System.arraycopy(visited, 0, v2, 0, v2.length);
       
			v1[N-1] = true;

			Integer s1 = val[N-1] + findMaxValue(W-wt[N-1].intValue(), wt, val, N-1,v1);
			Integer s2 = findMaxValue(W, wt, val, N-1,v2);
       
			if( s1 > s2 ){
				System.arraycopy(v1, 0, visited, 0, v1.length);
				return s1;
			} else{
				System.arraycopy(v2, 0, visited, 0, v2.length);
				return s2;
			}
		}
   }      
}
