package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_2665 {
    static int n;
    static boolean[][] visit;
    static String[] mero;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visit = new boolean[n][n];
        mero = new String[n];

        for(int i = 0; i < n; i++){
            mero[i] = br.readLine();
        }
        System.out.println(bfs());
    }

    private static int bfs(){
        PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        que.add(new int[]{0, 0, 0});
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        visit[0][0] = true;
        int count = 0;

        while(!que.isEmpty()){
            count = que.peek()[0];
            int x = que.peek()[1];
            int y = que.peek()[2];
            que.poll();

            if(x == n-1 && y == n-1){
                return count;
            }

            for(int i = 0; i < 4; i++){
                int rx = x + dx[i];
                int ry = y + dy[i];
                if(rx >= 0 && rx < n && ry >= 0 && ry < n && !visit[rx][ry]){
                    if(mero[rx].charAt(ry) == '1'){
                        que.add(new int[]{count, rx, ry});
                    }
                    else {
                        que.add(new int[]{count+1, rx, ry});
                    }
                    visit[rx][ry] = true;
                }
            }
        }

        return 0;
    }
}

