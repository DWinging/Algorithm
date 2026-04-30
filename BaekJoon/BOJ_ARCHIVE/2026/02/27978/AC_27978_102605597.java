/**
 * [BOJ] 27978 - 보물 찾기 2
 * - 제출 날짜: 2026년 2월 4일
 * - 결과: 맞았습니다!!
 * - 메모리: 14968 KB
 * - 시간: 160 ms
 */

import java.io.IOException;
import java.util.Arrays;

class Main {

    final static int[][] DICT = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}, {-1, 0}, {0, -1}, {1, -1}, {-1, -1}};
    static int c;
    
    public static void main(String[] args) throws IOException{
        c = System.in.read();    
        int h = readInt();
        int w = readInt();

        char[][] map = readCharArray(h, w);
        int s = searchStart(map, h, w);
        System.out.println(bfs(map, h, w, s));
    }

    private static int searchStart(char[][] map, int h, int w) {
        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                if(map[i][j] == 'K') {
                    return i * w + j;
                }
            }
        }
        return -1;
    }

    private static int bfs(char[][] map, int h, int w, int s) {
        int head = 0, tail = 0, size = h * w, cnt = 0;
        int[] deque = new int[size];   
        int[] visited = new int[size];
        Arrays.fill(visited, size + 1);
        visited[s] = 0;
        deque[tail] = s;
        tail = (tail + 1) % size;
        cnt++;

        while(cnt > 0) {
            int cur = deque[head];
            int y = cur / w;
            int x = cur % w;
            int t = visited[cur];
            head = (head + 1) % size;
            cnt--;

            if(map[y][x] == '*') return t;
            
            for(int i = 0; i < DICT.length; i++) {
                int ny = y + DICT[i][0];
                int nx = x + DICT[i][1];
                if(!(check(ny, nx, h, w) && map[ny][nx] != '#')) continue;

                int nv = ny * w + nx;
                int nextTime = t + (i < 3 ? 0 : 1);
                if(visited[nv] > nextTime) {
                    visited[nv] = nextTime;
                    if(i < 3) {
                        head = (head - 1 + size) % size;
                        deque[head] = nv;
                    }
                    else {
                        deque[tail] = nv;
                        tail = (tail + 1) % size;
                    }
                    cnt++;
                }
            }
        }
        return -1;
    }

    private static boolean check(int y, int x, int h, int w) {
        return y >= 0 && y < h && x >= 0 && x < w;
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

    private static char[][] readCharArray(int h, int w) throws IOException {
        char[][] map = new char[h][w];
        for(int i = 0; i < h; i++) {
            while(c <= ' ') c = System.in.read();
            for(int j = 0; j < w; j++) {
                map[i][j] = (char) c;
                c = System.in.read();
            }
        }
        return map;
    }
}