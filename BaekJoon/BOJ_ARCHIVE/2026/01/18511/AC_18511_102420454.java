/**
 * [BOJ] 18511 - 큰 수 구성하기
 * - 제출 날짜: 2026년 1월 29일
 * - 결과: 맞았습니다!!
 * - 메모리: 11492 KB
 * - 시간: 60 ms
 */

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String n = st.nextToken();
		int k = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[k];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve(arr, n, k));
	}
	
	private static int solve(int[] arr, String n, int k) {
		int num = Integer.parseInt(n);
		int[] stack = new int[n.length() * k + 3];
		int value = 0, top = -1;
		for(int i = k-1; i >= 0; i--) {
			stack[++top] = arr[i];
		}
		
		while(top >= 0) {
			int now = stack[top--];
			if(now > num) continue;
			if(now > value) value = now;
			
			for(int i : arr) {
				if((long) now * 10 + i <= num) {
					stack[++top] = now * 10 + i;
				}				
			}
		}
			
		return value;
	}
}
