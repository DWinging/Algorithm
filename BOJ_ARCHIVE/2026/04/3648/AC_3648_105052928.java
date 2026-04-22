/**
 * [BOJ] 3648 - 아이돌
 * - 제출 날짜: 2026년 4월 16일
 * - 결과: 맞았습니다!!
 * - 메모리: 43644 KB
 * - 시간: 240 ms
 */

import java.util.*;
import java.io.*;

class Main {
    static ArrayList<Integer>[] edge, reverseEdge;
    static int[] visited, stack, sccID;
    static int c, mark = 1, top, sccMark;
    static boolean isEOF = false;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        while (true) {
            int n = readInt();
            if (isEOF) break;
            int m = readInt();
            init(n);

            add(getIdx(-1), getIdx(1));

            while (m-- > 0) {
                int uIdx = getIdx(readInt());
                int vIdx = getIdx(readInt());
                add(uIdx ^ 1, vIdx);
                add(vIdx ^ 1, uIdx);
            }

            forward(n);
            backward(n);
            System.out.println(checkSCC(n) == 1 ? "yes" : "no");
            mark++;
        }
    }

    private static void init(int n) {
        int MAX_NODE = (n << 1) + 2;
        visited = new int[MAX_NODE];
        stack = new int[MAX_NODE];
        sccID = new int[MAX_NODE];
        edge = new ArrayList[MAX_NODE];
        reverseEdge = new ArrayList[MAX_NODE];
        for (int i = 0; i < MAX_NODE; i++) {
            edge[i] = new ArrayList<>();
            reverseEdge[i] = new ArrayList<>();
        }
        sccMark = 0;
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
        for (int i = 2; i <= maxNode; i++) {
            if (visited[i] != mark) dfsForward(i);
        }
    }

    private static void dfsForward(int idx) {
        visited[idx] = mark;
        for (int next : edge[idx]) {
            if (visited[next] != mark) dfsForward(next);
        }
        stack[++top] = idx;
    }

    private static void backward(int n) {
        int vMark = ++mark;
        while (top >= 0) {
            int node = stack[top--];
            if (visited[node] != vMark) {
                sccMark++;
                dfsBackward(node, vMark);
            }
        }
    }

    private static void dfsBackward(int idx, int vMark) {
        visited[idx] = vMark;
        sccID[idx] = sccMark;
        for (int next : reverseEdge[idx]) {
            if (visited[next] != vMark) dfsBackward(next, vMark);
        }
    }

    private static int checkSCC(int n) {
        for (int i = 1; i <= n; i++) {
            if (sccID[i << 1] == sccID[(i << 1) | 1]) return 0;
        }
        return 1;
    }

    private static int readInt() throws IOException {
        while (c != -1 && c <= ' ') c = System.in.read();
        if (c == -1) { isEOF = true; return -1; }
        boolean flag = c == '-';
        if (c == '-') c = System.in.read();
        int n = 0;
        while (c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}