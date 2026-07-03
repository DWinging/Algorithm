import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] list = new ArrayList[numCourses];
        int[] cnt = new int[numCourses];

        for(int i = 0; i < numCourses; i++) {
            list[i] = new ArrayList<>();
        }

        for(int pre[] : prerequisites) {
            int a = pre[0];
            int b = pre[1];

            cnt[a]++;
            list[b].add(a);
        }

        return topologicalSort(list, cnt, numCourses);
    }

    private int[] topologicalSort(ArrayList<Integer>[] list, int[] cnt, int n) {
        int[] que = new int[n];
        int head = 0, tail = 0;

        for(int i = 0; i < n; i++) {
            if(cnt[i] == 0) que[tail++] = i;
        }

        while(head < tail) {
            int cur = que[head++];
            for(int next : list[cur]) {
                cnt[next]--;
                if(cnt[next] == 0) {
                    que[tail++] = next;
                }
            }
        }

        return tail == n ? que : new int[0];
    }
}