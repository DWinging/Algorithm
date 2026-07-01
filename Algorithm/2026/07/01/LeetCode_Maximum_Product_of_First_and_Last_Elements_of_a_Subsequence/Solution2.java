class Solution {
    public long maximumProduct(int[] nums, int m) {
        int n = nums.length;

        if(m == 1) {
            long res = 0;
            for(long num : nums) {
                long val = num * num;
                if(res < val) res = val;
            }
            return res;
        }

        long res = -10_000_000_005L;
        long minVal = 100_005;
        long maxVal = -100_005;
        for(int i = m - 1, idx = 0; i < n; i++, idx++) {
            if(minVal > nums[idx]) minVal = nums[idx];
            if(maxVal < nums[idx]) maxVal = nums[idx];

            long val1 = nums[i] * minVal;
            long val2 = nums[i] * maxVal;

            if(res < val1) res = val1;
            if(res < val2) res = val2;
        }
        return res;
    }
}