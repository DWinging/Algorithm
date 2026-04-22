/**
 * [BOJ] 16432 - 떡장수와 호랑이
 * - 제출 날짜: 2026년 4월 15일
 * - 결과: 맞았습니다!!
 * - 메모리: 12028 KB
 * - 시간: 72 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    final static int BIT_SHIFT = 4;
    static int[] arr, res, visited; 
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        inputArray(n);

        res = new int[n];
        visited = new int[n];
        System.out.print(dfs(n) ? buildString() : -1);
    }

    private static void inputArray(int n) throws IOException {
        arr = new int[n << BIT_SHIFT | 10];        
        for(int i = 0; i < n; i++) {
            int m = readInt();
            for(int j = 0; j < m; j++) {
                arr[i << BIT_SHIFT | j] = readInt();
            }
            arr[i << BIT_SHIFT | m] = -1;
        }
    }

    private static boolean dfs(int n) {
        int[] stack = new int[n * 10]; 
        int top = -1;

        stack[++top] = 0; 
    
        while (top > -1) {
            int state = stack[top];
            int day = state >> 8;
            int preVal = (state >> 4) & 15;
            int startIdx = state & 15;
    
            if (day == n) return true;
    
            boolean found = false;
            int base = day << BIT_SHIFT;
            
            for (int i = startIdx; arr[base | i] > -1; i++) {
                int curVal = arr[base | i];
                
                if (curVal != preVal && curVal > 0 && (visited[day] & (1 << curVal)) == 0) {
                    visited[day] |= (1 << curVal);
                    stack[top] = (day << 8 | preVal << 4 | (i + 1));
                    stack[++top] = ((day + 1) << 8 | curVal << 4 | 0);
                    res[day] = curVal;
                    found = true;
                    break;
                }
            }
    
            if (!found) {
                top--; 
            }
        }
        return false;
    }

    private static String buildString() {
        StringBuilder sb = new StringBuilder();
        for(int i : res) {
            sb.append(i).append('\n');
        }
        return sb.toString();
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