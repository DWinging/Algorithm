fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map {it.toInt()}

    val map: Array<String> = Array(n) { readLine() }

    val (y, x) = findStartPoint(map, n, m)
    print(solve(map, n, m, y, x))
}

fun findStartPoint(map: Array<String>, n:Int, m: Int): Pair<Int, Int> {
    for(y in 0..<n) {
        for(x in 0..<m) {
            if(map[y][x] == 'E') return Pair(y, x)
        }
    }

    return Pair(-1, -1)
}

fun solve(map: Array<String>, n: Int, m:Int, sy: Int, sx: Int): Int {
    val visited: Array<BooleanArray> = Array(n) { BooleanArray(m) }
    visited[sy][sx] = true

    val que = IntArray(n * m)
    var left = 0
    var right = 0
    que[right++] = (sy shl 10) or sx

    val dy = intArrayOf(1, -1, 0, 0)
    val dx = intArrayOf(0, 0, 1, -1)

    var res = 0

    while(left < right) {
        val len = right - left
        var flag = false

        repeat(len) {
            val cur = que[left++]
            val cy = cur shr 10
            val cx = cur and ((1 shl 10) - 1)

            for (i in 0..<4) {
                val ny = cy + dy[i]
                val nx = cx + dx[i]

                if(check(ny, nx, n, m) && !visited[ny][nx] && map[ny][nx] != 'T') {
                    que[right++] = (ny shl 10) or nx
                    visited[ny][nx] = true

                    if(map[ny][nx] == 'S') flag = true
                    else res += map[ny][nx] - '0'
                }
            }
        }

        if(flag) break
    }

    return res
}

fun check(y: Int, x: Int, n: Int, m: Int): Boolean
    = y in 0..<n && x in 0..<m