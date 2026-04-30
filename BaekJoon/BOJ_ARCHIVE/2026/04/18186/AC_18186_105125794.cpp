/**
 * [BOJ] 18186 - 라면 사기 (Large)
 * - 제출 날짜: 2026년 4월 22일
 * - 결과: 맞았습니다!!
 * - 메모리: 5928 KB
 * - 시간: 104 ms
 */

#include <iostream>

using namespace std;

const int INF = 1000005;
static int arr[INF];

long long purchase(int idx, int end, long long w) {
    long long cost = INF;
    for(int i = idx; i <= end; i++) {
        if(cost > arr[i]) cost = arr[i];
    }
    
    if(cost == 0) return 0;

    for(int i = idx; i <= end; i++) {
        arr[i] -= (int)cost;
    }
    return cost * w;
}

long long solve(int n, long long b, long long c) {
    long long total = 0;
    
    for(int i = 0; i < n; i++) {
        if(arr[i] == 0) continue;
        
        if(arr[i + 1] > arr[i + 2]) {
            long long diff = arr[i + 1] - arr[i + 2];
            long long temp = (arr[i] < diff) ? arr[i] : diff;
            
            arr[i] -= (int)temp;
            arr[i + 1] -= (int)temp;
            total += temp * (b + c);
        }

        total += purchase(i, i + 2, b + 2 * c);
        total += purchase(i, i + 1, b + c);
        total += (long long)arr[i] * b;
        arr[i] = 0;
    }
    return total;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    long long b, c;
    if(!(cin >> n >> b >> c)) return 0;

    if(b <= c) {
        long long count_total = 0;
        for(int i = 0; i < n; i++) {
            int val;
            cin >> val;
            count_total += val;
        }
        cout << (count_total * b) << "\n";
    } else {
        for(int i = 0; i < n; i++) {
            cin >> arr[i];
        }
        cout << solve(n, b, c) << "\n";
    }
    
    return 0;
}