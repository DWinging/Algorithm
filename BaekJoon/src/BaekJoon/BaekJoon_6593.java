package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_6593 {

    static int L, R, C;
    static int[][][] visit;
    static String[][] building;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L == 0 && R == 0 && C == 0){
                break;
            }

            visit = new int[R][C][L];
            int x = -1, y = -1, z = -1;
            building = new String[R][L];
            for(int i = 0; i < L; i++){
                for(int j = 0; j < R; j++){
                    String temp = br.readLine();
                    building[j][i] = temp;
                    for(int k = 0; k < C && x == -1; k++){
                        if(temp.charAt(k) == 'S'){
                            x = j;
                            y = k;
                            z = i;
                            break;
                        }
                    }
                }
                br.readLine();
            }

            sb.append(bfs(x, y, z)).append("\n");
        }
        System.out.println(sb);
    }

    private static String bfs(int r, int c, int l){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {r, c, l});
        while(!que.isEmpty()){
            int x = que.peek()[0];
            int y = que.peek()[1];
            int z = que.peek()[2];
            que.poll();

            int[] xList = {1, -1, 0, 0};
            int[] yList = {0, 0, 1, -1};
            int[] zList = {1, -1};

            for(int i = 0; i < 4; i++){
                int tempX = x + xList[i];
                int tempY = y + yList[i];

                if(tempX >= 0 && tempX < R && tempY >= 0 && tempY < C){
                    if(building[tempX][z].charAt(tempY) == '.' && visit[tempX][tempY][z] == 0){
                        visit[tempX][tempY][z] = visit[x][y][z] + 1;
                        que.add(new int[]{tempX, tempY, z});
                        continue;
                    }
                    if(building[tempX][z].charAt(tempY) == 'E'){
                        return "Escaped in " + (visit[x][y][z] + 1) + " minute(s).";
                    }
                }
            }

            for(int i = 0; i < 2; i++){
                int tempZ = z + zList[i];
                if(tempZ >= 0 && tempZ < L){
                    if(building[x][tempZ].charAt(y) == '.' && visit[x][y][tempZ] == 0) {
                        visit[x][y][tempZ] = visit[x][y][z] + 1;
                        que.add(new int[]{x, y, tempZ});
                        continue;
                    }
                    if(building[x][tempZ].charAt(y) == 'E'){
                        return "Escaped in " + (visit[x][y][z] + 1) + " minute(s).";
                    }
                }
            }
        }

        return "Trapped!";
    }
}
