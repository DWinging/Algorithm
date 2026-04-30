/**
 * [BOJ] 25601 - 자바의 형변환
 * - 제출 날짜: 2026년 4월 8일
 * - 결과: 맞았습니다!!
 * - 메모리: 73996 KB
 * - 시간: 536 ms
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val tree = hashMapOf<String, String>()
    repeat(n - 1) {
        readLine().split(" ").let { tree[it.first()] = it.last() }
    }

    val (class1, class2) = readLine().split(" ")
    val result = if (isFromChange(tree, class1, class2) || isFromChange(tree, class2, class1)) 1 else 0
    print(result)
}

fun isFromChange(tree: HashMap<String, String>, c1: String, c2: String): Boolean {
    var key = c1
    while(tree.containsKey(key)) {
        if(tree.getValue(key) == c2) return true
        key = tree.getValue(key)
    }
    return false
}