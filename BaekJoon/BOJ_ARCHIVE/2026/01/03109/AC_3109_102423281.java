/**
 * [BOJ] 3109 - 빵집
 * - 제출 날짜: 2026년 1월 29일
 * - 결과: 맞았습니다!!
 * - 메모리: 46712 KB
 * - 시간: 356 ms
 */

import java.io.*;
import java.util.StringTokenizer;

class Main {

    final static int[][] DICT = {{1, 1}, {0, 1}, {-1, 1}};
    final static int MAX_RANGE = 10_005;
    
    static int[][] visited;
    static int[] stack;
    static String[] area;
    static int n, m;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new int[n][m];

        stack = new int[m * 3 + 5];

        area = new String[n];
        for(int i = 0; i < n; i++) {
            area[i] = br.readLine();
        }

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(dfs(i, 0, i + 1)) cnt++;
        }
        System.out.println(cnt);
    }

    private static boolean dfs(int y, int idx, int v) {
        int top = 0;
        stack[top] = y * m + 0;
        while(top >= 0) {
            int cur = stack[top--];
            int cy = cur / m;
            int cx = cur % m;
            visited[cy][cx] = v;
            if(cx == m-1) return true;
            
            for(int[] d : DICT) {
                int ny = cy + d[0];
                int nx = cx + d[1];
                if(check(ny, nx) && area[ny].charAt(nx) == '.' && visited[ny][nx] == 0) {
                    int nc = ny * m + nx;
                    stack[++top] = nc;
                }
            }
        }
        return false;
    }

    private static boolean check(int y, int x) {
        return y >= 0 && y < n && x >= 0 && x < m;
    }
}