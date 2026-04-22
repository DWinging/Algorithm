/**
 * [BOJ] 11909 - 배열 탈출
 * - 제출 날짜: 2026년 4월 19일
 * - 결과: 맞았습니다!!
 * - 메모리: 40592 KB
 * - 시간: 344 ms
 */

#include <iostream>

using namespace std;

int arr[2222][2222];
int dp[2222][2222];

void inputArray(int n);
int solve(int n);

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    
    inputArray(n);
    cout << solve(n);
}

void inputArray(int n) {
    for(int y = 0; y < n; y++) {
        for(int x = 0; x < n; x++) {
            cin >> arr[y][x];
        }
    }
}

int solve(int n) {
    for(int y = 0; y < n; y++) {
        for(int x = 0; x < n; x++) {
            if(y == 0 && x == 0) {
                dp[y][x] = 0;
            } else if(y == 0) {
                dp[y][x] = dp[y][x - 1];
                if(arr[y][x] >= arr[y][x - 1]) {
                    dp[y][x] += arr[y][x] + 1 - arr[y][x - 1];
                }
            } else if(x == 0) {
                dp[y][x] = dp[y - 1][x];
                if(arr[y][x] >= arr[y - 1][x]) {
                    dp[y][x] += arr[y][x] + 1 - arr[y - 1][x];
                }
            } else {
                int val = arr[y][x];
                int down = (val < arr[y][x - 1] ? 0 : val + 1 - arr[y][x - 1]) + dp[y][x - 1];
                int right = (val < arr[y - 1][x] ? 0 : val + 1 - arr[y - 1][x]) + dp[y - 1][x];
                dp[y][x] = down >= right ? right : down;
            }
        }
    }
    return dp[n - 1][n - 1];
}