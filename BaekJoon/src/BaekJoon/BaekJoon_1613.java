package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dict = new int[N+1][N+1];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            dict[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                if(i == k) continue;
                for(int j = 1; j <= N; j++){
                    if(i == j || j == k) continue;
                    if(dict[i][k] == 1 && dict[k][j] == 1){
                        dict[i][j] = 1;
                    }
                }
            }
        }

        int S = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < S; i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            sb.append(dict[num2][num1] - dict[num1][num2]).append("\n");
        }
        System.out.println(sb);
    }
}
