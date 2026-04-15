package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_2206 {
    static int row;
    static int col;
    static int[][][] visit;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        visit = new int[row][col][2];
        map = new int[row][col];
        for(int i = 0; i < row; i++){
            String temp = br.readLine();
            for(int j = 0; j < col; j++){
                map[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j)));
            }
        }

        bfs();

        if(visit[row-1][col-1][0] == 0 && visit[row-1][col-1][1] == 0){
            System.out.println(-1);
        }
        else if(visit[row-1][col-1][0] == 0){
            System.out.println(visit[row-1][col-1][1]);
        }
        else if(visit[row-1][col-1][1] == 0){
            System.out.println(visit[row-1][col-1][0]);
        }
        else {
            System.out.println(Math.min(visit[row-1][col-1][0], visit[row-1][col-1][1]));
        }
    }

    static void bfs(){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{0, 0});
        visit[0][0][0] = 1;
        while(!que.isEmpty()){
            int x = que.peek()[0];
            int y = que.peek()[1];
            que.poll();

            int[] xList = {1, -1, 0, 0};
            int[] yList = {0, 0, 1, -1};

            for(int i = 0; i < 4; i++){
                int tempX = x + xList[i];
                int tempY = y + yList[i];

                if(tempX >= 0 && tempX < row && tempY >= 0 && tempY < col){
                    if(map[tempX][tempY] == 0 && visit[tempX][tempY][0] == 0){
                        visit[tempX][tempY][0] = visit[x][y][0] + 1;
                        que.add(new int[]{tempX, tempY});
                    }
                    else if(map[tempX][tempY] == 1){
                        crashBfs(tempX, tempY, visit[x][y][0] + 1);
                    }
                }
            }
        }
    }

    static void crashBfs(int r, int c, int value){
        visit[r][c][1] = value;
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{r, c});

        while(!que.isEmpty()){
            int x = que.peek()[0];
            int y = que.peek()[1];
            que.poll();

            int[] xList = {1, -1, 0, 0};
            int[] yList = {0, 0, 1, -1};

            for(int i = 0; i < 4; i++){
                int tempX = x + xList[i];
                int tempY = y + yList[i];

                if(tempX >= 0 && tempX < row && tempY >= 0 && tempY < col){
                    if(map[tempX][tempY] == 0 && (visit[tempX][tempY][1] == 0 || visit[tempX][tempY][1] > visit[x][y][1] + 1)){
                        visit[tempX][tempY][1] = visit[x][y][1] + 1;
                        que.add(new int[]{tempX, tempY});
                    }
                }
            }
        }
    }
}
