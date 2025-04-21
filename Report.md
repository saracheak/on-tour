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
We used a hash table for this as we needed a structure that could map our vertex to its maximum path score. We used a hash table of size n (number of vertices) with the vertex value as the key and maximum path score (or default if we haven't calculated it yet) as value. This uses space complexity of O(n) as the size of the hash table is proportional to the number of keys and values. The time complexity for retrieving any element in the hash table is O(1), which is important as we often want to search for if a vertex's max_path has already been calculated. Overall we used a hashtable as the space and time complexity was reasonable and it fulfilled our requirements.


### 4. Give pseudocode for an algorithm that uses memoization to compute the maximum score.
// Global arrays
dp[1…n]       // dp[i] = max score starting from space i
visited[1…n] // array indicating true if i already visited, initialized to all false
nb[1…n]       // list of neighbors adjacent to space i
value[1…n]  // number written in space i
points[1…n] // point value for the space(1 or 2)
next[1…n]    // best next node after i on max scoring path, initialized to null

def calcScore(n):
	if visited[i]:
		return dp[i]
	visited[i] = true
	best = 0
	next[i] = null

// Explore neighbors with higher values
	for neighbor j in nb[i]:
		if value[j] > value[i]:
			score = calcScore(j)
			if score > best:
				best  = score
				next[i] = j  // tracking next best space
	dp[i] = points[i] + best
	return dp[i]

def getMaxScore(n):
	maxScore = 0
	start = 1
	
	// Getting score for all starting nodes
	for i= 1 to n:
		score = calcScore(i)
		if score > maxScore:
			maxScore = score
			start = i

	// Reconstructing best path
	path = []
  	while start != null:
		path.append(start)
		start = next(start)

	return(maxScore, path)

### 5. What is the time complexity of your memoized algorithm?
O(n+m)

### 6. Give pseudocode for an iterative algorithm for this problem.
 Algorithm: LongestTourScore
 Input: Vertices 
 Output: Maxscore

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

 Algorithm : UpdateCurrent
 Input: Vertex
 Input: HashVertex
 Output: Null
 
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

### 7. Describe how you could modify your algorithm to identify the maximum-scoring tour, not just the maximum possible score.


We talked about 2 possible soltuison:
1) store each max path for all vertices in a 2d array
2) only have 1 array which stores the max patj for all vertices and if score of path is greater than current, replace, if not, leave it.


### Bonus Describe (briefly) how you would modify your algorithm to account for adjacent equal values and wildcards. There is likely no algorithm that is guaranteed to solve this problem in polynomial time, so just focus on solving the problem correctly rather than quickly

greater than or equal to instead of just greater than
number of possible paths increases with this
some sort of cycle detection
wildcards can always be gone to
isVisited bool field in Vertex class

(TODO)
