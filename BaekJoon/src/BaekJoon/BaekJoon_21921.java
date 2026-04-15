package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class BaekJoon_21921 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int day = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] days = new int[day];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < day; i++){
            days[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int max = 0;
        for(int i = 0; i < target; i++){
            sum += days[i];
        }
        max = Math.max(sum, max);
        list.add(sum);

        for(int i = target; i < day; i++){
            sum = sum - days[i-target] + days[i];
            list.add(sum);
            max = Math.max(sum, max);
        }

        System.out.println(max == 0 ? "SAD" : max + "\n" + Collections.frequency(list, max));
    }
}
