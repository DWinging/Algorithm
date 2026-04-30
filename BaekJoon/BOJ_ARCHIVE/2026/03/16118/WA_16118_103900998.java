/**
 * [BOJ] 16118 - 달빛 여우
 * - 제출 날짜: 2026년 3월 15일
 * - 결과: 틀렸습니다
 */

import java.io.*;
import java.util.*;

public class Main {

    final static int MAX_RANGE = 800_000_000;
    static ArrayList<int[]>[] edge;
    static int[] distF;
    static int[][] distW;
    static int c;

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int m = readInt();

        init(n);
        inputEdge(m);
        dijkstraFox(1);
        dijkstraWolf(1);
        System.out.println(count(n));
    }

    private static void init(int n) {
        edge = new ArrayList[n + 1];
        distF = new int[n + 1];
        distW = new int[n + 1][2];

        for(int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
            distF[i] = MAX_RANGE;
            distW[i][0] = MAX_RANGE;
            distW[i][1] = MAX_RANGE;
        }
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- > 0) {
            int v1 = readInt();
            int v2 = readInt();
            int w = readInt() << 1;
            edge[v1].add(new int[]{v2, w});
            edge[v2].add(new int[]{v1, w});
        }
    }

    private static void dijkstraFox(int s) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{s, 0});
        distF[s] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int from = cur[0];
            int d = cur[1];

            if(distF[from] < d) continue;

            for(int[] next : edge[from]) {
                int nextD = next[1] + d;
                if(distF[next[0]] > nextD) {
                    pq.add(new int[] {next[0], nextD});
                    distF[next[0]] = nextD;
                }
            }
        }
    }

    private static void dijkstraWolf(int s) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[]{s, 0, 0});
        distW[s][0] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int from = cur[0];
            int d = cur[1];
            int toggle = cur[2] ^ 1;

            if(distW[from][toggle] < d) continue;

            for(int[] next : edge[from]) {
                int nextD = d + ((toggle & 1) == 0 ? next[1] << 1 : next[1] >> 1);
                if(distW[next[0]][toggle] > nextD) {
                    pq.add(new int[] {next[0], nextD, toggle});
                    distW[next[0]][toggle] = nextD;
                }
            }
        }
    }

    private static int count(int n) {
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(distF[i] < Math.min(distW[i][0], distW[i][1])) cnt++;
        }
        return cnt;
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
