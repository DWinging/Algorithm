#include <iostream>
#include <string>

using namespace std;

int dp[1001][1001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    
    string str;
    int T;
    cin >> T;

    for(int i = 0; i < 1001; i++) {
        for(int j = 0; j < 1001; j++) {
            dp[i][j] = 0;
        }
    }
    
    for(int t = 1; t <= T; t++) {
        cin >> str;
        int n = str.length();

        int res = 1;

        for(int len = 1; len <= n; len++) {
            for(int s = 0; s <= n - len; s++) {
                int e = s + len - 1;

                if(str[s] == str[e]) {
                    if(len <= 2 || dp[s + 1][e - 1] == t) {
                        dp[s][e] = t;
                        res = len;                        
                    }
                }
            }
        }        

        cout << '#' << t << ' ' << res << '\n';
    }
    
    return 0;
}