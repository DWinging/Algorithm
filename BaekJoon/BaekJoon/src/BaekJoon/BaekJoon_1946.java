package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine().trim());
        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] rank = new int[n + 1];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int s1 = Integer.parseInt(st.nextToken());
                int s2 = Integer.parseInt(st.nextToken());
                rank[s1] = s2;
            }

            int cnt = 1, score = rank[1];
            for(int i = 2; i <= n; i++) {
                if(score > rank[i]) {
                    score = rank[i];
                    cnt++;
                }
            }
            bw.write(cnt + "\n");
        }
        bw.flush();
        bw.close();
    }
}
