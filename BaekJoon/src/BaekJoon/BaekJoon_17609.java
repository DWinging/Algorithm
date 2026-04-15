package BaekJoon;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class BaekJoon_17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            bw.write(checkPalindrome(br.readLine()) + "\n");
        }
        bw.flush();
        bw.close();
    }

    public static int checkPalindrome(String text){
        int start = 0;
        int end = text.length()-1;
        while(start < end){
            if(text.charAt(start) != text.charAt(end)){
                return solve(text, start+1, end) || solve(text, start, end-1) ? 1 : 2;
            }
            start++;
            end--;
        }
        return 0;
    }

    public static boolean solve(String text, int start, int end){

        while(start < end){
            if(text.charAt(start) != text.charAt(end)) return false;
            start++;
            end--;
        }

        return true;
    }
}
