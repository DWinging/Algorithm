package BaekJoon;

import java.util.StringTokenizer;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BaekJoon_26069 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        HashMap<String, Character> map = new HashMap<>();
        map.put("ChongChong", 'a');

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            String name1 = st.nextToken();
            String name2 = st.nextToken();

            if(map.containsKey(name1) && !map.containsKey(name2)){
                map.put(name2, 'a');
            }
            else if(map.containsKey(name2)){
                map.put(name1, 'a');
            }
        }

        System.out.println(map.keySet().size());
    }
}
