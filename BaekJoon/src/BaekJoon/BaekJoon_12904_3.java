package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_12904_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        StringBuilder sb = new StringBuilder(br.readLine());

        while(sb.length() > target.length()){
            if(sb.charAt(sb.length()-1) == 'B'){
                sb.deleteCharAt(sb.length()-1);
                sb.reverse();
            }
            else {
                sb.deleteCharAt(sb.length()-1);
            }
        }

        System.out.println(target.contentEquals(sb) ? 1 : 0);
    }
}
