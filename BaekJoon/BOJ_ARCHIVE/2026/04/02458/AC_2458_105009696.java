/**
 * [BOJ] 2458 - 키 순서
 * - 제출 날짜: 2026년 4월 14일
 * - 결과: 맞았습니다!!
 * - 메모리: 19068 KB
 * - 시간: 160 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static BitSet[] up;
    static BitSet[] down;
    static ArrayList<Integer>[] upList;
    static ArrayList<Integer>[] downList;
    
    static int[] upOrder;
    static int[] downOrder;
    
    static int[] que;
    static int c;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int n = readInt();
        int m = readInt();
        init(n);
        inputOrder(m);
        
        typologicalSort(n, upList, upOrder, up);
        typologicalSort(n, downList, downOrder, down);

        System.out.println(solve(n));
    }

    private static void init(int n) {
        up = new BitSet[n + 1];
        down = new BitSet[n + 1];
        upList = new ArrayList[n + 1];
        downList = new ArrayList[n + 1];
        
        upOrder = new int[n + 1];
        downOrder = new int[n + 1];

        que = new int[n + 1];
        
        for(int i = 1; i <= n; i++) {
            up[i] = new BitSet(n + 1);
            down[i] = new BitSet(n + 1);
            upList[i] = new ArrayList<>();
            downList[i] = new ArrayList<>();
        }
    }

    private static void inputOrder(int m) throws IOException {
        while(m-- > 0) {
            int a = readInt();
            int b = readInt();

            upList[a].add(b);
            upOrder[b]++;
            
            downList[b].add(a);
            downOrder[a]++;
        }
    }

    private static void typologicalSort(int n, ArrayList<Integer>[] list, int[] order, BitSet[] set) {
        int head = 0, tail = 0;
        for(int i = 1; i <= n; i++) {
            if(order[i] == 0) {
                que[tail++] = i;
            }
        }

        while(head < tail) {
            int cur = que[head++];
            
            for(int next : list[cur]) {
                set[next].or(set[cur]);
                set[next].set(cur);
                
                if(--order[next] == 0) {
                    que[tail++] = next;
                }
            }
        }
    }

    private static int solve(int n) {
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (up[i].cardinality() + down[i].cardinality() == n - 1) {
                answer++;
            }
        }
        return answer;
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