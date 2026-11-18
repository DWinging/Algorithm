import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toInt()

    val arr = Array(n + 1) { mutableListOf<Int>() }
    repeat(n - 1) {
        val (u, v) = readLine()!!.split(" ").map { it.toInt() }
        arr[u].add(v)
        arr[v].add(u)
    }
    arr[1].add(0)

    val route = readLine()!!.split(" ").map {it.toInt()}.toIntArray()
    print(solve(arr, route, n, 1))
}

fun solve(arr: Array<MutableList<Int>>, route: IntArray, n: Int, start: Int): String {
    if(route[0] != 1) return "No"

    val que: Queue<Int> = ArrayDeque()
    que.add(start)

    val visited = BooleanArray(n + 1)
    visited[start] = true

    var idx = 1

    while(que.isNotEmpty()) {
        val cur = que.poll()
        val len = arr[cur].size - 1

        for(child in arr[cur]) {
            visited[child] = true
        }

        repeat(len) {
            val next = route[idx++]
            if(!visited[next]) return "No"
            que.add(next)
        }
    }

    return "Yes"
}