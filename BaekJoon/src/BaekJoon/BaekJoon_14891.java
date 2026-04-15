package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_14891 {
    private static class Cogwheel {
        int left = 6, right = 2;
        String line;

        Cogwheel(String line) {
            this.line = line;
        }
    }

    final static int TOTAL_COGWHEELS = 4;
    final static int TEETH = 8;
    final static char SOUTH = '1';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Cogwheel> cogwheels = new HashMap<>();
        settingCogwheels(cogwheels, br);
        chainingCogWheels(cogwheels, br);
        System.out.println(getScore(cogwheels));
    }

    private static void settingCogwheels(Map<Integer, Cogwheel> cogwheels, BufferedReader br) throws IOException {
        for(int i = 1; i <= TOTAL_COGWHEELS; i++) {
            cogwheels.put(i, new Cogwheel(br.readLine()));
        }
    }

    private static void chainingCogWheels(Map<Integer, Cogwheel> cogwheels, BufferedReader br) throws IOException {
        StringTokenizer st;
        int testCase = Integer.parseInt(br.readLine());
        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dict = Integer.parseInt(st.nextToken()) * (-1);
            boolean[] visited = new boolean[TOTAL_COGWHEELS + 1];
            checkTeeth(num, cogwheels, dict, visited);
        }
    }

    private static void checkTeeth(int num, Map<Integer, Cogwheel> cogwheels, int dict, boolean[] visited) {
        Cogwheel cogwheel = cogwheels.get(num);
        visited[num] = true;
        if(num - 1 > 0 && !visited[num-1] && check(cogwheels.get(num-1), cogwheels.get(num))) {
            checkTeeth(num - 1, cogwheels, dict * (-1), visited);
        }
        if(num + 1 <= TOTAL_COGWHEELS && !visited[num+1] && check(cogwheels.get(num), cogwheels.get(num + 1))) {
            checkTeeth(num + 1, cogwheels, dict * (-1), visited);
        }
        cogwheel.left = rotate(cogwheel.left, dict);
        cogwheel.right = rotate(cogwheel.right, dict);
    }

    private static boolean check(Cogwheel wheel1, Cogwheel wheel2) {
        String line1 = wheel1.line;
        String line2 = wheel2.line;
        return line1.charAt(wheel1.right) != line2.charAt(wheel2.left);
    }

    private static int rotate(int teeth, int dict) {
        int next = teeth + dict;
        if(next < 0) return TEETH - 1;
        else if(next >= TEETH) return 0;
        else return next;
    }

    private static int getScore(Map<Integer, Cogwheel> cogwheels) {
        int score = 0;
        int half = TEETH / 2;
        for(int i = 1; i <= TOTAL_COGWHEELS; i++) {
            Cogwheel cogwheel = cogwheels.get(i);
            String line = cogwheel.line;
            int left = cogwheel.left;
            int right = cogwheel.right;
            int teeth = (left + right) / 2;
            if(left > right) {
                teeth = (teeth + half) % TEETH;
            }
            if(line.charAt(teeth) == SOUTH) score += (int) Math.pow(2, (i-1));
        }
        return score;
    }
}
