class Solution {
    public int minGroups(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(intervals, (l1, l2) -> {
            if(l1[0] != l2[0]) return l1[0] - l2[0];
            else return l1[1] - l2[1];
        });

        for(int[] interval : intervals) {
            if(!pq.isEmpty() && pq.peek() < interval[0]) {
                pq.poll();
            } 
            pq.add(interval[1]);
        }

        return pq.size();
    }
}