# [Unknown] 4091

## 📊 문제 정보
| 항목 | 내용 |
| :--- | :--- |
| 티어 |  |
| 시간 제한 | 1 초 |
| 메모리 제한 | 128 MB |
| 알고리즘 | 등록된 분류가 없습니다. |
| 링크 | [백준 바로가기](https://www.acmicpc.net/problem/4091) |

---

## 📜 문제 설명

<p>As you may know, balloons are handed out during ACM contests to teams as they solve problems. However, this sometimes presents logistical challenges. In particular, one contest hosting site maintains two rooms, A and B, each containing a supply of balloons. There are N teams attending the contest at that site, each sitting at a different location. Some are closer to room A, others are closer to room B, and others are equally distant. Given the number of balloons needed by each team and the distance from each team to room A, and to room B, what is the minimum total possible distance that must be traveled by all balloons as they are delivered to their respective teams, assuming they are allocated in an optimal fashion from rooms A and B? For the purposes of this problem, assume that all of the balloons are identical.</p>

### 📥 입력

<p>There will be several test cases in the input. Each test case will begin with a line with three integers:</p>

<pre>N A B</pre>

<p>Where N is the number of teams (1 ≤ N ≤ 1,000), and A and B are the number of balloons in rooms A and B, respectively (0 ≤ A,B ≤ 10,000). On each of the next N lines there will be three integers, representing information for each team:</p>

<pre>K DA DB</pre>

<p>Where K is the total number of balloons that this team will need, DA is the distance of this team from room A, and DB is this team’s distance from room B (0 ≤ DA,DB ≤ 1,000). You may assume that there are enough balloons – that is, Σ(K’s) ≤ A+B. The input will end with a line with three 0s.</p>

### 📤 출력

<p>For each test case, output a single integer, representing the minimum total distance that must be traveled to deliver all of the balloons. Count only the outbound trip, from room A or room B to the team. Don’t count the distance that a runner must travel to return to room A or room B. Print each integer on its own line with no spaces. Do not print any blank lines between answers.</p>

---

## 💡 예제

### 예제 1
**Input:**
```text
3 15 35
10 20 10
10 10 30
10 40 10
0 0 0
```
**Output:**
```text
300
```

---

## 📜 나의 제출 기록

| 제출 번호 | 결과 | 메모리 | 시간 | 언어 | 제출 일자 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 104248707 | ✅ 맞았습니다!! | 12896 KB | 120 ms | Java 8 / 수정 | 2026년 3월 24일 |

