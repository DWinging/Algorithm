const val INF = 200_000

fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val t = readLine().toInt()

    repeat(t) {
        val n = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }.toIntArray()
        
        val dp = IntArray(n + 1) {INF + 1}
        val best = IntArray(n + 1) {INF + 1}
        dp[0] = 0

        for(i in 1..n) {
            val v = arr[i - 1]
            dp[i] = minOf(dp[i - 1] + 1, best[v])
            best[v] = minOf(dp[i - 1], best[v])
        }

        sb.append(n - dp[n]).append('\n')
    }

    print(sb)
}