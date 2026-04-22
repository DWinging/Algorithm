/**
 * [BOJ] 1437 - 수 분해
 * - 제출 날짜: 2026년 2월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 11500 KB
 * - 시간: 64 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solve(n));
    }

    private static int solve(int num) {
        int n = 1;
        while(num > 4) {
            n = (n * 3) % MOD;
            num -= 3;
        }
        return (n * num) % MOD;
    }
}
