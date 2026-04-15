package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_12764 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] list = new int[n][2];
        int[][] com = new int[2][n];
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list, (l1, l2) -> l1[0] - l2[0]);
        for(int i = 0; i < n; i++){
            int s = list[i][0];
            int e = list[i][1];
            for(int j = 0; j < n; j++){
                if(s >= com[0][j]) {
                    com[0][j] = e;
                    com[1][j]++;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(int i : com[1]) {
            if(i == 0) break;
            sb.append(i).append(" ");
            cnt++;
        }
        System.out.println(cnt + "\n" + sb);
    }
}