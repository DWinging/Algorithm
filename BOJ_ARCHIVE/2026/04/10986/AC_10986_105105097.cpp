/**
 * [BOJ] 10986 - 나머지 합
 * - 제출 날짜: 2026년 4월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 2028 KB
 * - 시간: 120 ms
 */

#include <iostream>

using namespace std;

long long cnts[1000];

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m;
    cin >> n >> m;

    long long totalCnt = 0;
    long long sumVal = 0;

    while(n-- > 0) {
        int val;
        cin >> val;

        sumVal += val;
        int remainder = sumVal % m;

        if(remainder == 0) totalCnt++;

        totalCnt += cnts[remainder];
        cnts[remainder]++;
    }

    cout << totalCnt;
    return 0;
}