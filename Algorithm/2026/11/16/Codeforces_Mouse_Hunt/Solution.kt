import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine()!!.toInt()

    val cost = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    val next = readLine()!!.split(" ").map { it.toInt() - 1 }.toIntArray()
    val visited = IntArray(n)
    var res = 0

    for (i in 0..<n) {
        if (visited[i] == 0) {
            val node = checkCycle(next, visited, i, i + 1)

            if (node != -1) {
                res += getMinCost(next, cost, node)
            }
        }
    }

    print(res)
}

fun checkCycle(next: IntArray, visited: IntArray, node: Int, mark: Int): Int {
    var idx = node

    while (visited[idx] == 0) {
        visited[idx] = mark
        idx = next[idx]
    }

    return if (visited[idx] == mark) idx else -1
}

fun getMinCost(next: IntArray, cost: IntArray, node: Int): Int {
    var res = cost[node]
    var idx = next[node]

    while (idx != node) {
        res = min(res, cost[idx])
        idx = next[idx]
    }

    return res
}
