package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_31857 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[] belt1 = inputBelt(br.readLine(), n);
        char[] belt2 = inputBelt(br.readLine(), n);

        int[] idx = operateFactory(belt1, belt2, n, p, m, br);
        System.out.println(finalBelt(belt1, idx[0], n));
        System.out.println(finalBelt(belt2, idx[1], n));
    }

    private static char[] inputBelt(String text, int n) {
        char[] belt = new char[n];
        for(int i = 0; i < n; i++) {
            belt[i] = text.charAt(i);
        }
        return belt;
    }

    private static int[] operateFactory(char[] belt1, char[] belt2, int n, int p, int m, BufferedReader br) throws IOException {
        int idx1 = 0, idx2 = 0;
        while(m-- > 0) {
            String command = br.readLine();
            if(command.charAt(0) == 'S') {
                switchBelt(belt1, belt2, n, p, idx1, idx2);
            }
            else if(command.charAt(0) == 'L') {
                if(command.charAt(2) == '1') {
                    idx1 = (idx1 + 1) % n;
                }
                else {
                    idx2 = (idx2 + 1) % n;
                }
            }
            else if(command.charAt(0) == 'R') {
                if(command.charAt(2) == '1') {
                    idx1 = idx1 - 1 == -1 ? n - 1 : idx1 - 1;
                }
                else {
                    idx2 = idx2 - 1 == -1 ? n - 1 : idx2 - 1;
                }
            }
            else if(command.charAt(0) == 'I') {
                p++;
            }
            else {
                p--;
            }
        }

        return new int[] {idx1, idx2};
    }

    private static void switchBelt(char[] belt1, char[] belt2, int n, int p, int idx1, int idx2) {
        for(int i = 0; i < p; i++) {
            char temp = belt1[idx1];
            belt1[idx1] = belt2[idx2];
            belt2[idx2] = temp;

            idx1 = (idx1 + 1) % n;
            idx2 = (idx2 + 1) % n;
        }
    }

    private static String finalBelt(char[] belt, int idx, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(belt[idx]);
            idx = (idx + 1) % n;
        }
        return sb.toString();
    }
}
