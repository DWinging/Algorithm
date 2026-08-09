class Solution {

    final static int INF = 100_000;

    public int[] findOriginalArray(int[] changed) {
        int[] val = new int[INF + 1];

        int n = changed.length;
        for(int num : changed) {
            val[num]++;
        }

        if((n & 1) == 1 || (val[0] & 1) == 1) return new int[0];

        int[] res = new int[n >> 1];
        int idx = 0; 
        for(int i = 0; i < (val[0] >> 1); i++) {                
            res[idx++] = 0;
        }

        for(int i = 1; i <= (INF >> 1); i++) {
            int temp = val[i];
            if(temp == 0) continue;

            if(val[i << 1] >= temp) {
                for(int j = 0; j < temp; j++) {
                    res[idx++] = i;
                }
                val[i << 1] -= temp;
            } else {
                return new int[0];
            }
        }

        if(idx == (n >> 1)) return res;
        else return new int[0];
    }   
}