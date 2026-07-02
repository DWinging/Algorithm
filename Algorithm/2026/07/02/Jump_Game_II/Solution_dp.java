class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 0; i < n; i++) {
            int val = nums[i];
            if(dp[i] == 0) continue;
            for(int j = 1; j <= val; j++) {
                if(i + j >= n) break;
                if(dp[i + j] == 0 || dp[i + j] > dp[i] + 1) dp[i + j] = dp[i] + 1;
            }
        }

        return dp[n - 1] - 1;
    }
}