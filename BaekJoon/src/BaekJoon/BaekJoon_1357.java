package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1357 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        StringBuilder num1 = new StringBuilder(st.nextToken());
        StringBuilder num2 = new StringBuilder(st.nextToken());
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.parseInt(num1.reverse().toString()) + Integer.parseInt(num2.reverse().toString()));
        System.out.println(Integer.parseInt(sb.reverse().toString()));
    }
}
