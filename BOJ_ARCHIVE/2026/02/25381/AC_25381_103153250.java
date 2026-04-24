/**
 * [BOJ] 25381 - ABBC
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 100점
 * - 메모리: 14264 KB
 * - 시간: 92 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int[] queA = new int[300_000];
        int[] queB = new int[300_000];
        int headA = 0, tailA = 0, headB = 0, tailB = 0, cnt = 0, idx = 0;

        char c = (char) System.in.read();
        while(c > ' ') {
            if(c == 'A') queA[tailA++] = idx++;
            else if(c == 'B') queB[tailB++] = idx++;
            else { if(headB < tailB) { cnt++; headB++;} }
            c = (char) System.in.read();
        }

        while(headA < tailA && headB < tailB) {
            while(headB < tailB && queA[headA] > queB[headB]) headB++;
            if(queA[headA] < queB[headB]) cnt++;
            headA++;
            headB++;
        }
        System.out.println(cnt);
    }
}
