class Solution {
    public String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();

        if(numRows == 1) return s;

        char[] arr = s.toCharArray();

        int[] add = {(numRows - 1) * 2, 0};
        int len = arr.length;

        for(int i = 0; i < Math.min(numRows, len); i++) {

            int idx = i, bit = 0;
            sb.append(arr[idx]);
            while(true) {
                if(add[bit] > 0) {
                    idx += add[bit];
                    if(idx < len) sb.append(arr[idx]);
                    else break;
                }
                bit ^= 1;
            }

            add[0] -= 2;
            add[1] += 2;
        }

        return sb.toString();
    }
}