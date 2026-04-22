/**
 * [BOJ] 21318 - 피아노 체조
 * - 제출 날짜: 2026년 4월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 84648 KB
 * - 시간: 652 ms
 */

import java.io.BufferedReader

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val sheetMusic = readLine().split(" ").map {it.toInt()}.toIntArray()
    val dp = searchMistakePoint(n, sheetMusic)
    val testCase = readLine().toInt()
    print(printResult(testCase, dp, this))
}

fun searchMistakePoint(n: Int, sheetMusic: IntArray) : IntArray {
    val dp = IntArray(n)
    dp[0] = 0
    for(note in 1 until n) {
        dp[note] = dp[note - 1]
        if(sheetMusic[note-1] > sheetMusic[note]) {
            dp[note]++
        }
    }
    return dp
}

fun printResult(testCase: Int, dp: IntArray, br: BufferedReader): String =
    buildString {
        repeat(testCase) {
            val (s, e) = br.readLine().split(" ").map {it.toInt() - 1}
            appendLine(dp[e] - dp[s])
        }
    }