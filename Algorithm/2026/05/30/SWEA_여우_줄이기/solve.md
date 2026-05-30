### SWEA 25838 [여우 줄이기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AZtxnSyquD7HBIQE) (Java)

> **날짜:** 2026년 5월 30일 <br>
> **알고리즘:** 자료구조, Stack<br>
> **언어:**  Java <br>
> **핵심 키워드:** stack 

## 📌 문제 개요

* 길아 N인 알파벳 수문자로 이루어진 문자열에서 "fox" 부분 문자열을 지우는 것을 반복할 수 있다.
* 부분 문자열을 적절히 지원서 문자열의 길이를 최소화 할 때, 문자열의 길이를 출력한다.

---

## 💡 풀이 핵심 (Core Logic)

* **Stack의 특징 활용:** Stack 라이브러리는 내부적으로 Array형태를 사용하고 있습니다. 따라서 Queue나 Deque와는 다르게 배열 처럼 idx 접근이 가능하고, 이를 활용하면 fox 문자열을 쉽게 탐색할 있습니다.

---

## 🛠 성능 최적화 인사이트 (Performance Insights)

* **Stack 직접 구현:** Stack을 사용하게 되면 pop 연산을 수행해야 하지만, 배열을 활용해 직접 구현하면 pop 연산 대신 idx를 줄이는 방식으로 연산량을 줄일 수 있습니다.

---

## 🧠 회고 및 결론

백준의 문자열 폭발 문제의 하위 난이도다. Stack의 원리를 활용하지 않는다면 시간초과가 발생하는 것이 특징이다.

---

### 📂 소스 코드
*   [Solution](./Solution.java)
 
---

### 🖥️ 실행 결과

![](./img/Solution.png)

---