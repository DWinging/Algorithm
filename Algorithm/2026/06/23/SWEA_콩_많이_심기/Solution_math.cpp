#include <iostream>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T, n, m;
    cin >> T;

    for(int t = 1; t <= T; t++) {
        cin >> n >> m;

        int res = 0;
        bool flag = true;
        for(int i = 0; i < n; i++) {
            int val = (m / 4) * 2 + ((m & 3) >= 2 ? 2 : (m & 3));
            res += flag ? val : m - val;
            if((i & 1) == 1) flag = !flag;
        }
        cout << '#' << t << ' ' << res << '\n';
    }
    
    return 0;
}