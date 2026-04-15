package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_17396 {

    static boolean[] ward;
    static ArrayList<ArrayList<Route>> route;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ward = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            ward[i] = st.nextToken().equals("0");
        }

        route = new ArrayList<>();
        for(int i = 0; i < n; i++){
            route.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int l1 = Integer.parseInt(st.nextToken());
            int l2 = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            route.get(l1).add(new Route(l2, t));
            route.get(l2).add(new Route(l1, t));
        }

        System.out.println(dijkstra(0, n));
    }

    private static long dijkstra(int start, int n){
        long[] dict = new long[n];
        Arrays.fill(dict, Long.MAX_VALUE);
        dict[0] = 0;
        PriorityQueue<Route> pq = new PriorityQueue<>((r1, r2) -> Long.compare(r1.time, r2.time));
        pq.offer(new Route(start, 0));
        while(!pq.isEmpty()) {
            Route now = pq.poll();
            int cur = now.location;
            long curTime = now.time;
            if(curTime > dict[cur]) continue;
            for(Route next : route.get(cur)) {
                int temp = next.location;
                long time = next.time + curTime;
                if((ward[temp] || temp == n-1) && dict[temp] > time) {
                    dict[temp] = time;
                    pq.offer(new Route(temp, time));
                }
            }
        }
        return dict[n-1] == Long.MAX_VALUE ? -1 : dict[n-1];
    }

    private static class Route {
        int location;
        long time;

        Route(int location, long time) {
            this.location = location;
            this.time = time;
        }
    }
}
