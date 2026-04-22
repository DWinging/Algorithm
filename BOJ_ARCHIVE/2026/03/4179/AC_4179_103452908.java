/**
 * [BOJ] 4179 - 불!
 * - 제출 날짜: 2026년 3월 3일
 * - 결과: 맞았습니다!!
 * - 메모리: 51080 KB
 * - 시간: 452 ms
 */

import java.io.*;
import java.util.*;

public class Main{
	
	static Deque<int[]> deque;
	static int[][] dict = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static char[][] area;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        deque = new ArrayDeque<>();
        area = new char[n][m];
        for(int y = 0; y < n; y++) {
            String line = br.readLine();
            for(int x = 0; x < m; x++) {
                area[y][x] = line.charAt(x);
                if(area[y][x] == 'F') deque.addLast(new int[] {y, x, 'F', 0});
            }
        }
        
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(area[y][x] == 'J') {
                    deque.addLast(new int[] {y, x, 'J', 0});
                    area[y][x] = '-';
                    break;
                }
            }
        }
		System.out.println(bfs(n, m));
	}
	
	private static String bfs(int n, int m) {
		while(!deque.isEmpty()) {
			int[] cur = deque.pollFirst();
			int cy = cur[0];
			int cx = cur[1];
			char z = (char) cur[2];
			int time = cur[3];
			
			for(int[] d: dict) {
				int ny = cy + d[0];
				int nx = cx + d[1];
				if(z == 'J') {
					if(!check(ny, nx, n, m)) {
						return String.valueOf(time + 1);
					}
					if(area[ny][nx] == '.') {
						deque.addLast(new int[] {ny, nx, z, time + 1});
						area[ny][nx] = '-';
					}
				}
				else {
					if(check(ny, nx, n, m) && (area[ny][nx] == '.' || area[ny][nx] == '-') ) {
						deque.addLast(new int[] {ny, nx, z, time + 1});
						area[ny][nx] = 'F';
					}
				}
			}
		}
		
		return "IMPOSSIBLE";		
	}
	
	private static boolean check(int y, int x, int n, int m) {
		return y >= 0 && y < n && x >= 0 && x < m;
	}
}
