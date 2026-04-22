/**
 * [BOJ] 25402 - 트리와 쿼리
 * - 제출 날짜: 2026년 3월 22일
 * - 결과: 틀렸습니다
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[] tree, parents, cnt, stack;
    static boolean[] selected;
    static int c, N;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        init();
        settingTree();
        System.out.print(solve());
    }

    private static void init() {
        tree = new int[N + 1];
        parents = new int[N + 1];
        cnt = new int[N + 1];
        stack = new int[N + 1];
        selected = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            parents[i] = i;
            cnt[i] = 1;
        }
    }

    private static void settingTree() throws IOException {
        ArrayList<Integer>[] edge = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            edge[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            int n1 = readInt();
            int n2 = readInt();
            edge[n1].add(n2);
            edge[n2].add(n1);
        }

        int top = -1;
        stack[++top] = 1;
        tree[1] = 1;
        while(top > -1) {
            int p = stack[top--];
            for(int e : edge[p]) {
                if(tree[e] == 0) {
                    tree[e] = p;
                    stack[++top] = e;
                }
            }
        }
    }

    private static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        int q = readInt();
        while(q-- > 0) {
            int top = inputQuery();
            int res = countQuery(top);
            sb.append(res).append('\n');
            reset(top);
        }
        return sb.toString();
    }

    private static void reset(int top) {
        for(int i = 0; i < top; i++) {
            int cur = stack[i];
            parents[cur] = cur;
            cnt[cur] = 1;
            selected[cur] = false;
        }
    }

    private static int inputQuery() throws IOException {
        int k = readInt();
        for(int i = 0; i < k; i++) {
            int cur = readInt();
            stack[i] = cur;
            selected[cur] = true;
        }

        for(int i = 0; i < k; i++) {
            int cur = stack[i];
            if(selected[tree[cur]]) {
                union(tree[cur], cur);
            }
        }
        return k;
    }

    private static void union(int a, int b) {
        if(a == b) return;
        int pA = find(a);
        int pB = find(b);

        parents[pB] = pA;
        cnt[pA] += cnt[pB];
        cnt[pB] = 0;
    }

    private static int find(int p) {
        if(p == parents[p]) return p;
        else return parents[p] = find(parents[p]);
    }

    private static int countQuery(int top) {
        int res = 0;
        for(int i = 0; i < top; i++) {
            int temp = cnt[i];
            res += (temp * (temp - 1)) / 2;
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
