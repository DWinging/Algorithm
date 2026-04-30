package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_11004 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSelect(arr, k - 1, 0, n-1);
        System.out.println(arr[k-1]);
    }

    private static void quickSelect(int[] arr, int k, int left, int right) {
        if(left >= right) return;

        int pivot = partition(arr, left, right);

        if(pivot == k) return;
        else if(pivot > k) {
            quickSelect(arr, k, left, pivot-1);
        }
        else {
            quickSelect(arr, k, pivot + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        swap(arr, left, mid);

        int pivotValue = arr[left];
        int i = left + 1;
        int j = right;

        while(i <= j) {
            while (i <= j && arr[i] < pivotValue) {
                i++;
            }

            while (i <= j && arr[j] > pivotValue) {
                j--;
            }

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        swap(arr, left, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
