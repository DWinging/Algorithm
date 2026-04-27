/**
 * [BOJ] 2912 - 백설공주와 난쟁이
 * - 제출 날짜: 2026년 4월 22일
 * - 결과: 맞았습니다!!
 * - 메모리: 4560 KB
 * - 시간: 128 ms
 */

#include <iostream>

using namespace std;

static int arr[300005];
static int ans[300005];
static int cnt[10005];
int sqrt_n = 512;

static struct Query {
    int l, r, idx, blk;
} q[10005];

bool compare(const Query& a, const Query& b) {
    if (a.blk != b.blk) return a.blk < b.blk;
    return (a.blk & 1) ? (a.r > b.r) : (a.r < b.r);
}

void mySort(int left, int right) {
    if (left >= right) return;
    int i = left, j = right;
    Query pivot = q[(left + right) >> 1];

    while (i <= j) {
        while (compare(q[i], pivot)) i++;
        while (compare(pivot, q[j])) j--;
        if (i <= j) {
            Query temp = q[i]; q[i] = q[j]; q[j] = temp;
            i++; j--;
        }
    }
    mySort(left, j);
    mySort(i, right);
}

inline void add(int val) {
    cnt[val]++;
}

inline void remove(int val) {
    cnt[val]--;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, c_max, m;
    if (!(cin >> n >> c_max)) return 0;

    for (int i = 1; i <= n; i++) cin >> arr[i];

    cin >> m;
    for (int i = 0; i < m; i++) {
        cin >> q[i].l >> q[i].r;
        q[i].idx = i;
        q[i].blk = q[i].l >> 9;
    }

    mySort(0, m - 1);

    int l = 1, r = 0;
    for (int i = 0; i < m; i++) {
        Query query = q[i];

        while (l > query.l) add(arr[--l]);
        while (r < query.r) add(arr[++r]);
        while (l < query.l) remove(arr[l++]);
        while (r > query.r) remove(arr[r--]);

        int current_k = (query.r - query.l + 1) >> 1;
        ans[query.idx] = -1;

        for (int c = 1; c <= c_max; c++) {
            if (cnt[c] > current_k) {
                ans[query.idx] = c;
                break;
            }
        }
    }

    for (int i = 0; i < m; i++) {
        if (ans[i] == -1) cout << "no\n";
        else cout << "yes " << ans[i] << "\n";
    }

    return 0;
}