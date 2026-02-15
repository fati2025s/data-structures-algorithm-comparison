# Range Minimum Query (RMQ) Benchmark Suite

A comprehensive Java-based implementation and performance analysis of various algorithms for solving the ***Range Minimum Query (RMQ)*** problem. This project compares multiple data structures in terms of ***Time Complexity*** (build and query) and ***Space Complexity***.

# 🚀 Implemented Algorithms

This suite includes five different approaches to solve the RMQ problem:

1. ***Naive***: A simple linear scan for each query.
- Query: O(n), Space: O(1)
2. ***Sparse Table***: Pre-computes answers for all power-of-two length ranges.
- Query:O(1), Space: O(nlog n)
3. ***Segment Tree***: A balanced binary tree structure.
- Query: O(log n), Space: O(n)
4. ***Block RMQ (Square Root Decomposition)***: Divides the array into sqrt{n} sized blocks.
- Query: O(sqrt{n}), Space: O(sqrt{n}) auxiliary
5. ***Hybrid (Fischer-Heun Implementation)***: (Bonus) A sophisticated combination of Block RMQ and Sparse Table to achieve near-optimal complexity.
- Query: O(1), Space: O(n)

# 🧠 Algorithm Explanations (In Simple Terms)

Here is a brief breakdown of how each method operates:

- ***Naive Approach***: Imagine every time someone asks for the minimum in a range, you manually check every single number from start to finish. It requires zero extra memory but is extremely time-consuming for large arrays.

- ***Sparse Table***: Think of this as having a giant "cheat sheet" where you’ve pre-calculated the answers to almost all possible questions. When a query comes in, you find the answer instantly in O(1). However, that "cheat sheet" (memory) takes up a lot of space, specifically O(nlog n).

- ***Segment Tree***: This method organizes numbers into a hierarchical tree structure. To find the minimum, you only need to check a few specific nodes in the tree rather than every element. It offers a great balance with O(log n) query speed and reasonable O(n) memory usage.

- ***Block RMQ (Square Root Decomposition)***: The array is divided into small buckets (blocks). Each bucket has a "representative" (its local minimum). When querying, you quickly skip over full blocks using their representatives and only manually scan the partial blocks at the beginning and end.

- ***Hybrid Approach (Emtiazy/Fischer-Heun)***: The "masterpiece" of this project. The idea is to divide the array into very tiny blocks and build a Sparse Table only for the representatives of these blocks.

Result: Since the number of representatives is small, the memory doesn't explode like a standard Sparse Table (O(n)), yet the query speed remains lightning-fast (O(1)) just like the Sparse Table.

# 📊 Performance Analysis

The project includes a Python-based visualization tool that generates benchmarks using matplotlib and pandas.

## Memory Usage

The benchmark reveals the significant space overhead of the ***Sparse Table*** compared to the linear space used by ***Segment Tree*** and the ***Hybrid*** approach.
![Memory table](https://github.com/fati2025s/data-structures-algorithm-comparison/blob/main/Memory.jpg)
## Query Time

Observe the transition from the slow O(n) Naive approach to the lightning-fast O(1)performance of the Sparse Table and Hybrid methods.
![Time](https://github.com/fati2025s/data-structures-algorithm-comparison/blob/main/Time.jpg)
