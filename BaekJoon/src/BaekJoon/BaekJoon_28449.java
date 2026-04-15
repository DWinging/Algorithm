package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_28449 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr1 = inputArray(n, st);
        st = new StringTokenizer(br.readLine());
        int[] arr2 = inputArray(m, st);
        Arrays.sort(arr2);

        long win = 0, lose = 0, drow = 0;
        for(int p : arr1) {
            int idx = binarySearch(p, arr2);
            if(p == arr2[idx]) drow++;
            win += idx;
            lose += m - idx;
        }

        System.out.println(win + " " + (lose - drow) + " " + drow);
    }

    private static int[] inputArray(int n, StringTokenizer st) {
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int binarySearch(int p, int[] arr) {
        int left = 0, right = arr.length, mid;
        while(left <= right) {
            mid = (left + right) / 2;
            if(p == arr[mid]) return mid;

            if(p < arr[mid]) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return left;
    }
}
