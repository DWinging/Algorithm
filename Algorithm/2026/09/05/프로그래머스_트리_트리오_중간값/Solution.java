import java.util.*;

class Solution {
    
    int[] visited, que;
    
    public int solution(int n, int[][] edges) {
        
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        visited = new int[n + 1];
        que = new int[n + 1];
        
        List<Integer>[] list = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            
            list[v1].add(v2);
            list[v2].add(v1);
        }
        
        int node1 = bfs(list, dist1, 1, 1);
        int node2 = bfs(list, dist1, node1, 2);
        bfs(list, dist2, node2, 3);
        
        return solve(dist1, dist2, node1, node2, n);       
    }
    
    private int bfs(
        List<Integer>[] list,
        int[] dist,
        int node,
        int mark
    ) {
        visited[node] = mark;
        dist[node] = 0;
        int head = 0, tail = 0;
        que[tail++] = node;
        
        while(head < tail) {
            int cur = que[head++];

            for(int next : list[cur]) {
                if(visited[next] < mark) {
                    que[tail++] = next;
                    dist[next] = dist[cur] + 1;
                    visited[next] = mark;
                }
            }
        }
        
        return que[head - 1];
    }
    
    private int solve(
        int[] dist1,
        int[] dist2,
        int node1,
        int node2,
        int n
    ) {
        int len = 0, val = dist1[node2];
        for(int i = 1; i <= n; i++) {
            if(i == node1 || i == node2) continue;
            
            int mid = dist1[i] < dist2[i] ? dist2[i] : dist1[i];
            if(len < mid) len = mid;
        }
        
        return len; 
    }
}