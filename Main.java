import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implement multiple methods of placing items randomly into a one-dimensional array. It is easy to convert a 1D array into a 2D array,
 * so this is sufficient to figure out the best method of placing fences for hivolts based on this.
 * @author Albert Ford
 *
 */
public class Main {

	private static int trials = 100000;
	
	public static void main(String[] args) {
		System.out.println("Example randomized array using method 1:");
		int[] array1 = newArray(8);
		System.out.println(Arrays.toString(method1(array1, 3)));
		System.out.println("Distribution using method 1:");
		long a1 = System.currentTimeMillis();
		System.out.println(Arrays.toString(testMethod1(121, 33)));
		long b1 = System.currentTimeMillis();
		System.out.println("Time taken: " + (b1-a1) + "ms");
		System.out.println();
		
		System.out.println("Example randomized array using method 2:");
		int[] array2 = newArray(8);
		System.out.println(Arrays.toString(method2(array2, 3)));
		System.out.println("Distribution using method 2:");
		System.out.println(Arrays.toString(testMethod2(4, 2)));
		System.out.println();
		
		System.out.println("Example randomized array using method 3:");
		int[] array3 = newArray(8);
		System.out.println(Arrays.toString(method3(array3, 3)));
		System.out.println("Distribution using method 3:");
		long a3 = System.currentTimeMillis();
		System.out.println(Arrays.toString(testMethod3(121, 33)));
		long b3 = System.currentTimeMillis();
		System.out.println("Time taken: " + (b3-a3) + "ms");
		System.out.println();
		
		System.out.println("Example randomized array using method 4:");
		int[] array4 = newArray(8);
		System.out.println(Arrays.toString(method4(array4, 3)));
		System.out.println("Distribution using method 4:");
		System.out.println(Arrays.toString(testMethod4(4, 2)));
		System.out.println();

		System.out.println("Example randomized array using method 5:");
		display(place(8, 0, new Integer[]{1, 2, 9}, new int[]{3, 1, 2}));
		System.out.println("Distribution using method 5 (LinkedList):");
		long a5 = System.currentTimeMillis();
		System.out.println(Arrays.toString(testMethod5(121, 33)));
		long b5 = System.currentTimeMillis();
		System.out.println("Time taken: " + (b5-a5) + "ms");
		System.out.println("Distribution using method 5 (ArrayList):");
		long c5 = System.currentTimeMillis();
		System.out.println(Arrays.toString(testMethod5a(121, 33)));
		long d5 = System.currentTimeMillis();
		System.out.println("Time taken: " + (d5-c5) + "ms");
		System.out.println();
	}
	
	/**
	 * Test randomness of method1
	 * @param length The length of the array
	 * @param itemCount The number of items to place
	 * @return The chance of an item to be found at each point in the array
	 */
	private static double[] testMethod1(int length, int itemCount) {
		//int trials = 1000000;
		double[] probabilities = new double[length];
		for (int i = 0; i < trials; i++) {
			int[] array = newArray(length);
			int[] finalArray = method1(array, itemCount);
			for (int j = 0; j < probabilities.length; j++) {
				probabilities[j] += finalArray[j];
			}
		}
		for (int i = 0; i < length; i++) {
			probabilities[i] /= trials;
		}
		return probabilities;
	}
	
	/**
	 * Test randomness of method2
	 * @param length The length of the array
	 * @param itemCount The number of items to place
	 * @return The chance of an item to be found at each point in the array
	 */
	private static double[] testMethod2(int length, int itemCount) {
		//int trials = 1000000;
		double[] probabilities = new double[length];
		for (int i = 0; i < trials; i++) {
			int[] array = newArray(length);
			int[] finalArray = method2(array, itemCount);
			for (int j = 0; j < probabilities.length; j++) {
				probabilities[j] += finalArray[j];
			}
		}
		for (int i = 0; i < length; i++) {
			probabilities[i] /= trials;
		}
		return probabilities;
	}
	
	/**
	 * Test randomness of method3
	 * @param length The length of the array
	 * @param itemCount The number of items to place
	 * @return The chance of an item to be found at each point in the array
	 */
	private static double[] testMethod3(int length, int itemCount) {
		//int trials = 1000000;
		double[] probabilities = new double[length];
		for (int i = 0; i < trials; i++) {
			int[] array = newArray(length);
			int[] finalArray = method3(array, itemCount);
			for (int j = 0; j < probabilities.length; j++) {
				probabilities[j] += finalArray[j];
			}
		}
		for (int i = 0; i < length; i++) {
			probabilities[i] /= trials;
		}
		return probabilities;
	}
	
	/**
	 * Test randomness of method4
	 * @param length The length of the array
	 * @param itemCount The number of items to place
	 * @return The chance of an item to be found at each point in the array
	 */
	private static double[] testMethod4(int length, int itemCount) {
		//int trials = 1000000;
		double[] probabilities = new double[length];
		for (int i = 0; i < trials; i++) {
			int[] array = newArray(length);
			int[] finalArray = method4(array, itemCount);
			for (int j = 0; j < probabilities.length; j++) {
				probabilities[j] += finalArray[j];
			}
		}
		for (int i = 0; i < length; i++) {
			probabilities[i] /= trials;
		}
		return probabilities;
	}
	
