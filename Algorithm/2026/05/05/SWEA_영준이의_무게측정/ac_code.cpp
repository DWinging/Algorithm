#include <iostream>
#include <stdlib.h>

using namespace std;

int fun_find(int* parents, int* diff, int* stack_arr, int p) {
    int top = 0;
    int curr = p;

    while (parents[curr] != curr) {
        stack_arr[top++] = curr;
        curr = parents[curr];
    }

    int root = curr;

    while (top > 0) {
        int node = stack_arr[--top];
        int parent_node = parents[node];
        diff[node] += diff[parent_node];
        parents[node] = root;
    }

    return root;
}

void fun_union(int* parents, int* diff, int* stack_arr, int a, int b, int w) {
    int pA = fun_find(parents, diff, stack_arr, a);
    int pB = fun_find(parents, diff, stack_arr, b);

    if (pA != pB) {
        parents[pB] = pA;
        diff[pB] = w + diff[a] - diff[b];
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T, n, m, a, b, w;
    string comm;

    cin >> T;

    for (int t = 1; t <= T; t++) {
        cout << '#' << t << ' ';
        cin >> n >> m;

        int* parents = (int*)malloc(sizeof(int) * (n + 1));
        int* diff = (int*)malloc(sizeof(int) * (n + 1));
        int* stack_arr = (int*)malloc(sizeof(int) * (n + 1));

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            diff[i] = 0;
        }

        while (m-- > 0) {
            cin >> comm;
            if (comm == "!") {
                cin >> a >> b >> w;
                fun_union(parents, diff, stack_arr, a, b, w);
            } else {
                cin >> a >> b;
                if (fun_find(parents, diff, stack_arr, a) == fun_find(parents, diff, stack_arr, b)) {
                    cout << diff[b] - diff[a] << ' ';
                } else {
                    cout << "UNKNOWN ";
                }
            }
        }

        free(parents);
        free(diff);
        free(stack_arr);
        cout << '\n';
    }
    return 0;
}