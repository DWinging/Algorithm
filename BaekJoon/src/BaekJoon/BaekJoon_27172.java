package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_27172 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        boolean[] value = new boolean[1000001];
        int[] score = new int[1000001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int temp = Integer.parseInt(st.nextToken());
            nums[i] = temp;
            value[temp] = true;
        }

        for(int num : nums) {
            for(int i = num * 2; i < value.length; i += num){
                if(value[i]) {
                    score[i]--;
                    score[num]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int num : nums) {
            sb.append(score[num]).append(" ");
        }
        System.out.println(sb);
    }
}
