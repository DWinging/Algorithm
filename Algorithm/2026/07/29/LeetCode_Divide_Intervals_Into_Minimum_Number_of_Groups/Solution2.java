class Solution {

    final static int SIZE = 1_000_000;

    public int minGroups(int[][] intervals) {
        int[] cnt = new int[SIZE + 2];

        for(int[] interval : intervals) {
            cnt[interval[0]]++;
            cnt[interval[1] + 1]--;
        }

        int res = 0;
        for(int i = 1; i <= SIZE; i++) {
            cnt[i] += cnt[i - 1];
            if(res < cnt[i]) res = cnt[i];
        }

        return res;
    }
}