#include <iostream>
#define MAX_SIZE 100000

using namespace std;

int arr[MAX_SIZE];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T; char c;
    cin >> T;
    cin.ignore();
    
    for(int t = 1; t <= T; t++) {
        int idx = 0, cnt = 0;
        while(cin.get(c) && c != '\n') { 
            if(c == 'a') {
                arr[idx++] = cnt;    
            }            
            cnt++;
        }

        long long res = 0;
        idx = 0; cnt = 0; 
        while(cin.get(c) && c != '\n') {
            if(c == 'a') {
                res += arr[idx] >= cnt ? arr[idx] - cnt : cnt - arr[idx];
                idx++;
            }     
            cnt++;
        }

        cout << '#' << t << ' ' << res << '\n';
    }
    
    return 0;
}