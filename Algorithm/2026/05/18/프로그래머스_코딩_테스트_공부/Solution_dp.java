import java.util.*;

class Solution {
    
    static final int INF = 305;
    
    public int solution(int alp, int cop, int[][] problems) {
        int[] targets = getTargets(problems);
        int targetA = targets[0];
        int targetC = targets[1];
        
        int[] startStats = clampInitialStats(alp, cop, targetA, targetC);
        alp = startStats[0];
        cop = startStats[1];
        
        return computeMinTime(alp, cop, targetA, targetC, problems);
    }
    
    private int[] getTargets(int[][] problems) {
        int targetA = 0;
        int targetC = 0;
        for (int[] prob : problems) {
            if (targetA < prob[0]) targetA = prob[0];
            if (targetC < prob[1]) targetC = prob[1];
        }
        return new int[]{targetA, targetC};
    }
    
    private int[] clampInitialStats(int alp, int cop, int targetA, int targetC) {
        if (alp > targetA) alp = targetA;
        if (cop > targetC) cop = targetC;
        return new int[]{alp, cop};
    }
    
    private int[][] createDpTable(int row, int col, int initAlp, int initCop) {
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i <= row; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[initAlp][initCop] = 0;
        return dp;
    }
    
    private int computeMinTime(int startA, int startC, int targetA, int targetC, int[][] problems) {
        int[][] dp = createDpTable(targetA, targetC, startA, startC);
        
        for (int i = startA; i <= targetA; i++) {
            for (int j = startC; j <= targetC; j++) {
                if (dp[i][j] == INF) continue;
                
                int nA = Math.min(i + 1, targetA);
                if (dp[nA][j] > dp[i][j] + 1) {
                    dp[nA][j] = dp[i][j] + 1;
                }
                
                int nC = Math.min(j + 1, targetC);
                if (dp[i][nC] > dp[i][j] + 1) {
                    dp[i][nC] = dp[i][j] + 1;
                }
                
                for (int[] prob : problems) {
                    if (prob[0] <= i && prob[1] <= j) {
                        int nextA = Math.min(i + prob[2], targetA);
                        int nextC = Math.min(j + prob[3], targetC);
                        int nextT = dp[i][j] + prob[4];
                        
                        if (dp[nextA][nextC] > nextT) {
                            dp[nextA][nextC] = nextT;
                        }
                    }
                }
            }
        }
        
        return dp[targetA][targetC];
    }
}