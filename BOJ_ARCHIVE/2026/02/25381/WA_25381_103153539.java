/**
 * [BOJ] 25381 - ABBC
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 100점
 * - 메모리: 12976 KB
 * - 시간: 96 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int[] deque = new int[300_000];
        int headA = 0, tailA = 0, headB = 300_000 - 1, tailB = 300_000 - 1, cnt = 0, idx = 0;

        char c = (char) System.in.read();
        while(c > ' ') {
            if(c == 'A') deque[tailA++] = idx++;
            else if(c == 'B') deque[tailB--] = idx++;
            else { if(headB > tailB) { cnt++; headB--;} }
            c = (char) System.in.read();
        }

        while(headA < tailA && headB > tailB) {
            while(headB > tailB && deque[headA] > deque[headB]) headB--;
            if(deque[headA] < deque[headB]) cnt++;
            headA++;
            headB--;
        }
        System.out.println(cnt);
    }
}
