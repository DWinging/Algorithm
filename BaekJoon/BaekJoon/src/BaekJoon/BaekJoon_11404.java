package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int money = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] list = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                list[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < money; i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken())-1;
            int num2 = Integer.parseInt(st.nextToken())-1;
            int temp = Integer.parseInt(st.nextToken());

            list[num1][num2] = Math.min(list[num1][num2], temp);
        }

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                if(i == k || list[i][k] == Integer.MAX_VALUE) continue;
                for(int j = 0; j < N; j++){
                    if(j == k || i == j || list[k][j] == Integer.MAX_VALUE) continue;
                    list[i][j] = Math.min(list[i][j], list[i][k] + list[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int[] i : list){
            for(int j : i){
                sb.append(j == Integer.MAX_VALUE ? 0 : j).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
