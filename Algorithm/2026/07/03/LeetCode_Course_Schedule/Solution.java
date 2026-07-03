import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] list = new ArrayList[numCourses];
        int[] cnt = new int[numCourses];
        
        for(int i = 0; i < numCourses; i++) {
            list[i] = new ArrayList<>();
        } 

        for(int[] pre : prerequisites) {
            int a = pre[0];
            int b = pre[1];

            cnt[a]++;
            list[b].add(a);
        }

        return topologicalSort(list, cnt, numCourses);
    }

    private boolean topologicalSort(ArrayList<Integer>[] list, int[] cnt, int n) {
        int[] que = new int[n];
        int head = 0, tail = 0;

        for(int i = 0; i < n; i++) {
            if(cnt[i] == 0) que[tail++] = i;
        }

        while(head < tail) {
            int total = tail - head;
            while(total-- > 0) {
                int cur = que[head++];

                for(int i : list[cur]) {
                    cnt[i]--;
                    if(cnt[i] == 0) {
                        que[tail++] = i;
                    }
                }
            }
        }

        return tail == n;
    }
}