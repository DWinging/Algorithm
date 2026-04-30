package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_10951 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String text = "";
        while((text = br.readLine()) != null && !text.isEmpty()){
            sb.append(text.charAt(0)-'0' + text.charAt(2)-'0').append("\n");
        }
        System.out.println(sb);
    }
}
