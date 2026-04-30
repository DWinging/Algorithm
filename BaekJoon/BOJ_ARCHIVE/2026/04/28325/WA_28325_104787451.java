/**
 * [BOJ] 28325 - 호숫가의 개미굴
 * - 제출 날짜: 2026년 4월 7일
 * - 결과: 21점
 * - 메모리: 12012 KB
 * - 시간: 144 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        long n = readLong();
        
        System.out.println(solve(n));
    }

    private static long solve(long n) throws IOException{
        long firstRoom = readLong();
        long rooms = firstRoom, antCount = 0L;
        boolean flag = true;
        while(n-- > 1) {
            // 쪽방이 있는 경우
            if(rooms > 0) {
                antCount += rooms;
                flag = true;
            } 
            // 쪽방이 없는 경우
            else {
                if(flag) {
                    antCount++;
                    flag = false;    
                } else {
                    flag = true;    
                }
            }
            rooms = readLong();
        }

         // 쪽방이 있는 경우
        if(rooms > 0) {
            antCount += rooms;
        } 
        // 쪽방이 없는 경우
        else {
            if(flag && firstRoom > 0) {
                antCount++;
            }
        }

        return antCount;
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