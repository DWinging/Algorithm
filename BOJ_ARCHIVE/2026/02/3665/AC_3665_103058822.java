/**
 * [BOJ] 3665 - 최종 순위
 * - 제출 날짜: 2026년 2월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 13444 KB
 * - 시간: 108 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int MAX_RANGE = 500;

    static StringBuilder resultString = new StringBuilder();
    static int[][] order = new int[MAX_RANGE + 1][MAX_RANGE + 1];
    static int[] arr = new int[MAX_RANGE + 1];
    static int[] cnt = new int[MAX_RANGE + 1];
    static int[] visited = new int[MAX_RANGE + 1];
    static int[] resultArr = new int[MAX_RANGE + 1];
    static int c;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();
        for(int t = 1; t <= T; t++) {
            int n = readInt();
            init(n);

            inputRanking(n);
            inputOrder(n, t);

            int m = readInt();
            changeRank(m, t);

            sb.append(solve(n, t) ? resultString.toString() : "IMPOSSIBLE").append('\n');
        }
        System.out.print(sb);
    }

    private static void init(int n) {
        resultString.setLength(0);
        for(int i = 1; i <= n; i++) {
            cnt[i] = 0;
        }
    }

    private static void inputRanking(int n) throws IOException {
        for(int i = 1; i <= n; i++) {
            int num = readInt();
            arr[i] = num;
            cnt[num] = i;
        }
    }

    private static void inputOrder(int n, int ver) {
        for(int i = 1; i <= n; i++) {
            int cur = arr[i];
            for(int j = i + 1; j <= n; j++) {
                order[cur][arr[j]] = ver;
            }
        }
    }

    private static void changeRank(int m, int ver) throws IOException {
        while(m-- > 0) {
            int n1 = readInt();
            int n2 = readInt();

            if(order[n1][n2] == ver) {
                order[n1][n2] = ver - 1;
                order[n2][n1] = ver;
                cnt[n1]++;
                cnt[n2]--;
            }
            else {
                order[n1][n2] = ver;
                order[n2][n1] = ver - 1;
                cnt[n1]--;
                cnt[n2]++;
            }
        }
    }

    private static boolean solve(int n, int ver) {
        for(int i = 1; i <= n; i++) {
            int order = cnt[i];
            if(visited[order] == ver) return false;
            visited[order] = ver;
            resultArr[order] = i;
        }

        for(int i = 1; i <= n; i++) {
            resultString.append(resultArr[i]).append(' ');
        }
        return true;
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
