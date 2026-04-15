package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_17386 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x1 = Long.parseLong(st.nextToken());
        long y1 = Long.parseLong(st.nextToken());
        long x2 = Long.parseLong(st.nextToken());
        long y2 = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long x3 = Long.parseLong(st.nextToken());
        long y3 = Long.parseLong(st.nextToken());
        long x4 = Long.parseLong(st.nextToken());
        long y4 = Long.parseLong(st.nextToken());


        long ccw1 = ccw(x1, y1, x2, y2, x3, y3);
        long ccw2 = ccw(x1, y1, x2, y2, x4, y4);;
        long ccw3 = ccw(x3, y3, x4, y4, x1, y1);;
        long ccw4 = ccw(x3, y3, x4, y4, x2, y2);;

        System.out.println(ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0 ? 1 : 0);
    }

    private static int ccw(long x1, long y1, long x2, long y2, long x3, long y3){
        return (x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2 + x1*y3) < 0 ? 1 : -1;
    }
}
