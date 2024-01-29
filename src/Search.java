import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = (right + left) / 2;
        while (left <= right) {
            if (nums[mid] < target) {
                left = mid;
            } else if (nums[mid] == target) {
                return mid;
            } else {
                right = mid;
            }
            mid = (right + left) / 2;
        }
        return -1;
    }

    public List<Integer> targetIndices_1(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target) res.add(i);
        }
        return res;
    }

    public List<Integer> targetIndices_2(int[] nums, int target) {
        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0, valid = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] < target) left++;
            else if(nums[i] > target) right++;
            else if(nums[i] == target) valid++;
        }
        for(int i = 0; i < valid; i++)
            res.add(left + i);
        return res;
    }

    public int[] platesBetweenCandles_Brute(String s, int[][] queries) {
        int[] res = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int left = queries[i][0];
            int right = queries[i][1];
            int candle = 0, plate = 0, valid = 0;
            while(left <= right){
                if(s.charAt(left)=='|'){
                    candle++;
                    break;
                }
                left++;
            }
            int leftBound = left;
            while(right >= leftBound){
                if(s.charAt(right)=='|'){
                    candle++;
                    break;
                }
                right--;
            }
            int rightBound = right;
            for(int j = leftBound; j <= rightBound; j++){
                if(s.charAt(j)=='*'){
                    valid++;
                }
            }
            res[i] = valid;
        }
        return res;
    }

    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] preSum = new int[n];
        preSum[0] = 0;
        for(int i = 1; i < n; i++){
            if (s.charAt(i - 1) == '*' && preSum[i-1] != 0) preSum[i] = preSum[i-1] + 1;
            else if (s.charAt(i - 1) == '|' && s.charAt(i) == '*') preSum[i] = 1;
            else if (s.charAt(i) == '|') preSum[i] = preSum[i-1];
        }
        System.out.println(Arrays.toString(preSum));
        return preSum;
    }
    public static void main(String[] args) {
        String str = "***|**|*****|**||**|*";
        int[][] queries = new int[2][2];
        Search s = new Search();
        s.platesBetweenCandles(str,queries);
    }
}
