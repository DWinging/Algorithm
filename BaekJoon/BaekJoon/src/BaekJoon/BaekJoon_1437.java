package BaekJoon;

import java.io.*;

public class BaekJoon_1437 {

    final static int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(solve(n));
    }

    private static int solve(int num) {
        int n = 1;
        while(num > 4) {
            n = (n * 3) % MOD;
            num -= 3;
        }
        return (n * num) % MOD;
    }
}
