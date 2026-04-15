package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BaekJoon_15664 {

    static int N, M;
    static int[] arr;
    static HashMap<Integer, Integer> list;
    static ArrayList<Integer> keys;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        list = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            if(list.containsKey(num)){
                list.put(num, list.get(num) + 1);
            }
            else {
                list.put(num, 1);
            }
        }

        keys = new ArrayList<>(list.keySet());
        Collections.sort(keys);

        dfs(0);
        System.out.println(sb);
    }

    private static void dfs(int index){
        if(index == M){
            for(int i : arr){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i : keys){
            if(list.get(i) == 0 || (index != 0 && arr[index-1] > i)){
                continue;
            }
            arr[index] = i;
            list.put(i, list.get(i) - 1);
            dfs(index + 1);
            list.put(i, list.get(i) + 1);
        }
    }
}
