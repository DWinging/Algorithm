/**
 * [BOJ] 2550 - 전구
 * - 제출 날짜: 2026년 1월 19일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;


class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] switchs = inputArray(n, br);
        int[] bulbs = inputArray(n, br);

        int[] orders = checkOrder(n, bulbs);

        int[] lis = solve(switchs, orders, n);
        System.out.println(buildString(lis));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static int[] checkOrder(int n, int[] arr) {
        int[] orders = new int[n + 1];
        for(int i = 0; i < n; i++) {
            orders[arr[i]] = i + 1;
        }
        return orders;
    }

    private static int[] solve(int[] switchs, int[] orders, int n) {
        int[] tails = new int[n];
        tails[0] = switchs[0];
        int[] parents = new int[n + 1];
        parents[switchs[0]] = -1;

        int cnt = 1;
        for(int i = 1; i < n; i++) {
            int s = switchs[i];
            if(orders[s] > orders[tails[cnt-1]]) {
                tails[cnt] = s;
                parents[s] = tails[cnt - 1];
                cnt++;
            }
            else {
                int idx = binarySearch(tails, orders, s, cnt);
                parents[s] = parents[tails[idx]];
                if(orders[tails[idx]] > orders[s]) {
                    tails[idx] = s;    
                }                
            }
        }

        int[] lis = new int[cnt];
        int temp = tails[cnt-1];
        for(int i = 0; i < cnt; i++) {
            lis[i] = temp;
            temp = parents[temp];
        }
        return lis;
    }

    private static int binarySearch(int[] tails, int[] order, int cur, int cnt) {
        int left = 0, right = cnt - 1, mid;
        while(left <= right) {
            mid = (left + right) / 2;

            if(order[cur] > order[tails[mid]]) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static String buildString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr.length).append("\n");
        
        Arrays.sort(arr);
        for(int i : arr) {
            sb.append(i).append(" ");
        }        
        return sb.toString();
    }
}