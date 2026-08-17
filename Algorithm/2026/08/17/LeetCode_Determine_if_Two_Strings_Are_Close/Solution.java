import java.util.*;

class Solution {
    public boolean closeStrings(String word1, String word2) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];

        for(char c : word1.toCharArray()) {
            cnt1[c - 'a']++;
        }

        for(char c : word2.toCharArray()) {
            if(cnt1[c - 'a'] == 0) return false;
            cnt2[c - 'a']++;
        }

        Arrays.sort(cnt1);
        Arrays.sort(cnt2);

        for(int i = 25; i >= 0; i--) {
            if(cnt1[i] != cnt2[i]) return false;
        }

        return true;
    }
}