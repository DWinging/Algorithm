#include <iostream>
#define MAX_SIZE 10000

int arr[MAX_SIZE];

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T, n, m;
    cin >> T;

    for(int t = 1; t <= T; t++) {
        cin >> n >> m;
        for(int i = 0; i < n; i++) cin >> arr[i];

        int right = 0, len = 0, res = 0;
        for(int left = 0; left < n; left++) {
            while(right < n && len < m) {
                len += arr[right++];
            }

            if(len == m) res++;
            len -= arr[left];
        }

        cout << '#' << t << ' ' << res << '\n';
    }
    
    return 0;
}