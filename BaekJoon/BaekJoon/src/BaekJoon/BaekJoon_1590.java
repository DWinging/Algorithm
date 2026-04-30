package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1590 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int time = Integer.MAX_VALUE;
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(s + i * (c-1) < t) continue;

            int temp = t <= s ? s - t : binarySearch(s, i, c, t);
            time = Math.min(temp, time);
        }

        System.out.println(time == Integer.MAX_VALUE ? -1 : time);
    }

    private static int binarySearch(int s, int i, int c, int t) {
        int left = 0, right = c - 1, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            int time = s + i * mid;

            if(time == t) return 0;

            if(time < t) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return s + i * left - t;
    }
}