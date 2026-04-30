package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class BaekJoon_11725 {
    static boolean[] visit;
    static int[][] matrix;
    static int n;
    static int m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visit = new boolean[n+1];

        matrix = new int[n+1][n+1];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            matrix[n1][n2] = 1;
            matrix[n2][n1] = 1;
        }

        int cnt = 0;
        for(int i = 1; i < n+1; i++) {
            if (!visit[i]) {
                bfs(i);
                cnt += 1;
            }
        }

        System.out.println(cnt);
    }

    private static void bfs(int node) {

        Queue<Integer> que = new LinkedList<>();
        que.add(node);
        while (!que.isEmpty()) {
            int v = que.poll();
            visit[v] = true;

            for(int j = 1; j < n+1; j++){
                if(!visit[j] && matrix[v][j] == 1){
                    que.add(j);
                }
            }
        }
    }
}
