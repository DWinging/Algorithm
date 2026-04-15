package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_11721 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= text.length(); i++){
            sb.append(text.charAt(i-1));
            if(i % 10 == 0){
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }
}
