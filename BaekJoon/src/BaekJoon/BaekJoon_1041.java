package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1041 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dice = new int[6];

        for(int i = 0; i < dice.length; i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        if(N == 1){
            Arrays.sort(dice);
            int result = 0;
            for(int i = 0; i < 5; i++){
                result += dice[i];
            }
            System.out.println(result);
            System.exit(0);
        }

        BigInteger[] num = new BigInteger[3];
        for(int i = 0; i < dice.length / 2; i++){
            num[i] = new BigInteger(String.valueOf(Math.min(dice[i], dice[dice.length - i - 1])));
        }
        Arrays.sort(num);

        num[2] = num[2].multiply(BigInteger.valueOf(4));
        long temp = N * 4 + 4 * (N - 2);
        num[1] = num[1].multiply(BigInteger.valueOf(temp));
        num[0] = num[0].multiply(BigInteger.valueOf(N*N*5 -temp-4));
        BigInteger result = new BigInteger("0");

        for (BigInteger bigInteger : num) {
            result = result.add(bigInteger);
        }
        System.out.println(result);
    }
}
