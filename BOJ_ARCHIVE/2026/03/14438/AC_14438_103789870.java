/**
 * [BOJ] 14438 - 수열과 쿼리 17
 * - 제출 날짜: 2026년 3월 12일
 * - 결과: 맞았습니다!!
 * - 메모리: 16468 KB
 * - 시간: 180 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {
	
	static int[] tree;
	static int c, N;
	
	public static void main(String[] args) throws IOException {
		c = System.in.read();
		N = readInt();
		inputTree();
		System.out.println(solve());
	}
	
	private static void inputTree() throws IOException {
		tree = new int[N * 2];
		for(int i = 0; i < N; i++) {
			tree[i + N] = readInt();
		}
		
		for(int i = N-1; i > 0; i--) {
			tree[i] = Math.min(tree[i << 1], tree[i << 1 | 1]);
		}
	}
	
	private static String solve() throws IOException {
		StringBuilder sb = new StringBuilder();
		int m = readInt();
		while(m-- > 0) {
			int comm = readInt();
			if(comm == 1) {
				int node = readInt() - 1;
				int val = readInt();
				updateQuery(node, val);
			} else {
				int n1 = readInt() - 1;
				int n2 = readInt() - 1;
				sb.append(searchQuery(n1, n2)).append('\n');
			}
		}
		return sb.toString();
	}
	
	private static void updateQuery(int node, int val) {
		int idx = node + N;
		tree[idx] = val;
		
		idx >>= 1;
		while(idx > 0) {
			tree[idx] = Math.min(tree[idx << 1], tree[idx << 1 | 1]);
			idx >>= 1;
		}
	}
	
	private static int searchQuery(int a, int b) {
		int l = a + N;
		int r = b + N;
		
		int res = 1_000_000_005;
		while(l <= r) {
			if((l & 1) == 1) res = Math.min(res, tree[l++]);
			if((r & 1) == 0) res = Math.min(res, tree[r--]);
			l >>= 1;
			r >>= 1;
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
