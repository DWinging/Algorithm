/**
 * [BOJ] 2098 - 외판원 순회
 * - 제출 날짜: 2026년 3월 10일
 * - 결과: 맞았습니다!!
 * - 메모리: 47412 KB
 * - 시간: 172 ms
 */

import java.util.*;
import java.io.*;

class Main {

    final static int MAX_VALUE = 16_000_005;
    static int[][] dist, visited;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);
        System.out.println(searchRoute(0, n));
    }

    private static void inputArray(int n) throws IOException {
        dist = new int[n][n];
        visited = new int[n][1 << n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) 
                dist[i][j] = readInt();    
            
            Arrays.fill(visited[i], MAX_VALUE);
        }
    }

    private static int searchRoute(int s, int n) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addLast(new int[]{s, 0, ((1 << n) - 1) ^ (1 << s)});
        int res = MAX_VALUE;
        
        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int from = cur[0];
            int d = cur[1];
            int bit = cur[2];

            if(res < d) continue;
            if(visited[from][bit] < d) continue;
            if(bit == 0) {
                if(dist[from][s] != 0) 
                    res = Math.min(res, d + dist[from][s]);
                continue;
            } 

            for(int to = 0; to < n; to++) {
                if((bit & (1 << to)) != 0 && dist[from][to] > 0) {
                    int temp = bit & ~(1 << to);
                    int toD = d + dist[from][to];
                    if(visited[to][temp] > toD) {
                        visited[to][temp] = toD;
                        deque.addLast(new int[]{to, toD, temp});
                    }
                }
            }
        }
        return res;
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