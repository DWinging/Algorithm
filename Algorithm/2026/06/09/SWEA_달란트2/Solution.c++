#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T, n, p;
    cin >> T;

    for(int t = 1; t <= T; t++) {
        cin >> n >> p;

        long long res = 1;
        int val = n / p;
        int temp = n % p;
        for(int i = 0; i < temp; i++) {
            res *= (val + 1);
        }
        
        for(int i = temp; i < p; i++) {
            res *= val;
        }

        cout << '#' << t << ' ' << res << '\n';
    }
    
    return 0;
}