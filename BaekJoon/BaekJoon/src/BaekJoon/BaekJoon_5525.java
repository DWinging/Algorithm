package BaekJoon;

import java.io.*;

public class BaekJoon_5525 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();

        int cnt = 0;
        int result = 0;

        for(int i = 0; i < m-2; i++){
            if(s.charAt(i) == 'I' && s.charAt(i + 1) == 'O' && s.charAt(i + 2) == 'I'){
                cnt += 1;
                if(cnt == n){
                    cnt -= 1;
                    result += 1;
                }
                i += 1;
            }
            else {
                cnt = 0;
            }
        }


        System.out.println(result);
    }
}
