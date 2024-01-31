import java.util.Random;
// LeetCode 528
public class RandomPickWithWeight {
    private int[] preSum;

    public RandomPickWithWeight(int[] w) {
        preSum = new int[w.length+1];
        preSum[0] = 0;
        for (int i = 1; i <= w.length; i++) {
            preSum[i] = w[i-1] + preSum[i - 1];
        }
    }

    public int pickIndex() {
        int n = preSum.length;
        Random rand = new Random();
        int random = rand.nextInt(preSum[n - 1]) + 1;
        return search(random);
    }

    private int search(int target) {
        int left = 0, right = preSum.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (preSum[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }
}
