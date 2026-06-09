import java.io.*;

class Solution {

    static final int MAX_NODES = 80205;

    static int[][] trie = new int[MAX_NODES][26];
    static int[] cnt = new int[MAX_NODES];
    static int[] isEnd = new int[MAX_NODES];
    
    static int[] input = new int[400];
    static int c, total = 0;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int k = readInt();
            int len = inputChar();
            
            for (int i = 0; i < len; i++) {
                insert(i, len);
            }

            sb.append('#').append(t).append(' ');
            findKth(k, sb);
            sb.append('\n');

            init();
        }
        System.out.print(sb);
    }

    private static void insert(int start, int len) {
        int current = 0;
        for(int i = start; i < len; i++) {
            int charIdx = input[i];
            if(trie[current][charIdx] == 0) {
                trie[current][charIdx] = ++total;
            }
            current = trie[current][charIdx];
            cnt[current]++;
        }
        isEnd[current]++;
    }

    private static void findKth(int k, StringBuilder sb) {
        int current = 0;
        
        while(true) {
            if (isEnd[current] >= k) {
                break;
            }
            k -= isEnd[current];

            boolean moved = false;
            for(int j = 0; j < 26; j++) {
                int next = trie[current][j];
                if(next == 0) continue;
                
                if(k > cnt[next]) {
                    k -= cnt[next];
                } else {
                    sb.append((char)(j + 'a'));
                    current = next;
                    moved = true;
                    break;
                }
            }
            if(!moved) break;
        }
    }

    private static void init() {
        for(int i = 0; i <= total; i++) {
            for(int j = 0; j < 26; j++) {
                trie[i][j] = 0;            
            }
            cnt[i] = 0;
            isEnd[i] = 0;
        }
        total = 0;
    }

    private static int inputChar() throws IOException {
        int idx = 0;
        while(c <= ' ') c = System.in.read();
        while(c >= 'a' && c <= 'z') {
            input[idx++] = c - 'a';
            c = System.in.read();
        }
        return idx;
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