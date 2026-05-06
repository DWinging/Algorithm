#include <string>
#include <vector>

using namespace std;

int searchBitCount(long long number) {
    for(int i = 1; i < 63; i++) {
        if(number <= ((1LL << i) - 1)) return i;
    }
    return 63;
}

int solve(long long number, int bit_count) {
    if(bit_count <= 1) return 1;
    
    int half = bit_count >> 1;
    
    if((number & (1LL << half)) == 0) return number == 0 ? 1 : 0;
    
    int res = solve(number >> (half + 1), half);
    if(res == 0) return 0;
    
    return solve(number & ((1LL << half) - 1), half);
}

vector<int> solution(vector<long long> numbers) {
    int n = numbers.size();
    vector<int> answer;
    
    for(int i = 0; i < n; i++) {
        int bit_count = searchBitCount(numbers[i]);
        answer.push_back(solve(numbers[i], bit_count));
    }
    
    return answer;
}