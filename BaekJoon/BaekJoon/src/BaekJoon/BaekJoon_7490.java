package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_7490 {

    static StringBuilder sb = new StringBuilder();
    static int n;
    static String[] list = {" ", "+", "-"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            n = Integer.parseInt(br.readLine());
            dfs(1, "1");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int num, String str) {
        if(num == n) {
            StringTokenizer st = new StringTokenizer(str, "+|-", true);
            int sum = Integer.parseInt(st.nextToken().replace(" ", ""));
            while(st.hasMoreTokens()) {
                if(st.nextToken().equals("+")) {
                    sum += Integer.parseInt(st.nextToken().replace(" ", ""));
                }
                else {
                    sum -= Integer.parseInt(st.nextToken().replace(" ", ""));
                }
            }

            if(sum == 0) {
                sb.append(str).append("\n");
            }
        }
        else {
            for(String s : list){
                dfs(num + 1, str + s + (num + 1));
            }
        }
    }
}
