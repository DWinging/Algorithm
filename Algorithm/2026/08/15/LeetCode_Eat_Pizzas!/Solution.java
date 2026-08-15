import java.io.*;

class Solution {
    public long maxWeight(int[] pizzas) {
        long res = 0;
        Arrays.sort(pizzas);

        int idx = pizzas.length - 1;
        int day = pizzas.length >> 2;

        int odd = (day + 1) >> 1;
        while(odd-- > 0) {
            res += pizzas[idx--];
        }

        int even = day >> 1;
        while(even-- > 0) {
            res += pizzas[idx - 1];
            idx -= 2;
        }

        return res;
    }
}