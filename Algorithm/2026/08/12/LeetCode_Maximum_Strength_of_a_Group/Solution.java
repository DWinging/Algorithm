class Solution {
    public long maxStrength(int[] nums) {
        if(nums.length == 1) return nums[0];

        long res = 1;
        int val = -10, cnt = 0;
        for(int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            if(temp == 0) continue;
            
            if(temp < 0 && val < temp) val = temp;

            res *= temp;
            cnt++;
        }

        if(res < 0) {
            res = cnt == 1 ? 0 : res / val;
        } else if(cnt == 0) {
            res = 0;
        }

        return res;
    }
}