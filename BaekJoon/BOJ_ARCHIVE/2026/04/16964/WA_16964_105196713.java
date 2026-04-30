/**
 * [BOJ] 16964 - DFS 스페셜 저지
 * - 제출 날짜: 2026년 4월 28일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

class Main {

    static class Node implements Comparable<Node> {
        int v, o;

        public Node(int v, int o) {
            this.v = v;
            this.o = o;
        }

        @Override
        public int compareTo(Node n) {
            return this.o - n.o;
        }
    }

    static ArrayList<Integer>[] edges;
    static int[] order;
    static boolean[] visited;
    static int c, res = 1, cnt = 1;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();

        inputArray(n);
        int root = inputOrder(n);

        visited = new boolean[n + 1];
        dfs(root);
        System.out.println(res);
    }

    private static void inputArray(int n) throws IOException {
        edges = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        
        while(n-- > 1) {
            int v1 = readInt();
            int v2 = readInt();
            edges[v1].add(v2);
            edges[v2].add(v1);
        }
    }

    private static int inputOrder(int n) throws IOException {
        order = new int[n + 1];
        int root = readInt();
        order[root] = 1;
        for(int i = 2; i <= n; i++) {
            order[readInt()] = i;
        }
        return root;
    }

    private static void dfs(int v) {
        if(order[v] != cnt) {
            res = 0;
            return;
        }

        visited[v] = true;
        cnt++;
        
        ArrayList<Node> list = new ArrayList<>();
        for(int e : edges[v]) {
            if(!visited[e] && order[e] >= cnt) {
                list.add(new Node(e, order[e]));
            }
        }

        Collections.sort(list);

        for(Node next : list) {
            if(res == 0) return;
            if(next.o == cnt) {
                dfs(next.v);
            } else {
                res = 0;
                return;
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