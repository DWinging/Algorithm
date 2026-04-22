/**
 * [BOJ] 1647 - 도시 분할 계획
 * - 제출 날짜: 2026년 2월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 65504 KB
 * - 시간: 1000 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<int[]> list;
    static int[] parents;
    static int n, m;

    static final InputStream in = System.in;
    static final byte[] buffer = new byte[1 << 16];
    static int ptr = 0, len = 0;

    public static void main(String[] args) throws Exception {
        n = readInt();
        m = readInt();

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) parents[i] = i;

        list = new ArrayList<>(m);

        inputRoad();
        System.out.println(solve());
    }

    private static void inputRoad() throws Exception {
        for (int i = 0; i < m; i++) {
            int a = readInt();
            int b = readInt();
            int c = readInt();
            list.add(new int[]{a, b, c});
        }

        list.sort(Comparator.comparingInt(o -> o[2]));
    }

    private static int solve() {
        int edgeCnt = 0, idx = 0, sum = 0;

        while (edgeCnt < n - 2) {
            int[] cur = list.get(idx++);
            if (union(cur[0], cur[1])) {
                sum += cur[2];
                edgeCnt++;
            }
        }
        return sum;
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) return false;
        parents[pa] = pb;
        return true;
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    private static int read() throws Exception {
        if (ptr >= len) {
            len = in.read(buffer);
            ptr = 0;
            if (len <= 0) return -1;
        }
        return buffer[ptr++];
    }

    private static int readInt() throws Exception {
        int c, n = 0;
        while ((c = read()) <= ' ') ;
        do {
            n = (n << 3) + (n << 1) + (c - '0');
        } while ((c = read()) >= '0');
        return n;
    }
}
