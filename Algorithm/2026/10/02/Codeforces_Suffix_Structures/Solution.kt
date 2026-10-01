fun main() = with(System.`in`.bufferedReader()) {
    val str1 = readLine()
    val str2 = readLine()

    val res1 = automaton(str1, str2)
    val res2 = isNotSubsequence(str1, str2)

    val answer = when {
        res1 == -1 -> "need tree"
        res1 == 0 -> "array"
        res2 -> "both"
        else -> "automaton"
    }

    print(answer)
}

fun automaton(str1: String, str2: String): Int {
    val cnt = IntArray(26) { 0 }
    for(c in str1) {
        cnt[c - 'a']++
    }

    for(c in str2) {
        val idx = c - 'a'
        if(cnt[idx] > 0) cnt[idx]--
        else return -1
    }

    return if (str1.length == str2.length) 0 else 1
}

fun isNotSubsequence(str1: String, str2: String): Boolean {
    var idx1 = 0
    var idx2 = 0
    while(idx2 < str2.length) {
        val c = str2[idx2]
        while(idx1 < str1.length && str1[idx1] != c) {
            idx1++
        }

        if(idx1 >= str1.length) return true
        idx1++
        idx2++
    }

    return idx2 != str2.length
}