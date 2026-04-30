/**
 * [BOJ] 16496 - 큰 수 만들기
 * - 제출 날짜: 2026년 1월 21일
 * - 결과: 틀렸습니다
 */

import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        ArrayList<String> list = inputList(n, br);

        solve(list, 0, bw);
        bw.flush();
        bw.close();
    }

    private static ArrayList<String> inputList(int n, BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(st.nextToken());
        }
        return list;
    }

    private static void solve(ArrayList<String> list, int idx, BufferedWriter bw) throws IOException {
        Map<Integer, ArrayList<String>> map = new HashMap<>();

        for(String s : list) {
            int num = s.charAt(Math.min(idx, s.length()-1)) - '0';
            if(!map.containsKey(num)) {
                map.put(num, new ArrayList<>());
            }
            map.get(num).add(s);
        }

        for(int i = 9; i >= 0; i--) {
            if(!map.containsKey(i)) continue;
            if(check(map.get(i), idx)) printList(map.get(i), bw);
            else solve(map.get(i), idx + 1, bw);
        }
    }

    private static boolean check(ArrayList<String> list, int len) {
        for(String l : list) {
            if(l.length() >= len) return false;
        }
        return true;
    }

    private static void printList(ArrayList<String> list, BufferedWriter bw) throws IOException {
        Collections.sort(list, (a, b) -> Integer.compare(a.length(), b.length()));
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i = 0, j = list.size() - 1; i < list.size(); i++, j--) {
            sb1.append(list.get(i));
            sb2.append(list.get(j));
        }
        
        if(sb1.toString().compareTo(sb2.toString()) > 0) {
            bw.write(sb1.toString());
        }
        else {
            bw.write(sb2.toString());
        }
    }
}