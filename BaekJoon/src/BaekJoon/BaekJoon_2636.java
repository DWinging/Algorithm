package BaekJoon;

import java.io.*;
public class BaekJoon_2636 {

    final static int BIT_SHIFT = 7;
    final static int[] DICT = {1, -1, (1 << BIT_SHIFT), -(1 << BIT_SHIFT)};

    static int[] map, que, cheeze;
    static int c, n, m, head = 0, tail = 0;

    public static void main(String[] args) throws IOException {
        init();
        inputArray();
        System.out.println(solve());
    }

    private static void init() throws IOException {
        c = System.in.read();
        n = readInt();
        m = readInt();

        map = new int[(n << BIT_SHIFT) | m];
        que = new int[n * m];
        cheeze = new int[n * m];
    }

    private static void inputArray() throws IOException {
        for(int y = 0; y < n; y++) {
            int cur = y << BIT_SHIFT;
            for(int x = 0; x < m; x++) {
                map[cur | x] = readInt();
            }
        }
    }

    private static String solve() {
        int time = -1, prev = 0, cnt = 0;
        que[tail++] = 0;
        map[0] = -1;
        while(head < tail) {
            prev = cnt;
            cnt = bfs();
            time++;
            for(int i = 0; i < cnt; i++) {
                int cur = cheeze[i];
                que[tail++] = cur;
            }
        }
        return time + "\n" + prev;
    }

    private static int bfs() {
        int cnt = 0;
        while(head < tail) {
            int cur = que[head++];
            for(int d : DICT) {
                int next = cur + d;
                if(!check(next)) continue;
                if(map[next] == -1) continue;
                if(map[next] == 0) que[tail++] = next;
                else if(map[next] == 1) cheeze[cnt++] = next;
                map[next] = -1;

            }
        }
        return cnt;
    }

    private static boolean check(int cur) {
        int y = cur >> BIT_SHIFT;
        int x = cur & ((1 << BIT_SHIFT) - 1);
        return y >= 0 && y < n && x >= 0 && x < m;
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int temp = 0;
        while(c >= '0' && c <= '9') {
            temp = (temp << 3) + (temp << 1) + (c - '0');
            c = System.in.read();
        }
        return temp;
    }
}
