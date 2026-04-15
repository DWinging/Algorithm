package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_18353_2 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] power = new int[n];
        int[] lis = new int[n];
        for(int i = 0; i < n; i++){
            power[i] = Integer.parseInt(st.nextToken());
        }

        int max = 1;
        int j = 0;
        for(int i = 0; i < n; i++){

        }

        System.out.println(n - max);
    }
}
