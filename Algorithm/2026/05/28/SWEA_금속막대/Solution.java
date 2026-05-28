import java.io.*;
import java.util.*;

class Solution {
    
    static Map<Integer, Integer> pointerMap = new HashMap<>();
    static Set<Integer> backSet = new HashSet<>();
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int T = readInt();

        for(int t = 1; t <= T; t++) {
            int n = readInt();
            
            resetStructures();
            inputScrews(n);
            int start = findStart();
            
            sb.append('#').append(t).append(solve(start)).append('\n');
        }
        System.out.print(sb);
    }

    private static void resetStructures() {
        pointerMap.clear();
        backSet.clear();
    }

    private static void inputScrews(int n) throws IOException {
        for(int i = 0; i < n; i++) {
            int f = readInt();
            int b = readInt();
            pointerMap.put(f, b);
            backSet.add(b);
        }
    }

    private static int findStart() {
        for (int key : pointerMap.keySet()) {
            if (!backSet.contains(key)) {
                return key;
            }
        }
        return -1;
    }

    private static String solve(int start) {
        StringBuilder sb = new StringBuilder();
        int idx = start;
        while (pointerMap.containsKey(idx)) {
            int next = pointerMap.get(idx);
            sb.append(' ').append(idx).append(' ').append(next);
            idx = next;
        }
        return sb.toString();
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();   
        }
        return n;
    }
}