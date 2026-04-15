package BaekJoon;

import java.io.*;

public class BaekJoon_11729 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println((int)(Math.pow(2, n) - 1));
        System.out.println(buildString(n, 1, 3));
    }

    private static String buildString(int n, int start, int end) {
        if(n == 1) return start + " " + end + "\n";

        StringBuilder sb = new StringBuilder();
        sb.append(buildString(n-1, start, 6 - start - end));
        sb.append(start).append(" ").append(end).append("\n");
        sb.append(buildString(n-1, 6 - start - end, end));

        return sb.toString();
    }
}
