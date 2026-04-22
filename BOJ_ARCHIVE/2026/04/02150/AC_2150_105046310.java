/**
 * [BOJ] 2150 - Strongly Connected Component
 * - 제출 날짜: 2026년 4월 15일
 * - 결과: 맞았습니다!!
 * - 메모리: 21160 KB
 * - 시간: 176 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static ArrayList<Integer>[] edge, reverseEdge;
    static int[] stack, sccIdx, visited;
    static int c, idx;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        init(n);
        inputArray(m);
        forwardDfs(edge, n);
        ArrayList<int[]> scc = backwardDfs(reverseEdge, n);
        System.out.print(buildString(scc, n));
    }

    private static void init(int n) {
        edge = new ArrayList[n + 1];
        reverseEdge = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
            reverseEdge[i] = new ArrayList<>();
        }

        stack = new int[n];
        sccIdx = new int[n + 1];
        visited = new int[n + 1];
    }

    private static void inputArray(int m) throws IOException {
        for(int i = 0; i < m; i++) {
            int v1 = readInt();
            int v2 = readInt();
            
            edge[v1].add(v2);
            reverseEdge[v2].add(v1);
        }        
    }

    private static void forwardDfs(ArrayList<Integer>[] edge, int n) {
        idx = 0;
        for(int i = 1; i <= n; i++) {
            if(visited[i] == 0) {
                dfs(edge, stack, i, 0, n, 1);
            }
        }
    }

    private static ArrayList<int[]> backwardDfs(ArrayList<Integer>[] edge, int n) {
        ArrayList<int[]> scc = new ArrayList<>();
        int[] res = new int[n + 1];
        for(int i = n-1; i >= 0; i--) {
            int v = stack[i];
            if(visited[v] == 1) {
                idx = 0;
                dfs(edge, res, v, 0, n, 2);
                
                int[] arr = new int[idx];
                for(int j = 0; j < arr.length; j++) {
                    arr[j] = res[j];
                }

                Arrays.sort(arr);
                scc.add(arr);               
                sccIdx[arr[0]] = scc.size(); 
            }
        }
        return scc;
    }

    private static void dfs(ArrayList<Integer>[] edge, 
                            int[] stack, int v, int cnt, int n, int flag) {
        if(cnt == n) return;
        visited[v] = flag;
        for(int next : edge[v]) {
            if(visited[next] != flag) {
                 dfs(edge, stack, next, cnt + 1, n, flag);   
            }
        }
        stack[idx++] = v;
    }

    private static String buildString(ArrayList<int[]> scc, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(scc.size()).append('\n');
        for(int i = 1; i <= n; i++) {
            if(sccIdx[i] > 0) {
                for(int v : scc.get(sccIdx[i] - 1)) {
                    sb.append(v).append(' ');
                }
                sb.append(-1).append('\n');   
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