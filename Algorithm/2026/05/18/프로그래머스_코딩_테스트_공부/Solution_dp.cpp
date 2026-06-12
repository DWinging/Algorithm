#include <string>
#include <vector>
#include <stdlib.h>
#define INF 305

using namespace std;

int solution(int alp, int cop, vector<vector<int>> problems) {
    int row = 0, col = 0, n = problems.size();
    for(int i = 0; i < n; i++) {
        if(row < problems[i][0]) row = problems[i][0];
        if(col < problems[i][1]) col = problems[i][1];
    }
    
    int** dp = (int**)malloc(sizeof(int*) * (row + 1));
    for(int i = 0; i <= row; i++) {
        dp[i] = (int*)malloc(sizeof(int) * (col + 1));
        for(int j = 0; j <= col; j++) {
            dp[i][j] = INF;
        }
    }
    if(alp > row) alp = row;
    if(cop > col) cop = col;
    dp[alp][cop] = 0;
    
    for(int i = alp; i <= row; i++) {
        for(int j = cop; j <= col; j++) {
            if(dp[i][j] == INF) continue;
            
            int nA = (i + 1) > row ? row : i + 1;
            if(dp[nA][j] > (dp[i][j] + 1)) {
                dp[nA][j] = dp[i][j] + 1;
            }
            
            int nC = (j + 1) > col ? col : j + 1;
            if(dp[i][nC] > (dp[i][j] + 1)) {
                dp[i][nC] = dp[i][j] + 1;
            }
            
            for(int k = 0; k < n; k++) {
                if(problems[k][0] <= i && problems[k][1] <= j) {
                    int nA_prob = (i + problems[k][2]) > row ? row : i + problems[k][2];
                    int nC_prob = (j + problems[k][3]) > col ? col : j + problems[k][3];
                    int nT_prob = dp[i][j] + problems[k][4];
                    
                    if(dp[nA_prob][nC_prob] > nT_prob) dp[nA_prob][nC_prob] = nT_prob;
                }
            }
        }
    }
    
    int answer = dp[row][col];
    for(int i = 0; i <= row; i++) {
        free(dp[i]);
    }
    free(dp);
    return answer;
}