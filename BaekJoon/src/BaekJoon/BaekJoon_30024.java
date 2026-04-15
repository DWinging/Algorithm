package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_30024 {

    static boolean[][] visit;
    static int[][] field;
    static PriorityQueue<Field> que;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visit = new boolean[n][m];
        field = new int[n][m];
        que = new PriorityQueue<>((f1, f2) -> Integer.compare(f2.value, f1.value));
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            if(!visit[i][0]) {
                visit[i][0] = true;
                que.offer(new Field(i, 0, field[i][0]));
            }
            if(!visit[i][m-1]) {
                visit[i][m-1] = true;
                que.offer(new Field(i, m-1, field[i][m-1]));
            }
        }

        for(int i = 0; i < m; i++){
            if(!visit[0][i]){
                visit[0][i] = true;
                que.offer(new Field(0, i, field[0][i]));
            }
            if(!visit[n-1][i]) {
                visit[n-1][i] = true;
                que.offer(new Field(n-1, i, field[n-1][i]));
            }
        }

        int k = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while(k-- > 0) {
            Field f = que.poll();
            sb.append(f.x + 1).append(" ").append(f.y + 1).append("\n");
            for(int i = 0; i < 4; i++){
                int rx = dx[i] + f.x;
                int ry = dy[i] + f.y;
                if(rx >= 0 && rx < n && ry >= 0 && ry < m && !visit[rx][ry]) {
                    visit[rx][ry] = true;
                    que.add(new Field(rx, ry, field[rx][ry]));
                }
            }
        }

        System.out.println(sb);
    }


    private static class Field {
        int x;
        int y;
        int value;

        Field(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
