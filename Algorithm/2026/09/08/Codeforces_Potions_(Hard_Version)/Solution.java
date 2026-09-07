import java.util.*;
import java.io.*;

class Main {

    static int c;
    
    public static void main(String[] args) throws IOException {
        c = System.in.read();
        int t = readInt();
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long hp = 0;
        int cnt = 0;
        while(t-- > 0) {
            int val = readInt();
            if(val < 0) {
                if(val + hp >= 0) {
                    pq.add(val);
                    hp += val;
                } else {
                    if(!pq.isEmpty() && pq.peek() < val) {
                        hp -= pq.poll();
                        hp += val;
                        pq.add(val);                    
                    }
                }
            } else {
                hp += val;
                cnt++;
            }
        }
        System.out.println(cnt + pq.size());
    }

    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        boolean flag = false;
        if(c == '-') { flag = true; c = System.in.read(); }
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return flag ? -n : n;
    }
}