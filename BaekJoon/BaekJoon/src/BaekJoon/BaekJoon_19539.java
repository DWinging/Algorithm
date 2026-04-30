package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_19539 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int odd = 0, even = 0;
        long day = 0;
        while(n-- > 0) {
            int h = Integer.parseInt(st.nextToken());
            day += h;
            odd += h % 2;
            even += h / 2;
        }

        bw.write(check(day, odd, even));
        bw.flush();
        bw.close();
    }

    private static String check(long day, int odd, int even) {
        if(day % 3 != 0) return "NO";
        else {
            if(even >= odd && (even - odd) % 3 == 0) {
                return "YES";
            }
            else return "NO";
        }
    }
}
