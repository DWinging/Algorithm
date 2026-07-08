import java.util.*;

class Solution {
    public int scheduleCourse(int[][] courses) {
        int n = courses.length;
        Arrays.sort(courses, (l1, l2) -> l1[1] - l2[1]);

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for(int i = 0; i < n; i++) {
            int next = courses[i][0];
            int day = courses[i][1];

            if(time + next <= day) {
                pq.add(next);
                time += next;
            } else if(!pq.isEmpty() && pq.peek() > next) {
                time -= pq.poll();
                time += next;
                pq.add(next);
            }
        }

        return pq.size();
    }
}