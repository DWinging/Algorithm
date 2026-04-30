package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BaekJoon_16165 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, String[]> hashMap = new HashMap<String, String[]>();
        String name;
        int cnt;
        String[] list;
        for(int i = 0; i < N; i++){
            name = br.readLine();
            cnt = Integer.parseInt(br.readLine());
            list = new String[cnt];
            for(int j = 0; j < list.length; j++){
                list[j] = br.readLine();
            }
            Arrays.sort(list);
            hashMap.put(name, list);
        }

        while(M-- > 0){
            name = br.readLine();
            if(Integer.parseInt(br.readLine()) == 1){
                boolean check = false;
                for(String key : hashMap.keySet()){
                    for(String s : hashMap.get(key)){
                        if(s.equals(name)){
                            sb.append(key).append("\n");
                            check = true;
                            break;
                        }
                    }
                    if(check) break;
                }
            }
            else {
                for(String s : hashMap.get(name)){
                    sb.append(s).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
