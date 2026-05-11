import java.io.*;

public class Main {

    /**
     * Fast I/O 처리를 위한 내부 클래스
     * System.in.read()를 사용하여 바이트 단위로 직접 입력받아 성능을 극대화함
     */
    private static class Input {
        private char[] buffer; // 문자열 객체 생성을 피하기 위한 공용 char 버퍼

        public Input(int max_size) {
            buffer = new char[max_size];
        }

        /**
         * 공백을 무시하고 정수값을 읽어옴 (비트 연산 활용)
         */
        public int readInt() throws IOException {
            int c = System.in.read();
            while(c <= ' ') c = System.in.read(); // 공백, 줄바꿈 스킵
            int n = 0;
            while(c >= '0' && c <= '9') {
                // n = n * 10 + digit와 동일한 비트 연산 최적화
                n = (n << 3) + (n << 1) + (c & 15);
                c = System.in.read();
            }
            return n;
        }

        /**
         * 소문자 알파벳 단어를 읽어 buffer에 저장하고 그 길이를 반환함
         * String 객체 생성을 하지 않아 GC 부하가 없음
         */
        public int inputNextWord() throws IOException {
            int c = System.in.read();
            while(c <= ' ') c = System.in.read();
            int idx = 0;
            while(c >= 'a' && c <= 'z') {
                buffer[idx++] = (char) c;
                c = System.in.read();
            }
            return idx; // 실제 읽어들인 문자의 길이 반환
        }

        public char[] getBuffer() {
            return buffer;
        }
    }

    /**
     * LCRS(Left-Child Right-Sibling) 방식의 정적 배열 Trie
     * 메모리 사용량을 최소화하면서 트리 구조를 1차원 배열로 평탄화함
     */
    private static class Trie {
        private final int[] child;    // 첫 번째 자식 노드의 인덱스
        private final int[] sibling;  // 다음 형제 노드의 인덱스
        private final char[] nodeVal; // 해당 노드가 나타내는 문자값
        private final int[] count;    // 해당 노드를 접두사로 공유하는 단어 수
        private int totalCnt = 1;     // 새로 할당될 노드 번호 (0은 루트)

        public Trie(int size) {
            // 전체 문자열 길이 합 + 1 만큼의 공간 할당
            this.child = new int[size + 1];
            this.sibling = new int[size + 1];
            this.nodeVal = new char[size + 1];
            this.count = new int[size + 1];
        }

        /**
         * 단어를 트라이에 삽입
         */
        public void inputWord(char[] word, int len) {
            int curr = 0; // 루트(0번 노드)부터 시작

            for (int i = 0; i < len; i++) {
                char c = word[i];
                int next = -1;
                
                int childNode = child[curr];
                int prevSibling = -1;

                // 1. 현재 노드의 자식들을 형제 포인터를 타고 순회하며 일치하는 문자 탐색
                while (childNode != 0) {
                    if (nodeVal[childNode] == c) {
                        next = childNode;
                        break;
                    }
                    prevSibling = childNode;
                    childNode = sibling[childNode];
                }

                // 2. 일치하는 문자가 자식 중에 없다면 새 노드 생성
                if (next == -1) {
                    next = totalCnt++;
                    nodeVal[next] = c;
                    
                    if (child[curr] == 0) {
                        child[curr] = next; // 첫 번째 자식으로 등록
                    } else {
                        sibling[prevSibling] = next; // 기존 자식들의 마지막 형제로 연결
                    }
                }

                curr = next;
                count[curr]++; // 현재 접두사 경로를 지나는 단어 카운트 증가
            }
        }

        /**
         * 타겟 단어의 접두사별 일치 개수를 한꺼번에 계산하여 반환
         */
        public String getAnswer(char[] word, int len) {
            // 출력 최적화를 위해 StringBuilder 사용
            StringBuilder sb = new StringBuilder(len * 6);
            int curr = 0, idx = 0;

            // 접두사 탐색 루프
            while(idx < len) {
                char c = word[idx];
                int next = -1;
                
                int childNode = child[curr];

                // 형제 노드 순회하며 매칭되는 문자 찾기
                while (childNode != 0) {
                    if (nodeVal[childNode] == c) {
                        next = childNode;
                        break;
                    }
                    childNode = sibling[childNode];
                }

                // 트라이에 해당 접두사 경로가 더 이상 없으면 중단
                if (next == -1) {
                    break; 
                }

                sb.append(count[next]).append(' ');
                curr = next;
                idx++;
            }

            // 트라이 탐색이 도중에 끊겼다면 남은 길이는 모두 0으로 채움
            while(idx < len) {
                sb.append('0').append(' ');
                idx++;
            }
            
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        // 단어 최대 길이 10만 + 여유 공간
        Input input = new Input(100_005);
        int n = input.readInt();
        int m = input.readInt();

        // 전체 노드 개수는 모든 단어 길이의 합(20만)을 넘지 않음
        Trie trie = new Trie(200_005);

        // 사전 단어 입력 및 트라이 구축
        for(int i = 0; i < n; i++) {
            int len = Math.min(input.inputNextWord(), m);
            char[] word = input.getBuffer();
            trie.inputWord(word, len);
        }

        // 타겟 단어 입력 및 접두사별 카운트 조회
        int targetLen = input.inputNextWord();
        char[] target = input.getBuffer();
        System.out.println(trie.getAnswer(target, targetLen));
    }
}