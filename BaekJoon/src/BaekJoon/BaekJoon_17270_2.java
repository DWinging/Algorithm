package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_17270_2 {

    static int[][] dict;
    static final int INF = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dict = new int[v+1][v+1];
        for(int i = 0; i <= v; i++){
            Arrays.fill(dict[i], INF);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            dict[v1][v2] = Math.min(dict[v1][v2], t);
            dict[v2][v1] = Math.min(dict[v2][v1], t);
        }

        st = new StringTokenizer(br.readLine());
        int j = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        floyd(v);

        System.out.println(getLocation(v, j, s));
    }

    private static int getLocation(int v, int j, int s) {
        int minTime = INF;
        int location = -1;
        int jTime = INF;
        for(int i = 1; i <= v; i++) {
            if(i == j || i == s) continue;
            int time = dict[j][i] + dict[s][i];
            if(minTime > time) {
                if(dict[j][i] > dict[s][i]) {
                    location = -1;
                    jTime = INF;
                }
                else {
                    location = i;
                    jTime = dict[j][i];
                }
                minTime = time;
            }
            else if(minTime == time) {
                if(dict[j][i] <= dict[s][i] && jTime > dict[j][i]) {
                    location = i;
                    jTime = dict[j][i];
                }
            }
        }
        return location;
    }

    private static void floyd(int v) {
        for(int k = 1; k <= v; k++){
            for(int i = 1; i <= v; i++){
                if(i == k || dict[i][k] == INF) continue;
                for(int j = 1; j <= v; j++){
                    if(i == j || k == j || dict[k][j] == INF) continue;
                    if(dict[i][j] > dict[i][k] + dict[k][j]) {
                        dict[i][j] = dict[i][k] + dict[k][j];
                    }
                }
            }
        }
    }
}
