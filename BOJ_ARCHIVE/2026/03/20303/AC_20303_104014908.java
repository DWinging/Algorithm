/**
 * [BOJ] 20303 - 할로윈의 양아치
 * - 제출 날짜: 2026년 3월 18일
 * - 결과: 맞았습니다!!
 * - 메모리: 12556 KB
 * - 시간: 188 ms
 */

import java.util.*;
import java.io.*;

class Main {
    
    static int[] parents, cnt, sum;
    static int c, total = 0, N, M, K;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        N = readInt();
        M = readInt();
        K = readInt();

        inputArray();
        solve();
        System.out.println(calculateDp());
    }

    private static void inputArray() throws IOException {
        parents = new int[N + 1];
        cnt = new int[N + 1];
        sum = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parents[i] = i;
            cnt[i] = 1;
            sum[i] = readInt();
        }
    }

    private static void solve() throws IOException {
        int m = M;
        while(m-- > 0) {
            int a = readInt();
            int b = readInt();
            union(a, b);
        }
    }

    private static void union(int a, int b) throws IOException {
        int pA = find(a);
        int pB = find(b);

        if(pA != pB) {
            if(cnt[pA] >= cnt[pB]) {
                parents[pB] = pA;
                cnt[pA] += cnt[pB];
                sum[pA] += sum[pB];
            } else {
                parents[pA] = pB;
                cnt[pB] += cnt[pA];
                sum[pB] += sum[pA];
            }
        }
    }

    private static int find(int p) {
        if(parents[p] == p) return p;
        return parents[p] = find(parents[p]);
    }

    private static int calculateDp() {
        int[] dp = new int[K];
        for(int i = 1; i <= N; i++) {
            if(parents[i] == i && cnt[i] < K) {
                int count = cnt[i];
                int val = sum[i];
                for(int j = K-1; j >= count; j--) {
                    dp[j] = Math.max(dp[j], dp[j - count] + val);
                }
            }
        }
        return dp[K-1];
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