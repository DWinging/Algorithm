/**
 * [BOJ] 14427 - 수열과 쿼리 15
 * - 제출 날짜: 2026년 4월 5일
 * - 결과: 맞았습니다!!
 * - 메모리: 23564 KB
 * - 시간: 296 ms
 */

import java.io.*;
import java.util.*;

public class Main {

    private static class Node implements Comparable<Node> {
        int idx, val, ver;

        public Node(int idx, int val, int ver) {
            this.idx = idx;
            this.val = val;
            this.ver = ver;
        }

        @Override
        public int compareTo(Node o) {
            if(this.val == o.val) return Integer.compare(this.idx, o.idx);
            return Integer.compare(this.val, o.val);
        }
    }

    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] version;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);
        System.out.println(solve(n));
    }

    private static void inputArray(int n) throws IOException {
        version = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            int val = readInt();
            pq.add(new Node(i, val, 0));
        }
    }

    private static String solve(int n) throws IOException {
        StringBuilder sb = new StringBuilder();
        int m = readInt();
        while(m-- > 0) {
            int comm = readInt();
            if(comm == 1) {
                int i = readInt();
                int v = readInt();
                version[i]++;
                pq.add(new Node(i, v, version[i]));
            } else {
                while(!pq.isEmpty()) {
                    int idx = pq.peek().idx;
                    int ver = pq.peek().ver;
                    if(version[idx] == ver) break;
                    else pq.poll();
                }

                sb.append(pq.peek().idx).append('\n');
            }
        }
        return sb.toString();
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
