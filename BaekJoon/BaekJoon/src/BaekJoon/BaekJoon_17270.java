package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// resolving dijkstra
public class BaekJoon_17270 {

    static ArrayList<ArrayList<Location>> list;
    static int[][] dict;
    static final int INF = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dict = new int[2][v+1];
        list = new ArrayList<>();
        for(int i = 0; i <= v; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            list.get(v1).add(new Location(v2, t));
            list.get(v2).add(new Location(v1, t));
        }

        st = new StringTokenizer(br.readLine());
        int j = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        dijkstra(j, 0);
        dijkstra(s, 1);

        System.out.println(getLocation(v, j, s));
    }

    private static void dijkstra(int v, int idx) {
        Arrays.fill(dict[idx], INF);
        dict[idx][v] = 0;
        PriorityQueue<Location> pq = new PriorityQueue<>((t1, t2) -> t1.time - t2.time);
        pq.offer(new Location(v, 0));

        while(!pq.isEmpty()) {
            Location now = pq.poll();
            int cur = now.point;
            int curTime = now.time;
            if(curTime > dict[idx][cur]) continue;
            for(Location next : list.get(cur)) {
                int nextPoint = next.point;
                int nextTime = curTime + next.time;
                if(dict[idx][nextPoint] > nextTime) {
                    dict[idx][nextPoint] = nextTime;
                    pq.offer(new Location(nextPoint, nextTime));
                }
            }
        }
    }

    private static int getLocation(int v, int j, int s) {
        int minTime = INF;
        int location = -1;
        int jTime = INF;
        for(int i = 1; i <= v; i++){
            if(i == j || i == s) continue;
            int time = dict[0][i] + dict[1][i];
            if(minTime > time) {
                if(dict[0][i] > dict[1][i]) {
                    location = -1;
                    jTime = INF;
                }
                else {
                    location = i;
                    jTime = dict[0][i];
                }
                minTime = time;
            }
            else if(minTime == time && jTime > dict[0][i]) {
                if(dict[0][i] <= dict[1][i] && dict[0][i] < jTime) {
                    location = i;
                    jTime = dict[0][i];
                }
            }
        }
        return location;
    }

    private static class Location {
        int point;
        int time;

        Location(int point, int time) {
            this.point = point;
            this.time = time;
        }
    }
}
