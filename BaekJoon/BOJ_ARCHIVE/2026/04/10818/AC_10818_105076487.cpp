/**
 * [BOJ] 10818 - 최소, 최대
 * - 제출 날짜: 2026년 4월 17일
 * - 결과: 맞았습니다!!
 * - 메모리: 5800 KB
 * - 시간: 404 ms
 */

#include <iostream>

using namespace std;

int INF = 1000000;

int main() {
    int n;
    cin >> n;

    int arr[n];
    
    int min = INF + 1;
    int max = -(INF + 1);
    for(int i = 0; i < n; i++) {
        cin >> arr[i];

        if(min > arr[i]) {
            min = arr[i];
        }

        if(max < arr[i]) {
            max = arr[i];
        }
    }

    cout << min << " " << max;
    
    return 0;
}