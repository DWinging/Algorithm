/**
 * [BOJ] 11280 - 2-SAT - 3
 * - 제출 날짜: 2026년 4월 16일
 * - 결과: 맞았습니다!!
 * - 메모리: 25204 KB
 * - 시간: 252 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static ArrayList<Integer>[] edge, reverseEdge;
    static int[] visited, stack, sccID;
    static int c, mark = 1, top, sccMark;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        inputEdge(m);
        forward(n);
        backward(n);
        System.out.println(checkSCC(n));
    }

    private static void init(int n) {
        int MAX_NODE = (n << 1) + 2; 
        visited = new int[MAX_NODE];
        stack = new int[MAX_NODE];
        sccID = new int[MAX_NODE];
        edge = new ArrayList[MAX_NODE];
        reverseEdge = new ArrayList[MAX_NODE];
        for(int i = 0; i < MAX_NODE; i++) {
            edge[i] = new ArrayList<>();
            reverseEdge[i] = new ArrayList<>();
        }
    }

    private static void inputEdge(int m) throws IOException {
        while(m-- > 0) {
            int uIdx = getIdx(readInt());
            int vIdx = getIdx(readInt());
            add(uIdx ^ 1, vIdx);
            add(vIdx ^ 1, uIdx);
        }
    }

    private static int getIdx(int idx) {
        return idx > 0 ? (idx << 1) : ((-idx << 1) | 1);
    }

    private static void add(int from, int to) {
        edge[from].add(to);
        reverseEdge[to].add(from);
    }

    private static void forward(int n) {
        top = -1;
        int maxNode = (n << 1) | 1;
        for(int i = 2; i <= maxNode; i++) {
            if(visited[i] != mark) dfsForward(i);
        }
    }

    private static void dfsForward(int idx) {
        visited[idx] = mark;
        for(int next : edge[idx]) {
            if(visited[next] != mark) dfsForward(next);
        }
        stack[++top] = idx;
    }

    private static void backward(int n) {
        sccMark = 0;
        while(top >= 0) {
            int node = stack[top--];
            if(sccID[node] == 0) {
                sccMark++;
                dfsBackward(node);
            }
        }
    }

    private static void dfsBackward(int idx) {
        sccID[idx] = sccMark;
        for(int next : reverseEdge[idx]) {
            if(sccID[next] == 0) dfsBackward(next);
        }
    }

    private static int checkSCC(int n) {
        for(int i = 1; i <= n; i++) {
            if(sccID[i << 1] == sccID[(i << 1) | 1]) return 0;
        }
        return 1;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = c == '-';
        if(c == '-') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}