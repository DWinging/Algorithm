/**
 * [BOJ] 18513 - 샘터
 * - 제출 날짜: 2026년 3월 31일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

class Main {

    final static int MAX_RANGE = 100_000_000;
    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int k = readInt() + n;
        System.out.println(bfs(n, k));
    }

    private static long bfs(int n, int k) throws IOException {
        Queue<Integer> que = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            int p = readInt();
            que.add(p);
            set.add(p);
        }

        long len = 1, total = 0;
        while(set.size() < k) {
            int cnt = que.size();
            while(cnt-- > 0) {
                int cur = que.poll();
                if(cur + 1 <= MAX_RANGE & set.add(cur + 1)) {
                    total += len;
                    que.add(cur + 1);
                }
                if(set.size() == k) break;

                if(cur - 1 >= -MAX_RANGE & set.add(cur - 1)) {
                    total += len;
                    que.add(cur - 1);
                }
                if(set.size() == k) break;
            }      
            len++;
        }

        return total;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = c == '-';
        if(c == '-') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}