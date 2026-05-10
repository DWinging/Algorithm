public class UserSolution {

    final int MAX_SIZE = 1_000_000;
    int[][] trie = new int[MAX_SIZE + 1][26];
    int[][] count = new int[MAX_SIZE + 1][26];
    int total = 0;
    
	public void init() {	
        for(int i = 0; i <= total; i++) {
            for(int j = 0; j < 26; j++) {
                trie[i][j] = -1;
                count[i][j] = 0;
            }
        }
        total = 0;
	}
	
	public void insert(int buffer_size, String buf) {	
        int pointer = 0;
        for(char str : buf.toCharArray()) {
            int c = str - 'a';
            
            if(trie[pointer][c] <= 0) {
                trie[pointer][c] = ++total;
            }            
            
            count[pointer][c]++;
            pointer = trie[pointer][c];            
        }
	}
	
	public int query(int buffer_size, String buf) {
        int pointer = 0;
        int cnt = 0;
        for(char str : buf.toCharArray()) {
            int c = str - 'a';
            
            if(trie[pointer][c] <= 0) return 0;
            
            cnt = count[pointer][c];
            pointer = trie[pointer][c];            
        }
		return cnt;
	}
}