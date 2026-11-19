fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val tc = readLine()!!.toInt()

    repeat(tc) {
        val (n, s) = readLine()!!.split(' ').map {it.toInt()}
        val arr = readLine()!!.split(' ').map {it.toLong()}.toLongArray()

        sb.append(solve(arr, n, s)).append("\n")
    }

    print(sb.toString())
}

fun solve(arr: LongArray, n: Int, s: Int): String {
    var res = 0
    var left = 0
    var right = 0
    var idx = 0
    var sum = s.toLong()

    for(end in 0..<n) {
        sum += arr[end]
        while(idx <= end && sum < 0) {
            sum -= arr[idx++]
        }

        if(sum >= 0 && res < end - idx + 1) {
            left = idx + 1
            right = end + 1
            res = end - idx + 1
        }
    }

    return if (res == 0) "-1" else "$left $right"
}