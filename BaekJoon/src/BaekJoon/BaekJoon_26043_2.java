package BaekJoon;

import java.util.*;
import java.io.*;

public class BaekJoon_26043_2 {
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int testCase = Integer.parseInt(br.readLine());
        Queue<Integer> student = new LinkedList<>();
        Queue<Integer> que = new LinkedList<>();
        ArrayList<Integer> aList = new ArrayList<>();
        ArrayList<Integer> bList = new ArrayList<>();
        ArrayList<Integer> cList = new ArrayList<>();

        for(int i = 0; i < testCase; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            if(Integer.parseInt(st.nextToken()) == 1){
                int temp = Integer.parseInt(st.nextToken());
                student.offer(Integer.parseInt(st.nextToken()));
                que.offer(temp);
            }
            else {
                int temp = que.poll();
                if(Integer.parseInt(st.nextToken()) == student.poll()){
                    aList.add(temp);
                }
                else{
                    bList.add(temp);
                }
            }
        }

        while(!que.isEmpty()){
            cList.add(que.poll());
        }

        printValue(aList);
        printValue(bList);
        printValue(cList);

        bw.flush();
        bw.close();
    }

    private static void printValue(ArrayList<Integer> list) throws IOException {
        if(list.size() == 0){
            bw.write("None\n");
        }
        else {
            Collections.sort(list);
            for(int i : list){
                bw.write(i + " ");
            }
            bw.write("\n");
        }
    }
}
