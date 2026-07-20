import java.util.*;

class Solution {

    Map<Integer, Integer>[] dp; // key : sum, value : count

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        dp = new HashMap[n];
        for(int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
        }

        int res = dfs(nums, 0, 0, n, target);
        return res;
    }

    private int dfs(int[] nums, int idx, int sum, int n, int target) {
        if(idx == n) {
            return sum == target ? 1 : 0;
        }

        if(dp[idx].containsKey(sum)) {
            return dp[idx].get(sum);
        }

        int val = dfs(nums, idx + 1, sum + nums[idx], n, target) + dfs(nums, idx + 1, sum - nums[idx], n, target);
        dp[idx].put(sum, val);
        return val;
    }
}