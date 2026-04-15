package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_15553 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n-1];
        int now = Integer.parseInt(br.readLine()) + 1;
        for(int i = 0; i < n-1; i++) {
            int next = Integer.parseInt(br.readLine());
            arr[i] = next - now;
            now = next + 1;
        }

        Arrays.sort(arr);
        int time = n;
        for(int i = 0; i < n - k; i++) {
            time += arr[i];
        }
        System.out.println(time);
    }
}
