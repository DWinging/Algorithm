package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BaekJoon_17479 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> aMenu = new HashMap<>();
        HashMap<String, Integer> bMenu = new HashMap<>();
        HashSet<String> cMenu =new HashSet<>();

        for(int i = 0; i < A; i++){
            st = new StringTokenizer(br.readLine());
            aMenu.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < B; i++){
            st = new StringTokenizer(br.readLine());
            bMenu.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < C; i++){
            cMenu.add(br.readLine());
        }

        int N = Integer.parseInt(br.readLine());
        long aMenuSum = 0, bMenuSum = 0;
        int cMenuCnt = 0;

        for(int i = 0; i < N; i++){
            String menu = br.readLine();
            if(aMenu.containsKey(menu)){
                aMenuSum += aMenu.get(menu);
            }
            else if(bMenu.containsKey(menu)){
                bMenuSum += bMenu.get(menu);
            }
            else {
                cMenuCnt++;
                if(cMenuCnt >= 2){
                    break;
                }
            }
        }

        if((aMenuSum < 20000 && bMenuSum != 0) || (aMenuSum + bMenuSum < 50000 && cMenuCnt != 0) || (cMenuCnt > 1)){
            System.out.println("No");
        }
        else {
            System.out.println("Okay");
        }
    }
}
