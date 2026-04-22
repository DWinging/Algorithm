/**
 * [BOJ] 1647 - 도시 분할 계획
 * - 제출 날짜: 2026년 2월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 74940 KB
 * - 시간: 1264 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<int[]> list;
    static int[] parents;
    static int c, n, m;

    public static void main(String[] args) throws IOException {
        init();
        inputRoad();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        n = readInt();
        m = readInt();

        parents = new int[n + 1];
        for(int i = 1; i <= n; i++) parents[i] = i;

        list = new ArrayList<>();
    }

    private static void inputRoad() throws IOException {
        int a, b, c;
        while(m-- > 0) {
            a = readInt();
            b = readInt();
            c = readInt();

            list.add(new int[]{a, b, c});
        }

        list.sort((l1, l2) -> Integer.compare(l1[2], l2[2]));
    }

    private static int solve() {
        int edgeCnt = 0, idx = 0, sum = 0;
        while(edgeCnt < n - 2) {
            int[] cur = list.get(idx++);
            int a = cur[0];
            int b = cur[1];
            int w = cur[2];

            if(union(a, b)) {
                sum += w;
                edgeCnt++;
            }
        }
        return sum;
    }

    private static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return false;
        parents[pa] = pb;
        return true;
    }

    private static int find(int node) {
        if(parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}
