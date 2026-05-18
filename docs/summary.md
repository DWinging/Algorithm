# 📅 Algorithm Solve Log

### 🚀 2026-05-18 | Lv3 [코딩 테스트 공부](https://school.programmers.co.kr/learn/courses/30/lessons/118668)

> 플랫폼: 프로그래머스 <br>
> 알고리즘: DP(동적 계획법), 다익스트라

**💡 풀이 핵심(Core Logic):**
*  **문제의 핵심 파악하기:** 
*  모든 문제를 풀 수 있는 알고력과 코딩력을 얻는 최단시간을 return해야함
*  모든 문제를 풀 필요가 없고, 알고력과 코딩력이 일정 수준에 도달하는 시간을 구하는 문제
*  다익스트라와 DP 풀이 방식이 있음

**🔗 기록:** [github](../Algorithm/2026/05/18/프로그래머스_코딩_테스트_공부/)

---

### 🚀 2026-05-17 | D4 [트리 바꾸기](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AZyNVrgKAVXHBIRj&categoryId=AZyNVrgKAVXHBIRj&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1) (26390)

> 플랫폼: SWEA <br>
> 알고리즘: 트리

**💡 풀이 핵심(Core Logic):**
*  **문제의 핵심 파악하기:** 
*  N개의 정점과 N-1개의 간선을 가진 **연결 그래프(트리)**가 주어짐 => 혼자서 따로 떨어진 노드는 존재하지 않음
*  필요한 작업 횟수의 최솟값을 구해야 함 => 트리를 하나의 체인으로 바꿀 필요는 없음
*  **작업의 특징 파악하기:**
*  이미 연결된 X, Y를 골라 X와 Y사이의 간선을 끊고, X에 연결되어 있지 않은 정점 Z를 골라 X와 Z를 연결함
*  1번의 작업을 수행하더라도, X에서 연결된 간선의 수는 유지된다는 사실을 파악해야 함

**🔗 기록:** [github](../Algorithm/2026/05/17/SWEA_트리_바꾸기/)

---

### 🚀 2026-05-15 | Lv4 [행렬과 연산](https://school.programmers.co.kr/learn/courses/30/lessons/118670)

> 플랫폼: 프로그래머스 <br>
> 알고리즘: Deque

* **🔧 트러블 슈팅:**

* **인덱스 관리:**
* Deque 자료 구조를 사용하면 쉽게 풀 수 있지만, Deque 대신 Array를 사용한 풀이를 시도함
* 단순하게 생각했지만, midDeque를 각각의 Head와 Tail을 관리해야한다는 점을 놓치면서 풀이에 실패함


**🔗 기록:** [github](../Algorithm/2026/05/14/프로그래머스_가장_긴_팰린드롬/)

---


### 🚀 2026-05-14 | Lv3 [가장 긴 팰린드롬](https://school.programmers.co.kr/learn/courses/30/lessons/12904)

> 플랫폼: 프로그래머스 <br>
> 알고리즘: DP(동적 계획법)

** 💡 풀이 핵심 (Core Logic)**

* **DP 상태 정의:** `dp[i][j]`를 길이가 `i`이고 시작 인덱스가 `j`인 부분 문자열의 팰린드롬 여부로 정의.

<br>

* **기저 상태(Base Case) 통합:**
* 길이가 1인 경우(`dp[1][i]`)는 무조건 참.
* 길이가 0인 경우(`dp[0][i]`)를 참으로 두어, 길이가 2인 문자열이 들어왔을 때 별도 분기 없이 `dp[i-2][j+1]` 참조로 통과되도록 설계.

<br>

* **점화식:** `s[j] == s[j+i-1] && dp[i-2][j+1]`
* 현재 양 끝 문자가 같고, 그 사이 구간(길이가 2 짧고 시작점이 1 큰 구간)이 팰린드롬이면 현재 구간도 팰린드롬.

<br>

* **일관성:** 이중 루프 하나로 길이 2부터 `len`까지 모든 케이스를 예외 없이 처리하여 가독성과 논리적 완결성을 높임.
  
**🔗 기록:** [github](../Algorithm/2026/05/14/프로그래머스_가장_긴_팰린드롬/)

---

### 🚀 2026-05-13 | D6 [인수의 생일 파티](https://school.programmers.co.kr/learn/courses/30/lessons/68646)

> 플랫폼: SWEA <br>
> 알고리즘: Dijkstra, SPFA

