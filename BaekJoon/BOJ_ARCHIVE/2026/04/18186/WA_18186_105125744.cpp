/**
 * [BOJ] 18186 - 라면 사기 (Large)
 * - 제출 날짜: 2026년 4월 22일
 * - 결과: 틀렸습니다
 */

#include <iostream>

using namespace std;

const int INF = 1000002;
static int arr[INF];

long long purchase(int idx, int end, int w) {
    int cost = INF;
    for(int i = idx; i <= end; i++) {
        if(cost > arr[i]) cost = arr[i];
    }
    
    for(int i = idx; i <= end; i++) {
        arr[i] -= cost;
    }
    return cost * w;
}

long long solve(int n, int b, int c) {
    long long total = 0;
    
    for(int i = 0; i < n; i++) {
        if(arr[i] == 0) continue;
        if(arr[i + 1] == 0) {
            total += arr[i] * b;
            continue;
        }

        if(arr[i + 1] > arr[i + 2]) {
            int temp = arr[i] < arr[i + 1] - arr[i + 2] ? arr[i] : arr[i + 1] - arr[i + 2];
            arr[i] -= temp;
            arr[i + 1] -= temp;
            total += temp * (b + c);
        }

        total += purchase(i, i + 2, b + c * 2);
        total += purchase(i, i + 1, b + c);
        total += arr[i] * b;
    }
    return total;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n, b, c;
    cin >> n >> b >> c;

    if(b <= c) {
        long long total = 0;
        for(int i = 0; i < n; i++) {
            int val;
            cin >> val;
            total += val;
        }
        cout << (total * b);
    } else {
        for(int i = 0; i < n; i++) {
            cin >> arr[i];
        }
        cout << solve(n, b, c);
    }
    return 0;
}