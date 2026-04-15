package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_1343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        text = text.replaceAll("XXXX", "AAAA");
        text = text.replaceAll("XX", "BB");
        System.out.println(text.contains("X") ? -1 : text);
    }
}
