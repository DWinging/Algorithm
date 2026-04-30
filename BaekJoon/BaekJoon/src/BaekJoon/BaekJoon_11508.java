package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_11508 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int cnt = 0, sum = 0;
        for(int i = n-1; i >= 0; i--) {
            cnt++;
            if(cnt % 3 == 0) continue;
            sum += arr[i];
        }
        System.out.println(sum);
    }
}
