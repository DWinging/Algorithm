/**
 * [BOJ] 15831 - 준표의 조약돌
 * - 제출 날짜: 2026년 4월 26일
 * - 결과: 250점
 * - 메모리: 12144 KB
 * - 시간: 120 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int b = readInt();
        int w = readInt();

        boolean[] color = inputColor(n);
        System.out.println(solve(color, n, b, w));
    }

    private static int solve(boolean[] color, int n, int b, int w) {
        int bCnt = 0, wCnt = 0, res = 0;
        int left = 0, right = 0;

        while(left < n) {
            while(right < n && bCnt <= b) {
                if(bCnt <= b && wCnt >= w && res < (right - left)) {
                    res = right - left;
                }
                if(color[right]) wCnt++;
                else bCnt++;
                right++;
            }

            if(bCnt <= b && wCnt >= w && res < (right - left)) {
                res = right - left;
            }

            if(color[left]) wCnt--;
            else bCnt--;
            left++;
        }
        return res;
    }

    private static boolean[] inputColor(int n) throws IOException {
        boolean[] color = new boolean[n];
        while(c <= ' ') c = System.in.read();
        for(int i = 0; i < n; i++) {
            color[i] = c == 'W';
            c = System.in.read();
        }
        return color;
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