package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());
        int[] list = new int[testCase];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < list.length; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(list);

        int start = 0;
        int end = list.length-1;
        int sum = 0;
        int cnt = 0;

        while(start < end){
            sum = list[start] + list[end];
            if(sum < target){
                start++;
            }
            else if(sum == target){
                start++;
                end--;
                cnt++;
            }
            else {
                end--;
            }
        }
        System.out.println(cnt);
    }
}
