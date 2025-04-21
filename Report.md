1. How you can break down a large problem instance into one or more smaller instances? Your
answer should include how the solution to the original problem is constructed from the sub-
problems and why this breakdown makes sense.
- breaking down into paths and max score of each vertex so when we come across the vertex again we already know the max score

2. What are the base cases of this problem, and what are their solutions?
- max vertex = 1 / or 2 circles (?)
- if value is already in the hash table

3. What data structure would you use to store the partial solutions to this problem? Justify
your answer.
- hash table
- O(1) if you already have the value stored so its fast

4. Give pseudocode for an algorithm that uses memoization to compute the maximum score.
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

5. What is the time complexity of your memoized algorithm?
O(n+m)

6. Give pseudocode for an iterative algorithm for this problem.


7. Describe how you could modify your algorithm to identify the maximum-scoring tour, not
just the maximum possible score.


Bonus Describe (briefly) how you would modify your algorithm to account for adjacent equal values
and wildcards. There is likely no algorithm that is guaranteed to solve this problem in
polynomial time, so just focus on solving the problem correctly rather than quickly
