# RandomPlacement
The goal of this small project is to explore different methods of putting objects in an array in an even random distribution. This is for the Hivolts project where fences, mhos, and the player were placed randomly on a 2-dimensional array. Previously, objects were placed randomly, and if the spot was already occupied, it would chose another one at random until it found an empty spot.

## Method 1
The first method I thought of was to place everything at the start of the array and then shuffle it. The array is shuffled by switching elements of the array successively with the Fisher-Yates shuffle.

## Method 2
The second method I tried was to first place the objects, then to place empty space randomly around them. I gave each location between objects equal weight which ended up creating an uneven distribution. In hindsight, if the placement of the empty spaces was weighted by how much space there was between objects already, method 2 would be another formulation of method 5 which was correct.

## Method 3
