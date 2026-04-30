package BaekJoon;


import java.util.*;
import java.io.*;

public class BaekJoon_18353 {
    static ArrayList<Integer> lds;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] power = new int[n];
        for(int i = 0; i < n; i++){
            power[i] = Integer.parseInt(st.nextToken());
        }

        lds = new ArrayList<>();
        lds.add(power[0]);

        for(int i = 1; i < n; i++){
            if(lds.get(lds.size()-1) > power[i]){
                lds.add(power[i]);
            }
            else{
                int findIndex = binarySearch(0, lds.size()-1, power[i]);
                lds.set(findIndex, power[i]);
            }
        }

        System.out.println(n - lds.size());
    }

    public static int binarySearch(int start, int end, int target){
        int index = 0;
        while (start <= end){
            int mid = (start + end) / 2;
            if (lds.get(mid) < target){
                end = mid -1;
                index = mid;
            }
            else{
                start = mid + 1;
            }
        }
        return index;
    }
}
