/**
 * [BOJ] 17090 - 미로 탈출하기
 * - 제출 날짜: 2026년 4월 9일
 * - 결과: 맞았습니다!!
 * - 메모리: 15272 KB
 * - 시간: 120 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static int BIT_SHIFT = 10;
    final static int[] DIR = {(1 << BIT_SHIFT), -(1 << BIT_SHIFT), 1, -1};
    static int[] map, que;
    static int c, head, tail;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        
        map = new int[n << BIT_SHIFT | m];
        que = new int[n * m];
        inputMap(n, m);
        System.out.println(bfs(n, m));
    }

    private static void inputMap(int n, int m) throws IOException {
        head = 0; tail = 0;
        for(int y = 0; y < n; y++) {
            while(c <= ' ') c = System.in.read();
            for(int x = 0; x < m; x++) {
                int d = inputDirt(c);
                int cur = y << BIT_SHIFT | x;
                int next = cur + DIR[d];
                if(!check(next, n, m)) {
                    que[tail++] = cur;
                    map[cur] = -1;
                } else {
                    map[y << BIT_SHIFT | x] = d;
                }

                c = System.in.read();
            }
        }
    }

    private static int inputDirt(int val) {
        if(val == 'D') return 0;
        else if(val == 'U') return 1;
        else if(val == 'R') return 2;
        else return 3;
    }

    private static int bfs(int n, int m) {
        int res = tail;
        while(head < tail) {
            int cur = que[head++];

            for(int d : DIR) {
                int next = cur + d;
                if(check(next, n, m) && map[next] >= 0) {
                    if(next + DIR[map[next]] == cur) {
                        que[tail++] = next;
                        map[next] = -1;                        
                        res++;
                    }
                }
            }
        }
        return res;
    }

    private static boolean check(int cur, int n, int m) {
        int y = cur >> BIT_SHIFT;
        int x = cur & ((1 << BIT_SHIFT) - 1);
        return y >= 0 && y < n && x >= 0 && x < m;
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