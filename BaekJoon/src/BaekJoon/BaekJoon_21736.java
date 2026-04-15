package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_21736 {

    static boolean[][] visit;
    static int cnt = 0;
    static String[][] matrix;

    static Queue<int[]> que;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        matrix = new String[n][m];
        visit = new boolean[n][m];

        que = new LinkedList<>();

        for(int i = 0; i < n; i++){
            String[] temp = br.readLine().split("");

            for(int j = 0; j < temp.length; j++){
                matrix[i][j] = temp[j];

                if(que.isEmpty() && temp[j].equals("I")){
                    que.add(new int[]{i, j});
                }
            }
        }

        dfs();

        if(cnt == 0){
            System.out.println("TT");
        }
        else{
            System.out.println(cnt);
        }
    }

    public static void dfs(){

        while(!que.isEmpty()){
            int w = que.peek()[0];
            int h = que.peek()[1];
            que.poll();

            if(visit[w][h]){
                continue;
            }

            visit[w][h] = true;
            if(matrix[w][h].equals("P")){
                cnt += 1;
            }

            int[] x_dir = {1, -1, 0, 0};
            int[] y_dir = {0, 0, 1, -1};

            for(int i = 0; i < 4; i++){
                int x = w + x_dir[i];
                int y = h + y_dir[i];

                if(x >= 0 && y >= 0 && x < n && y < m && !visit[x][y]){
                    if(matrix[x][y].equals("O") || matrix[x][y].equals("P")){
                        que.add(new int[]{x, y});
                    }
                }
            }
        }
    }

    public static class BaekJoon_15990 {

        final static int MOD = 1_000_000_009;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine());
            int[] testCase = new int[n];
            int maxValue = 0;
            for(int i = 0; i < n; i++) {
                int temp = Integer.parseInt(br.readLine());
                testCase[i] = temp;
                maxValue = Math.max(maxValue, temp);
            }
            int[][] dp = setDp(maxValue);
            for(int i : testCase) {
                sb.append(sumOf(dp[i])).append("\n");
            }
            System.out.println(sb);
        }

        private static int[][] setDp(int maxValue) {
            int[][] dp = new int[maxValue + 1][4];
            dp[1][1] = 1;
            dp[2][2] = 1;
            dp[3][1] = dp[3][2] = dp[3][3] = 1;

            for(int i = 4; i <= maxValue; i++) {
                for(int j = 1; j <= 3; j++) {
                    for(int k = 1; k <= 3; k++) {
                        if(j == k) continue;
                        dp[i][j] = (dp[i][j] + dp[i - j][k]) % MOD;
                    }
                }
            }
            return dp;
        }

        private static int sumOf(int[] list) {
            int sum = 0;
            for(int i : list ){
                sum = (sum + i) % MOD;
            }
            return sum;
        }
    }
}