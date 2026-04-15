package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_12789 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int index = 1;

        while(st.hasMoreTokens()){

            if ( !stack.isEmpty() && stack.peek() == index){
                stack.pop();
                index += 1;
            }
            else {
                int num = Integer.parseInt(st.nextToken());
                if (index == num){
                    index += 1;
                }
                else {
                    stack.add(num);
                }
            }
        }

        while (!stack.isEmpty()) {
            int num = stack.peek();
            if ( index == num) {
                stack.pop();
                index += 1;
            }
            else {
                break;
            }
        }

        if ( n + 1 == index) {
            System.out.println("Nice");
        }
        else {
            System.out.println("Sad");
        }
    }
}