	/**
	 * Test randomness of method5
	 * @param length The length of the array
	 * @param itemCount The number of items to place
	 * @return The chance of an item to be found at each point in the array
	 */
	private static double[] testMethod5(int length, int itemCount) {
		//int trials = 1000000;
		double[] probabilities = new double[length];
		for (int i = 0; i < trials; i++) {
			int[] array = newArray(length);
			Object[] finalArray = method5(array, itemCount);
			for (int j = 0; j < probabilities.length; j++) {
				probabilities[j] += (Integer) finalArray[j];
			}
		}
		for (int i = 0; i < length; i++) {
			probabilities[i] /= trials;
		}
		return probabilities;
	}
	private static double[] testMethod5a(int length, int itemCount) {
		//int trials = 1000000;
		double[] probabilities = new double[length];
		for (int i = 0; i < trials; i++) {
			int[] array = newArray(length);
			Object[] finalArray = method5a(array, itemCount);
			for (int j = 0; j < probabilities.length; j++) {
				probabilities[j] += (Integer) finalArray[j];
			}
		}
		for (int i = 0; i < length; i++) {
			probabilities[i] /= trials;
		}
		return probabilities;
	}

	/**
	 * Create a new array ready for things to be placed into it
	 * @param n - The length of the array
	 * @return an int[] of length n
	 */
	private static int[] newArray(int n) {
		int[] array = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = 0;
		}
		return array;
	}
	
	private static void display(Object[] array) {
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * Method 1:
	 * Place items at the beginning of the array, then shuffle the array.
	 * Items are assumed to be the number 1, and the array is assumed to be full of zeros.
	 * @param array The array to place items into
	 * @param itemCount The number of items to place
	 */
	private static int[] method1(int[] array, int itemCount) {
		for (int i = 0; i < itemCount; i++) {
			array[i] = 1;
		}
		// Fisher-Yates shuffle
		for (int i = array.length - 1; i > 0; i--) {
			int j = (int) Math.floor((i + 1) * Math.random());
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		return array;
	}
	
	/**
	 * Method 2:
	 * Have the items next to each other. Place empty spaces between the items randomly.
	 * Potential problem raised by Ben: This might not produce uniformly random distribution.
	 * This is inspired my the method in counting of counting the number of ways to place
	 * things into categories (sticks and stones, stars and stripes, etc)
	 * @param array The array to place items into
	 * @param itemCount The number of items to place
	 */
	private static int[] method2(int[] array, int itemCount) {
		// An array that says how much empty space there is between each item
		int[] emptySpaces = new int[itemCount + 1];
		for (int i = 0; i < array.length - itemCount; i++) {
			int index = (int) Math.floor((itemCount + 1) * Math.random());
			emptySpaces[index] += 1;
		}
		int index = 0;
		for (int i = 0; i < emptySpaces.length; i++) {
			for (int j = 0; j < emptySpaces[i]; j++) {
				array[index] = 0;
				index += 1;
			}
			if (index < array.length) {
				array[index] = 1;
				index += 1;
			}
		}
		return array;
	}
	
	/**
	 * Method 3: (rejected)
	 * Iterate through the array and have a small chance of placing an item at each point in
	 * the array. The problem is that there is no guarantee that all items will get placed,
	 * and if you limit the number of items that can get placed, items will tend to be
	 * near the beginning of the array. A potential solution is looping back to the beginning
	 * of the array if needed.
	 */
	private static int[] method3(int[] array, int itemCount) {
		boolean allItemsPlaced = false;
		int itemsPlaced = 0;
		while (!allItemsPlaced) {
			for (int i = 0; i < array.length; i++) {
				if (Math.random() < ((double) (itemCount - itemsPlaced) / ((double) (array.length - i)))) {
					array[i] = 1;
					itemsPlaced++;
					if (itemsPlaced == itemCount) {
						allItemsPlaced = true;
						break;
					}
				}
			}
		}
		return array;
	}
	
	/**
	 * Method 4:
	 * This is kind of like Method 2, but instead of placing empty spaces around items you
	 * place items around empty space
	 * @param array The array to place items into
	 * @param itemCount The number of items to place
	 */
	private static int[] method4(int[] array, int itemCount) {
		int[] inverse = method2(array, array.length - itemCount);
		for (int i = 0; i < inverse.length; i++) {
			if (inverse[i] == 0) {
				inverse[i] = 1;
			} else {
				inverse[i] = 0;
			}
		}
		return inverse;
	}
	
	/**
	 * Method 5: (correct?)
	 * Create a linked list of the empty elements of the array, then add items randomly throughout
	 * @param array The array
	 * @param itemCount
	 * @return
	 */
	private static Object[] method5(int[] array, int itemCount) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = itemCount; i < array.length; i++) {
			list.addFirst(0);
		}
		for (int i = 1; i <= itemCount; i++) {
			int index = (int) Math.floor((array.length - itemCount + i) * Math.random());
			list.add(index, 1);
		}
		return list.toArray();
	}
	private static Integer[] method5a(int[] array, int itemCount) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = itemCount; i < array.length; i++) {
			list.add(0);
		}
		for (int i = 1; i <= itemCount; i++) {
			int index = (int) Math.floor((array.length - itemCount + i) * Math.random());
			list.add(index, 1);
		}
		Integer[] a = new Integer[array.length];
		return list.toArray(a);
	}
	
	/**
	 * Create an array of objects in random order.
	 * @param length The length of the returned array
	 * @param fill The default element of the array
	 * @param items An array of items to be placed randomly
	 * @param itemCounts An array of ints that determine how many items to place
	 * @return An array of objects with the items randomly placed
	 */
	private static Object[] place(int length, Object fill, Object[] items, int[] itemCounts) {
		ArrayList<Object> list = new ArrayList<Object>();
		int empty = length;
		for (int i : itemCounts) {
			empty -= i;
		}
		for (int i = 0; i < empty; i++) {
			list.add(fill);
		}
		for (int i = 0; i < items.length; i++) {
			for (int j = 0; j < itemCounts[i]; j++) {
				double index = Math.floor(list.size() * Math.random());
				list.add((int) index, items[i]);
			}
		}
		return list.toArray();
	}
	
}
