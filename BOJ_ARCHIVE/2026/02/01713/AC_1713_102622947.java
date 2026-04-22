/**
 * [BOJ] 1713 - 후보 추천하기
 * - 제출 날짜: 2026년 2월 4일
 * - 결과: 맞았습니다!!
 * - 메모리: 18244 KB
 * - 시간: 180 ms
 */

import java.util.*;
import java.io.*;

class Main {
    
	public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[] arr = inputArray(k, br);
        ArrayList<int[]> list = displayPicture(arr, n, k);
        System.out.println(buildString(list));
    }

    private static int[] inputArray(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }

    private static ArrayList<int[]> displayPicture(int[] arr, int n, int k) {
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            int idx = searchIdx(list, arr[i]);
            if(idx != -1) {
                list.get(idx)[1]++;
            }
            else if(list.size() < n) {
                list.add(new int[]{arr[i], 1, i});
            }
            else {
                list.sort((l1, l2) -> {
                    if(l1[1] != l2[1]) return Integer.compare(l1[1], l2[1]);
                    else return Integer.compare(l1[2], l2[2]);
                });
                list.remove(0);
                list.add(new int[]{arr[i], 1, i});
            }
        }
        return list;
    }

    private static int searchIdx(ArrayList<int[]> list, int target) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i)[0] == target) return i;
        }
        return -1;
    }

    private static String buildString(ArrayList<int[]> list) {
        list.sort((l1, l2) -> Integer.compare(l1[0], l2[0]));

        StringBuilder sb = new StringBuilder();
        for(int[] i : list) {
            sb.append(i[0]).append(' ');
        }
        return sb.toString();
    }
}