class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid[0].length;
        int[] dp = new int[m + 1];
        
        if (obstacleGrid[0][0] == 0) {
            dp[1] = 1;
        }

        for (int[] row : obstacleGrid) {
            for (int j = 1; j <= m; j++) {
                if (row[j - 1] == 1) {
                    dp[j] = 0;
                } else {
                    dp[j] += dp[j - 1]; 
                }
            }
        }

        return dp[m];
    }
}