/**
 * [BOJ] 25381 - ABBC
 * - 제출 날짜: 2026년 2월 21일
 * - 결과: 100점
 * - 메모리: 13380 KB
 * - 시간: 92 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        int[] dq = new int[300000];
        int hA = 0, tA = 0, hB = 299999, tB = 299999, cnt = 0, idx = 0;
        
        char c;
        while((c = (char) System.in.read()) > ' ') {
            if(c == 'A') dq[tA++] = idx++;
            else if(c == 'B') dq[tB--] = idx++;
            else { if(hB > tB) { cnt++; hB--;} }
        }

        while(hA < tA && hB > tB) {
            while(hB > tB && dq[hA] > dq[hB]) hB--;
            if(dq[hA++] < dq[hB--]) cnt++;
        }
        System.out.println(cnt);
    }
}
