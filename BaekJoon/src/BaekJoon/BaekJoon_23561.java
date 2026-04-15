package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_23561 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] age = new int[n * 3];
        for(int i = 0; i < age.length; i++) {
            age[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(age);
        System.out.println(age[n * 2 - 1] - age[n]);
    }
}
