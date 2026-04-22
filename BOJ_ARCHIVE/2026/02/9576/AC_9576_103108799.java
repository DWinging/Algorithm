/**
 * [BOJ] 9576 - 책 나눠주기
 * - 제출 날짜: 2026년 2월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 11996 KB
 * - 시간: 88 ms
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

    final static int MAX_RANGE = 1000;
    
    static Book[] books = new Book[MAX_RANGE + 1];
    static boolean[] check = new boolean[MAX_RANGE + 1];
    static int c, n, m;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        setArray();
        
        int t = readInt();
        while(t-- > 0) {
            init();
            inputRange();
            sb.append(solve()).append('\n');
        }
        System.out.print(sb);
    }

    private static void setArray() {
        for(int i = 1; i <= MAX_RANGE; i++) {
            books[i] = new Book(0, 0);
        }
    }

    private static void init() throws IOException {
        n = readInt();
        m = readInt();
        Arrays.fill(check, false);
    }

    private static void inputRange() throws IOException {
        for(int i = 1; i <= m; i++) {
            int s = readInt();
            int e = readInt();
            books[i].s = s;
            books[i].e = e;
        }
        Arrays.sort(books, 1, m + 1); 
    }

    private static int solve() {
        int cnt = 0;
        for(int i = 1; i <= m; i++) {
            int s = books[i].s;
            int e = books[i].e;

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