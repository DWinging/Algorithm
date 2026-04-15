package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_20055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] belt = new int[n][2];
        Deque<Integer> underBelt = new ArrayDeque<>();
        inputBelt(n, belt, underBelt, br);
        System.out.println(operateBelt(n, k, belt, underBelt));
    }

    private static void inputBelt(int n, int[][] belt, Deque<Integer> underBelt, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            belt[i][0] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < n; i++) {
            underBelt.addFirst(Integer.parseInt(st.nextToken()));
        }
    }

    private static int operateBelt(int n, int k, int[][] belt, Deque<Integer> underBelt) {
        int cnt = 0, step = 0;
        while(cnt < k) {
            step++;
            // step 1
            step1(n, belt, underBelt);
            // step 2
            cnt += step2(n, belt);
            // step 3
            cnt += step3(belt);
        }
        return step;
    }

    private static void step1(int n, int[][] belt, Deque<Integer> underBelt) {
        underBelt.addLast(belt[n-1][0]);
        for(int i = n-1; i > 0; i--) {
            belt[i][0] = belt[i-1][0];
            belt[i][1] = belt[i-1][1];
        }
        belt[n-1][1] = 0;
        belt[0][0] = underBelt.pollFirst();
        belt[0][1] = 0;
    }

    private static int step2(int n, int[][] belt) {
        int cnt = 0;
        for(int i = n-1; i > 0; i--) {
            if(belt[i-1][1] == 1 && belt[i][0] >= 1 && belt[i][1] == 0) {
                belt[i][0]--;
                belt[i][1] = 1;
                belt[i-1][1] = 0;
                if(belt[i][0] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static int step3(int[][] belt) {
        if(belt[0][0] != 0) {
            belt[0][0]--;
            belt[0][1] = 1;
            if(belt[0][0] == 0) {
                return 1;
            }
        }
        return 0;
    }
}
