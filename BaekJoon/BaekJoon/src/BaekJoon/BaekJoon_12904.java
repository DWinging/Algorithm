package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BaekJoon_12904 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Character> list = new ArrayList<>();
        String target = br.readLine();
        String text = br.readLine();
        for(int i = 0; i < text.length(); i++){
            list.add(text.charAt(i));
        }

        for(int i = 0; i < text.length()-target.length(); i++){
            if(list.get(list.size()-1) == 'A'){
                list.remove(list.size()-1);
            }
            else {
                list.remove(list.size()-1);
                Collections.reverse(list);
            }
        }

        boolean check = true;
        for(int i = 0; i < target.length(); i++){
            if(target.charAt(i) != list.get(i)){
                check = false;
                break;
            }
        }

        System.out.println(check ? 1 : 0);
    }

}
