package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_2649 {

    final static int MOD = 10000000;
    final static String SUCCESS_MESSAGE = "yes ";
    final static String FAIL_MESSAGE = "danger";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String input = "";
        while((input = br.readLine()) != null && !input.isEmpty()) {
            int len = Integer.parseInt(input) * MOD;
            int cnt = Integer.parseInt(br.readLine());
            int[] arr = inputPieces(cnt, br);
            bw.write(searchPieces(len, arr) + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static int[] inputPieces(int cnt, BufferedReader br) throws IOException {
        int[] arr = new int[cnt];
        for(int i = 0; i < cnt; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        return arr;
    }

    private static String searchPieces(int target, int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while(left < right) {
            int len = arr[left] + arr[right];
            if(len == target) return SUCCESS_MESSAGE + arr[left] + " " + arr[right];

            if(len > target) right--;
            else left++;
        }
        return FAIL_MESSAGE;
    }
}
