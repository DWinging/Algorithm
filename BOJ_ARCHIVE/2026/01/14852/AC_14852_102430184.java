/**
 * [BOJ] 14852 - 타일 채우기 3
 * - 제출 날짜: 2026년 1월 29일
 * - 결과: 맞았습니다!!
 * - 메모리: 19340 KB
 * - 시간: 76 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int mod = 1_000_000_007;
		
		if(n == 1) {
			System.out.println(2);
			return;
		}
		if(n == 2) {
			System.out.println(7);
			return;
		}
		if(n == 3) {
			System.out.println(22);
			return;
		}
		long[] dp = new long[n + 1];
		
		dp[0] = 1;
		dp[1] = 2;
		dp[2] = 7;
		dp[3] = 22;
		long sum = 64;
		for(int i = 4; i <= n; i++) {
			dp[i] = (sum + dp[i-2]) % mod;
			sum = (sum + dp[i] * 2) % mod;
		}
		System.out.println(dp[n]);		
	}
}
