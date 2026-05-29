### 🚀 SWEA 19004. [점프 놀이](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYtrEOraDk0DFAR-) (Java)

> **날짜:** 2026년 4월 30일 <br>
> **알고리즘:** Dynamic Programming (DP), Depth First Search (DFS)   <br>
> **언어:** Java 8   <br>
> **핵심 키워드:** Memoization, Bottom-up vs Top-down, Low-level Optimization

## 📌 문제 개요
$N \times N$ 격자판에서 각 타일에 적힌 숫자 $i$번에서 $i+1$번으로 순차적으로 점프하여 최종적으로 $K$번 타일에 도달하는 최단 거리를 구하는 문제입니다. 점프 거리는 **맨해튼 거리**($|y1-y2| + |x1-x2|$)를 사용하며, $1$부터 $K$까지 모든 숫자를 거쳐야 합니다. 하나라도 숫자가 누락되면 도달 불가능으로 간주합니다.

---

## 💡 풀이 핵심 (Core Logic)

### 1. 상태 정의 (State)
*   `dp[y][x]` : $(y, x)$ 좌표에 위치한 타일에서 마지막 $K$번 타일까지 도달하는 데 필요한 **최소 거리**.

### 2. 점진적 최적화 (Step-by-step)
*   **색상별 좌표 그룹화**: 효율적인 탐색을 위해 입력 단계에서 색상($1 \sim K$)별로 좌표를 분류하여 리스트(또는 연결 리스트 구조)로 관리합니다.
*   **중복 계산 방지**: 동일한 좌표를 방문할 때마다 하위 경로를 다시 계산하지 않도록 **메모이제이션(Memoization)**을 적용합니다.

---

## 📊 풀이 방식 비교 (Top-down vs Bottom-up)

| 비교 항목 | Top-down (DFS + Memo) | Bottom-up (Iteration DP) |
| :--- | :--- | :--- |
| **구현 방식** | 재귀 호출을 이용한 하향식 탐색 | 반복문을 이용한 상향식(역순) 탐색 |
| **장점** | 로직이 직관적이며 필요한 경로만 탐색 | 재귀 오버헤드가 없고 시스템 안정성이 높음 |
| **단점** | 재귀 깊이가 깊어질 경우 스택 오버헤드 위험 | 모든 상태 전이 방향을 미리 설계해야 함 |
| **최적화** | `dp[y][x]` 방문 여부 체크 | 정적 배열을 활용한 연결 리스트 직접 구현 |

---

## 🛠 성능 최적화 인사이트 (Performance Insights)

### 1. Fast I/O 및 Low-level 구현
*   `System.in.read()`를 이용한 커스텀 `readInt()` 메서드를 구현하여 대량의 입력 데이터를 빠르게 처리했습니다.
*   `Math.abs()` 대신 삼항 연산자를 사용한 거리 계산 등 세밀한 최적화를 적용했습니다.

### 2. 메모리 관리 (Memory Management)
*   매 테스트 케이스마다 객체를 생성하지 않고, `ArrayList.clear()` 또는 **정적 객체 풀(Static Object Pool)**을 구성하여 GC(Garbage Collector)의 부하를 최소화했습니다.

### 3. 구조적 최적화 (Linked Structure)
*   **Bottom-up 풀이**에서는 `ArrayList` 조차 사용하지 않고, 각 `Point` 객체에 `pre` 인덱스를 두어 **정적 배열 기반의 연결 리스트**를 직접 구현했습니다. 이는 메모리 단편화를 방지하고 캐시 효율성을 높이는 결과를 가져왔습니다.

---

## 🧠 회고 및 결론

1.  **재귀 오버헤드의 실체**: Top-down 방식도 첫 번째 출발 지점에서 하위 경로를 한 번 계산하고 나면, 이후 지점부터는 $O(1)$로 값을 반환하므로 실질적인 연산량은 Bottom-up과 차이가 거의 없음을 확인함
2.  **본질은 중복 제거**: 방식의 차이는 있으나 핵심은 "이미 계산된 최적해를 어떻게 다시 꺼내 쓰느냐"로 동일함
3.  **시스템적 사고**: 시스템 내부(스택, GC, 메모리 구조)에서 코드가 어떻게 동작하는지 고민하는 과정을 코드로 확인함

---

### 📂 기록
*   [Detailed Review (Velog)](https://velog.io/@dong20/SWEA-19004-점프-놀이D4)

*   [Top-down (DFS + Memoization) 풀이 바로가기](./Solution_TopDown.java)
*   [Bottom-up (Iteration DP) 풀이 바로가기](./Solution_BottomUp.java)
*   [Bottom-up (Iteration DP) 풀이 바로가기](./Solution_BottomUp.c++)
 
---

### 🖥️ 실행 결과

![](./img/Solution_TopDown_java.png) <br> TopDown - Java <br> 
![](./img/Solution_BottomUp_java.png) <br> BottomUp - Java <br>
![](./img/Solution_BottomUp_c++.png) <br> BottomUp - c++

---