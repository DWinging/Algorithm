#include<iostream>
#define MAX_SIZE 200000

int arr[MAX_SIZE];
int visited[MAX_SIZE + 1];

using namespace std;

int main(int argc, char** argv) {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T, n;
    cin >> T;

    for(int i = 0; i <= MAX_SIZE; i++) visited[i] = 0;

    for(int t = 1; t <= T; t++) {
        cin >> n;
        for(int i = 0; i < n; i++) {
            cin >> arr[i];
        }

        int cnt = 0;

        for(int i = 0; i < n; i++) {
            int val = arr[i];
            if(visited[val - 1] < t) cnt++;
            visited[val] = t;
        }

        cout << '#' << t << ' ' << cnt << '\n';
    }
    
    return 0;
}