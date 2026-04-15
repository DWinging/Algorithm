package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1072 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long game = Long.parseLong(st.nextToken());
        long win = Long.parseLong(st.nextToken());
        long zValue = win * 100 / game;

        System.out.println(zValue >= 99 ? -1 : solve(game, win, zValue));
    }

    private static long solve(long game, long win, long zValue) {
        long max = game;
        long min = 0;
        long mid;
        long answer = 0;

        while(min <= max){
            mid = (max + min) / 2;
            long z = (win + mid) * 100 / (game + mid);

            if(z > zValue){
                answer = mid;
                max = mid-1;
            }
            else{
                min = mid+1;
            }
        }

        return answer;
    }

}
