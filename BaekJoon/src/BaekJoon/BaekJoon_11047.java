package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Integer> stack = new Stack<>();

        int coins = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());

        int coin = 0;

        for(int i = 0; i < coins; i++){
            stack.push(Integer.parseInt(br.readLine()));
        }

        while(!stack.isEmpty()){
            if(money == 0){
                break;
            }
            int temp = stack.pop();

            if(money / temp != 0){
                coin += money / temp;
                money = money % temp;
            }
        }

        System.out.println(coin);
    }
}
