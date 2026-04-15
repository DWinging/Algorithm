package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_1083 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> num = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            num.add(Integer.parseInt(st.nextToken()));
        }

        int moveIndex = 0, changeIndex = 0;
        while(moveIndex < s && changeIndex < n){
            int maxNum = num.get(changeIndex), maxIdx = -1;
            int index = changeIndex+1, cnt = 0;

            while(moveIndex + cnt < s && index < n){
                if(maxNum < num.get(index)){
                    maxNum = num.get(index);
                    maxIdx = index;
                }
                cnt++;
                index++;
            }

            if(maxIdx != -1) {
                num.remove(maxIdx);
                num.add(changeIndex, maxNum);
                moveIndex += maxIdx - changeIndex;
            }
            changeIndex++;
        }

        for(int i : num){
            sb.append(i).append(" ");
        }

        System.out.println(sb);
    }
}
