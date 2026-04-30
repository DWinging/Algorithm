package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class BaekJoon_16169 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] total = new int[n];
        int[] time = new int[n];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int rank = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            time[i] = speed;
            if(!map.containsKey(rank)) {
                map.put(rank, new ArrayList<>());
            }
            map.get(rank).add(i);
        }

        ArrayList<Integer> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet);
        int max = 0;
        for(int key : keySet) {
            if(key != keySet.size()) {
                for(int i : map.get(key)) {
                    total[i] += time[i];
                    for(int j : map.get(key + 1)) {
                        total[j]  = Math.max(total[j], (int)Math.pow((i-j), 2) + total[i]);
                    }
                }
            }
            else {
                for(int i : map.get(key)) {
                    total[i] += time[i];
                    max = Math.max(total[i], max);
                }
            }
        }
        System.out.println(max);
    }
}
