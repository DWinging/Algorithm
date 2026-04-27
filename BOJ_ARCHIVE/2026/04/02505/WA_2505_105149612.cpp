/**
 * [BOJ] 2505 - 두 번 뒤집기
 * - 제출 날짜: 2026년 4월 25일
 * - 결과: 틀렸습니다
 */

#include <iostream>
#define SIZE 10000

using namespace std;

int arr[SIZE + 1];

int searchNum(int target, int n) {
    for(int i = target + 1; i <= n; i++) {
        if(arr[i] == target) return i;
    }
    return -1;
}

void swap(int s, int e) {
    while(s < e) {
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp;
        s++;
        e--;
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;

    for(int i = 1; i <= n; i++) cin >> arr[i];

    int s1 = 1, e1 = 2, s2 = 1, e2 = 2;
    bool flag = true;
    for(int i = 1; i <= n; i++) {
        if(arr[i] != i) {
            if(flag) {
                s1 = i;
                e1 = searchNum(i, n);
                swap(s1, e1);
                flag = false;
            } else {
                s2 = i;
                e2 = searchNum(i, n);
                break;
            }
        }
    }

    cout << s1 << " " << e1 << "\n";
    cout << s2 << " " << e2;
    
    return 0;
}