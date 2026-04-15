package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2143 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arrA = inputArray(br);
        int[] arrB = inputArray(br);

        Map<Integer, Integer> subA = calculateSub(arrA);
        Map<Integer, Integer> subB = calculateSub(arrB);

        System.out.println(countOf(n, subA, subB));
    }

    private static int[] inputArray(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) + arr[i-1];
        }
        return arr;
    }

    private static Map<Integer, Integer> calculateSub(int[] arr) {
        Map<Integer, Integer> sub = new HashMap<>();
        int size = arr.length;
        for(int i = size-1; i >= 1; i--) {
            for(int j = i; j < size; j++) {
                sub.compute(arr[j] - arr[j-i], (k, v) -> v == null ? 1 : v + 1);
            }
        }
        return sub;
    }

    private static long countOf(int target, Map<Integer, Integer> subA, Map<Integer, Integer> subB) {
        long total = 0;
        for(int key : subA.keySet()) {
            int temp = target - key;
            if(subB.containsKey(temp)) total += (long)subA.get(key) * subB.get(temp);
        }
        return total;
    }
}
