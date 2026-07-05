class Solution {
    
    static final int INF = 100_005;

    public int minSubArrayLen(int target, int[] nums) {
        int res = INF, n = nums.length;
        int val = 0, left = 0, right = 0;
        while(left < n) {
            while(right < n && val < target) {
                val += nums[right++];
            }

            if(val >= target) {
                int len = right - left;
                if(res > len) {
                    res = len;
                }
            } else {
                break;
            }
            val -= nums[left++];
        }

        return res == INF ? 0 : res;
    }
}