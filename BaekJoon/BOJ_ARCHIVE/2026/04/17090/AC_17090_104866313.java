/**
 * [BOJ] 17090 - 미로 탈출하기
 * - 제출 날짜: 2026년 4월 9일
 * - 결과: 맞았습니다!!
 * - 메모리: 28780 KB
 * - 시간: 116 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static int BIT_SHIFT = 10;
    final static int[] DIR = {(1 << BIT_SHIFT), -(1 << BIT_SHIFT), 1, -1};
    static int[] map, visited;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        
        map = new int[n << BIT_SHIFT | m];
        visited = new int[n << BIT_SHIFT | m];
        inputMap(n, m);
        System.out.println(solve(n, m));
    }

    private static void inputMap(int n, int m) throws IOException {
        for(int y = 0; y < n; y++) {
            while(c <= ' ') c = System.in.read();
            for(int x = 0; x < m; x++) {
                map[y << BIT_SHIFT | x] = inputDirt(c);
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

    private static int solve(int n, int m) {
        int res = 0;
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(visited[y << BIT_SHIFT | x] == 0) {
                    res += dfs(y << BIT_SHIFT | x, n, m);
                }
            }
        }
        return res;
    }

    private static int dfs(int cur, int n, int m) {
        visited[cur] = -1;
        int d = DIR[map[cur]];
        int next = cur + d;

        if(!check(next, n, m)) {
            visited[cur] = 1;
            return 1;
        }

        if(visited[next] == 0) {
            int val = dfs(next, n, m);
            if(val == 0) return 0;
            else {
                visited[cur] = 1;
                return val + 1;  
            }
        } else if(visited[next] == 1) {
            visited[cur] = 1;
            return 1;
        } else {
            return 0;
        }
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