package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BaekJoon_32978 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashSet<String> set = new HashSet<>();

        while(st.hasMoreTokens()){
            set.add(st.nextToken());
        }
        st = new StringTokenizer(s);
        while(st.hasMoreTokens()){
            String temp = st.nextToken();
            if(!set.contains(temp)) {
                System.out.println(temp);
                break;
            }
        }
    }
}
