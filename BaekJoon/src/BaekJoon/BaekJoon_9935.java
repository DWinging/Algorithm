package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_9935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        Stack<StringBuilder> stack = new Stack<>();
        Stack<Integer> count = new Stack<>();

        String[] texts = br.readLine().split("");
        String[] bombs = br.readLine().split("");

        int cnt = 0;
        boolean check = false;
        for(String text : texts){
            if(text.equals(bombs[cnt])){
                cnt += 1;
                temp.append(text);
            }
            else if(text.equals(bombs[0])){
                count.push(cnt);
                stack.push(temp);
                cnt = 1;
                temp = new StringBuilder();
                temp.append(text);
            }
            else{
                while(!stack.isEmpty()){
                    answer.insert(0, stack.pop());

                }
                check = true;
                answer.append(text);
                count.clear();
                cnt = 0;
                temp = new StringBuilder();
            }

            if(cnt == bombs.length){
                if(!count.isEmpty()){
                    cnt = count.pop();
                    temp = stack.pop();
                }
                else{
                    cnt = 0;
                    temp = new StringBuilder();
                }
            }
        }

        if(check){
            System.out.println(answer);
        }
        else{
            System.out.println("FRULA");
        }

    }
}
