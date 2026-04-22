/**
 * [BOJ] 1913 - 달팽이
 * - 제출 날짜: 2026년 2월 3일
 * - 결과: 맞았습니다!!
 * - 메모리: 69204 KB
 * - 시간: 312 ms
 */

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] dict = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[][] arr = new int[n][n];
        int y = 0, x = 0, d = 0;
        for(int i = n * n; i > 0; i--) {
            arr[y][x] = i;
            int ny = y + dict[d][0];
            int nx = x + dict[d][1];
            
            if(!(check(ny, nx, n) && arr[ny][nx] == 0)) {
                d = (d + 1) % 4;
                ny = y + dict[d][0];
                nx = x + dict[d][1];
            }
            
            y = ny;
            x = nx;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sb.append(arr[i][j]).append(' ');
                if(arr[i][j] == m) {
                    y = i + 1;
                    x = j + 1;
                }
            }
            sb.append('\n');
        }
        sb.append(y).append(' ').append(x);
        System.out.println(sb);
    }

    private static boolean check(int y, int x, int n) {
        return y >= 0 && y < n && x >= 0 && x < n;
    }
}