package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_2473 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Long> nums = new ArrayList<>();
        for(int i = 0; i < n; i++){
            nums.add(Long.parseLong(st.nextToken()));
        }

        Collections.sort(nums);

        long n1 = 0;
        long n2 = 0;
        long n3 = 0;
        long min = Long.MAX_VALUE;

        for(int i = 0; i < n - 2; i++){
            long temp = nums.get(i);
            int s = i + 1;
            int e = n-1;
            while(s < e) {
                long sum = temp + nums.get(s) + nums.get(e);
                if(Math.abs(sum) < min) {
                    n1 = temp;
                    n2 = nums.get(s);
                    n3 = nums.get(e);
                    min = Math.abs(sum);
                }

                if(sum == 0) {
                    break;
                }
                else if(sum < 0) {
                    s++;
                }
                else {
                    e--;
                }
            }
        }

        System.out.println(n1 + " " + n2 + " " + n3);
    }
}