**💡 핵심 인사이트:**

* **Dijkstra (PQ):** $O(E \log V)$. 매 갱신마다 힙(Heap) 재배치 비용이 발생하며, 정점 전체를 동기화해야 하는 상황에서는 이 정렬 세금이 성능 병목의 원인이 됨. (161ms)
* **SPFA (Deque):** 평균 $O(V+E)$. 정렬 없이 $O(1)$의 Deque 연산만 수행. '전파' 특성이 강한 문제 데이터에서 다익스트라 대비 약 40%의 시간 및 20%의 메모리 절감 효과를 보임. (95ms)
* **Early Exit (조기 종료):** `total < n` 조건을 통해 모든 노드의 거리가 확정된 순간 루프를 탈출함. 큐에 잔류한 중복 노드들에 의한 불필요한 힙 연산과 탐색을 물리적으로 차단함.
* **Modularity (캡슐화):** 로직과 상태를 클래스로 격리하여 설계의 밀도를 높임. 유연한 구조 덕분에 코드 전면 수정 없이 내부 엔진(PQ ↔ Deque)만 교체하는 전략적 성능 최적화가 가능했음.
  
**🔗 기록:** [github](../Algorithm/2026/05/13/SWEA_인수의_생일_파티/) | [velog](https://velog.io/@dong20/Java-SWEA-인수의-생일-파티)

---

### 🚀 2026-05-12 | Lv3 [풍선 터트리기](https://school.programmers.co.kr/learn/courses/30/lessons/68646)

> 플랫폼: 프로그래머스 <br>
> 알고리즘: Stack

**💡 핵심 인사이트:**

* **정렬 + Lazy Delete:** : $O(NlonN)$의 시간 복잡도를 가짐. Lazy Delete 방식을 적용하기 떄문에 반복적인 if 조건 분기나 별도의 상태 추적 로직이 필요함.
* **PriorityQueue(우선 순위 큐):** 시간복잡도가 $O(NlogN)$에 수렴함. 전체 배열 정렬 방식보다 논리적으로 유리하지만, 최악의 경우 차이가 없음
* **Stack(정적 배열 제어):** $O(N)$ (배열을 한 번 순회하며 전처리)의 시간복잡도를 가짐. int[] 정적배열과 top 포인터만 사용하여 메모리를 제어하며, 현재 값과 `stack[top]` 단 1번의 비교 연산만으로 로직이 수행하여 오버헤드가 줄어듬
  
**🔗 기록:** [github](../Algorithm/2026/05/12/프로그래머스_풍선_터트리기/) | [velog](https://velog.io/@dong20/Java-프로그래머스-풍선-터트리기)


---

### 🚀 2026-05-11 | Medium [검색어와 접두사](https://www.codetree.ai/ko/trails/complete/curated-cards/challenge-search-terms-and-prefixes/description) 

> 플랫폼: 코드 트리 <br>
> 알고리즘: 트라이

**💡 핵심 인사이트:**

* **메모리 사용량 절감:** 메모리 낭비가 심한 2차원 배열 기반 트라이가 아닌 1차원 배열 기반 트라이인 LCRS를 구현함
* **CPU 캐시 지역성 극대화:** LCRS 트라이는 메모리 상의 데이터가 연속으로 배치함으로써 캐시 히트율을 비약적으로 상승시키면서 빠르고 안정적인 속도를 보여줌
* **확장성:** 문자의 종류나 길이에 영향을 받지 않는 LCRS를 구현하여 확장성을 높임
  
**🔗 기록:** [github](../Algorithm/2026/05/11/코드트리_검색어와_접두사/) | [velog](https://velog.io/@dong20/Java-코드트리-검색어와-접두사)

---

### 🚀 2026-05-10 | D5 [홍준이의 사전 놀이](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV_6pTXqsXUDFAWS&categoryId=AV_6pTXqsXUDFAWS&categoryType=CODE&problemTitle=%EC%82%AC%EC%A0%84&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)

> 플랫폼: SWEA <br>
> 알고리즘: 트라이

**💡 핵심 인사이트:**

* **2차원 정적 배열 기반 Trie:** `trie[MAX_NODE][26]` 배열을 선언하여, 각 노드에서 다음 알파벳으로 이동하는 경로를 인덱스 참조($O(1)$)로 즉시 찾아가도록 설계했습니다.
* **실시간 접두사 카운팅:** 단어를 삽입할 때 트리를 내려가면서 거쳐가는 모든 노드의 `count` 값을 `+1` 해줍니다. 이렇게 하면 `query` 시 해당 접두사의 마지막 노드에 저장된 값을 읽기만 하면 됩니다.
  
**🔗 기록:** [github](../Algorithm/2026/05/10/SWEA_홍준이의_사전놀이/)

---

### 🚀 2026-05-09 | D4 [서로소 집합](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr&categoryId=AWBJKA6qr2oDFAWr&categoryType=CODE&problemTitle=%EC%84%9C%EB%A1%9C%EC%86%8C&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)

> 플랫폼: SWEA <br>
> 알고리즘: 분리 집합, Union-Find

**💡 핵심 인사이트:**

* **경로 압축을 통한 연산 최적화 (O(α(N))):** `find` 수행 시 부모 노드를 루트 노드로 즉시 갱신하는 경로 압축(Path Compression) 기법을 적용하여, 비선형 트리 구조를 평탄화함으로써 연산당 시간 복잡도를 애커만 역함수 수준으로 수렴시켜 탐색 효율을 극대화합니다.
* **반복문 기반의 비재귀 구현 (Iterative Stability):** 재귀 호출에 따른 시스템 스택 오버헤드 및 메모리 제한 리스크를 제거하기 위해 반복문(While-loop)으로 `find` 로직을 설계함으로써, 대규모 데이터셋 처리 환경에서도 일관된 성능과 구조적 안정성을 확보합니다.
  
**🔗 기록:** [github](../Algorithm/2026/05/09/SWEA_서로소_집합/)

---

### 🚀 2026-05-08 | mid [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/)

> 플랫폼: LeetCode <br>
> 알고리즘: 트리, 분할 정복, 재귀


**💡 핵심 인사이트:**
* **정교한 인덱스 관리:** 배열 복사(substring, Slicing)를 배제하고 인덱스(Start, End)만으로 구간을 정의하는 방식은 성능상 이점이 크지만, 오프셋 계산에서 한 치의 오차도 허용하지 않는 정밀한 설계 능력을 요구합니다.
* **최적화의 기술 (O(N)):** inorder 배열의 root를 탐색하는 과정에서 HashMap을 활용하면 편향트리에서도 안정적인 성능을 기대할 수 있습니다.

**🔧 트러블 슈팅:**
* **변수명 오타로 인한 런타임 에러 (s vs e):** C++ 리팩토링 중 왼쪽 서브트리의 구간을 설정할 때, 시작점(sPre)이 아닌 끝점(ePre)을 기준으로 계산식을 작성하여 범위를 이탈하는 버그가 발생함.
* **중복 탐색의 비효율 제거:** 초기 설계에서 매 재귀마다 inorder 배열을 순차 탐색하던 로직을 HashMap 기반 인덱싱으로 전환함. "고유값(Unique Values)"이라는 문제의 전제 조건을 활용해 시간 복잡도를 $O(N^2)$에서 $O(N)$으로 끌어올려 성능 안정성을 확보함.
  
**🔗 기록:** [github](../Algorithm/2026/05/08/leetCode_Construct%20Binary%20Tree%20from%20Preorder%20and%20Inorder%20Traversal/)

---

### 🚀 2026-05-07 | Lv3 [카드 짝 맞추기](https://school.programmers.co.kr/learn/courses/30/lessons/72415?language=java)

> 플랫폼: 프로그래머스 <br>
> 알고리즘: 시뮬레이션, BFS, DFS


**💡 핵심 인사이트 (함수 모듈화의 역설):**
* **결합도를 낮추는 타협점:** 중복 코드를 없애기 위해 무작정 함수를 분리하면, 분리된 함수들이 전역 상태를 변경하며 꼬리를 무는 **'함수 체인(Function Chain)'**이 발생해 결합도가 기형적으로 높아집니다.
* **흐름의 가시화:** 모든 중복을 제거(DRY)하는 것보다, `Card` 객체에게 상태 제어를 위임하여 **백트래킹의 핵심 흐름(상태 변경 $\rightarrow$ 재귀 $\rightarrow$ 복구)을 DFS 루프 내부에 직관적으로 노출**시키는 것이 유지보수성과 설계 측면에서 훨씬 유리함을 체감했습니다.

**🔧 트러블 슈팅:**
* **카드 방문 순서 미지정 (Stack Overflow):** 동일한 번호의 카드 2장 중 어느 카드를 먼저 방문할지 순서를 강제하지 않아 탐색이 방향성을 잃는 문제가 발생함. DFS가 중복된 상태를 파고들며 제자리를 맴돌아 스택 오버플로우가 발생했으며, 1회 탐색 스텝에서 (A $\rightarrow$ B), (B $\rightarrow$ A) 경로를 명확히 강제하여 탐색 방향성을 확보함.
* **Ctrl 이동의 정지 조건 디테일 (미끄러짐 버그):** `Ctrl + 방향키` 이동 시, "카드 위에서 멈춘다"는 조건을 한 칸 이동한 *후*에 검사하여 바로 앞의 카드를 미끄러져 지나치는 버그가 발생함. `while` 루프 최상단에서 현재 위치의 카드 존재 여부를 *먼저* 체크하고 멈추도록 로직의 검사 순서를 교정함.
  
**🔗 기록:** [github](../Algorithm/2026/05/07/카드_짝_맞추기/) | [velog](https://velog.io/@dong20/Java-프로그래머스-카드-짝-맞추기)

---

### 🚀 2026-05-06 | Lv3 [표현 가능한 이진 트리](https://school.programmers.co.kr/learn/courses/30/lessons/150367?language=cpp#)

> 플랫폼: 프로그래머스 <br>
> 알고리즘: 분할 정복, 비트 마스킹

**🔧 트러블 슈팅:**
* **트리의 크기(비트 수) 산정 오류:** 초기에 변수의 비트 크기를 1, 2, 3, 4... 씩 선형적으로 탐색하려 했으나, 포화 이진 트리가 형성되지 않는 경우가 발생함. 비트 사이즈를 $2^n$-1 규격에 맞추어 트리의 중앙을 보장하도록 사이즈 산출 로직을 교정함
* **재귀 함수 기저 조건 누락:** 문제 로직 특성상 별도의 기저 조건이 없어도 무한 루프가 발생하지 않지만, 위험성을 내포한 코드로 `if(bit_count <= 1) return 1;` 기저 조건을 추가함

**🔗 기록:** [github](../Algorithm/2026/05/06/표현_가능한_이진트리/) | [velog](https://velog.io/@dong20/c-프로그래머스-표현-가능한-이진-트리)

---

### 🚀 2026-05-05 | D6 [영준이의 무게측정](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LeQJqDpwDFAXc) (1849)

> 플랫폼: SWEA <br>
> 알고리즘: Union-Find, 가중치 분리 집합 (Weighted DSU)

**🔧 트러블 슈팅:**
* **경로 압축 시점의 논리적 오류:** Weighted Union-Find에서 가중치를 갱신하는 타이밍이 중요함. Root를 우선 탐색한 후 스택을 활용해 역으로 내려오면서(Top-Down)으로 갱신해야 정확하게 가중치를 갱신할 수 있음

**🔗 기록:** [github](../Algorithm/2026/05/05/영준이의_무게측정/) | [velog](https://velog.io/@dong20/c-SWEA-영준이의-무게측정)

---

### 🚀 2026-05-04 | D5 [영어 공부](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AXNQOb3avD0DFAXS&categoryId=AXNQOb3avD0DFAXS&categoryType=CODE&problemTitle=%EC%98%81%EC%96%B4&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1) (10507)

> 플랫폼: SWEA <br>
> 알고리즘: Two-Pointer

**💡 핵심 인사이트:**
*  **미래 예측 방식:** right + 1을 참조해 다음 칸의 상태를 미리 검증한 뒤 포인터를 이동시키는 방식. 배열 경계 초과를 막기 위한 수동 예외 처리가 필요해 제어 흐름이 복잡해지고 유지 보수성이 떨어지는 한계가 있음
*  **현재 수습 방식:** for문으로 right를 먼저 전진시켜 상태를 갱신하고, 한도 초과시 while문으로 left를 당겨 정상 상태로 복구(수습)하는 선언적 방식. 예외 상황이 일반 로직에 자연스럽게 흡수되어 가독성과 코드의 견고함이 크게 향상됨
*  **c++ 메모리 관리**: 유효 범위를 벗어난 인덱스(date[n]) 참조 시 런타임 에러를 발생시키는 Java와 달리, C++은 쓰레기 값을 반환하며 로직을 강행하는 Undefined Behavior 특성이 있음. 우연히 정답 처리되었더라도 메모리를 오염시킬 수 있는 시한폭탄이므로, 환경에 구애받지 않는 안전한 경계 처리가 필수적임

**🔗 기록:** [github](../Algorithm/2026/05/04/영어_공부/) | [velog](https://velog.io/@dong20/c-SWEA-영어-공부)

---

### 🚀 2026-05-02 | Easy [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/description/)

> 플랫폼: LeetCode <br>
> 알고리즘: Merge Sort, LinkedList, Two Pointer

**🔧 트러블 슈팅:**
*   **객체 참조 오류 및 데이터 유실 방지**: `cur->next = list1->next`와 같이 다음 노드를 직접 연결하던 실수를 교정하여, 현재 비교 중인 노드 자체가 결과 리스트에서 누락되지 않도록 **포인터 할당 범위를 정확히 타겟팅**함.
*   **경계 조건(Edge Case) 및 런타임 에러 제어**: `node->next` 참조 방식의 루프 종료 조건이 유발하는 마지막 노드 누락과 빈 리스트(`nullptr`) 입력 시의 세그멘테이션 폴트(Segmentation Fault)를 방지하기 위해, **노드 자체의 유효성 검증 방식**으로 로직을 개선하여 안정성을 높임.
*   **잔여 노드 처리의 시간 복잡도 최적화**: 모든 노드를 하나씩 다시 연결하던 불필요한 반복문을 제거하고, 연결 리스트의 기하학적 특성을 활용해 **남은 리스트의 헤드만 한 번에 연결($O(1)$)**함으로써 불필요한 연산 사이클과 잠재적인 무한 루프 위험을 동시에 해결함.
  
**🔗 기록:** [github](../Algorithm/2026/05/02/LeetCode_MergeTwoSortedLists/)

---

### 🚀 2026-05-01 | Lv3 [경주로 건설](https://school.programmers.co.kr/learn/courses/30/lessons/67259) 

> 플랫폼: 프로그래머스 <br>
> 알고리즘: Dijkstra, 0-1BFS 응용

**💡 핵심 인사이트:**
*   **가중치 특성을 활용한 자료구조 최적화**: 가중치가 두 종류(100, 600)로 제한적인 데이터 도메인의 특성을 파악하여, 무거운 `PriorityQueue` 대신 `Deque`를 도입해 정렬 오버헤드를 완벽히 제거함.
*   **정렬 유지 vs 가벼운 갱신(Relaxation)의 트레이드오프**: 힙 정렬 비용을 지불하는 다익스트라 대신 일부 중복 탐색을 허용하는 전수 갱신 방식을 택하여, 실전 퍼포먼스를 최대 10배 향상시킴.
*   **JVM 오버헤드 최소화 (Low-level)**: 힙 재구조화 및 `Comparable` 인터페이스 기반의 객체 비교 연산 사이클을 걷어내어, CPU 캐시 적중률을 높이고 기계적 연산 효율을 극대화함.
  
**🔗 기록:** [github](../Algorithm/2026/05/01/프로그래머스_경주로건설/) | [velog](https://velog.io/@dong20/Java-프로그래머스-경주로-건설)

---

### 🚀 2026-04-30 | D4 [점프 놀이 (19004)](https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AYtrEOraDk0DFAR-&categoryId=AYtrEOraDk0DFAR-&categoryType=CODE&problemTitle=%EC%A0%90%ED%94%84+%EB%86%80%EC%9D%B4&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1)

> 플랫폼: SWEA <br>
> 알고리즘: DP + DFS (Sequential Pathfinding)

**💡 핵심 인사이트:**
*   **재귀의 첫 고생이 DP의 자산**: Top-down 방식도 첫 탐색 이후 메모이제이션을 통해 재귀 진입을 차단하므로, 실질 연산 횟수는 Bottom-up과 동일함.
*   **Static Linked List 구현**: `ArrayList`를 지양하고 `Point` 객체 내 `pre` 인덱스를 두어 정적 배열 기반의 연결 리스트를 직접 구현 (GC 부하 최소화).
*   **Low-level 최적화**: `System.in.read()` 기반 Fast I/O 및 삼항 연산자를 활용한 거리 계산 최적화 적용.
  
**🔗 기록:** [github](../Algorithm/2026/04/30/SWEA_19004/) | [velog](https://velog.io/@dong20/SWEA-19004-점프-놀이D4)

---
