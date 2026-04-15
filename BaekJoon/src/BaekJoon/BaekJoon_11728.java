package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_11728 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] a = new int[Integer.parseInt(st.nextToken())];
        int[] b = new int[Integer.parseInt(st.nextToken())];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < a.length; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < b.length; i++){
            b[i] = Integer.parseInt(st.nextToken());
        }

        int aIndex = 0;
        int bIndex = 0;

        while(aIndex < a.length && bIndex < b.length){
            if(a[aIndex] > b[bIndex]){
                sb.append(b[bIndex]).append(" ");
                bIndex++;
            }
            else{
                sb.append(a[aIndex]).append(" ");
                aIndex++;
            }
        }

        for(int i = aIndex; i < a.length; i++){
            sb.append(a[i]).append(" ");
        }

        for(int i = bIndex; i < b.length; i++){
            sb.append(b[i]).append(" ");
        }

        System.out.println(sb);
    }
}
