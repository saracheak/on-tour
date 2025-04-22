### 1. How you can break down a large problem instance into one or more smaller instances? Your answer should include how the solution to the original problem is constructed from the sub- problems and why this breakdown makes sense.

When thinking about this project, we found that it exhibited features of optimal substructure, where this problem
can be broken down into sub-problems as follows:
The original problem: Find the path that leads to the maximum score of a directed graph with vertices.
The sub-problem: From a vertex V, find the maximum score path I can take (and store into a datastructure).
The sub-sub-problem: Find all the paths and path-scores I can take from Vertex V.

The way we thought about this was to first address the sub-sub-problem. If we haven't encountered this vertex
before, we calculate all the paths we can take from this vertex. 

Next to address the sub-problem. From all these paths calculated, choose the one which gives us the max score and 
store it into a data structure, such as a hashtable. Do this for all the vertices.

The solution to the original problem is the score from the path followed from the vertex with the highest
score in the hashtable.

### 2. What are the base cases of this problem, and what are their solutions?
Base Case 1: The vertex value has the maximum value out of all vertices in the graph, therefore return the value
of its weight/score (1 or 2).

Base Case 2: The vertex is not adjacent to any vertices with a larger value, therefore we cannot "go" anywhere
and we return the value of its weight/score (1 or 2).

Base Case 3: The vertex already has a max_path value in the hashtable. We have already calculated the optimal max 
path to go from this vertex, so we return the value of the max_path of this vertex stored in the hashtable.


### 3. What data structure would you use to store the partial solutions to this problem? Justify your answer.
We used a hash table for this as we needed a structure that could map our vertex to its maximum path score. We used a hash table of size n (number of vertices) with the vertex as the key and maximum path score (or default if we haven't calculated it yet) as value. This uses space complexity of O(n) as the size of the hash table is proportional to the number of keys and values. The time complexity for retrieving any element in the hash table is O(1), which is important as we often want to search for if a vertex's max_path has already been calculated. Overall we used a hashtable as the space and time complexity was reasonable and it fulfilled our requirements.


### 4. Give pseudocode for an algorithm that uses memoization to compute the maximum score.
```
// Global maps
dp= empty map       // dp[v] = max score starting from space v
visited = empty map // visited[v] indicating true if v already visited
next= empty map  // next[v] = best next vertex on the max path starting with v

def calcScore(v):
	if visited[v]:
		return dp[v]
	visited[v] = true
	bestScore = 0
	bestNext = null

// Explore neighbors of v
	for neighbor u in v.neighbors:
		if u.value > v.value:
			score = calcScore(u)
			if score > bestScore:
				bestScore = score
				bestNext = u  // tracking next best space
	// Score final results in dp and ext
	dp[v] =v.points + bestScore
	next[v] = bestNext
	return dp[v]

def getMaxScore(graph):
	maxScore = 0
	start = null
	
	//  Starting tour from each vertex
	for each vertex v in graph:
		score = calcScore(v)
		if score > maxScore:
			maxScore = score
			start = v

	// Reconstructing best path
	path = empty list
  	while start != null:
		path.append(start)
		start = next(start)

	return(maxScore, path)

```

### 5. What is the time complexity of your memoized algorithm?
O(n+m)

### 6. Give pseudocode for an iterative algorithm for this problem.
 Algorithm: LongestTourScore
 Input: Vertices 
 Output: Maxscore
```
 sort Vertices and store in Searching array
//sort and record in a new array for finding max easier

 Hashtable<Vertex, Integer>HashVertices 
// declear the hash table

 for(int i=vertices.size()-1;i>=0;i--)
 	UpdateCurrent(Vertices[SearchingArray[i]],HashVertices)
 end
//  Iterate all vertices from max to min using SearchingArray(which sort before)

int Max=0
 for i in Vertices
 	if max<HashVertex[i]
 		max=HashVertex[i]
	end
 end
//Iterate Hashtable

 return Max
```
 Algorithm : UpdateCurrent
 Input: Vertex
 Input: HashVertex
 Output: Null
 ```
 If Vertex.getNearbyVertex==0
 	return
 end
 
 int weight=0
//weight for record

for(int i=0; i<Vertex.numberOfVertex; i++)
//Iterate all neighbor
 
	if Vertices[Vertex.getNearbyVertex[i]-1].getVal> vertex.getVal
 	//compare Val(can be delete)
  
		if Vertices[Vertex.getNearbyVertex[i]-1] is find in HashVertices
		// check if in hashtable
  
			if weight< HashVertices[ Vertices[Vertex.getNearbyVertex[i]-1]]
			//compare with record
   
				weight=HashVertices[ Vertices[Vertex.getNearbyVertex[i]-1]]
				//update weight
			end
		end
	end
 end
 
 weight=weight+Vertex.getweight
 //weight plus own
 
 HashVertices[Vertex]=weight
```
### 7. Describe how you could modify your algorithm to identify the maximum-scoring tour, not just the maximum possible score.

We talked about 2 possible solutions for finding the maximum tour, not just the score.
1) store each max tour for all vertices in a 2d array of size n
   
   Declare a 2d array of size n, arr[i][j], where i is the value of the vertex and j is an array which represents the actual tour of its maximum score.
   After finding the maximum score for Vertex V, store the tour that corresponds to the maximum score in the 2d array.
   After the path finding algorithm, we are left with all of the maximum paths for all of the vertices.
   This makes it very easy to get the highest scoring tour from any vertex chosen, however, the space complexity is an issue. Just initialising the array is O(n), and then storing the vertex value O(1) and maximum tour O(n-1) for each vertex. Resulting in a worst case space complexity of O(n^2).
   
