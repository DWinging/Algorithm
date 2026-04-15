package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_15787 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int len = 20;

        boolean[][] trains = new boolean[n][len];
        boolean[] transfer = {true, false};
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken())-1;
            if(command == 1 || command == 2) {
                int seat = Integer.parseInt(st.nextToken())-1;
                trains[num][seat] = transfer[command-1];
            }
            else if(command == 3) {
                for(int i = len-1; i > 0; i--) {
                    trains[num][i] = trains[num][i-1];
                }
                trains[num][0] = false;
            }
            else {
                for(int i = 0; i < len-1; i++) {
                    trains[num][i] = trains[num][i+1];
                }
                trains[num][len-1] = false;
            }
        }

        Set<String> set = new HashSet<>();
        for(boolean[] train : trains) {
            StringBuilder sb = new StringBuilder();
            for(boolean t : train) {
                sb.append(t ? 1 : 0);
            }
            set.add(sb.toString());
        }
        System.out.println(set.size());
    }
}
