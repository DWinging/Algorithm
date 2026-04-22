/**
 * [BOJ] 17069 - 파이프 옮기기 2
 * - 제출 날짜: 2026년 2월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 21260 KB
 * - 시간: 124 ms
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val house = Array(n) { readLine().split(" ").map { it.toLong() }.toLongArray()}
    val dp = setDP(n, house)
    print(getResult(dp, n))
}

fun getResult(dp: Array<Array<LongArray>>, n: Int): Long =
    dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2]


fun setDP(n: Int, house: Array<LongArray>): Array<Array<LongArray>> {
    val dp = Array(n) { Array(n) { LongArray(3) } }
    dp[0][1][0] = 1

    for(y in 0 until n) {
        for(x in 0 until n) {
            if (house[y][x] == 1L) continue
            if(x - 1 >= 0) {
                dp[y][x][0] += dp[y][x-1][0] + dp[y][x-1][2]
            }
            if(y - 1 >= 0) {
                dp[y][x][1] += dp[y-1][x][1] + dp[y-1][x][2]
            }
            if(x - 1 >= 0 && y - 1 >= 0 && house[y][x-1] == 0L && house[y-1][x] == 0L && house[y-1][x-1] == 0L) {
                dp[y][x][2] += dp[y-1][x-1][0] + dp[y-1][x-1][1] + dp[y-1][x-1][2]
            }
        }
    }
    return dp
}