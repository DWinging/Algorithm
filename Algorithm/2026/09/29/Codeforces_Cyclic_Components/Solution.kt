import java.util.ArrayDeque

fun main() = with(java.lang.System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map {it.toInt()}
    val graph = Array(n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (v, u) = readLine().split(" ").map {it.toInt()}
        graph[v].add(u);
        graph[u].add(v);
    }

    val visited = BooleanArray(n + 1)
    var cnt = 0;

    for(i in 1..n) {
        if(!visited[i]) {
            cnt += bfs(graph, visited, i)
        }
    }

    print(cnt)
}

fun bfs(
    graph: Array<MutableList<Int>>,
    visited: BooleanArray,
    start: Int
): Int {
    val que = java.util.ArrayDeque<Int>()
    que.add(start)

    visited[start] = true

    var cycle = true
    while(que.isNotEmpty()) {
        val cur = que.removeFirst();
        if(graph[cur].size != 2) cycle = false

        for(next in graph[cur]) {
            if(!visited[next]) {
                que.add(next)
                visited[next] = true
            }
        }
    }

    return if (cycle) 1 else 0
}