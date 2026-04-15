package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_6137 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(testCase-- > 0){
            sb.append(br.readLine());
        }

        int s = 0;
        int e = sb.length()-1;
        StringBuilder newText = new StringBuilder();
        int index = 0;
        while(s <= e){
            if(sb.charAt(s) < sb.charAt(e)){
                newText.append(sb.charAt(s));
                s++;
            }
            else if(sb.charAt(s) > sb.charAt(e)){
                newText.append(sb.charAt(e));
                e--;
            }
            else {
                int min = s+1;
                int max = e-1;
                while(true){
                    if(min >= max){
                        newText.append(sb.charAt(s));
                        s++;
                        break;
                    }
                    if(sb.charAt(min) > sb.charAt(max)){
                        newText.append(sb.charAt(e));
                        e--;
                        break;
                    }
                    else if(sb.charAt(min) < sb.charAt(max)){
                        newText.append(sb.charAt(s));
                        s++;
                        break;
                    }
                    min++;
                    max--;
                }
            }
            index++;
            if(index == 80){
                newText.append("\n");
                index = 0;
            }
        }
        System.out.println(newText);
    }
}
