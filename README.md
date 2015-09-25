# RandomPlacement
The goal of this small project is to explore different methods of putting objects in an array in an even random distribution. This is for the Hivolts project where fences, mhos, and the player were placed randomly on a 2-dimensional array. Previously, objects were placed randomly, and if the spot was already occupied, it would chose another one at random until it found an empty spot.

## Method 1
The first method I thought of was to place everything at the start of the array and then shuffle it. The array is shuffled by switching elements of the array successively with the Fisher-Yates shuffle.

## Method 2
The second method I tried was to first place the objects, then to place empty space randomly around them. I gave each location between objects equal weight which ended up creating an uneven distribution. In hindsight, if the placement of the empty spaces was weighted by how much space there was between objects already, method 2 would be another formulation of method 5 which was correct.

## Method 3
My third idea was to itereate through the array with a chance of adding an object at each index of the array, and stopping once enough objects were placed. The code would restart placing objects at the beginning of the array if it had looped through the array without placing all the objects. After multiple failed attemps, and after finishing methods 4 and 5, the probability of placing an object turned out to be (# of items left to place) / (length - index), which yielded an even distribution. This method is similar to the inefficient guess and check method that I started out with, but it does not have the problem of continuously guessing occupied spaces since it skips over them automatically.

## Method 4
The fourth try was just an attempt to readapt method 2 into something that yielded an even distribution. I knew that I was close, but I incorrectly flipped the empty space and the objects. The only result was that objects now tended to form at the first and last element of the array instead of the middle.

## Method 5
The fifth method is my best guess at the intended algorithm for placement in Hivolts. It creates a list with length reduced by the number of objects to be placed. Then it adds the objects at random indices. This guarantees no overlap. I tried using both an ArrayList and a LinkedList, and the LinkedList turned out to be faster.

## Distributions
Methods 1, and 5 gave even distributions. After I changed the probabilities, method 3 gave an even distribution as well. Method 2 resulted in objects tending toward the middle, and method 4 resulted in objects tending toward the first and last elements of the array.

## Speed
I ran each method 10 million times with placing 4 objects in an array of length 8. There was variation in time each time I ran the code, but the ranking was always the same. From fastest to slowest with example times:
Method 3 (4186ms), Method 5 with ArrayList (4490ms), Method 1 (4514ms), and Method 5 with LinkedList (4840ms).

Then I compared speeds with placing 33 objects in an array of length 121 since that is what I'll do for Hivolts. I ran each method 100 thousand times.

![Graph](http://i60.tinypic.com/15guwdt.png)

This time, from fastest to slowest median:
Method 5 with LinkedList (600.5ms), Method 5 with ArrayList (628.5ms), Method 3 (641.5ms), Method 1 (725.5ms).

## Conclusions
It appears that Method 5 using linked lists is best suited for Hivolts. Based on the difference between 4 objects in an 8 element array and 33 objects in a 121 element array, method 5 is relatively slower with higher object density. It would make sense that the performance of method 1 is unaffected by how many objects are placed becuase shuffling does not care about the contents of the array. Method 3 might actually become more efficient the more dense the array is because it might have a higher chance of terminating earlier.

### Potential Optimizations
Currently, method 5 using linked lists iterates through a random section of the list for every object that is placed. Instead, a list of sorted random numbers could be generated, and then the code would only have to iterate through the list once to place all the objects. This was not implemented because of the convenience of using the methods provided by the existing implementation in java.util.LinkedList.
