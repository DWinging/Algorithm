package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_2230 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] list = new long[N];
        for(int i = 0; i < N; i++){
            list[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(list);

        int min = 0;
        int max = 1;
        long value = Long.MAX_VALUE;
        while(min < N && max < N){
            long temp = list[max] - list[min];
            if(temp > M){
                value = Math.min(value, temp);
                min++;
            }
            else if(temp < M){
                max++;
            }
            else {
                value = temp;
                break;
            }
        }
        System.out.println(value);
    }
}
