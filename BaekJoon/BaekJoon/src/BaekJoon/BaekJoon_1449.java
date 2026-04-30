package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1449 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int cnt = 1;
        int idx = arr[0] + l - 1;
        for(int i = 1; i < n; i++) {
            if(arr[i] > idx) {
                idx = arr[i] + l - 1;
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
