import java.util.Arrays;

/**
 * Implement multiple methods of placing items randomly into a one-dimensional array. It is easy to convert a 1D array into a 2D array,
 * so this is sufficient to figure out the best method of placing fences for hivolts based on this.
 * @author Albert Ford
 *
 */
public class Main {

	public static void main(String[] args) {
		int[] array1 = newArray(8);
		System.out.println(Arrays.toString(method1(array1, 3)));
		int[] array2 = newArray(8);
		System.out.println(Arrays.toString(method2(array2, 3)));
		int[] array4 = newArray(8);
		System.out.println(Arrays.toString(method4(array4, 3)));
		
		System.out.println(Arrays.toString(testMethod1(8, 4)));
		
		System.out.println(Arrays.toString(testMethod2(8, 4)));
		
		System.out.println(Arrays.toString(testMethod4(8, 4)));
	}
	
	/**
	 * Test randomness of method1
	 * @param length The length of the array
	 * @param itemCount The number of items to place
	 * @return The chance of an item to be found at each point in the array
	 */
	private static double[] testMethod1(int length, int itemCount) {
		int trials = 10000000;
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
		int trials = 10000000;
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
	 * Test randomness of method4
	 * @param length The length of the array
	 * @param itemCount The number of items to place
	 * @return The chance of an item to be found at each point in the array
	 */
	private static double[] testMethod4(int length, int itemCount) {
		int trials = 10000000;
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
		for (int i = 0; i < array.length - 1; i++) {
			int j = (int) Math.floor(array.length * Math.random());
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
	 * near the beginning of the array.
	 */
	
	/**
	 * Method 4: (correct?)
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
	
}
