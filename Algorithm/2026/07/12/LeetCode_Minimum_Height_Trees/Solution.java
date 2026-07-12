import java.util.*;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        ArrayList<Integer>[] list = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        int[] cnt = new int[n];
        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            list[a].add(b);
            list[b].add(a);

            cnt[a]++;
            cnt[b]++;
        }

        int[] que = new int[n];
        int head = 0, tail = 0;

        for(int i = 0; i < n; i++) {
            if(cnt[i] <= 1) {
                que[tail++] = i;
                cnt[i] -= 2;
            }
        }

        while(n > 2) {
            int len = tail - head;
            while(len-- > 0) {
                int cur = que[head++];
                n--;
                for(int next : list[cur]) {
                    cnt[next]--;
                    if(cnt[next] == 1) {
                        que[tail++] = next;
                        cnt[next] -= 2;
                    }
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        while(head < tail) {
            res.add(que[head++]);
        }
        return res;
    }
}