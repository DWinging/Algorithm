package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon_5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            String[] num = new String[n];

            for(int i = 0; i < n; i++){
                num[i] = br.readLine();
            }

            Arrays.sort(num);

            sb.append(solve(num)).append("\n");
        }
        System.out.println(sb);
    }

    private static String solve(String[] num) {
        for(int i = 1; i < num.length; i++){
            if(num[i].startsWith(num[i-1])) return "NO";
        }
        return "YES";
    }
}
