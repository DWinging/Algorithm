class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int minVal = arrays.get(0).get(0);
        int maxVal = arrays.get(0).get(arrays.get(0).size() - 1);
        int answer = 0;

        for(int i = 1; i < arrays.size(); i++) {
            List<Integer> array = arrays.get(i);
            
            int minCur = array.get(0);
            int maxCur = array.get(array.size() - 1);

            answer = Math.max(answer, maxVal - minCur);
            answer = Math.max(answer, maxCur - minVal);

            minVal = Math.min(minVal, minCur);
            maxVal = Math.max(maxVal, maxCur);
        }

        return answer;
    }
}