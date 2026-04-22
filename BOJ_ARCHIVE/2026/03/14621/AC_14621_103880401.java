/**
 * [BOJ] 14621 - 나만 안되는 연애
 * - 제출 날짜: 2026년 3월 14일
 * - 결과: 맞았습니다!!
 * - 메모리: 18588 KB
 * - 시간: 208 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] univ;
    static int[] parents;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        System.out.println(solve(n, m));
    }

    private static void init(int n) throws IOException {
        univ = new int[n + 1];
        parents = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            while(c <= ' ') c = System.in.read();
            univ[i] = c;
            parents[i] = i;
            c = System.in.read();
        }
    }

    private static int solve(int n, int m) throws IOException {
        ArrayList<int[]> list = new ArrayList<>();
        while(m-- > 0) {
            int u1 = readInt();
            int u2 = readInt();
            int w = readInt();
            if(univ[u1] != univ[u2]) list.add(new int[]{u1, u2, w});
        }

        list.sort((a, b) -> Integer.compare(a[2], b[2]));

        int res = 0, cnt = 0;
        for(int[] cur : list) {
            int u1 = find(cur[0]);
            int u2 = find(cur[1]);
            int w = cur[2];

            if(u1 != u2) {
                parents[u2] = u1;
                res += w;
                cnt++;
                if(cnt == n-1) return res;
            }
        }
        return -1;
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        else return parents[p] = find(parents[p]);
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
