/**
 * [BOJ] 28325 - 호숫가의 개미굴
 * - 제출 날짜: 2026년 4월 7일
 * - 결과: 100점
 * - 메모리: 12004 KB
 * - 시간: 156 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        System.out.println(solve(n));
    }
    
    private static long solve(int n) throws IOException {
        long antCount = 0L;
        int firstCnt = -1, currentCnt = 0;
        
        while(n-- > 0) {
            long room = readLong();
            // 쪽방이 있는 경우
            if(room > 0) {
                antCount += room;
                if(firstCnt == -1) {
                    firstCnt = currentCnt;
                } else {
                    antCount += (currentCnt + 1) >> 1;
                }
                currentCnt = 0;
            } 
            // 쪽방이 없는 경우
            else {
                currentCnt++;
            }
        }

        if(firstCnt == -1) {
            return currentCnt >> 1;
        } else {
            antCount += (firstCnt + currentCnt + 1) >> 1; 
            return antCount;
        }
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