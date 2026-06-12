#include <iostream>
#include <string>
#include <cstring>
#define MAX_SIZE 10000

using namespace std;

int wCnt[MAX_SIZE + 1];
int eCnt[MAX_SIZE + 1];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T, n, m;
    if (!(cin >> T)) return 0;

    for(int t = 1; t <= T; t++) {
        cin >> n >> m;

        memset(wCnt, 0, sizeof(int) * (n + 1));
        memset(eCnt, 0, sizeof(int) * (n + 1));

        int w = 0, e = 0;
        string row;

        for(int i = 0; i < m; i++) {
            cin >> row; 
            for(int j = 1; j <= n; j++) {
                if(row[j - 1] == 'W') {
                    wCnt[j]++;
                    w++;
                } else {
                    eCnt[j]++;
                }
            }
        }

        int res = 0, minW = w, minE = e;
        for(int i = 1; i <= n; i++) {
            w -= wCnt[i];
            e += eCnt[i];

            if(w + e < minW + minE) {
                res = i;
                minW = w;
                minE = e;
            }
        }

        cout << '#' << t << ' ' << res << ' ' << res + 1 << '\n';
    }    

    return 0;
}