package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_12919 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String target = br.readLine();

        int start = 0;
        int end = target.length()-1;
        boolean check = target.charAt(0) == 'B';
        while(end - start >= text.length()){
            if(check){
                if(target.charAt(start++) == 'B')
                    check = false;
            }
            else {
                if(target.charAt(end--) == 'B')
                    check = true;
            }
        }

        System.out.println(start + " " + end + " " + check);
        int index = 0;
        if(check){
            for(int i = start; i <= end; i++){
                if(text.charAt(index++) != target.charAt(i)){
                    System.out.println(0);
                    System.exit(0);
                }
            }
        }
        else {
            for(int i = end; i >= start; i--){
                if(text.charAt(index++) != target.charAt(i)){
                    System.out.println(0);
                    System.exit(0);
                }
            }
        }

        System.out.println(1);
    }
}
