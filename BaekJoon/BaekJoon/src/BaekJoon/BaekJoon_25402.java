package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_25402 {

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
        stack = new int[N];
        selected = new boolean[N + 1];

        for(int i = 1; i <= N; i++) {
            parents[i] = i;
            cnt[i] = 1;
        }
        cnt[0] = 0;
        selected[0] = true;
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
        tree[1] = 0;
    }

    private static String solve() throws IOException {
        StringBuilder sb = new StringBuilder();
        int q = readInt();
        while(q-- > 0) {
            int k = inputQuery();
            calculateQuery(k);
            long res = countQuery(k);
            sb.append(res).append('\n');
            reset(k);
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
        cnt[0] = 0;
    }

    private static int inputQuery() throws IOException {
        int k = readInt();
        for(int i = 0; i < k; i++) {
            int cur = readInt();
            stack[i] = cur;
            selected[cur] = true;
        }
        return k;
    }

    private static void calculateQuery(int k){
        for(int i = 0; i < k; i++) {
            int cur = stack[i];
            if(selected[tree[cur]]) {
                union(tree[cur], cur);
            }
        }
    }

    private static void union(int a, int b) {
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

    private static long countQuery(int top) {
        long res = (((long) cnt[0] * (cnt[0] - 1)) / 2);
        for(int i = 0; i < top; i++) {
            long temp = cnt[stack[i]];
            if(temp > 1) {
                res += (temp * (temp - 1)) / 2;
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
