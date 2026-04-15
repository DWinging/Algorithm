package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_3151 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] aList = new int[n];
        for(int i = 0; i < n; i++){
            aList[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(aList);

        long cnt = 0;
        for(int i = 0; i < n && aList[i] <= 0; i++){
            int s = i + 1;
            int e = n-1;
            while(s < e) {
                long sum = aList[i] + aList[s] + aList[e];
                long sIdx = 1;
                long eIdx = 1;
                if(sum == 0) {
                    if(aList[s] == aList[e]) {
                        cnt += countValue(e - s + 1);
                        break;
                    }
                    while(s + 1 < e && aList[s] == aList[s + 1]) {
                        sIdx++;
                        s++;
                    }
                    while(e - 1 > s && aList[e] == aList[e-1]){
                        eIdx++;
                        e--;
                    }

                    cnt += sIdx * eIdx;
                }
                if(aList[i] + aList[s] + aList[e] > 0) e--;
                else s++;
            }
        }

        System.out.println(cnt);
    }

    private static long countValue(long n) {
        return n * (n-1) / 2;
    }
}
