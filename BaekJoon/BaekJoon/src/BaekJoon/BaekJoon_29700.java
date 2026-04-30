package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_29700 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] seats = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
        int seat = Integer.parseInt(st.nextToken());

        for(int i = 0; i < seats.length; i++){
            String temp = br.readLine();
            for(int j = 0; j < seats[i].length; j++){
                seats[i][j] = temp.charAt(j) - '0';
            }
        }

        int cnt = 0;
        for (int[] s : seats) {
            int sum = 0;
            for (int j = 0; j < seat && j < s.length; j++) {
                sum += s[j];
            }
            if (sum == 0) {
                cnt++;
            }
            for (int j = seat; j < s.length; j++) {
                sum = sum - s[j - seat] + s[j];
                if (sum == 0) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
