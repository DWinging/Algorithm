#include <iostream>
#include <stdlib.h>

using namespace std;

int find(int* parents, int p) {
    while(p != parents[p]) {
        int temp = parents[p];
        parents[p] = parents[temp];
        p = temp;
    }

    return p;
}

void solve(int* parents, int n, int m) {
    int comm, a, b;
    while(m-- > 0) {
        cin >> comm >> a >> b;

        int pA = find(parents, a);
        int pB = find(parents, b);
        if(comm == 0) parents[pB] = pA;
        else cout << (pA == pB ? 1 : 0);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int T, n, m;

    cin >> T;

    for(int t = 1; t <= T; t++) {
        cin >> n >> m;

        int* parents = (int*)malloc(sizeof(int) * (n + 1));
        for(int i = 1; i <= n; i++) parents[i] = i;

        cout << '#' << t << ' ';
        solve(parents, n, m);
        cout << '\n';

        free(parents);
    }
    
    return 0;
}