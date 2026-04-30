/**
 * [BOJ] 14725 - 개미굴
 * - 제출 날짜: 2026년 1월 20일
 * - 결과: 맞았습니다!!
 * - 메모리: 13392 KB
 * - 시간: 104 ms
 */

import java.util.*;
import java.io.*;

class Main {

    private static class AntRoom implements Comparable<AntRoom> {
        String feed;
        ArrayList<AntRoom> list;

        AntRoom(String feed) {
            this.feed = feed;
            list = new ArrayList<>();
        }

        @Override
        public int compareTo(AntRoom a) {
            return this.feed.compareTo(a.feed);
        }
    }

    final static String FLOOR = "--";
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        ArrayList<AntRoom> antRoom = new ArrayList<>();
        settingAntRoom(antRoom, n, br);

        printRoom(antRoom, 0, bw);
        bw.flush();
        bw.close();
    }

    private static void settingAntRoom(ArrayList<AntRoom> antRoom, int n, BufferedReader br) throws IOException {
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            String[] info = new String[len];
            for(int j = 0; j < len; j++) {
                info[j] = st.nextToken();
            }
            checkAntRoom(antRoom, info, 0);
        }    
    }

    private static void checkAntRoom(ArrayList<AntRoom> list, String[] info, int idx) {
        for(AntRoom room : list) {
            if(room.feed.equals(info[idx])) {
                checkAntRoom(room.list, info, idx + 1);
                return;
            }
        }
        list.add(addNewAntRoom(info, idx));
    }

    private static AntRoom addNewAntRoom(String[] info, int idx) {
        AntRoom room = new AntRoom(info[idx]);
        if(idx < info.length-1) room.list.add(addNewAntRoom(info, idx + 1));
        return room;
    }

    private static void printRoom(ArrayList<AntRoom> list, int f, BufferedWriter bw) throws IOException {
        if(list.size() == 0) return;
        
        Collections.sort(list);
        
        
        for(AntRoom room : list) {
            for(int i = 0; i < f; i++) {
                bw.write(FLOOR);
            }    
            bw.write(room.feed + "\n");

            printRoom(room.list, f + 1, bw);
        }
    }
}