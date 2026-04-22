/**
 * [BOJ] 2637 - 장난감 조립
 * - 제출 날짜: 2026년 2월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 11436 KB
 * - 시간: 56 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<int[]>[] list;
    static int[][] cnt;
    static int[] weight, deque;
    static int c, head = 0, tail = 0;

    public static void main(String[] args) throws IOException {
        c = System.in.read();

        int n = readInt();
        int m = readInt();

        init(n);
        inputOrder(m);
        solve(n);
        System.out.print(buildString(n));
    }

    private static void init(int n) {
        list = new ArrayList[n + 1];
        cnt = new int[n + 1][n + 1];
        weight = new int[n + 1];
        deque = new int[n];

        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
            cnt[i][i] = 1;
        }
        cnt[n][n] = 0;
    }

    private static void inputOrder(int m) throws IOException {
        while(m-- > 0) {
            int x = readInt();
            int y = readInt();
            int k = readInt();

            list[y].add(new int[]{x, k});
            cnt[x][x] = 0;
            weight[x]++;
        }
    }

    private static void solve(int n) {
        insertQueue(n);
        while(head < tail) {
            int cur = deque[head++];
            countPart(cur, n);
        }
    }

    private static void insertQueue(int n) {
        for(int i = 1; i <= n; i++) {
            if(weight[i] == 0) deque[tail++] = i;
        }
    }

    private static void countPart(int idx, int n) {
        for(int[] i : list[idx]) {
            int x = i[0];
            int k = i[1];
            for(int j = 1; j <= n; j++) {
                cnt[x][j] += cnt[idx][j] * k;
            }
            weight[x]--;
            if(weight[x] == 0) deque[tail++] = x;
        }
    }

    private static String buildString(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < n; i++) {
            int temp = cnt[n][i];
            if(temp == 0) continue;
            sb.append(i).append(' ').append(temp).append('\n');
        }
        return sb.toString();
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
