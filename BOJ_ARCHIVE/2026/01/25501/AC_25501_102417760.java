/**
 * [BOJ] 25501 - 재귀의 귀재
 * - 제출 날짜: 2026년 1월 29일
 * - 결과: 맞았습니다!!
 * - 메모리: 18524 KB
 * - 시간: 176 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {
	
	static int step = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder(1 << 17);
		for(int i = 0; i < n; i++) {
			String text = br.readLine();
			int size = text.length();
			step = 1;
			if(solve(text, size)) {
				sb.append(1).append(' ');
			}
			else {
				sb.append(0).append(' ');
			}
			sb.append(step);
			
			if(sb.length() > ( 1 >> 16)) {
				System.out.println(sb);
				sb.setLength(0);
			}
		}
		System.out.println(sb);
	}
	
	private static boolean solve(String text, int size) {
		int left = 0, right = size - 1;
		
		while(left < right) {
			if(text.charAt(left++) != text.charAt(right--)) {
				return false;
			}
			step++;
		}
		return true;
	}
}
