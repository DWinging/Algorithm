import java.util.*

data class Node(val y: Int, val x: Int, val dir: Int, val cnt: Int, val total: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine()!!.split(' ').map {it.toInt()}

    val map = Array(n) { readLine()!! }

    val (y, x) = findStart(map, n, m)

    println(bfs(map, n, m, y, x))
}

fun findStart(map: Array<String>, n: Int, m: Int) : Pair<Int, Int>{
    for(y in 0..<n ){
        for(x in 0..<m) {
            if(map[y][x] == 'S') return Pair(y, x)
        }
    }

    return Pair(-1, -1)
}

fun bfs(map: Array<String>, n: Int, m: Int, y: Int, x: Int): Int {
    val dist = arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, -1),
        intArrayOf(1, 0),
        intArrayOf(-1, 0)
    )

    val visited = Array(n) { Array(m) { IntArray(4) { 4 } } }
    repeat(4) { it ->
        visited[y][x][it] = 0
    }

    val que: Queue<Node> = ArrayDeque()
    que.add(Node(y, x, -1, 0, 0))

    while(que.isNotEmpty()) {
        val cur = que.poll()
        val cy = cur.y
        val cx = cur.x
        val dir = cur.dir
        val cnt = cur.cnt
        val total = cur.total

        if(map[cy][cx] == 'T') return total

        repeat(4) { it ->
            val ny = cy + dist[it][0]
            val nx = cx + dist[it][1]
            val nc = if(dir == it) cnt + 1 else 1

            if(map[ny][nx] != '#' && visited[ny][nx][it] > nc) {
                que.add(Node(ny, nx, it, nc, total + 1))
                visited[ny][nx][it] = nc
            }
        }
    }

    return -1
}