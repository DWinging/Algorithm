/**
 * [BOJ] 16928 - 뱀과 사다리 게임
 * - 제출 날짜: 2026년 2월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 11452 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static int START_POINT = 1;
    final static int END_POINT = 100;
    
    static int[] move = new int[END_POINT + 1];
    static int c;
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt() + readInt();
        inputMove(n);
        System.out.println(bfs());
    }

    private static void inputMove(int n) throws IOException {
        while(n-- > 0) {
            int from = readInt();
            int to = readInt();
            move[from] = to;
        }
    }

    private static int bfs() {
        int dice = 6, head = 0, tail = 0;
        int[] deque = new int[END_POINT + 1];
        int[] visited = new int[END_POINT + 1];

        deque[tail++] = START_POINT;
        while(head < tail) {
            int cur = deque[head++];
            int t = visited[cur];

            if(cur == END_POINT) return t;
            
            for(int d = 1; d <= dice; d++) {
                if(cur + d > END_POINT) continue;
                
                int next = move[cur + d] != 0 ? move[cur + d] : cur + d;
                if(visited[next] == 0) {
                    deque[tail++] = next;
                    visited[next] = t + 1;
                }
            }
        }
        return -1;
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