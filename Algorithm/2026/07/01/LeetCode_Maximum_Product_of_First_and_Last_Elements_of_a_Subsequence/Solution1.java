class Solution {
    public long maximumProduct(int[] nums, int m) {
        if(m == 1) {
            long res = 0;
            for(long num : nums) {
                long val = num * num;
                if(res < val) res = val;
            }
            return res;
        }

        int n = nums.length;
        int[][] arr = new int[n][2];
        int min = 100_005;
        int max = -100_005;
        for(int i = n - 1; i >= m - 1; i--) {
            int val = nums[i];
            if(min > val) min = val;
            if(max < val) max = val;

            arr[i][0] = min;
            arr[i][1] = max;
        }

        long res = -10_000_000_005L;
        for(int i = 0; i < n - m + 1; i++) {
            long num = nums[i];
            long val1 = num * arr[i + m - 1][0];
            long val2 = num * arr[i + m - 1][1];

            res = Math.max(res, Math.max(val1, val2));
        }
        return res;
    }
}