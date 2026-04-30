/**
 * [BOJ] 18436 - 수열과 쿼리 37
 * - 제출 날짜: 2026년 3월 13일
 * - 결과: 맞았습니다!!
 * - 메모리: 20244 KB
 * - 시간: 216 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {
	
	static int[] tree, arr;
	static int c;
	
	public static void main(String[] args) throws IOException {
		int n = readInt();
		init(n);
		System.out.print(solve(n));
	}
	
	private static void init(int n) throws IOException {
		tree = new int[n * 4];
		arr = new int[n + 1];
		
		for(int i = 1; i <= n; i++) arr[i] = readInt() % 2;
		settingTree(1, 1, n);
	}
	
	private static void settingTree(int node, int s, int e) throws IOException {
		if(s == e) {
			tree[node] = arr[s];
			return;
		}
		
		int mid = (s + e) >> 1;
		settingTree(node << 1, s, mid);
		settingTree(node << 1 | 1, mid + 1, e);
		tree[node] = tree[node << 1] + tree[node << 1 | 1];
	}
	
	private static String solve(int n) throws IOException {
		StringBuilder sb = new StringBuilder();
		int m = readInt();
		while(m-- > 0) {
			int comm = readInt();
			if(comm == 1) {
				int idx = readInt();
				int x = readInt() % 2;
				if(x != arr[idx]) {
					updateQuery(1, 1, n, idx, x - arr[idx]);
					arr[idx] ^= 1;
				}
			} else {
				int l = readInt();
				int r = readInt();
				sb.append(sumQuery(1, 1, n, l, r, comm == 3)).append('\n');
			}
		}
		return sb.toString();
	}
	
	private static void updateQuery(int node, int s, int e, int idx, int w) {
		tree[node] += w;
		if(s == e) return;
		
		int mid = (s + e) >> 1;
		if(idx <= mid) updateQuery(node << 1, s, mid, idx, w);
		else updateQuery(node << 1 | 1, mid + 1, e, idx, w);
	}
	
	private static int sumQuery(int node, int s, int e, int a, int b, boolean flag) {
		if(b < s || e < a) return 0;
		
		if(a <= s && e <= b) return flag ? tree[node] : (e - s + 1) - tree[node];
		
		int mid = (s + e) >> 1;
		int val1 = sumQuery(node << 1, s, mid, a, b, flag);
		int val2 = sumQuery(node << 1 | 1, mid + 1, e, a, b, flag);
		return val1 + val2;
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
