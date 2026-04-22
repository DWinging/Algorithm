/**
 * [BOJ] 18870 - 좌표 압축
 * - 제출 날짜: 2026년 1월 30일
 * - 결과: 맞았습니다!!
 * - 메모리: 347244 KB
 * - 시간: 1784 ms
 */

import java.io.*;
import java.util.*;

class Main {    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            int temp = Integer.parseInt(st.nextToken());
            arr[i] = temp;
            if(!map.containsKey(temp)) map.put(temp, -1);
        }

        int[] arr2 = new int[map.size()];
        int idx = 0;
        for(int key : map.keySet()) {
            arr2[idx++] = key;
        }

        Arrays.sort(arr2);
        StringBuilder sb = new StringBuilder();
        for(int i : arr) {
            if(map.get(i) > -1) sb.append(map.get(i)).append(' ');
            else {
                int rank = binarySearch(arr2, i);
                map.put(i, rank);
                sb.append(rank).append(' ');
            }
        }
        System.out.println(sb);
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1, mid;
        while(left <= right) {
            mid = (left + right) / 2;

            if(arr[mid] == target) return mid;

            if(arr[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    } 
}