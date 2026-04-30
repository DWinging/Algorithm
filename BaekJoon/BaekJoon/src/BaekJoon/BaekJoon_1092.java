package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] crane = new int[n];

        for(int i = 0; i < n; i++){
            crane[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(crane);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> boxs = new ArrayList<>();
        for(int i = 0; i < m; i++){
            boxs.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(boxs);

        if(boxs.get(m-1) > crane[n-1]){
            System.out.println(-1);
            System.exit(0);
        }

        int time = 0;
        while(m > 0){
            int index = n-1;
            for(int i = boxs.size()-1; i >= 0 && index != -1; i--){
                if(boxs.get(i) <= crane[index]){
                    index--;
                    m--;
                    boxs.remove(i);
                }
                else {
                    break;
                }
            }
            time++;
        }
        System.out.println(time);
    }
}
