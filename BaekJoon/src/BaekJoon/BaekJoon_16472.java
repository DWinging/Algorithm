package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_16472 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String text = br.readLine();
        int[] alpha = new int[26];

        int cnt = 0;
        int kind = 0;
        int s = 0;
        int e = 0;
        int max = 0;

        while(e < text.length()){
            if(++alpha[text.charAt(e)-'a'] == 1){
                kind++;
            }
            if(kind > N){
                if(max < cnt){
                    max = cnt;
                }
                while(kind > N){
                    if(--alpha[text.charAt(s)-'a'] == 0){
                        kind--;
                    }
                    s++;
                    cnt--;
                }
            }
            cnt++;
            e++;
        }

        System.out.println(max > cnt ? max : cnt);
    }
}
