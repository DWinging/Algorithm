class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int cnt = 0, far = 0, near = 0;
        
        while (far < n - 1) {
            int farthest = 0;            
            for (int i = near; i <= far; i++) { 
                if(farthest < i + nums[i]) {
                    farthest = i + nums[i];
                }
            }

            near = far + 1;
            far = farthest;
            cnt++;
        }

        return cnt;
    }
}