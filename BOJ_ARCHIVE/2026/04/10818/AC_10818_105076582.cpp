/**
 * [BOJ] 10818 - 최소, 최대
 * - 제출 날짜: 2026년 4월 17일
 * - 결과: 맞았습니다!!
 * - 메모리: 2020 KB
 * - 시간: 96 ms
 */

#include <iostream>

using namespace std;

int INF = 1000000;

int main() {
    
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, temp;
    cin >> n >> temp;

    int min_val = temp;
    int max_val = temp;
    
    for(int i = 1; i < n; i++) {
        cin >> temp;

        if(min_val > temp) {
            min_val = temp;
        } else if(max_val < temp) {
            max_val = temp;
        }
    }

    cout << min_val << " " << max_val;
    
    return 0;
}