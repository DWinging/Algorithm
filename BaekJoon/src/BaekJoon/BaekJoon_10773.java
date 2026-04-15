package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon_10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stack = new int[n];
        int index = 0;
        while(n-- > 0){
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                index--;
            }
            else {
                stack[index++] = num;
            }
        }

        int sum = 0;
        for(int i = 0; i < index; i++){
            sum += stack[i];
        }
        System.out.println(sum);
    }
}
