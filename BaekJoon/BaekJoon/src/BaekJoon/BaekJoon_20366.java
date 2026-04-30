package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_20366 {

    final static int MAX_RANGE = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = inputArray(n, br);
        Arrays.sort(arr);
        System.out.println(minOf(arr));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int minOf(int[] arr) {
        int minValue = MAX_RANGE * 2 + 1;
        for(int i = 0; i < arr.length - 3; i++) {
            for(int j = arr.length - 1; j >= i + 3; j--) {
                int left = i + 1, right = j - 1;
                int s1 = arr[i] + arr[j];
                while(left < right) {
                    int s2 = arr[left] + arr[right];
                    minValue = Math.min(minValue, Math.abs(s1 - s2));

                    if(s1 > s2) left++;
                    else right--;
                }
            }
        }
        return minValue;
    }
}
