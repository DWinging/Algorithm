import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for(int num : nums) {
            set.add(num);
        }

        int res = 0;
        for(int num : set) {
            if(!set.contains(num - 1)) {
                int next = num;
                while(set.contains(next + 1)) {
                    next++;
                }

                if(next - num + 1 > res) res = next - num + 1;
            }
        }

        return res;
    }
}