package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon_2623 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] singer = new int[n+1];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int n2 = Integer.parseInt(st.nextToken());
                list.get(n1).add(n2);
                singer[n2]++;
                n1 = n2;
            }
        }

        Queue<Integer> que = new LinkedList<>();
        for(int i = 1; i <= n; i++){
            if(singer[i] == 0) {
                que.offer(i);
            }
        }

        Stack<Integer> stack = new Stack<>();
        while(!que.isEmpty()) {
            int s = que.poll();
            for(int i : list.get(s)) {
                if(--singer[i] == 0){
                    que.offer(i);
                }
            }
            stack.push(s);
        }

        System.out.println(stack.size() == n ? printSinger(stack) : 0);
    }

    private static StringBuilder printSinger(Stack<Integer> stack) {
        StringBuilder sb = new StringBuilder();
        for(int i : stack) {
            sb.append(i).append("\n");
        }
        return sb;
    }
}
