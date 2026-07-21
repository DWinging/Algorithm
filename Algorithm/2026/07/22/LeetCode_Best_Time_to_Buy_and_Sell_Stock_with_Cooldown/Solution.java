class Solution {

    int[][] dp;

    public int maxProfit(int[] prices) {
        int n = prices.length;
        dp = new int[n][2];
        for(int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }

        int res = dfs(0, 0, prices);
        return res;
    }

    private int dfs(int idx, int holding, int[] prices) {
        if(idx >= prices.length) return 0;
        if(dp[idx][holding] > -1) return dp[idx][holding];

        int res = dfs(idx + 1, holding, prices);

        if(holding == 0) {
            res = Math.max(res, -prices[idx] + dfs(idx + 1, 1, prices));
        } else {
            res = Math.max(res, prices[idx] + dfs(idx + 2, 0, prices));
        }

        return dp[idx][holding] = res;
    }
}