# [근성아 일하자] 32358

## 📊 문제 정보
| 항목 | 내용 |
| :--- | :--- |
| 티어 | Gold V |
| 시간 제한 | 1 초 |
| 메모리 제한 | 1024 MB |
| 알고리즘 | 구현, 자료구조, 정렬, 이분 탐색, 집합과 맵, 시뮬레이션, 트리를 사용한 집합과 맵, 두 포인터 |
| 링크 | [백준 바로가기](https://www.acmicpc.net/problem/32358) |

---

## 📜 문제 설명

<blockquote>
<p><em>근성은 나무에 관심이 많다.</em></p>
</blockquote>

<p>평소 나무를 깨끗이 관리해 온 근성은 그 능력을 인정받아 북구청 청소행정과에 근무하게 되었다. 어느 날, 민규는 근성을 일하게 하기 위해 나무가 있는 위치에 쓰레기를 버리려고 한다. 나무는 수직선과 같은 일직선상에 있고 근성의 현재 위치는 원점이다. 아래의 두 가지 쿼리를 수행할 때 근성의 총 이동거리를 구하는 프로그램을 작성하시오.</p>

<ul>
	<li> <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>1</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$1$</span></mjx-container> <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D465 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>x</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$x$</span></mjx-container> : 민규가 정수 좌표 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D465 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>x</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$x$</span></mjx-container>에 있는 나무에 쓰레기를 버린다.</li>
	<li> <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mn class="mjx-n"><mjx-c class="mjx-c32"></mjx-c></mjx-mn></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mn>2</mn></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$2$</span></mjx-container> : 근성은 현재 위치에서 시작하여 쓰레기가 있는 나무 중 가장 가까운 나무로 이동하여 쓰레기를 수거하고, 모든 쓰레기를 수거할 때까지 이 행동을 반복한다. 만약 현재 위치에서 가장 가까운 나무가 두 그루 이상이라면, 좌표가 가장 작은 나무로 이동한다.</li>
</ul>

### 📥 입력

<p>첫 번째 줄에 쿼리의 개수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>이 주어진다.</p>

<p>두 번째 줄부터 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>개의 줄에 걸쳐 본문에 주어진 것과 같은 형식의 쿼리가 한 줄에 하나씩 주어진다.</p>

### 📤 출력

<p>근성의 총 이동거리를 출력한다.</p>

---

## 💡 예제

### 예제 1
**Input:**
```text
7
1 4
1 2
1 -2
2
1 0
2
1 5
```
**Output:**
```text
12
```

---

## 📜 나의 제출 기록

| 제출 번호 | 결과 | 메모리 | 시간 | 언어 | 제출 일자 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 101675699 | ✅ 맞았습니다!! | 67068 KB | 496 ms | Java 8 / 수정 | 2026년 1월 7일 |
| 101675656 | ❌ 틀렸습니다 | - KB | - ms | Java 8 / 수정 | 2026년 1월 7일 |
| 101651049 | ❌ 틀렸습니다 | - KB | - ms | Java 8 / 수정 | 2026년 1월 6일 |

