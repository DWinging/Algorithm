package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon_1339 {

    static int[] alpha = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            setAlpha(br.readLine());
        }
        Arrays.sort(alpha);
        int result = 0;
        for(int i = alpha.length-1, j = 0; i >= 0 && alpha[i] != 0; i--, j++){
            result += alpha[i] * (9 - j);
        }
        System.out.println(result);
    }

    private static void setAlpha(String number){
        for(int i = 0; i < number.length(); i++){
            alpha[number.charAt(i)-'A'] += (int) Math.pow(10, (number.length() - i-1));
        }
    }
}
