const val INF = 1_000_000_000

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val t = readLine().toInt()

    repeat(t) {
        val n = readLine().toInt()

        val arr = Array(n) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        val res = binarySearch(arr, n)
        sb.append("$res\n")
    }

    print(sb)
}

fun binarySearch(arr: Array<IntArray>, n: Int): Int {
    var left = 0
    var right = INF
    var res = INF

    while (left <= right) {
        val mid = left + (right - left) / 2

        if (solve(arr, n, mid)) {
            res = mid
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    return res
}

fun solve(arr: Array<IntArray>, n: Int, mid: Int): Boolean {
    var min = 0L
    var max = 0L
    val k = mid.toLong()

    for (i in 0..<n) {
        min = maxOf(min - k, arr[i][0].toLong())
        max = minOf(max + k, arr[i][1].toLong())

        if (min > max) return false
    }

    return true
}