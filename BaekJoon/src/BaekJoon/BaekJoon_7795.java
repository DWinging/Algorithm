package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(testCase-- > 0){
            st = new StringTokenizer(br.readLine());
            int[] aList = new int[Integer.parseInt(st.nextToken())];
            int[] bList = new int[Integer.parseInt(st.nextToken())];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < aList.length; i++){
                aList[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < bList.length; i++){
                bList[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(aList);
            Arrays.sort(bList);


        }
    }
}
