import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val t = readLine()!!.toInt()

    repeat(t) {
        val (n, k) = readLine()!!.split(' ').map { it.toInt() }
        val arr = Array(3) { readLine()!! }

        var s = 0
        for (i in 0 until 3) {
            if (arr[i][0] == 's') {
                s = i
                break
            }
        }

        sb.append(bfs(arr, n, s)).append("\n")
    }

    print(sb)
}

fun bfs(arr: Array<String>, n: Int, s: Int): String {
    val que: Queue<Pair<Int, Int>> = ArrayDeque()
    val visited = Array(3) { BooleanArray(n) }
    val dir = intArrayOf(-1, 0, 1)

    que.add(Pair(s, 0))
    visited[s][0] = true

    while (que.isNotEmpty()) {
        val (cy, cx) = que.poll()

        if (cx + 1 >= n) return "YES"
        if (arr[cy][cx + 1] != '.') continue

        val nx = cx + 3

        for (d in dir) {
            val ny = cy + d

            if (ny !in 0..2) continue
            if (!checkTrain(arr, ny, cx + 1, n)) continue

            if (nx >= n) return "YES"

            if (!visited[ny][nx]) {
                visited[ny][nx] = true
                que.add(Pair(ny, nx))
            }
        }
    }

    return "NO"
}

fun checkTrain(arr: Array<String>, y: Int, x: Int, n: Int): Boolean {
    for (i in 0..2) {
        val nx = x + i
        if (nx < n && arr[y][nx] != '.') return false
    }
    return true
}
