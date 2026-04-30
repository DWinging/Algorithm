/**
 * [BOJ] 13548 - 수열과 쿼리 6
 * - 제출 날짜: 2026년 4월 22일
 * - 결과: 맞았습니다!!
 * - 메모리: 29640 KB
 * - 시간: 556 ms
 */

import java.util.*;
import java.io.*;

class Main {

    private static class Query implements Comparable<Query> {
        int l, r, idx, blk;

        public Query(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
            this.blk = l / sqrN;
        }

        @Override
        public int compareTo(Query q) {
            if(this.blk != q.blk) return Integer.compare(this.blk, q.blk);
            else if((this.blk & 1) == 0) return Integer.compare(this.r, q.r);
            else return Integer.compare(q.r, this.r);
        }
    }

    static Query[] query;
    static int[] arr, cnt, ans, table;
    static int sqrN = 320, c, maxAns = 0;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);

        int m = readInt();
        inputQuery(m);

        solve(n, m);
        System.out.println(buildString());
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            arr[i] = readInt();
        }
    }

    private static void inputQuery(int m) throws IOException {
        query = new Query[m];
        for(int i = 0; i < m; i++) {
            int l = readInt();
            int r = readInt();
            query[i] = new Query(l, r, i);
        }
        Arrays.sort(query);
    }

    private static void solve(int n, int m) {
        cnt = new int[1_000_001];
        ans = new int[m];
        table = new int[m];

        int l = 1, r = 0;
        for(int i = 0; i < m; i++) {
            Query q = query[i];

            while(l > q.l) add(arr[--l]);
            while(r < q.r) add(arr[++r]);
            while(l < q.l) remove(arr[l++]);
            while(r > q.r) remove(arr[r--]);

            ans[q.idx] = maxAns;
        }
    }

    private static void add(int val) {
        table[cnt[val]]--;

        cnt[val]++;
        table[cnt[val]]++;

        if(cnt[val] > maxAns) maxAns = cnt[val];
    }

    private static void remove(int val) {
        if(cnt[val] == maxAns && table[cnt[val]] == 1) maxAns--;
        table[cnt[val]]--;
        cnt[val]--;
        table[cnt[val]]++;
    }

    private static String buildString() {
        StringBuilder sb = new StringBuilder();
        for(int n : ans) {
            sb.append(n).append('\n');
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