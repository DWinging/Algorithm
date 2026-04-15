package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.StringTokenizer;

public class BaekJoon_23294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        Deque<Integer> back = new ArrayDeque<>();
        Deque<Integer> front = new ArrayDeque<>();
        int index = 0;
        long total = 0;
        long[] volume = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            volume[i] = Long.parseLong(st.nextToken());
        }

        while(q-- > 0){
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("B")){
                if(!back.isEmpty()){
                    front.add(index);
                    index = back.peekLast();
                    back.pollLast();
                }
            }
            else if(cmd.equals("F")){
                if(!front.isEmpty()){
                    back.add(index);
                    index = front.peekLast();
                    front.pollLast();
                }
            }
            else if(cmd.equals("A")){
                while(!front.isEmpty()){
                    total -= volume[front.pollFirst()];
                }
                if(index != 0) {
                    back.add(index);
                }
                index = Integer.parseInt(st.nextToken());
                total += volume[index];;
                while(total > c){
                    total -= volume[back.pollFirst()];
                }
            }
            else {
                Deque<Integer> temp = new ArrayDeque<>();
                while(!back.isEmpty()){
                    temp.addFirst(back.pollLast());
                    if(!back.isEmpty() && Objects.equals(back.peekLast(), temp.peekFirst())){
                        while(!back.isEmpty() && (Objects.equals(back.peekLast(), temp.peekFirst()))){
                            total -= volume[back.pollLast()];
                        }
                    }
                }
                back = temp;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(index).append("\n");
        sb.append(!back.isEmpty() ? print(back) : -1).append("\n");
        sb.append(!front.isEmpty() ? print(front) : -1);
        System.out.println(sb);
    }

    private static String print(Deque<Integer> deque){
        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()){
            sb.append(deque.pollLast()).append(" ");
        }
        return sb.toString();
    }
}
