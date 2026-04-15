package BaekJoon;

import java.util.*;

public class BaekJoon_28702 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String answer = "";

        for(int i = 0; i < 3; i++){
            String text = sc.nextLine();

            if(!text.equals("Fizz") && !text.equals("Buzz") && !text.equals("FizzBuzz") && answer.isEmpty()){
                int value = Integer.parseInt(text);
                int temp = value % 15;
                temp = temp + 3 - i;

                if(temp == 3 || temp == 6 || temp == 9 || temp == 12){
                    answer = "Fizz";
                }
                else if(temp == 5 || temp == 10){
                    answer = "Buzz";
                }
                else if(temp == 15){
                    answer = "FizzBuzz";
                }
                else {
                    answer = String.valueOf(value + 3 - i);
                }
            }
        }

        System.out.println(answer);
    }
}
