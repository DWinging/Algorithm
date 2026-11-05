import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val t = readLine()!!.toInt()

    repeat(t) {
        val input = readLine()!!.split(" ")
        val n = input[0].toInt()
        val s = input[1].toLong()

        val arr = Array(n) {
            readLine()!!.split(" ").map { it.toLong() }.toLongArray()
        }

        arr.sortWith { a, b -> b[0].compareTo(a[0]) }

        var left = 0L
        var right = s
        var res = 0L
        val half = n / 2 + 1
        while(left <= right) {
            val mid = (left + right) / 2

            if(solve(arr, mid, s, half)) {
                left = mid + 1
                res = mid
            } else {
                right = mid - 1
            }
        }

        sb.append("$res\n")
    }

    print(sb.toString())
}

fun solve(
    arr: Array<LongArray>,
    mid: Long,
    s: Long,
    half: Int
): Boolean {
    var sum = 0L
    var cnt = 0

    for (cur in arr) {
        if (cnt < half && cur[1] >= mid) {
            sum += maxOf(cur[0], mid)
            cnt++
        } else {
            sum += cur[0]
        }
    }

    return cnt == half && sum <= s
}