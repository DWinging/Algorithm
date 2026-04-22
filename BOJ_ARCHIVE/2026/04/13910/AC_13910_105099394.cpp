/**
 * [BOJ] 13910 - 개업
 * - 제출 날짜: 2026년 4월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 2032 KB
 * - 시간: 20 ms
 */

#include <iostream>

using namespace std;

int total = 10000;
bool arr[10001];
int woks[100];

int calculateArray(int m) {
    for(int i = 0; i < m; i++) cin >> woks[i];

    int max_val = 0;
    for(int i = 0; i < m; i++) {
        int val = woks[i];
        arr[val] = true;
        if(val > max_val) max_val = val;
        
        for(int j = i + 1; j < m; j++) {
            int sum_val = val + woks[j];
            if(total >= sum_val) {
                arr[sum_val] = true;
                if(sum_val > max_val) max_val = sum_val;
            }
        }
    }
    return max_val;
}

int solve(int n, int max_val) {
    int dp[n + 1];

    dp[0] = 0;
    for(int i = 1; i <= n; i++) {
        dp[i] = total + 1;
    }
    
    for(int i = 1; i <= max_val; i++) {
        if(!arr[i]) continue;

        for(int j = i; j <= n; j++) {
            if(dp[j] > dp[j - i] + 1) {
                dp[j] = dp[j - i] + 1;
            }
        }
    }

    return dp[n] == total + 1 ? -1 : dp[n];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, m;
    cin >> n >> m;

    int max_val = calculateArray(m);
    cout << solve(n, max_val);
    
    return 0;
}