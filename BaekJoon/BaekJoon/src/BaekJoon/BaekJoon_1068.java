package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_1068 {

    static int number;
    static int cnt;
    static ArrayList<ArrayList<Integer>> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tree = new ArrayList<>();

        int node = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        number = Integer.parseInt(br.readLine());
        int root = 0;
        for(int i = 0; i < node; i++){
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i < node; i++){
            int num = Integer.parseInt(st.nextToken());
            if(num == -1){
                root = i;
            }
            else {
                tree.get(num).add(i);
            }
        }

        cnt = 0;
        if(root != number){
            dfs(root);
        }

        System.out.println(cnt);
    }

    static void dfs(int node){
        if(tree.get(node).isEmpty()){
            cnt += 1;
        }
        else {
            for(int i = 0; i < tree.get(node).size(); i++){
                if(tree.get(node).get(i) != number){
                    dfs(tree.get(node).get(i));
                }
                else if(tree.get(node).size() == 1){
                    cnt++;
                }
            }
        }
    }
}
