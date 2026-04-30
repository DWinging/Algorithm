package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_33613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        if(n == 3) {
            System.out.println(y == 2 && x == 2 ? 1 : 4);
        }
        else {
            long cnt = (long) n * n;
            System.out.println(n % 2 == 0 ? cnt / 2 : (y + x) % 2 == 0 ? (cnt + 1) / 2 : (cnt - 1) / 2);
        }
    }
}
