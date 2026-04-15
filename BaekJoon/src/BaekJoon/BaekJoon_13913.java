package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon_13913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(N == K ? 0 + "\n" + N : bfs(N, K));
    }

    private static StringBuilder bfs(int start, int target){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        int[] visit = new int[100001];
        int[] route = new int[100001];
        visit[start] = 1;

        while(!que.isEmpty()){
            int x = que.poll();

            if(x == target){
                break;
            }

            int temp = x * 2;
            if(temp < visit.length && visit[temp] == 0){
                visit[temp] = visit[x] + 1;
                route[temp] = x;
                que.add(temp);
            }

            temp = x + 1;
            if(temp < visit.length && visit[temp] == 0){
                visit[temp] = visit[x] + 1;
                route[temp] = x;
                que.add(temp);
            }

            temp = x - 1;
            if(temp >= 0 && visit[temp] == 0){
                visit[temp] = visit[x] + 1;
                route[temp] = x;
                que.add(temp);
            }
        }

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        int index = target;
        stack.push(index);

        while(index != start){
            index = route[index];
            stack.push(index);
        }
        sb.append(visit[target]-1).append("\n");
        for(int i = stack.size()-1; i >= 0; i--){
            sb.append(stack.get(i)).append(" ");
        }

        return sb;
    }
}
