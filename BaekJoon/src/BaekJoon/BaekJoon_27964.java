package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BaekJoon_27964 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int topping = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        if(topping < 4){
            System.out.println("sad");
        }
        else {
            HashSet<String> cheese = new HashSet<>();
            while(st.hasMoreTokens()){
                String temp = st.nextToken();
                if(temp.contains("Cheese") && temp.endsWith("Cheese")){
                    cheese.add(temp);
                }
            }

            System.out.println(cheese.size() < 4 ? "sad" : "yummy");
        }
    }
}
