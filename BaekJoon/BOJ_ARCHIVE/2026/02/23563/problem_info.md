# [벽 타기] 23563

## 📊 문제 정보
| 항목 | 내용 |
| :--- | :--- |
| 티어 | Gold III |
| 시간 제한 | 1 초 |
| 메모리 제한 | 256 MB |
| 알고리즘 | 그래프 이론, 최단 경로, 데이크스트라, 격자 그래프, 0-1 너비 우선 탐색 |
| 링크 | [백준 바로가기](https://www.acmicpc.net/problem/23563) |

---

## 📜 문제 설명

<p>루시우는 높이가 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43B TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>H</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$H$</span></mjx-container>이고 너비가 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D44A TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>W</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$W$</span></mjx-container>인 맵의 시작점에서 끝점까지 이동하려고 한다.</p>

<ul>
	<li>맵은 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43B TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>H</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$H$</span></mjx-container>개의 행과&nbsp;<mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D44A TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>W</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$W$</span></mjx-container>개의 열로 이루어진 격자판 모양이다. 각 칸은 벽 또는 빈칸이다.</li>
	<li>루시우는 상, 하, 좌, 우 방향&nbsp;인접한 칸으로&nbsp;한 칸씩 이동할 수 있다. 벽으로는 이동할 수 없다.</li>
	<li>루시우가 한 칸을 이동하는 데에는&nbsp;1초가 걸린다.</li>
	<li>하지만 루시우가 <strong>벽을 타고 이동</strong>하면&nbsp;순식간에 (0초의 시간에) 상, 하, 좌, 우 방향 인접한 칸으로 이동할 수 있다.
	<ul>
		<li>어떤 빈칸의 상하좌우 중 하나가 벽이면 이 칸은 <strong>벽에 인접한 칸</strong>이라고 한다.</li>
		<li>벽에 인접한 칸에서 벽에 인접한 칸으로 이동하면 <strong>벽을 타고 이동</strong>한다고 말한다.</li>
	</ul>
	</li>
</ul>

<p>루시우가 맵의 시작점에서 끝점까지 이동하는 데 걸리는 최소 시간을 구하여라.</p>

### 📥 입력

<p>첫째 줄에는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43B TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>H</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$H$</span></mjx-container>와 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D44A TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>W</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$W$</span></mjx-container>가 공백을 사이에 두고 주어진다. 맵은 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43B TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>H</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$H$</span></mjx-container>개의 행과&nbsp;<mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D44A TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>W</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$W$</span></mjx-container>개의 열로 이루어진 격자판 모양이다.</p>

<p>둘째 줄부터, <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D43B TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>H</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$H$</span></mjx-container>개의 줄에 걸쳐서&nbsp;맵의 모습을&nbsp;나타내는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D44A TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>W</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$W$</span></mjx-container>개의 문자가 주어진다.</p>

<ul>
	<li><code>#</code>는 벽을 뜻한다.</li>
	<li><code>.</code>는 빈칸을 뜻한다.</li>
	<li><code>S</code>는 맵의&nbsp;시작점을 뜻한다. 시작점은 빈칸이다.</li>
	<li><code>E</code>는 맵의 끝점을 뜻한다. 끝점은 빈칸이다.</li>
</ul>

### 📤 출력

<p>루시우가 맵의 시작점에서 끝점까지 이동하는 데 걸리는 최소 시간을 출력하라.</p>

---

## 💡 예제

### 예제 1
**Input:**
```text
5 5
#####
#..E#
#.S.#
#...#
#####
```
**Output:**
```text
1
```

### 예제 2
**Input:**
```text
10 10
##########
#........#
#...#....#
#........#
#.E....S.#
#........#
#........#
##.......#
#........#
##########
```
**Output:**
```text
2
```

---

## 📜 나의 제출 기록

| 제출 번호 | 결과 | 메모리 | 시간 | 언어 | 제출 일자 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 102572165 | ✅ 맞았습니다!! | 15040 KB | 160 ms | Java 8 / 수정 | 2026년 2월 3일 |
| 102571785 | ✅ 맞았습니다!! | 24472 KB | 308 ms | Java 8 / 수정 | 2026년 2월 3일 |
| 102571707 | ✅ 맞았습니다!! | 26264 KB | 320 ms | Java 8 / 수정 | 2026년 2월 3일 |

