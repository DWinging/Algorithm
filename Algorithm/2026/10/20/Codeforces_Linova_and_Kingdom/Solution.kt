import java.util.*

fun dfs(
    list: Array<MutableList<Int>>,
    route: Array<IntArray>,
    parents: IntArray,
    p: Int,
    idx: Int,
    depth: Int
): Int {
    parents[idx] = p
    route[idx][0] = depth

    for(child in list[idx]) {
        if(child == p) continue

        route[idx][1] += dfs(list, route, parents, idx, child, depth + 1)
    }
    return route[idx][1]
}

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine()!!.split(' ').map { it.toInt() }

    val list = Array(n + 1) { mutableListOf<Int>() }
    for (i in 1..<n) {
        val (u, v) = readLine()!!.split(' ').map { it.toInt() }
        list[u].add(v)
        list[v].add(u)
    }

    val parents = IntArray(n + 1) { it }

    val route = Array(n + 1) { _ -> intArrayOf(0, 1) }
    val temp = dfs(list, route, parents, 0, 1, 0)

    Arrays.sort(route, 1, n + 1) { a, b ->
        val scoreA = a[0] - a[1] + 1
        val scoreB = b[0] - b[1] + 1
        scoreB.compareTo(scoreA)
    }

    var res = 0L
    for(i in 1..k) {
        res += (route[i][0] - route[i][1] + 1).toLong()
    }

    println(res)
}