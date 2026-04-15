package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_20546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cost = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int lastCost = 0, cnt = 0;
        int[] j = {cost, 0}; // 준현
        int[] s = {cost, 0}; // 성민

        while(st.hasMoreTokens()) {
            int now = Integer.parseInt(st.nextToken());

            if(j[0] >= now) {
                j[1] += j[0] / now;
                j[0] %= now;
            }

            if(lastCost > now) {
                cnt = cnt < 0 ? 1 : cnt + 1;
            }
            else if(lastCost == now) {
                cnt = 0;
            }
            else {
                cnt = cnt > 0 ? -1 : cnt - 1;
            }

            if(cnt >= 3) {
                s[1] += s[0] / now;
                s[0] %= now;
            }
            else if(cnt <= -3) {
                s[0] += now * s[1];
                s[1] = 0;
            }

            lastCost = now;
        }

        int jTotal = j[0] + j[1] * lastCost;
        int sTotal = s[0] + s[1] * lastCost;
        System.out.println(jTotal > sTotal ? "BNP" : jTotal < sTotal ? "TIMING" : "SAMESAME");
    }
}
