/**
 * [BOJ] 18185 - 라면 사기 (Small)
 * - 제출 날짜: 2026년 4월 22일
 * - 결과: 맞았습니다!!
 * - 메모리: 2060 KB
 * - 시간: 0 ms
 */

#include <iostream>

using namespace std;

static int arr[10002];

int solve(int n) {
    int total = 0;
    for(int i = 0; i < n; i++) {
        if(arr[i] == 0) continue;
        if(arr[i + 1] > arr[i + 2]) {
            int cnt = arr[i] < (arr[i + 1] - arr[i + 2]) ? arr[i] : (arr[i + 1] - arr[i + 2]);
            total += cnt * 5;
            arr[i] -= cnt;
            arr[i + 1] -= cnt;
        }

        int temp = arr[i];
        if(arr[i + 1] < temp) temp = arr[i + 1];
        if(arr[i + 2] < temp) temp = arr[i + 2];

        arr[i] -= temp;
        arr[i + 1] -= temp;
        arr[i + 2] -= temp;
        total += temp * 7;
        
        temp = arr[i] < arr[i + 1] ? arr[i] : arr[i + 1];
        arr[i] -= temp;
        arr[i + 1] -= temp;
        total += temp * 5;
        
        total += arr[i] * 3;
    }
    return total;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    cin >> n;
    for(int i = 0; i < n; i++) cin >> arr[i];

    cout << solve(n);
    return 0;
}