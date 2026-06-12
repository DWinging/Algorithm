#include <iostream>
#include <cstdlib>
#define SIZE 5000

using namespace std;

int counts[SIZE + 1];
int res[SIZE];

void init(int n);
void countingNum(int n);
void solve(int n);
int next_num(int num);
void fun_print(int t, int n);

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int T, n;
    cin >> T;

    for(int t = 1; t <= T; t++) {
        cout << '#' << t;
        cin >> n;
        for(int i = 1; i <= SIZE; i++) counts[i] = 0;
        
        countingNum(n);
        solve(n);
        cout << '\n';
    }
    
    return 0;
}

void countingNum(int n) {
    int num;
    int temp = n;
    while(temp-- > 0) {
        cin >> num;    
        counts[num]++;
    }
}

void solve(int n) {
    int remain = n, idx = 0;
    int num = next_num(1);
    
    while(remain > 0) {
        if(counts[num] == remain) {
            while(counts[num]-- > 0) cout << ' ' << num;
            break;
        }

        if(counts[num + 1] == 0) {
            remain -= counts[num];
            while(counts[num]-- > 0) cout << ' ' << num;
            
            num = next_num(num + 2);
        } else {
            if(counts[num] + counts[num + 1] == remain) {
                while(counts[num + 1]-- > 0) cout << ' ' << num + 1;
                while(counts[num]-- > 0) cout << ' ' << num;
                break;
            } else {
                remain -= counts[num] + 1;
                while(counts[num]-- > 0) cout << ' ' << num;

                int temp = next_num(num + 2);
                cout << ' ' << temp;
                counts[temp]--;
                
                num = num + 1;
            }
        }
    }
}

int next_num(int num) {
    for(int i = num; i <= SIZE; i++) {
        if(counts[i] > 0) {
            return i;
        }
    }
    return -1;
}