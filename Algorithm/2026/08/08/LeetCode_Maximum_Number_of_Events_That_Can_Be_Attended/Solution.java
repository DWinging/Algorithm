import java.util.*;

class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> {
            if(a[1] != b[1]) return a[1] - b[1];
            else return b[0] - a[0];
        });

        int n = events.length;
        int max = events[n - 1][1];

        int[] parents = new int[max + 2];
        for(int i = 1; i <= max + 1; i++) {
            parents[i] = i;
        }

        int res = 0;
        for(int[] event : events) {
            int s = event[0];
            int e = event[1];

            int pS = find(s, parents);
            if(pS <= e) {
                parents[pS] = find(pS + 1, parents);
                res++;
            }
        }

        return res;
    }

    private int find(int p, int[] parents) {
        if(p == parents[p]) return p;
        else return parents[p] = find(parents[p], parents);
    }
}