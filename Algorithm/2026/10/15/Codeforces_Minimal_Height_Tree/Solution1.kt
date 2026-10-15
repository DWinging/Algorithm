fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder()
    val t = readLine()!!.toInt()

    repeat(t) {
        val n = readLine()!!.toInt()
        val arr = readLine()!!.split(" ").map { it.toInt() }

        var level = 1
        var idx = 0
        var pTotal = 1
        var pCnt = 0

        for(i in 1..<n) {
            if(arr[i - 1] > arr[i]) {
                pCnt++

                if(pTotal == pCnt) {
                    pTotal = i - idx - 1
                    idx = i - 1
                    pCnt = 0
                    level++
                }
            }
        }

        sb.append("$level\n")
    }

    println(sb.toString())
}