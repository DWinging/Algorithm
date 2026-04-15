package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_21737 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String text = br.readLine();

        StringBuilder sb = new StringBuilder();
        Deque<String> deque = new ArrayDeque<>();
        boolean check = false;
        for(char c : text.toCharArray()) {
            if(c == 'C') check = true;
            if(c - '0' >= 0 && c - '0' < 10) {
                sb.append(c);
            }
            else {
                if(sb.length() > 0) {
                    deque.addLast(sb.toString());
                    sb = new StringBuilder();
                }
                deque.addLast(String.valueOf(c));
            }
        }

        if(!check) {
            System.out.println("NO OUTPUT");
            return;
        }

        int num = 0;
        while(!deque.isEmpty()) {
            String temp = deque.pollFirst();
            if(temp.equals("C")) bw.write(num + " ");
            else if(temp.equals("S") || temp.equals("M") || temp.equals("U") || temp.equals("P")) {
                num = calculate(deque, temp, num, bw);
            }
            else {
                num = Integer.parseInt(temp);
            }
        }
        bw.flush();
        bw.close();
    }

    private static int calculate(Deque<String> deque, String str, int num, BufferedWriter bw) throws IOException {
        String temp = deque.pollFirst();
        while(!deque.isEmpty() && temp.equals("C")) {
            bw.write(num + " ");
            temp = deque.pollFirst();
        }
        if(deque.isEmpty()) return num;

        if(str.equals("S")) num -= Integer.parseInt(temp);
        else if(str.equals("M")) num *= Integer.parseInt(temp);
        else if(str.equals("U")) num /= Integer.parseInt(temp);
        else if (str.equals("P")) num += Integer.parseInt(temp);
        return num;
    }
}
