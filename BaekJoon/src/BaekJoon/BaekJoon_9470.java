package BaekJoon;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_9470 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        int[] cnt, max, result;
        ArrayList<ArrayList<Integer>> list;
        StringTokenizer st;
        Queue<Integer> que = new LinkedList<>();

        while(testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            cnt = new int[m+1];
            max = new int[m+1];
            result = new int[m+1];
            list = new ArrayList<>();

            for(int i = 0; i <= m; i++){
                list.add(new ArrayList<>());
            }

            for(int i = 0; i < p; i++) {
                st = new StringTokenizer(br.readLine());
                int n1 = Integer.parseInt(st.nextToken());
                int n2 = Integer.parseInt(st.nextToken());
                list.get(n1).add(n2);
                cnt[n2]++;
            }

            for(int i = 1; i <= m; i++){
                if(cnt[i] == 0) {
                    result[i]++;
                    max[i]++;
                    que.offer(i);
                }
            }

            int num = 0;
            while(!que.isEmpty()) {
                int idx = que.poll();
                if(max[idx] >= 2) result[idx]++;
                num = Math.max(result[idx], num);
                for(int i : list.get(idx)) {
                    if(--cnt[i] == 0) {
                        que.offer(i);
                    }

                    if(result[i] < result[idx]) {
                        result[i] = result[idx];
                        max[i] = 1;
                    }
                    else if(result[i] == result[idx]) {
                        max[i]++;
                    }
                }
            }

            bw.write(k + " " + num + "\n");
        }
        bw.flush();
        bw.close();
    }
}
