/**
 * [BOJ] 13023 - ABCDE
 * - 제출 날짜: 2026년 3월 4일
 * - 결과: 맞았습니다!!
 * - 메모리: 21244 KB
 * - 시간: 156 ms
 */

import java.io.*;
import java.util.*;

class Main {

    static ArrayList<Integer>[] list;
    static boolean[] visited;
    static boolean flag = false;
    static int c, total = 4;    
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        setList(n, m);
        solve(n);
        System.out.println(flag ? 1 : 0);
    }

    private static void setList(int n, int m) throws IOException {
        visited = new boolean[n];
        list = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        while(m-- > 0) {
            int a = readInt();
            int b = readInt();
            list[a].add(b);
            list[b].add(a);
        }        
    }

    private static void solve(int n) {
        for(int i = 0; i < n && !flag; i++) {
            visited[i] = true;
            backtracking(i, 0);
            visited[i] = false;
        }
    }

    private static void backtracking(int idx, int cnt) {
        if(cnt == total) { flag = true; return;}

        for(int i : list[idx]) {
            if(!visited[i] && !flag) {
                visited[i] = true;
                backtracking(i, cnt + 1);
                visited[i] = false;
            }
        }
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