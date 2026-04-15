package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_10989 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] num = new int[10001];

        for(int i = 0; i < n; i++){
            num[Integer.parseInt(br.readLine())]++;
        }

        for(int i = 1; i < num.length; i++){
            while(num[i]-- > 0){
                sb.append(i).append("\n");
            }
        }

        System.out.println(sb);
    }
}
