/**
 * [BOJ] 2146 - 다리 만들기
 * - 제출 날짜: 2026년 2월 15일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int BIT_SHIFT = 7;
    final static int[] DICT = {1, -1, (1 << BIT_SHIFT), -(1 << BIT_SHIFT)};
    static int[][] map;
    static int[] dequeXY, dequeLand, dequeN, dequeD;
    static int c, top = 0;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        map = new int[(n << BIT_SHIFT) | n][2];
        inputMap(n);

        settingLand(n);
        System.out.println(buildBridge(n));
    }

    private static void inputMap(int n) throws IOException {
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                map[(y << BIT_SHIFT) | x][0] = readInt();
            }
        }
    }

    private static void settingLand(int n) {
        dequeLand = new int[n * n];
        dequeXY = new int[1 << 15];
        dequeN = new int[1 << 15];
        dequeD = new int[1 << 15];

        int idx = 2;
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                int cur = (y << BIT_SHIFT) | x;
                if(map[cur][0] == 1){
                    map[cur][0] = idx;
                    markingMap(n, cur, idx++);
                }
            }
        }
    }

    private static void markingMap(int n, int xy, int idx) {
        int head = 0, tail = 0;
        dequeLand[tail++] = xy;

        while(head < tail) {
            int cur = dequeLand[head++];

            boolean flag = false;

            for(int d : DICT) {
                int next = cur + d;
                if(!check(next, n)) continue;
                if(map[next][0] == 1) {
                    map[next][0] = idx;
                    dequeLand[tail++] = next;
                } else if(map[next][0] == 0){
                    flag = true;
                }
            }

            if(flag) {
                dequeXY[top] = cur;
                dequeN[top] = idx;
                dequeD[top] = 0;
                top++;
            }
        }
    }

    private static int buildBridge(int n) {
        int head = 0, tail = top;
        while(head < tail) {
            int cur = dequeXY[head];
            int curIdx = dequeN[head];
            int curDist = dequeD[head];
            head++;

            for(int dict : DICT) {
                int next = cur + dict;
                if(!check(next, n)) continue;
                if(map[next][0] == 0) {
                    map[next][0] = curIdx;
                    map[next][1] = curDist + 1;
                    dequeXY[tail] = next;
                    dequeN[tail] = curIdx;
                    dequeD[tail] = curDist + 1;
                    tail++;
                }
                else if(map[next][0] != curIdx){
                    return curDist + map[next][1];
                }
            }
        }
        return -1;
    }

    private static boolean check(int cur, int n) {
        int y = cur >> BIT_SHIFT;
        int x = cur & ((1 << BIT_SHIFT) - 1);
        return y >= 0 && y < n && x >= 0 && x < n;
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
