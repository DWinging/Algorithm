package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_16974 {
    static long[] len, patty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());
        len = new long[n + 1];
        patty = new long[n + 1];

        calculatePatty(n);
        System.out.println(countPatty(n, x));
    }

    private static void calculatePatty(int n) {
        len[0] = 1;
        patty[0] = 1;
        for(int i = 1; i <= n; i++) {
            len[i] = len[i - 1] * 2 + 3;
            patty[i] = patty[i - 1] * 2 + 1;
        }
    }

    private static long countPatty(int n, long x) {
        if(n == 0 && x == 1) return 1;
        if(x <= 1) return 0;
        if(x <= len[n-1] + 1) {
            return countPatty(n-1, x - 1);
        }
        else if(x == len[n-1] + 2) {
            return patty[n-1] + 1;
        }
        else if(x <= len[n-1] * 2 + 2){
            return countPatty(n-1, x - (len[n-1] + 2)) + patty[n-1] + 1;
        }
        return patty[n];
    }
}