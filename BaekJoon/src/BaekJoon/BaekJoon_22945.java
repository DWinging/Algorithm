package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_22945 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        System.out.println(maxOf(arr, n));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        return arr;
    }

    private static long maxOf(int[] arr, int n) {
        int left = 0, right = n - 1;
        long maxValue = 0;
        while(left + 2 <= right) {
            long value = (long)(right - left - 1) * Math.min(arr[left], arr[right]);
            maxValue = Math.max(value, maxValue);

            if(arr[left] < arr[right]) left++;
            else right--;
        }
        return maxValue;
    }
}
