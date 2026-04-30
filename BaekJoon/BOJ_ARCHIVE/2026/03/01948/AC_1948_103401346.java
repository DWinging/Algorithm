/**
 * [BOJ] 1948 - 임계경로
 * - 제출 날짜: 2026년 3월 1일
 * - 결과: 맞았습니다!!
 * - 메모리: 20992 KB
 * - 시간: 156 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    final static int MAX = 10_000;
    static ArrayList<int[]>[] edge;
    static ArrayList<int[]>[] reverseEdge;
    static int[] cnt, time, deque;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        inputEdge(m);
        int start = readInt();
        int end = readInt();

        deque = new int[n];
        int t = topologicalSort(start, end, n);
        System.out.println(t + "\n" + backTrace(end));
    }

    private static void inputEdge(int m) throws IOException {
        edge = new ArrayList[MAX + 1];
        reverseEdge = new ArrayList[MAX + 1];

        for(int i = 1; i <= MAX; i++) {
            edge[i] = new ArrayList<>();
            reverseEdge[i] = new ArrayList<>();
        }

        cnt = new int[MAX + 1];
        time = new int[MAX + 1];

        while(m-- > 0) {
            int from = readInt();
            int to = readInt();
            int cost = readInt();
            edge[from].add(new int[] {to, cost});
            reverseEdge[to].add(new int[] {from, cost});
            cnt[to]++;
        }
    }

    private static int topologicalSort(int s, int e, int n) {
        int head = 0, tail = 0;
        int[] costQue = new int[n];
        cnt[s] = -1;
        deque[tail] = s;
        costQue[tail] = 0;
        tail++;

        while(head < tail) {
            int cur = deque[head];
            int cost = costQue[head];
            head++;

            if(cur == e) return time[e];

            for(int[] next : edge[cur]) {
                int to = next[0];
                int t = cost + next[1];
                cnt[to]--;

                if(t > time[to]) time[to] = t;

                if(cnt[to] == 0) {
                    cnt[to] = -1;
                    deque[tail] = to;
                    costQue[tail] = time[to];
                    tail++;
                }
            }
        }

        return -1;
    }

    private static int backTrace(int end) {
        boolean[] visited = new boolean[MAX + 1];
        visited[end] = true;
        int head = 0, tail = 0, cnt = 0;
        deque[tail++] = end;

        while(head < tail) {
            int cur = deque[head++];
            int t = time[cur];
            for(int[] node : reverseEdge[cur]) {
                int v = node[0];
                if(t - node[1] == time[v]) {
                    cnt++;
                    if(!visited[v]) {
                        visited[v] = true;
                        deque[tail++] = v;
                    }
                }
            }
        }
        return cnt;
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
