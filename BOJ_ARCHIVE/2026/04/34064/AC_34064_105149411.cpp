/**
 * [BOJ] 34064 - 밤(Time For The Moon Night)
 * - 제출 날짜: 2026년 4월 25일
 * - 결과: 맞았습니다!!
 * - 메모리: 4952 KB
 * - 시간: 20 ms
 */

#include <iostream>
#define SIZE 500

using namespace std;

static int map[SIZE][SIZE];
static int dist[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
static int que[SIZE * SIZE + 5][2];

void inputStar(int k) {
    int y, x;
    while(k-- > 0) {
        cin >> y >> x;
        map[y-1][x-1] = -5;
    }
}

void inputRange(int val) {
    int y1, x1, y2, x2;
    cin >> y1 >> x1;
    cin >> y2 >> x2;
    for(int y = y1 - 1; y < y2; y++) {
        for(int x = x1 - 1; x < x2; x++) {
            map[y][x] += val;
        }
    }
}

bool check(int y, int x, int n, int m) {
    return y >= 0 && y < n && x >= 0 && x < m;
}

long long bfs(int sy, int sx, int n, int m) {
    long long cnt1 = 0;
    long long cnt2 = 0;
    int val = map[sy][sx];
    if((val & 1) != 0) cnt1++;
    if((val & 2) != 0) cnt2++;
    map[sy][sx] = -1;
    
    int head = 0, tail = 0;
    que[tail][0] = sy;
    que[tail][1] = sx;
    tail++;

    while(head < tail) {
        int cy = que[head][0];
        int cx = que[head][1];
        head++;

        for(int i = 0; i < 4; i++) {
            int ny = cy + dist[i][0];
            int nx = cx + dist[i][1];
            if(check(ny, nx, n, m) && map[ny][nx] > -1) {
                val = map[ny][nx];
                if((val & 1) != 0) cnt1++;
                if((val & 2) != 0) cnt2++;
                map[ny][nx] = -1;
                
                que[tail][0] = ny;
                que[tail][1] = nx;
                tail++;     
            }
        }
    }

    return cnt1 * cnt2;
}

long long solve(int n, int m) {
    long long res = 0LL;
    for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
            if(map[i][j] > -1) {
                res += bfs(i, j, n, m);
            }
        }
    }
    return res;
}

int main() {

    ios::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n, m, k;
    cin >> n >> m >> k;

    inputStar(k);
    inputRange(1);
    inputRange(2);

    cout << solve(n, m);
    
    return 0;
}