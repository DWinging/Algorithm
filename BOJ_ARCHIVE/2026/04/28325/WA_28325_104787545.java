/**
 * [BOJ] 28325 - 호숫가의 개미굴
 * - 제출 날짜: 2026년 4월 7일
 * - 결과: 100점
 * - 메모리: 14104 KB
 * - 시간: 160 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        long[] rooms = inputArray(n);
        long first = solve(rooms, n, 0);
        long second = solve(rooms, n, 1) + rooms[0];
        System.out.println(Math.max(first, second));
    }

    private static long[] inputArray(int n) throws IOException {
        long[] rooms = new long[n + 1];
        for(int i = 0; i < n; i++) {
            rooms[i] = readLong();
        }
        rooms[n] = rooms[0];
        return rooms;
    }

    private static long solve(long[] rooms, int n, int idx) {
        if(rooms[0] == 0) rooms[n] = idx;
        long antCount = 0L, len = 0L;
        boolean flag = true;
        for(int i = idx; i < n - 1; i++) {
            long room = rooms[i];
            // 쪽방이 있는 경우
            if(room > 0) {
                antCount += room;
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
        }

        // 쪽방이 있는 경우
        if(rooms[n-1] > 0) {
            antCount += rooms[n-1];
        } 
        // 쪽방이 없는 경우
        else {
            if(flag && rooms[n] > 0) {
                antCount++;
            }
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