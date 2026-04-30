package BaekJoon;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BaekJoon_25193 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String text = br.readLine();
        int cnt = 0;
        for(int i = 0; i < n; i++){
            if(text.charAt(i) == 'C'){
                cnt++;
            }
        }
        bw.write(cnt % (n-cnt+1) == 0 ? String.valueOf(cnt / (n-cnt+1)) : String.valueOf(cnt / (n-cnt+1) + 1));
        bw.flush();
        bw.close();
    }
}
