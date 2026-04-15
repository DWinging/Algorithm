package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            int h = Integer.parseInt(st.nextToken());
            for(int j = 0; j < n; j++) {
                if(arr[j] == 0) {
                    if(h == 0) {
                        arr[j] = i;
                        break;
                    }
                    else h--;
                }
            }
        }

        for(int i : arr) {
            bw.write(i + " ");
        }
        bw.flush();
        bw.close();
    }
}
