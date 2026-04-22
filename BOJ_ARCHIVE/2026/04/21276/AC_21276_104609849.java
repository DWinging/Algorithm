/**
 * [BOJ] 21276 - 계보 복원가 호석
 * - 제출 날짜: 2026년 4월 2일
 * - 결과: 맞았습니다!! (19/19)
 * - 메모리: 84608 KB
 * - 시간: 468 ms
 */

import java.util.*;
import java.io.*;

class Main {

    static Map<String, Integer> nameHash = new HashMap<>();
    static String[] names;

    static ArrayList<Integer>[] ancestor, childs;
    static int[] cnt;
    static StringBuilder input = new StringBuilder();
    static int c;
    
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        c = System.in.read();
        int n = readInt();
        
        init(n);              // 전역 변수 초기화
        inputNameList(n);     // 이름 목록 저장
        inputAncestor();      // 석호촌 정보 저장
        topologySort(sb, n);  // 석호촌 관계 정리
        buildString(sb, n);   // 결과물 생성
        System.out.print(sb); // 출력
    }

    private static void init(int n) throws IOException {
        names = new String[n];

        ancestor = new ArrayList[n];
        childs = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            ancestor[i] = new ArrayList<>();
            childs[i] = new ArrayList<>();
        }
        
        cnt = new int[n];
    }

    private static void inputNameList(int n) throws IOException {
        for(int i = 0; i < n; i++) {
            names[i] = readString();
        }

        Arrays.sort(names);

        for(int i = 0; i < n; i++) {
            nameHash.put(names[i], i);
        }
    }

    private static void inputAncestor() throws IOException {
        int m = readInt();
        while(m-- > 0) {
            int x = nameHash.get(readString());
            int y = nameHash.get(readString());

            ancestor[y].add(x);
            cnt[x]++;
        }
    }

    private static void topologySort(StringBuilder sb, int n) {
        int[] que = new int[n];
        int head = 0, tail = 0;
        for(int i = 0; i < n; i++) {
            if(cnt[i] == 0) {
                que[tail++] = i;
                sb.append(names[i]).append(' ');
            }
        }
        sb.insert(0, tail + "\n");
        sb.append('\n');

        while(head < tail) {
            int cur = que[head++];
            for(int next : ancestor[cur]) {
                cnt[next]--;
                if(cnt[next] == 0) {
                    que[tail++] = next;
                    childs[cur].add(next);
                }
            }
        }
    }

    private static void buildString(StringBuilder sb, int n) {
        for(int i = 0; i < n; i++) {
            Collections.sort(childs[i]);
            sb.append(names[i])
                .append(' ')
                .append(childs[i].size())
                .append(' ');

            for(int child : childs[i]) {
                sb.append(names[child]).append(' ');
            }
            sb.append('\n');
        }
    }
    
    private static int readInt() throws IOException {
        while(c <= ' ') c = System.in.read();
        int n = 0;
        while(c >= '0' && c <= '9') {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }

    private static String readString() throws IOException {
        while(c <= ' ') c = System.in.read();
        input.setLength(0);
        while(c >= 'a' && c <= 'z') {
            input.append((char) c);
            c = System.in.read();
        }
        return input.toString();
    }
}