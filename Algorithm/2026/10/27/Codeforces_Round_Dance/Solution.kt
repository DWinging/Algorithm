fun main() = with(System.`in`.bufferedReader()) {
    val sb: StringBuilder = StringBuilder()
    val t: Int = readLine()!!.toInt()

    repeat(t) {
        val n = readLine()!!.toInt()
        val arr: IntArray = intArrayOf(0) + readLine()!!.split(" ").map { it.toInt() }.toIntArray()

        val parents = IntArray(n + 1) { it }
        val cnt = IntArray(n + 1)

        for(i in 1..n) {
            union(parents, i, arr[i])

            if (arr[arr[i]] == i && i > arr[i]) continue

            cnt[i]++
            cnt[arr[i]]++
        }

        val maxCircle = (1..n).count { parents[it] == it }
        val temp = (1..n).count { cnt[it] == 1 } shr 1
        val minCircle = if (temp > 0) maxCircle - temp + 1 else maxCircle

        sb.append("$minCircle $maxCircle\n")
    }
    print(sb.toString())
}

fun union(parents: IntArray, a: Int, b: Int) {
    val pA = find(parents, a)
    val pB = find(parents, b)
    if(pA != pB) parents[pB] = pA
}

fun find(parents: IntArray, p: Int): Int {
    if (parents[p] != p) {
        parents[p] = find(parents, parents[p])
    }
    return parents[p]
}