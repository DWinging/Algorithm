package BaekJoon;

import java.io.*;

public class BaekJoon_14500 {

    final static int BIT_SHIFT = 9;
    final static int BLOCK = 4;
    final static int[] DICT = {1, -1, (1 << BIT_SHIFT), -(1 << BIT_SHIFT)};

    static int[] map;
    static boolean[] visited;
    static int c, n, m, result = 0;

    public static void main(String[] args) throws IOException {
        c = System.in.read();
        init();
        inputArray();
        solve();
        System.out.println(result);
    }

    private static void init() throws IOException {
        n = readInt();
        m = readInt();
        map = new int[(n << BIT_SHIFT) | m];
        visited = new boolean[(n << BIT_SHIFT) | m];
    }

    private static void inputArray() throws IOException {
        for(int y = 0; y < n; y++) {
            int cur = y << BIT_SHIFT;
            for(int x = 0; x < m; x++) {
                map[cur | x] = readInt();
            }
        }
    }

    private static void solve() {

        for(int y = 0; y < n; y++) {
            int cur = y << BIT_SHIFT;
            for(int x = 0; x < m; x++) {
                // ㅜ 모양 제외 탐색
                visited[cur | x] = true;
                backtracking(cur | x, map[cur | x], 1);
                visited[cur | x] = false;

                // ㅜ 모양 탐색
                search(cur | x);
            }
        }
    }

    private static void backtracking(int cur, int sum, int cnt) {
        if(cnt == BLOCK) {
            result = Math.max(sum, result);
            return;
        }

        for(int d : DICT) {
            int next = d + cur;
            if(check(next) && !visited[next]) {
                visited[next] = true;
                backtracking(next, sum + map[next], cnt + 1);
                visited[next] = false;
            }
        }
    }

    private static void search(int cur) {
        int sum = map[cur];
        int cnt = 0, min = 1001;
        for(int d : DICT) {
            int next = d + cur;
            if(!check(next)) continue;
            min = Math.min(map[next], min);
            sum += map[next];
            cnt++;
        }

        if(cnt == 3) result = Math.max(sum, result);
        if(cnt == 4) result = Math.max(sum - min, result);
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
