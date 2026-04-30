package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
public class BaekJoon_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        StringTokenizer s = new StringTokenizer(br.readLine(), "-");
        StringTokenizer add = new StringTokenizer(s.nextToken(), "+");
        int temp;
        while (add.hasMoreTokens()) {
            sum += Integer.parseInt(add.nextToken());
        }
        while (s.hasMoreTokens()) {
            temp = 0;
            add = new StringTokenizer(s.nextToken(), "+");
            while (add.hasMoreTokens()) {
                temp += Integer.parseInt(add.nextToken());
            }

            sum -= temp;
        }
        System.out.println(sum);
    }
}