package BaekJoon;

import java.io.*;

public class BaekJoon_24524 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();

        int[] arr = new int[26];
        int idx = 1;
        for(char c : t.toCharArray()) {
            arr[c-'a'] = idx++;
        }

        int[] cnt = new int[t.length() + 1];
        for(char c : s.toCharArray()) {
            idx = arr[c-'a'];
            if(idx == 0) continue;
            if(idx == 1) cnt[1]++;
            else {
                if(cnt[idx-1] > 0) {
                    cnt[idx-1]--;
                    cnt[idx]++;
                }
            }
        }
        System.out.println(cnt[t.length()]);
    }
}
