/**
 * [BOJ] 2912 - 백설공주와 난쟁이
 * - 제출 날짜: 2026년 4월 22일
 * - 결과: 틀렸습니다
 */

#include <iostream>

using namespace std;

static int arr[300005];
static int ans[300005];
static int cnt[10005];
static int table[300005];
static int color_of_cnt[300005];
int max_ans = 0;
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
    table[cnt[val]]--;
    cnt[val]++;
    table[cnt[val]]++;

    color_of_cnt[cnt[val]] = val;
    
    if(cnt[val] > max_ans) max_ans = cnt[val];
}

inline void remove(int val) {
    if(cnt[val] == max_ans && table[cnt[val]] == 1) max_ans--;

    table[cnt[val]]--;
    cnt[val]--;
    table[cnt[val]]++;
    
    color_of_cnt[cnt[val]] = val; 
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n, m, k;
    cin >> n >> k;
    k = (k + 1) >> 1;

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

        int current_k = (r - l + 1) >> 1;
        ans[query.idx] = (max_ans > current_k) ? color_of_cnt[max_ans] : -1;
    }

    for(int i = 0; i < m; i++) {
        if(ans[i] == -1) {
            cout << "no";
        } else {
            cout << "yes " << ans[i];
        }
        cout << "\n";
    }
    
    return 0;
}


