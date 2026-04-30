/**
 * [BOJ] 21921 - 블로그
 * - 제출 날짜: 2026년 2월 2일
 * - 결과: 맞았습니다!!
 * - 메모리: 12880 KB
 * - 시간: 108 ms
 */

import java.util.*;
import java.io.*;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException{
		int n = readInt();
		int x = readInt();
		int[] arr = inputArray(n);
		System.out.println(solve(n, x, arr));
	}
	
	private static int[] inputArray(int n) throws IOException {
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = readInt();
		}
		return arr;
	}
	
	private static String solve(int n, int x, int[] arr) {
		int visitor = 0, maxValue = 0;
		for(int i = 0; i < x; i++) {
			visitor += arr[i];
		}
		maxValue = visitor;
		
		int left = 0, right = x, len = 1;
		while(right < n) {
			visitor += arr[right++] - arr[left++];
			if(visitor > maxValue) {
				maxValue = visitor;
				len = 1;
			}
			else if(visitor == maxValue) {
				len++;
			}
		} 
		return maxValue == 0 ? "SAD" : maxValue + "\n" + len;
	}
	
	
	private static int readInt() throws IOException {
		int c = System.in.read();
		while(c <= ' ') {
			c = System.in.read();
		}
		
		int n = 0;
		while(c >= '0' && c <= '9') {
			n = (n * 10) + (c - '0');
			c = System.in.read();
		}
		return n;
	}

}
