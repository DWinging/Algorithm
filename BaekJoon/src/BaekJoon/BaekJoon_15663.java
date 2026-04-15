package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BaekJoon_15663 {

    static int N, M;
    static int[] arr;
    static ArrayList<Integer> keySet;
    static HashMap<Integer, Integer> list;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        list = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int num = Integer.parseInt(st.nextToken());
            if(list.containsKey(num)){
                list.put(num, list.get(num) + 1);
            }
            else {
                list.put(num, 1);

            }
        }
        keySet = new ArrayList<>(list.keySet());
        Collections.sort(keySet);

        solve(0);
        System.out.println(sb);
    }

    private static void solve(int index){
        if(index == M){
            for(int i : arr){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (Integer integer : keySet) {
            if (list.get(integer) == 0) {
                continue;
            }
            arr[index] = integer;
            list.put(integer, list.get(integer) - 1);
            solve(index + 1);
            list.put(integer, list.get(integer) + 1);
        }
    }
}
