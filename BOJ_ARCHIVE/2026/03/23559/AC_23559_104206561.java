/**
 * [BOJ] 23559 - 밥
 * - 제출 날짜: 2026년 3월 23일
 * - 결과: 맞았습니다!!
 * - 메모리: 19768 KB
 * - 시간: 312 ms
 */

import java.io.*;
import java.util.*;

class Main {

    final static int A_COST = 5000;
    final static int B_COST = 1000;
    
    static PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int x = readInt() - (B_COST * n);
        int res = inputMenu(n);
        System.out.println(solve(res, x));
    }

    private static int inputMenu(int n) throws IOException {
        int sum = 0;
        while(n-- > 0) {
            int a = readInt();
            int b = readInt();
                        
            if(a > b) pq.add(a - b);
            sum += b;
        }
        return sum;
    }

    private static int solve(int sum, int x) {
        int cost = A_COST - B_COST;
        while(!pq.isEmpty() && x >= cost) {
            sum += pq.poll();
            x -= cost;
        }
        return sum;
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