2) declare a an which stores the max tour for all vertices.
   
   This would be a global variable array which stores the maximum-scoring tour. As we iterate through each of the vertices, we store the tour for the vertex into the global array IF it has a score higher than the current score, or array is empty (as it would be before the first vertex has been visited). Else, we know that this tour is not the maximum-scoring tour for all vertices, so keep what is currently stored in the array. 



### Bonus Describe (briefly) how you would modify your algorithm to account for adjacent equal values and wildcards. There is likely no algorithm that is guaranteed to solve this problem in polynomial time, so just focus on solving the problem correctly rather than quickly

When comparing whether we can go to an adjacent vertex B from vertex A, we are currently only moving if B>A. To acccount for adjacent equal values, we would change to if B>=A, so only move if the adjacent vertex is greater than or equal to current vertex. 

For wildcards, a solution we talked about was to assume wildcard is e.g. -1 and update traversal condition to factor this in. For example, we can move from B to A if B>A OR B == -1. This would open up more paths. However, this may cause some issues if we have a situation such as e.g. 3 -> * -> 5 and 3 -> * -> 2. If we assume wildcard is -1, after we move to the wildcard space, we would be able to move to both 5 and 2, but we would actually only want to be able to go to 5. A solution to this is to assume that the wildcard is the same value as the vertex you have just come from, i.e. in this case, assume * to be 3. We make use of the same conditional change as in the adjacent equal values (B>=A) and this would allow us to account for wildcards. Another solution is to compare the adjacent vertices directly, so if we encounter a wildcard, we would look at all the paths connected to the wildcard and compare the next vertex (i.e. 5) with the previous one (i.e. 3). If it is valid (5>=3), continue, else we stop the path at the wildcard.

The number of possible paths most likely increases with these changes and we may run into a scenario where we encounter cycles, particularly if, in a worst case such as a graph where all the vertices have the same value. In this case, it would be wise to include a alreadyVisited boolean field in the Vertex class, which is initialised to false and we can make it true everytime we visit this vertex in the path. We would add another condiition in the if statement: move to B if B>=A AND B==!alreadyVisited. This bool value would have to be reset before every path.
