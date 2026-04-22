/**
 * [BOJ] 9576 - 책 나눠주기
 * - 제출 날짜: 2026년 2월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 12356 KB
 * - 시간: 100 ms
 */

import java.util.*;
import java.io.*;

class Main {

    private static class Book implements Comparable<Book> {
        int s, e;
        public Book(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Book b) {
            if(this.e != b.e) return Integer.compare(this.e, b.e);
            return Integer.compare(this.s, b.s);
        }
    }

    static PriorityQueue<Book> pq;
    static boolean[] check = new boolean[1001];
    static int c, n, m;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        pq = new PriorityQueue<>();
        c = System.in.read();
        int t = readInt();
        while(t-- > 0) {
            init();
            inputRange();
            sb.append(solve()).append('\n');
        }
        System.out.print(sb);
    }

    private static void init() throws IOException {
        n = readInt();
        m = readInt();
        pq.clear();
        Arrays.fill(check, false);
    }

    private static void inputRange() throws IOException {
        for(int i = 0; i < m; i++) {
            int s = readInt();
            int e = readInt();
            pq.add(new Book(s, e));
        }
    }

    private static int solve() {
        int cnt = 0;
        while(!pq.isEmpty()) {
            int s = pq.peek().s;
            int e = pq.peek().e;
            pq.poll();

            while(s <= e) {
                if(!check[s]) {
                    check[s] = true;
                    cnt++;
                    break;
                }
                s++;
            }
        }
        return cnt;
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