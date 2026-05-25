import java.util.*;
import java.io.*;

class Solution {

    static final int MAX_SIZE = 500;

    static List<Integer>[] friends = new ArrayList[MAX_SIZE + 1];
    static {
        for(int i = 1; i <= MAX_SIZE; i++) {
            friends[i] = new ArrayList<>();
        }
    }
    
    static int[] visited = new int[MAX_SIZE + 1];
    static int[] que = new int[MAX_SIZE + 1];

    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();

        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            int m = readInt();

            for(int i = 1; i <= n; i++) friends[i].clear();

            inputFriends(m);
            sb.append('#').append(t).append(' ').append(solve(1, 2, t)).append('\n');
        }
        System.out.print(sb);
    }

    private static void inputFriends(int m) throws IOException {
        while(m-- > 0) {
            int f1 = readInt();
            int f2 = readInt();

            friends[f1].add(f2);
            friends[f2].add(f1);
        }
    }
    
    private static int solve(int s, int depth, int mark) {
        int head = 0, tail = 0;
        que[tail++] = s;
        visited[s] = mark;

        int cnt = 0, d = 0;
        while(d < depth) {
            int size = tail - head;
            d++;
            
            while(size-- > 0) {
                int cur = que[head++];
                
                for(int friend : friends[cur]) {
                    if(visited[friend] < mark) {
                    	visited[friend] = mark;
                        que[tail++] = friend;
                        cnt++;
                    }                
                }
            }   
        }

        return cnt;
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