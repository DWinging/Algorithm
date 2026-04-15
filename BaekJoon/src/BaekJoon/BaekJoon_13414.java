package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_13414 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int success = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++){
            String key = br.readLine();
            map.put(key, i);
        }

        ArrayList<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> (map.get(o1).compareTo(map.get(o2))));

        for(int i = 0; i < success && i < keySet.size(); i++){
            sb.append(keySet.get(i)).append("\n");
        }
        System.out.println(sb);
    }
}
