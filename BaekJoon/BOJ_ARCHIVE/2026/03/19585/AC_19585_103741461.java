/**
 * [BOJ] 19585 - 전설
 * - 제출 날짜: 2026년 3월 11일
 * - 결과: 맞았습니다!!
 * - 메모리: 1163240 KB
 * - 시간: 2932 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {
	
	static int MAX_SIZE = 4_000_000;
	static int[][] color = new int[MAX_SIZE + 1][26];
	static int[][] nick = new int[MAX_SIZE + 1][26];
	static boolean[] isEndC = new boolean[MAX_SIZE + 1];
	static boolean[] isEndN = new boolean[MAX_SIZE + 1];
	static int[] buffer = new int[2_000];
	static int c, colorCnt, nickCnt, input;
	
	public static void main(String[] args) throws IOException {
		c = System.in.read();
		int n = readInt();
		int m = readInt();
		
		inputColor(n);
		inputName(m);
		
		int q = readInt();
		System.out.print(solve(q));
	}
	
	private static void inputColor(int n) throws IOException {
		colorCnt = 1;
		while(n-- > 0) {
			int pointer = 0;
			int len = readString();
			for(int i = 0; i < len; i++) {
				int val = buffer[i];
				if(color[pointer][val] == 0) {
					color[pointer][val] = colorCnt;
					pointer = colorCnt++;
				} else {
					pointer = color[pointer][val];
				}
			}
			isEndC[pointer] = true;
		}
	}
	
	private static void inputName(int n) throws IOException {
		nickCnt = 1;
		while(n-- > 0) {
			int pointer = 0;
			int len = readString();
			for(int i = len - 1; i >= 0; i--) {
				int val = buffer[i];
				if(nick[pointer][val] == 0) {
					nick[pointer][val] = nickCnt;
					pointer = nickCnt++;
				} else {
					pointer = nick[pointer][val];
				}
			}
			isEndN[pointer] = true;
		}
	}
	
	private static String solve(int n) throws IOException {
		StringBuilder sb = new StringBuilder();
		while(n-- > 0) {
			int len = readString();
			sb.append(isChecked(len)).append('\n');
		}
		return sb.toString();
	}
	
	private static String isChecked(int len) {
		boolean[] validNick = new boolean[len];
		int nickPtr = 0;
		
		for(int i = len - 1; i >= 0; i--) {
			int val = buffer[i];
			if(nick[nickPtr][val] > 0) {
				nickPtr = nick[nickPtr][val];
				if(isEndN[nickPtr]) validNick[i] = true;
			} else {
				break;
			}
		}
		
		int colorPtr = 0;
		for(int i = 0; i < len-1; i++) {
			int val = buffer[i];
			if(color[colorPtr][val] > 0) {
				colorPtr = color[colorPtr][val];
				if(isEndC[colorPtr] && validNick[i + 1]) {
					return "Yes";
				}				
			} else {
				break;
			}
		}
		
		return "No";
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
	
	private static int readString() throws IOException {
		while(c <= ' ') c = System.in.read();
		int cnt = 0;
		while(c > ' ') {
			buffer[cnt++] = c - 'a';
			c = System.in.read();
		}
		return cnt;
	}
}
