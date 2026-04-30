package BaekJoon;

import java.io.*;
import java.util.StringTokenizer;

public class BaekJoon_20922 {

    final static int MAX_RANGE = 100_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int n = Integer.parseInt(temp[0]);
        int k = Integer.parseInt(temp[1]);

        int[] arr = inputArray(n, br);
        System.out.println(getMaxSequence(n, k, arr));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int getMaxSequence(int n, int k, int[] arr) {
        int left = 0, maxLen = 0;
        int[] cnt = new int[MAX_RANGE];
        for(int right = 0; right < n; right++) {
            int cur = arr[right];
            cnt[cur]++;
            while(cnt[cur] > k) {
                cnt[arr[left++]]--;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
