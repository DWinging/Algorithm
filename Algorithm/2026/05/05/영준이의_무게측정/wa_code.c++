#include <iostream>
#include <stdlib.h>

using namespace std;

int fun_find(int* parents, int* diff, int p) {
    while(parents[p] != p) {
        int temp = parents[p];
        diff[p] += diff[parents[temp]];
        parents[p] = parents[temp];
        p = temp;
    }
    return p;
}

void fun_union(int* parents, int* diff, int a, int b, int w) {
    int pA = fun_find(parents, diff, a);
    int pB = fun_find(parents, diff, b);

    if(pA != pB) {
        parents[pB] = pA;
        diff[pB] = diff[a] - diff[b] - w;
    }
}

void init(int* parents, int* diff, int n) {
    for(int i = 0; i <= n; i++) {
        parents[i] = i;
        diff[i] = 0;
    }
}

int main(int argc, char** argv) {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T, n, m, a, b, w;
    string comm;

    cin >> T;

    for(int t = 1; t <= T; t++) {
        cout << '#' << t << ' ';

        cin >> n >> m;

        int* parents = (int*)malloc(sizeof(int) * (n + 1));
        int* diff = (int*)malloc(sizeof(int) * (n + 1));

        init(parents, diff, n);
        
        while(m-- > 0) {
            cin >> comm;
            
            if(comm == "!") {
                cin >> a >> b >> w;
                fun_union(parents, diff, a, b, w);                            
            } else {
                cin >> a >> b;

                int pA = fun_find(parents, diff, a);
                int pB = fun_find(parents, diff, b);

                if(pA == pB) {
                    cout << diff[a] - diff[b] << ' ';
                } else {
                    cout << "UNKNOWN" << ' ';
                }
            }
        }

        free(parents);
        free(diff);
        
        cout << '\n';
    }
    
    return 0;
}