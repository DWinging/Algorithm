package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2457 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] monthOfDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for(int i = 1; i < monthOfDays.length; i++) {
            monthOfDays[i] += monthOfDays[i-1];
        }

        StringTokenizer st;
        int[][] date = new int[n][2];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < date[i].length; j++) {
                int m = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                date[i][j] = convertDay(monthOfDays, m, d);
            }
        }

        Arrays.sort(date, (o1, o2) -> {return o1[0] - o2[0];});

        int start = convertDay(monthOfDays, 3, 1);
        int end = convertDay(monthOfDays, 11, 30);
        System.out.println(calculateDay(date, start, end));
    }

    private static int convertDay(int[] monthOfDays, int m, int d) {
        return monthOfDays[m-1] + d;
    }

    private static int calculateDay(int[][] date, int start, int end) {
        int s = start, idx = 0, cnt = 0;
        while(idx < date.length && s <= end) {
            if(date[idx][0] > s) break;
            int temp = s;
            while(idx < date.length) {
                if(date[idx][0] > s) {
                    break;
                }
                temp = Math.max(temp, date[idx][1]);
                idx++;
            }
            s = temp;
            cnt++;
        }
        return s > end ? cnt : 0;
    }
}
