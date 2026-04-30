package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1208 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] even = new int[(n / 2) + (n % 2)];
        int[] odd = new int[n / 2];
        inputArray(even, odd, n, br);

        List<Integer> evenSum = getSubSum(even);
        List<Integer> oddSum = getSubSum(odd);

        System.out.println(countOf(evenSum, oddSum, k));
    }

    private static void inputArray(int[] even, int[] odd, int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) even[i / 2] = Integer.parseInt(st.nextToken());
            else odd[i / 2] = Integer.parseInt(st.nextToken());
        }
    }

    private static List<Integer> getSubSum(int[] arr) {
        List<Integer> subSum = new ArrayList<>();
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0, 0});

        while(!deque.isEmpty()) {
            int[] temp = deque.pollFirst();
            int cur = temp[0];
            int sum = temp[1];

            if(cur == arr.length) {
                subSum.add(sum);
                continue;
            }

            deque.add(new int[]{cur + 1, sum});
            deque.add(new int[]{cur + 1, sum + arr[cur]});
        }

        Collections.sort(subSum);
        return subSum;
    }

    private static long countOf(List<Integer> evenSum, List<Integer> oddSum, int k) {
        long total = 0;

        for(int i = 0; i < evenSum.size(); i++) {
            int value = evenSum.get(i);
            int lower = binarySearchLowerCase(oddSum, k - value);
            int upper = binarySearchUpperCase(oddSum, k - value);

            if(upper - lower > 0) {
                long cnt = 1;
                while(i + 1 < evenSum.size() && evenSum.get(i + 1) == value) {
                    cnt++;
                    i++;
                }
                total += cnt * (upper - lower);
            }
        }

        return k == 0 ? total - 1 : total;
    }

    private static int binarySearchLowerCase(List<Integer> list, int k) {
        int left = 0, right = list.size() - 1, mid;
        while(left <= right) {
            mid = (left + right) / 2;

            if(list.get(mid) < k) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static int binarySearchUpperCase(List<Integer> list, int k) {
        int left = 0, right = list.size() - 1, mid;
        while(left <= right) {
            mid = (left + right) / 2;

            if(list.get(mid) <= k) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
}
