/**
 * [BOJ] 2505 - 두 번 뒤집기
 * - 제출 날짜: 2026년 4월 25일
 * - 결과: 맞았습니다!!
 * - 메모리: 2100 KB
 * - 시간: 0 ms
 */

#include <iostream>
#include <algorithm>

#define SIZE 10001
using namespace std;

int n;
int origin[SIZE], arr[SIZE];
int res[2][2];

void my_reverse(int s, int e) {
    while (s < e) {
        swap(arr[s++], arr[e--]);
    }
}

bool is_sorted() {
    for (int i = 1; i <= n; i++) if (arr[i] != i) return false;
    return true;
}

void solve() {
    for (int i = 1; i <= n; i++) arr[i] = origin[i];
    int cnt = 0;
    res[0][0] = 1; res[0][1] = 1;
    res[1][0] = 1; res[1][1] = 1;
    
    for (int i = 1; i <= n && cnt < 2; i++) {
        if (arr[i] != i) {
            int pos = -1;
            for (int j = 1; j <= n; j++) {
                if (arr[j] == i) { pos = j; break; }
            }
            res[cnt][0] = i;
            res[cnt][1] = pos;
            my_reverse(i, pos);
            cnt++;
        }
    }

    if (is_sorted()) return;

    for (int i = 1; i <= n; i++) arr[i] = origin[i];
    cnt = 0;
    res[0][0] = 1; res[0][1] = 1;
    res[1][0] = 1; res[1][1] = 1;
    
    for (int i = n; i >= 1 && cnt < 2; i--) {
        if (arr[i] != i) {
            int pos = -1;
            for (int j = 1; j <= n; j++) {
                if (arr[j] == i) { pos = j; break; }
            }
            res[cnt][0] = pos;
            res[cnt][1] = i;
            my_reverse(pos, i);
            cnt++;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    if (!(cin >> n)) return 0;
    for (int i = 1; i <= n; i++) cin >> origin[i];

    solve();

    cout << res[0][0] << " " << res[0][1] << "\n";
    cout << res[1][0] << " " << res[1][1];

    return 0;
}