package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1865 {

    static ArrayList<ArrayList<Road>> road;
    static int n;
    static int[] dist;
    static final int INF = 25000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            road = new ArrayList<ArrayList<Road>>();
            dist = new int[n+1];

            for(int i = 0; i <= n; i++){
                road.add(new ArrayList<>());
            }

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                road.get(start).add(new Road(end, weight));
                road.get(end).add(new Road(start, weight));
            }

            for(int i = 0; i < w; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                road.get(start).add(new Road(end, -weight));
            }

            sb.append(solve());
        }

        System.out.println(sb);
    }

    private static String solve() {
        for(int i = 1; i <= n; i++){
            if(bellmanford(i)) {
                return "YES\n";
            }
        }
        return "NO\n";
    }

    private static boolean bellmanford(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean update = false;
        for(int i = 1; i < n; i++){
            update = false;
            for(int j = 1; j <= n; j++){
                for(Road r : road.get(j)) {
                    if(dist[j] != INF && dist[r.end] > dist[j] + r.weight) {
                        dist[r.end] = dist[j] + r.weight;
                        update = true;
                    }
                }
            }

            if(!update) {
                break;
            }
        }

        if(update) {
            for(int j = 1; j <= n; j++){
                for(Road r : road.get(j)) {
                    if(dist[j] != INF && dist[r.end] > dist[j] + r.weight) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static class Road {
        int end;
        int weight;

        Road(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
}
