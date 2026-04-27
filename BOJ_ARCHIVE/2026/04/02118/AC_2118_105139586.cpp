/**
 * [BOJ] 2118 - 두 개의 탑
 * - 제출 날짜: 2026년 4월 24일
 * - 결과: 맞았습니다!!
 * - 메모리: 2216 KB
 * - 시간: 4 ms
 */

#include <iostream>
#define SIZE 50000
using namespace std;

int arr[SIZE];

int inputArray(int n) {
    int total = 0;
    for(int i = 0; i < n; i++) {
        int val;
        cin >> val;
        arr[i] = val;
        total += val;
    }
    return total;
}

int solve(int total, int n) {
    int left = 0, right = 0;
    int forward = 0, backward = total;
    int res = 0;
    
    while(left < n) {
        while(right < n && forward < backward) {
            if(res < forward) res = forward;
            forward += arr[right];
            backward -= arr[right];
            right++;
        }

        if(forward >= backward && res < backward) res = backward;

        forward -= arr[left];
        backward += arr[left];
        left++;
    }
    return res;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;
    int total = inputArray(n);
    cout << solve(total, n);    
    return 0;
}