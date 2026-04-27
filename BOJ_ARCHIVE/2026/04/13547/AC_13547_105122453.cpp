/**
 * [BOJ] 13547 - 수열과 쿼리 5
 * - 제출 날짜: 2026년 4월 22일
 * - 결과: 맞았습니다!!
 * - 메모리: 50460 KB
 * - 시간: 112 ms
 */

#include <iostream>

using namespace std;

static int arr[1000005];
static int ans[1000005];
static int cnt[10000005];
int current_ans;
int sqrt_n = 320;

static struct Query {
    int l, r, idx, blk;
} q[100005];

bool compare(const Query& a, const Query& b) {
    if(a.blk != b.blk) return a.blk < b.blk;
    return (a.blk & 1) ? (a.r > b.r) : (a.r < b.r);
}

void mySort(int left, int right) {
    if(left >= right) return;
    int i = left, j = right;
    Query pivot = q[(left + right) >> 1];

    while(i <= j) {
        while(compare(q[i], pivot)) i++;
        while(compare(pivot, q[j])) j--;
        if(i <= j) {
            Query temp = q[i]; q[i] = q[j]; q[j] = temp;
            i++; j--;
        }
    }
    mySort(left, j);
    mySort(i, right);
}

inline void add(int val) {
    if(++cnt[val] == 1) current_ans++;
}

inline void remove(int val) {
    if(--cnt[val] == 0) current_ans--;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m;
    cin >> n;

    for(int i = 1; i <= n; i++) cin >> arr[i];

    cin >> m;
    for(int i = 0; i < m; i++) {
        int a, b;
        cin >> q[i].l;
        cin >> q[i].r;
        q[i].idx = i;
        q[i].blk = q[i].l / sqrt_n;
    }

    mySort(0, m - 1);

    int l = 1, r = 0;
    for(int i = 0; i < m; i++) {
        Query query = q[i];
        
        while(l > query.l) add(arr[--l]);
        while(r < query.r) add(arr[++r]);
        while(l < query.l) remove(arr[l++]);
        while(r > query.r) remove(arr[r--]);

        ans[query.idx] = current_ans;
    }

    for(int i = 0; i < m; i++) cout << ans[i] << "\n";
    
    return 0;
}


