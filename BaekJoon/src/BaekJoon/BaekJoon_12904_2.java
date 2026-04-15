package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_12904_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String target = br.readLine();
        String text = br.readLine();

        char left = '0';
        int right = text.length()-1;
        boolean dire = true;

        while(right - left >= target.length()){
            char temp;
            if(dire){
                temp = text.charAt(right--);
            }
            else {
                temp = text.charAt(left++);
            }
            if(temp == 'B'){
                dire = !dire;
            }
        }

        boolean check = true;
        if(dire){
            for(int i = 0; i < target.length(); i++){
                if(target.charAt(i) != text.charAt(left)){
                    check = false;
                    break;
                }
                left++;
            }
        }
        else {
            for(int i = 0; i < target.length(); i++){
                if(target.charAt(i) != text.charAt(right)){
                    check = false;
                    break;
                }
                right--;
            }
        }

        System.out.println(check ? 1 : 0);
    }
}
