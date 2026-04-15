package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_3055 {

    static int R, C;
    static int[][] visit;
    static String[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visit = new int[R][C];
        map = new String[R];

        int s_x = -1;
        int s_y = -1;

        for(int i = 0; i < R; i++){
            map[i] = br.readLine();
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(map[i].charAt(j) == '*'){
                    visit[i][j] = 1;
                    w_bfs(i, j);
                    continue;
                }
                if(map[i].charAt(j) == 'S'){
                    s_x = i;
                    s_y = j;
                }
            }
        }

        visit[s_x][s_y] = 1;
        System.out.println(bfs(s_x, s_y));
    }

    private static void w_bfs(int r, int c){
        Deque<int[]> que = new ArrayDeque<>();
        que.addLast(new int[]{r, c});

        int[] xList = {1, -1, 0, 0};
        int[] yList = {0, 0, 1, -1};

        while(!que.isEmpty()){
            int x = que.peekFirst()[0];
            int y = que.peekFirst()[1];
            que.pollFirst();

            for(int i = 0; i < 4; i++){
                int tempX = xList[i] + x;
                int tempY = yList[i] + y;
                if(tempX >= 0 && tempX < R && tempY >= 0 && tempY < C && map[tempX].charAt(tempY) == '.' && (visit[tempX][tempY] == 0 || visit[tempX][tempY] > visit[x][y] + 1)){
                    visit[tempX][tempY] = visit[x][y] + 1;
                    que.addLast(new int[]{tempX, tempY});
                }
            }
        }
    }

    private static String bfs(int r, int c){
        Deque<int[]> que = new ArrayDeque<>();
        que.addLast(new int[] {r, c});

        int[] xList = {1, -1, 0, 0};
        int[] yList = {0, 0, 1, -1};

        while(!que.isEmpty()){
            int x = que.peekFirst()[0];
            int y = que.peekFirst()[1];
            que.pollFirst();

            for(int i = 0; i < 4; i++){
                int tempX = xList[i] + x;
                int tempY = yList[i] + y;
                if(tempX >= 0 && tempX < R && tempY >= 0 && tempY < C){
                    if(map[tempX].charAt(tempY) == 'D'){
                        return String.valueOf(visit[x][y]);
                    }
                    if(map[tempX].charAt(tempY) == '.' && (visit[tempX][tempY] == 0 || visit[tempX][tempY] > visit[x][y] + 1)){
                        visit[tempX][tempY] = visit[x][y] + 1;
                        que.addLast(new int[] {tempX, tempY});
                    }
                }
            }
        }

        return "KAKTUS";
    }
}
