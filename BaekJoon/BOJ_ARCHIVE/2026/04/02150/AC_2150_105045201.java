/**
 * [BOJ] 2150 - Strongly Connected Component
 * - 제출 날짜: 2026년 4월 15일
 * - 결과: 맞았습니다!!
 * - 메모리: 26812 KB
 * - 시간: 364 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static boolean[] visited;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();

        ArrayList<Integer>[] edge = new ArrayList[n + 1];
        ArrayList<Integer>[] reverseEdge = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            edge[i] = new ArrayList<>();
            reverseEdge[i] = new ArrayList<>();
        }
        
        inputArray(m, edge, reverseEdge);
        
        visited = new boolean[n + 1];   
        Stack<Integer> stack = forwardDfs(edge, n);
        ArrayList<int[]> scc = backwardDfs(reverseEdge, n, stack);
        System.out.print(buildString(scc));
    }

    private static void inputArray(int m, ArrayList<Integer>[] edge, 
                                   ArrayList<Integer>[] reverseEdge) throws IOException {

        for(int i = 0; i < m; i++) {
            int v1 = readInt();
            int v2 = readInt();
            
            edge[v1].add(v2);
            reverseEdge[v2].add(v1);
        }        
    }

    private static Stack<Integer> forwardDfs(ArrayList<Integer>[] edge, int n) {
        Stack<Integer> result = new Stack<>();
        for(int i = 1; i <= n; i++) {
            if(!visited[i]) {
                dfs(edge, i, n, 0, result, true);
            }
        }
        return result;
    }

    private static ArrayList<int[]> backwardDfs(ArrayList<Integer>[] edge, int n, Stack<Integer> stack) {
        Stack<Integer> result = new Stack<>();
        ArrayList<int[]> scc = new ArrayList<>();
        while(!stack.isEmpty()) {
            int v = stack.pop();
            if(visited[v]) {
                dfs(edge, v, n, 0, result, false);
                int[] arr = new int[result.size()];
                for(int i = 0; i < arr.length; i++) {
                    arr[i] = result.pop();
                }
                Arrays.sort(arr);
                scc.add(arr);
            }
        }
        return scc;
    }

    private static void dfs(ArrayList<Integer>[] edge, 
                            int v, int n, int cnt,
                            Stack<Integer> stack, boolean flag) {
        if(cnt == n) return;
        visited[v] = flag;
        for(int next : edge[v]) {
            if(visited[next] != flag) {
                 dfs(edge, next, n, cnt + 1, stack, flag);   
            }
        }
        stack.push(v);
    }

    private static String buildString(ArrayList<int[]> scc) {
        Collections.sort(scc, (a, b) -> Integer.compare(a[0], b[0]));
        StringBuilder sb = new StringBuilder();
        sb.append(scc.size()).append('\n');
        for(int[] arr : scc) {
            for(int v : arr) {
                sb.append(v).append(' ');
            }
            sb.append(-1).append('\n');
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