import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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

    private int[] preSum;
    public int shipWithinDays(int[] weights, int days) {
        preSum = new int[weights.length + 1];
        preSum[0] = 0;
        int cap = 0;
        for(int i = 1; i <= weights.length; i++){
            preSum[i] = preSum[i - 1] + weights[i - 1];
            cap = Math.max(cap, weights[i-1]);
        }
        while(returnDays(cap) != days){
            cap++;
        }
        return cap;
    }
    private int returnDays(int capacity){
        int res = 1;
        int pre = 0;
        for(int i = 1; i < preSum.length; i++){
            if(preSum[i] - preSum[pre] > capacity) {
                res++;
                pre = i - 1;
            }
        }
        return res;
    }

    // 870
    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        int[] res = new int[nums1.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (int[] pair1, int[] pair2) -> {
                    return pair2[1] - pair1[1];
                });
        for (int i = 0; i < nums1.length; i++) {
            pq.offer(new int[] { i, nums2[i] });
        }
        int left = 0, right = nums2.length - 1;
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            if(temp[1] >= nums1[right]){
                res[temp[0]] = nums1[left];
                left++;
            }else{
                res[temp[0]] = nums1[right];
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String str = "***|**|*****|**||**|*";
        int[][] queries = new int[2][2];
        Search s = new Search();
//        s.platesBetweenCandles(str,queries);
        System.out.println(s.shipWithinDays(new int[]{1,2,3,4,5,6,7,8,9,10}, 5));
    }
}
