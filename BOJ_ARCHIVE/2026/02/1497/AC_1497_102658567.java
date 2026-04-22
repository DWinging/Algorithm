/**
 * [BOJ] 1497 - 기타콘서트
 * - 제출 날짜: 2026년 2월 5일
 * - 결과: 맞았습니다!!
 * - 메모리: 11464 KB
 * - 시간: 68 ms
 */

import java.util.*;
import java.io.*;

import java.io.IOException;

class Main {

    static int c;
    static int maxSongs = 0;
    static int minCount = -1;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        long[] guiter = new long[n];
        for(int i = 0; i < n; i++) {
            while(c <= ' ') c = System.in.read();
            while(c > ' ') c = System.in.read();
            
            while(c <= ' ') c = System.in.read();
            
            int songIdx = 0;
            while(c > ' ') {
                if(c == 'Y') guiter[i] |= (1L << songIdx);
                songIdx++;
                c = System.in.read();
            }
        }

        solve(guiter, n, 0, 0, 0L);
        System.out.println(maxSongs == 0 ? -1 : minCount);
    }

    private static void solve(long[] guiter, int n, int cnt, int idx, long value) {
        int currentSongCount = Long.bitCount(value);
        if(currentSongCount > maxSongs) {
            maxSongs = currentSongCount;
            minCount = cnt;
        } else if(currentSongCount == maxSongs) {
            if(minCount == -1 || minCount > cnt) {
                minCount = cnt;
            }
        }
        
        if(cnt > maxSongs || idx == n) return;

        solve(guiter, n, cnt, idx + 1, value);
        solve(guiter, n, cnt + 1, idx + 1, (value | guiter[idx]));
    }

    private static boolean check(long value, int n) {
        for(int i = 0; i < n; i++) {
            if((value & (1 << i)) != 0) return false;
        }
        return true;
    }
    
    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c - '0');
            c = System.in.read();
        }
        return n;
    }
}