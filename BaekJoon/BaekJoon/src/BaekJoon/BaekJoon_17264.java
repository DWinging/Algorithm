package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BaekJoon_17264 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        HashMap<String, String> map = new HashMap<>();
        for(int i = 0; i < P; i++){
           st = new StringTokenizer(br.readLine());
           map.put(st.nextToken(), st.nextToken());
        }

        int score = 0;
        for(int i = 0; i < N && score < G; i++){
            String name = br.readLine();
            if(map.containsKey(name) && map.get(name).equals("W")){
                score += W;
            }
            else {
                score -= L;
                score = score >= 0 ? score : 0;
            }
        }

        System.out.println(score >= G ? "I AM NOT IRONMAN!!" : "I AM IRONMAN!!");
    }
}
