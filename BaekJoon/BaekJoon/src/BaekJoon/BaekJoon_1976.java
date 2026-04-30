package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1976 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[][] route = inputRoute(n, br);
        int[] schedule = inputSchedule(m, br);

        floyd(route, n);
        System.out.println(checkSchedule(route, schedule) ? "YES" : "NO");
    }

    private static boolean[][] inputRoute(int n, BufferedReader br) throws IOException {
        boolean[][] route = new boolean[n][n];
        StringTokenizer st;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                route[i][j] = st.nextToken().equals("1");
            }
            route[i][i] = true;
        }
        return route;
    }

    private static int[] inputSchedule(int m, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] schedule = new int[m];
        for(int i = 0; i < m; i++) {
            schedule[i] = Integer.parseInt(st.nextToken())-1;
        }
        return schedule;
    }

    private static void floyd(boolean[][] route, int n) {
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                if(i == k || !route[i][k]) continue;
                for(int j = 0; j < n; j++) {
                    if(k != j && route[k][j]) route[i][j] = true;
                }
            }
        }
    }

    private static boolean checkSchedule(boolean[][] route, int[] schedule) {
        int currentCity = schedule[0];
        for(int nextCity : schedule) {
            if(!route[currentCity][nextCity]) return false;
            currentCity = nextCity;
        }
        return true;
    }
}
