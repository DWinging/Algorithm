/**
 * [BOJ] 9202 - Boggle
 * - 제출 날짜: 2026년 3월 12일
 * - 결과: 틀렸습니다
 */

import java.io.*;
import java.util.*;

public class Main {
	
	static class Trie {
		int cnt;
		int[] arr;
		
		public Trie() {
			this.cnt = 0;
			this.arr = new int[26];
		}
	}
	
	final static int[][] DIST = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
	static TreeSet<String> set;
	static Trie[] trie;
	static int[][] grid = new int[4][4];
	static boolean[] isEnd;
	static boolean[][] visited = new boolean[4][4];
	static char[] res = new char[8];
	static int c, score;
	
	public static void main(String[] args) throws IOException {
		c = System.in.read();
		init();
		System.out.println(solve());
	}
	
	private static void init() throws IOException {
		int n = readInt();
		trie = new Trie[n * 8];
		trie[0] = new Trie();
		isEnd = new boolean[n * 8];
		int cnt = 1;
		for(int i = 0; i < n; i++) {
			int pointer = 0;
			while(c <= ' ') c = System.in.read();
			while(c > ' ') {
				int val = c - 'A';
				if(trie[pointer].arr[val] == 0) {
					trie[pointer].arr[val] = cnt;
					pointer = cnt;
					trie[pointer] = new Trie();
					cnt++;
				} else {
					pointer = trie[pointer].arr[val];
				}
				trie[pointer].cnt++;
				c = System.in.read();
			}
			isEnd[pointer] = true;			
		}
	}
	
	private static String solve() throws IOException {
		StringBuilder sb = new StringBuilder();
		set = new TreeSet<>((s1, s2) -> {
			if(s1.length() != s2.length()) {
				return s2.length() - s1.length();
			} else {
				return s1.compareTo(s2);
			}
		});
		
		int m = readInt();
		
		for(int i = 1; i <= m; i++) {
			set.clear();
			inputGrid();
			score = 0;
			for(int y = 0; y < 4; y++) {
				for(int x = 0; x < 4; x++) {
					dfs(y, x, 0, 0);
				}
			}
			
			sb.append(score).append(' ')
				.append(set.first())
				.append(' ')
				.append(set.size())
				.append('\n');
		}
		
		return sb.toString();
	}
	
	private static void inputGrid() throws IOException {
		for(int i = 0; i < 4; i++) {
			while(c <= ' ') c = System.in.read(); 
			for(int j = 0; j < 4; j++) {
				grid[i][j] = c - 'A';
				c = System.in.read();
			}
		}
	}
	
	private static void dfs(int y, int x, int cnt, int pointer) {
		if(trie[pointer].arr[grid[y][x]] == 0) return;
		pointer = trie[pointer].arr[grid[y][x]];
		res[cnt++] = (char)(grid[y][x] + 'A');
		visited[y][x] = true;
		if(isEnd[pointer]) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < cnt; i++) {
				sb.append(res[i]);
			}
			if(set.add(sb.toString())) {
				score += getScore(sb.length());
			}
		}
		if(cnt == 8) return;
		for(int[] d : DIST) {
			int ny = y + d[0];
			int nx = x + d[1];
			if(check(ny, nx, 4) && !visited[ny][nx]) {
				dfs(ny, nx, cnt, pointer);
			}
		}
		visited[y][x] = false;
	}
	
	private static boolean check(int y, int x, int n) {
		return y >= 0 && y < n && x >= 0 && x < n;
	}
	
	private static int getScore(int n) {
		if(n <= 2) return 0;
		else if(3 <= n && n <= 4) return 1;
		else if(5 <= n && n <= 6) return n - 3;
		else if(n == 7) return 5;
		else return 11;
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
