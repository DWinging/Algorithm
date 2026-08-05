class Solution {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + ((right - left) >> 1);

            boolean sameLeft = mid > left && nums[mid] == nums[mid - 1];
            boolean sameRight = mid < right && nums[mid] == nums[mid + 1];

            if (!sameLeft && !sameRight) {
                return nums[mid];
            }

            if (sameLeft) {
                if (((mid - left) & 1) == 0) {
                    right = mid - 2;
                } else {
                    left = mid + 1;
                }
            } else {
                if (((right - mid) & 1) == 0) {
                    left = mid + 2;
                } else {
                    right = mid - 1;
                }
            }
        }

        return nums[left];
    }
}