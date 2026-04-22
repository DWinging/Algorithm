/**
 * [BOJ] 28325 - 호숫가의 개미굴
 * - 제출 날짜: 2026년 4월 7일
 * - 결과: 100점
 * - 메모리: 13076 KB
 * - 시간: 144 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        System.out.println(solve(n));
    }
    
    private static long solve(int n) throws IOException {
        int[] stack = new int[n];
        int top = -1, cnt = 0, total = n;

        long antCount = 0L, room = 0L;
        while(n-- > 0) {
            room = readLong();
            // 쪽방이 있는 경우
            if(room > 0) {
                antCount += room;
                stack[++top] = cnt;
                cnt = 0;
            } 
            // 쪽방이 없는 경우
            else {
                cnt++;
            }
        }
        
        if(cnt == total) return cnt >> 1;
        else stack[0] += cnt;

        for(int i = 0; i <= Math.max(top, 0); i++) {
            antCount += (stack[i] + 1) >> 1;
        }
        return antCount;
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

    private static long readLong() throws IOException {
        while(c <= ' ') c = System.in.read();
        long n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}