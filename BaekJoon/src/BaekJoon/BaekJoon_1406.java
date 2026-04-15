package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class BaekJoon_1406 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        LinkedList<Character> list = new LinkedList<>();
        String text = br.readLine();
        for(int i = 0; i < text.length(); i++){
            list.add(text.charAt(i));
        }
        ListIterator<Character> iter = list.listIterator();
        while(iter.hasNext()){
            iter.next();
        }

        int testCase = Integer.parseInt(br.readLine());

        while(testCase-- > 0){
            String temp = br.readLine();
            if(temp.charAt(0) == 'P'){
                iter.add(temp.charAt(2));
            }
            else if(temp.charAt(0) == 'L'){
                if(iter.hasPrevious()){
                    iter.previous();
                }
            }
            else if(temp.charAt(0) == 'D'){
                if(iter.hasNext()){
                    iter.next();
                }
            }
            else {
                if(iter.hasPrevious()){
                    iter.previous();
                    iter.remove();
                }
            }
        }

        for(char i : list){
            sb.append(i);
        }

        System.out.println(sb);
    }
}