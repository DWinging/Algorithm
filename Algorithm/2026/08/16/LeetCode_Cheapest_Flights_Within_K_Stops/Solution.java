import java.util.*;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] list = new ArrayList[n];

        for(int i = 0; i < n; i++) 
            list[i] = new ArrayList<>();

        for(int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];

            list[from].add(new int[]{to, cost});
        }

        int[][] dist = new int[n][k + 2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= k + 1; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            else return a[2] - b[2];
        });
        pq.add(new int[]{src, 0, 0});
        dist[src][0] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int from = cur[0];
            int cost = cur[1];
            int cnt = cur[2];

            if(from == dst) return cost;
            if(cnt == k + 1 || dist[from][cnt] < cost) continue;

            for(int[] next : list[from]) {
                int to = next[0];
                int nCost = cost + next[1];
                if(dist[to][cnt + 1] > nCost) {
                    pq.add(new int[]{to, nCost, cnt + 1});
                    dist[to][cnt + 1] = nCost;
                }
            }
        }

        return -1;
    }
}