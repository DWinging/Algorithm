package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_14890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for(int i = 0; i < n; i++) {
//            if(checkRow(map, n, k, i)) cnt++;
//            if(checkCol(map, n, k, i)) cnt++;
        }
        System.out.println(cnt);
    }
}

