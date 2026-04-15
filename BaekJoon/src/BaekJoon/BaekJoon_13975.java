package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon_13975 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Long> que;
        long sum;

        while(testCase-- > 0) {
            int k = Integer.parseInt(br.readLine());
            que = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < k; i++){
                que.add(Long.parseLong(st.nextToken()));
            }

            sum = 0;
            while(que.size() > 1){
                long a = que.poll();
                long b = que.poll();
                sum += a + b;
                que.add(a + b);
            }

            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
