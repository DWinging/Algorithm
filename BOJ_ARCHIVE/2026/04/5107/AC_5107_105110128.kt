/**
 * [BOJ] 5107 - 마니또
 * - 제출 날짜: 2026년 4월 21일
 * - 결과: 맞았습니다!!
 * - 메모리: 16068 KB
 * - 시간: 120 ms
 */

fun main() = with(System.`in`.bufferedReader()) {
    val result = mutableListOf<Int>()
    while(true) {
        val n = readLine().toInt()
        if(n == 0) break
        val set = HashSet<String>()
        val chain = HashMap<String, String>()
        repeat(n) {
            readLine().split(" ").let { chain[it.first()] = it.last()}
        }

        var cnt = 0
        for(key in chain.keys) {
            if(set.contains(key)) continue
            chaining(set, chain, key)
            cnt++
        }
        result.add(cnt)
    }
    println(buildString {
        for(i in result.indices) {
            appendLine("${i + 1} ${result[i]}")
        }
    })
}

fun chaining(set: HashSet<String>, chain: HashMap<String, String>, key: String) {
    var temp = key
    while(chain.containsKey(temp) && !set.contains(temp)) {
        set.add(temp)
        temp = chain.getValue(temp)
    }
}