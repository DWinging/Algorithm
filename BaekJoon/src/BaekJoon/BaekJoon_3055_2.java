package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class BaekJoon_3055_2 {
    static int R, C;
    static int[][] visit;
    static String[] map;
    static int[] xList = {1, -1, 0, 0};
    static int[] yList = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visit = new int[R][C];
        map = new String[R];

        Queue<int[]> water = new LinkedList<>();
        Queue<int[]> s = new LinkedList<>();

        for(int i = 0; i < R; i++){
            map[i] = br.readLine();
            for(int j = 0; j < C; j++){
                if(map[i].charAt(j) == '*'){
                    visit[i][j] = 1;
                    water.add(new int[]{i, j});
                    continue;
                }
                if(map[i].charAt(j) == 'S'){
                    s.add(new int[]{i, j});
                }
            }
        }

        w_bfs(water);
        System.out.println(bfs(s));
    }

    private static void w_bfs(Queue<int[]> que){
        while(!que.isEmpty()){
            int x = que.peek()[0];
            int y = que.peek()[1];
            que.poll();

            for(int i = 0; i < 4; i++){
                int tempX = xList[i] + x;
                int tempY = yList[i] + y;
                if(tempX >= 0 && tempX < R && tempY >= 0 && tempY < C && map[tempX].charAt(tempY) == '.' && (visit[tempX][tempY] == 0 || visit[tempX][tempY] > visit[x][y] + 1)){
                    visit[tempX][tempY] = visit[x][y] + 1;
                    que.add(new int[]{tempX, tempY});
                }
            }
        }
    }

    private static String bfs(Queue<int[]> que){
        visit[que.peek()[0]][que.peek()[1]] = 1;

        while(!que.isEmpty()){
            int x = que.peek()[0];
            int y = que.peek()[1];
            que.poll();

            for(int i = 0; i < 4; i++){
                int tempX = xList[i] + x;
                int tempY = yList[i] + y;
                if(tempX >= 0 && tempX < R && tempY >= 0 && tempY < C){
                    if(map[tempX].charAt(tempY) == 'D'){
                        return String.valueOf(visit[x][y]);
                    }
                    if(map[tempX].charAt(tempY) == '.' && (visit[tempX][tempY] == 0 || visit[tempX][tempY] > visit[x][y] + 1)){
                        visit[tempX][tempY] = visit[x][y] + 1;
                        que.add(new int[] {tempX, tempY});
                    }
                }
            }
        }

        return "KAKTUS";
    }
}
