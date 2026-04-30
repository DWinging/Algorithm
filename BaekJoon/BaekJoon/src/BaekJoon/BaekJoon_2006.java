package BaekJoon;

import java.io.*;
import java.util.*;

public class BaekJoon_2006 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Integer> room = new ArrayList<>();
        ArrayList<ArrayList<Info>> match = new ArrayList<>();
        for(int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();

            matching(room, match, l, n, m);
        }

        for(ArrayList<Info> list : match) {
            bw.write(list.size() == m ? "Started!\n" : "Waiting!\n");
            Collections.sort(list);

            for(Info info : list) {
                bw.write(info.level + " " + info.name + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static class Info implements Comparable<Info> {
        int level;
        String name;

        Info(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Info i) {
            return this.name.compareTo(i.name);
        }
    }

    private static void matching(ArrayList<Integer> room, ArrayList<ArrayList<Info>> match, int l, String n, int m) {
        for(int i = 0; i < room.size(); i++) {
            if((match.get(i).size() < m) && (l <= room.get(i) + 10 && l >= room.get(i) - 10)) {
                match.get(i).add(new Info(l, n));
                return;
            }
        }

        room.add(l);
        match.add(new ArrayList<Info>());
        match.get(match.size() - 1).add(new Info(l, n));
    }
}