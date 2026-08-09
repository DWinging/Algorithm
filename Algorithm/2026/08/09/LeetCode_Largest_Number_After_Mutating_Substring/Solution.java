class Solution {
    public String maximumNumber(String num, int[] change) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int len = num.length();

        while (i < len && num.charAt(i) - '0' >= change[num.charAt(i) - '0']) {
            sb.append(num.charAt(i));
            i++;
        }

        while (i < len && num.charAt(i) - '0' <= change[num.charAt(i) - '0']) {
            int n = num.charAt(i) - '0';
            sb.append(change[n]);
            i++;
        }

        while (i < len) {
            sb.append(num.charAt(i));
            i++;
        }

        return sb.toString();
    }
}