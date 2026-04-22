/**
 * [BOJ] 1697 - 숨바꼭질
 * - 제출 날짜: 2026년 2월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 12244 KB
 * - 시간: 76 ms
 */

import java.util.*;
import java.io.*;

import java.io.*;

public class Main {

    final static int MAX_RANGE = 100001;

    static int[] deque, visited;
    static int c, start, end, head = 0, tail = 0;
    
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(bfs());
    }

    private static void init() throws IOException {
        c = System.in.read();
        start = readInt();
        end = readInt();
        
        deque = new int[MAX_RANGE];
        deque[tail++] = start;
        
        visited = new int[MAX_RANGE];
        visited[start] = 1;
    }

    private static int bfs() {
        while(head < tail){
            int cur = deque[head++];
            if(cur == end) return visited[cur] - 1;

            int t = visited[cur] + 1;
            checkMove(cur - 1, t);  // -1로 이동
            checkMove(cur + 1, t);  // +1로 이동
            checkMove(cur * 2, t);  // *2로 이동
        }
        return -1;
    }

    private static void checkMove(int cur, int time) {
        if(cur >= 0 && cur < MAX_RANGE && visited[cur] == 0){
            visited[cur] = time;
            deque[tail++] = cur;
        }
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
