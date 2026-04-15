package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_7569 {

    static int n;
    static int m;
    static int floor;

    static ArrayList<int[][]> visit;
    static ArrayList<int[][]> tomato;

    static Queue<int[]> que;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        que = new LinkedList<>();

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        floor = Integer.parseInt(st.nextToken());

        visit = new ArrayList<int[][]>();
        tomato = new ArrayList<int[][]>();

        for(int i = 0; i < floor; i++){
            visit.add(new int[n][m]);
            tomato.add(new int[n][m]);

            for(int j = 0; j < n; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < m; k++){
                    tomato.get(i)[j][k] = Integer.parseInt(st.nextToken());
                    if(tomato.get(i)[j][k] == 1){
                        que.add(new int[]{i, j, k});
                        visit.get(i)[j][k] = 1;
                    }
                }
            }
        }

        dfs();

        int answer = 0;
        for(int i = 0; i < visit.size(); i++){
            int temp = maxValue(i);
            if(temp == -1){
                answer = 0;
                break;
            }
            else {
                answer = Math.max(answer, temp);
            }
        }

        System.out.println(answer - 1);
    }

    static int maxValue(int index){
        int value = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visit.get(index)[i][j] == 0 && tomato.get(index)[i][j] == 0){
                    return -1;
                }
                value = Math.max(value, visit.get(index)[i][j]);
            }
        }

        return value;
    }

    static void dfs(){

        int[] x = {1, -1, 0, 0};
        int[] y = {0, 0, 1, -1};

        while(!que.isEmpty()){
            int f = que.peek()[0];
            int row = que.peek()[1];
            int col = que.peek()[2];
            que.poll();

            if(f - 1 >= 0 && tomato.get(f - 1)[row][col] == 0 && visit.get(f - 1)[row][col] == 0){
                visit.get(f - 1)[row][col] = visit.get(f)[row][col] + 1;
                que.add(new int[] { f-1, row, col});
            }

            if(f + 1 < floor && tomato.get(f + 1)[row][col] == 0 && visit.get(f + 1)[row][col] == 0){
                visit.get(f + 1)[row][col] = visit.get(f)[row][col] + 1;
                que.add(new int[] { f+1, row, col});
            }

            for(int i = 0; i < 4; i++){
                int tempX = row + x[i];
                int tempY = col + y[i];

                if(tempX >= 0 && tempX < n && tempY >= 0 && tempY < m && tomato.get(f)[tempX][tempY] == 0 && visit.get(f)[tempX][tempY] == 0){
                    visit.get(f)[tempX][tempY] = visit.get(f)[row][col] + 1;
                    que.add(new int[] {f, tempX, tempY});
                }
            }
        }

    }
}
