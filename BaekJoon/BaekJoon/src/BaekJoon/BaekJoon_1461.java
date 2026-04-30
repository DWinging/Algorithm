package BaekJoon;

import java.io.*;
import java.util.*;
public class BaekJoon_1461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int temp = Integer.parseInt(st.nextToken());
            if(temp > 0) positive.add(temp);
            else negative.add(temp * (-1));
        }
        Collections.sort(positive);
        Collections.sort(negative);
        int result = 0;
        if(positive.isEmpty()) result = working(negative, M, M) * 2 + negative.get(negative.size()-1);
        else if(negative.isEmpty()) result = working(positive, M, M) * 2 + positive.get(positive.size()-1);
        else {
            if(positive.get(positive.size()-1) > negative.get(negative.size()-1)) result = (working(positive, M, M) + working(negative, 0, M)) * 2 + positive.get(positive.size()-1);
            else result = (working(positive, 0, M) + working(negative, M, M)) * 2 + negative.get(negative.size()-1);
        }
        System.out.println(result);
    }
    private static int working(ArrayList<Integer> list, int index, int m){
        int result = 0;
        for(int i = list.size()-1-index; i >= 0; i -= m) result += list.get(i);
        return result;
    }
}
