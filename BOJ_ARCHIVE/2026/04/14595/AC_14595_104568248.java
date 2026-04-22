/**
 * [BOJ] 14595 - 동방 프로젝트 (Large)
 * - 제출 날짜: 2026년 4월 1일
 * - 결과: 맞았습니다!!
 * - 메모리: 15896 KB
 * - 시간: 96 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

class Main {

    static int[] room;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        room = new int[n + 1];

        crashWall();
        System.out.println(countRoom(n));
    }

    private static void crashWall() throws IOException {
        int m = readInt();
        while(m-- > 0) {
            int x = readInt();
            int y = readInt();
            room[x]++;
            room[y]--;
        }
    }

    private static int countRoom(int n) {
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            room[i] += room[i-1];
            if(room[i] == 0) cnt++;
        }
        return cnt;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c  & 15);
            c = System.in.read();
        }
        return n;
    }
}