package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_21610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new  OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];
        boolean[][] visited = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{n-1, 0});
        deque.addFirst(new int[]{n-1, 1});
        deque.addFirst(new int[]{n-2, 0});
        deque.addFirst(new int[]{n-2, 1});

        int[][] dict = {{1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}};

        Deque<int[]> cloud = new ArrayDeque<>();
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) % dict.length;
            int s = Integer.parseInt(st.nextToken());

            while(!deque.isEmpty()) {
                int[] cur = deque.pollFirst();
                int ny = (cur[0] + dict[d][0] * s);
                int nx = (cur[1] + dict[d][1] * s);

                ny = (ny % n + n) % n;
                nx = (nx % n + n) % n;

                cloud.addLast(new int[]{ny, nx});
                visited[ny][nx] = true;
                arr[ny][nx]++;
            }

            while(!cloud.isEmpty()) {
                int[] cur = cloud.pollFirst();
                int y = cur[0];
                int x = cur[1];

                int cnt = 0;
                for(int i = 0; i < dict.length; i += 2) {
                    int ny = y + dict[i][0];
                    int nx = x + dict[i][1];
                    if(check(ny, nx, n) && arr[ny][nx] != 0) cnt++;
                }

                arr[y][x] += cnt;
            }

            for(int y = 0; y < n; y++) {
                for(int x = 0; x < n; x++) {
                    if(arr[y][x] >= 2 && !visited[y][x]) {
                        deque.addLast(new int[]{y, x});
                        arr[y][x] -= 2;
                    }
                    if(visited[y][x]) {
                        visited[y][x] = false;
                    }
                }
            }
        }

        int total = 0;
        for(int[] row : arr) {
            for(int x : row) {
                total += x;
            }
        }
        bw.write(total + "");
        bw.flush();
        bw.close();
    }

    private static boolean check(int y, int x, int n) {
        return y >= 0 && y < n && x >= 0 && x< n;
    }
}
