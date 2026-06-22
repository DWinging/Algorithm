#include <iostream>

const int MAX_SIZE = 1000;

using namespace std;

int arr[MAX_SIZE][MAX_SIZE];

int forward(int n, int m, int y, int t) {
    int cnt = 0;
    for(int i = 0; i < m; i++) {
        if(arr[y][i] < t) {
            if(y + 2 < n) arr[y + 2][i] = t;
            if(i + 2 < m) arr[y][i + 2] = t;
            cnt++;
        }
    }
    return cnt;
}

int backward(int n, int m, int y, int t) {
    int cnt = 0;
    for(int i = m - 1; i >= 0; i--) {
        if(arr[y][i] < t) {
            if(y + 2 < n) arr[y + 2][i] = t;
            if(i - 2 >= 0) arr[y][i - 2] = t;
            cnt++;
        }
    }
    return cnt;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T, n, m;

    cin >> T;

    for(int t = 1; t <= T; t++) {
        cin >> n >> m;

        int res = 0;
        for(int i = 0; i < n; i++) {
            res += (i & 1) == 0 ? forward(n, m, i, t) : backward(n, m, i, t);
        }
        cout << '#' << t << ' ' << res << '\n';
    }
    
    return 0;
}