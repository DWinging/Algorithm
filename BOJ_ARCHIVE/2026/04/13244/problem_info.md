# [Tree] 13244

## 📊 문제 정보
| 항목 | 내용 |
| :--- | :--- |
| 티어 | Gold IV |
| 시간 제한 | 2 초 |
| 메모리 제한 | 512 MB |
| 알고리즘 | 그래프 이론, 자료 구조, 그래프 탐색, 트리, 깊이 우선 탐색, 분리 집합 |
| 링크 | [백준 바로가기](https://www.acmicpc.net/problem/13244) |

---

## 📜 문제 설명

<p>One of the most important data structures in computer science is the tree. You already dealt with binary trees in the qualification round. This problem is about general trees.</p>

<p>Trees are the subset of graphs that have the following 3 properties:</p>

<ol>
	<li>It is connected: for every node you can reach every other node following edges.</li>
	<li>If an edge is removed, the graph is no longer connected. That is, some nodes cannot be reached anymore.</li>
	<li>When an edge is added between two existing nodes A and B, a cycle is created. There is a cycle if there is more than one way to go from A to B.</li>
</ol>

<p>Your task is to decide if a given graph is a tree or not.</p>

### 📥 입력

<p>The first line will contain an integer T representing the number of graphs to check. There will be at most 10 graphs in each test case.</p>

<p>Each of the graph will be represented as follows:</p>

<p>The first line will contain an integer N with the number of nodes in the graph. The number of nodes will be between 1 and 1,000. The identifier of each node will be an integer from 1 to N.&nbsp;</p>

<p>The next line will contain an integer M with the number of edges in the graph. There will be at most 10<sup>6</sup> edges.</p>

<p>The next M lines will contain 2 integers A and B each. These are the two nodes connected by an edge.</p>

<p>The total sum of M in all test cases is at most 10<sup>6</sup>.</p>

### 📤 출력

<p>For each graph, a single line with “tree” if the graph represents a tree or “graph“ otherwise.</p>

---

## 💡 예제

### 예제 1
**Input:**
```text
2
4
3
2 1
3 4
1 3
3
3
1 2
1 2
3 2
```
**Output:**
```text
tree
graph
```

### 예제 2
**Input:**
```text
2
7
5
7 2
2 4
4 3
5 6
6 1
7
6
7 2
2 4
4 3
4 5
6 5
1 6
```
**Output:**
```text
graph
tree
```

---

## 📜 나의 제출 기록

| 제출 번호 | 결과 | 메모리 | 시간 | 언어 | 제출 일자 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 105192328 | ✅ 맞았습니다!! | 27520 KB | 84 ms | Java 8 / 수정 | 2026년 4월 27일 |
| 105192255 | ❌ 메모리 초과 | - KB | - ms | Java 8 / 수정 | 2026년 4월 27일 |

