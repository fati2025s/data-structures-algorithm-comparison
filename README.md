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

# 📊 Performance Analysis

The project includes a Python-based visualization tool that generates benchmarks using matplotlib and pandas.

## Memory Usage

The benchmark reveals the significant space overhead of the ***Sparse Table*** compared to the linear space used by ***Segment Tree*** and the ***Hybrid*** approach.
![Memory table](https://github.com/fati2025s/data-structures-algorithm-comparison/blob/main/Memory.jpg)
## Query Time

Observe the transition from the slow O(n) Naive approach to the lightning-fast O(1)performance of the Sparse Table and Hybrid methods.
![Time](https://github.com/fati2025s/data-structures-algorithm-comparison/blob/main/Time.jpg)
