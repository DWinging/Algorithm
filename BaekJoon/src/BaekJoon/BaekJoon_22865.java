package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_22865 {

    static ArrayList<ArrayList<Road>> road;
    static int n;
    static final int INF = 1000000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        road = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            road.add(new ArrayList<>());
        }

        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            road.get(s).add(new Road(e, w));
            road.get(e).add(new Road(s, w));
        }

        int[] dictA = dijkstra(a);
        int[] dictB = dijkstra(b);
        int[] dictC = dijkstra(c);

        int max = 0;
        int index = 0;
        for(int i = 1; i < n+1; i++){
            if(i == a || i == b || i == c) continue;
            int len = Math.min(dictA[i], Math.min(dictB[i], dictC[i]));
            if(len > max) {
                max = len;
                index = i;
            }
        }

        System.out.println(index);
    }

    private static int[] dijkstra(int start) {

        boolean[] visit = new boolean[n+1];
        int[] dict = new int[n+1];
        Arrays.fill(dict, INF);
        dict[start] = 0;
        PriorityQueue<Road> pq = new PriorityQueue<>((l1, l2) -> l1.len - l2.len);
        pq.add(new Road(start, 0));

        while(!pq.isEmpty()) {
            Road now = pq.poll();
            int cur = now.end;
            if(!visit[cur]) {
                visit[cur] = true;
                for(Road next : road.get(cur)) {
                    if(!visit[next.end] && dict[next.end] > dict[cur] + next.len) {
                        dict[next.end] = dict[cur] + next.len;
                        pq.add(new Road(next.end, dict[next.end]));
                    }
                }
            }
        }

        return dict;
    }

    private static class Road {
        int end;
        int len;

        Road(int end, int len) {
            this.end = end;
            this.len = len;
        }
    }
}
