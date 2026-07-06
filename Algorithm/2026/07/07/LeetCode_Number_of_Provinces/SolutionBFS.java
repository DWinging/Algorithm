class Solution {

    int[] que;

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;

        boolean[] visited = new boolean[n];
        que = new int[n];
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                bfs(isConnected, visited, i);
                cnt++;
            }
        }

        return cnt;
    }

    private void bfs(int[][] isConnected, boolean[] visited, int s) {        
        int head = 0, tail = 0;
        que[tail++] = s;
        visited[s] = true;
        while(head < tail) {
            int cur = que[head++];

            for(int i = 0; i < isConnected[cur].length; i++) {
                if(isConnected[cur][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    que[tail++] = i;
                }
            }
        }
    }
}