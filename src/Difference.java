// 差分数组
public class Difference {
    private int[] diff;
    public Difference(int [] nums){
        int length = nums.length;
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < length; i++){
            diff[i] = nums[i] - nums[i-1];
        }
    }
    public void increment(int left,int right,int value){
        diff[left] += value;
        if (right < diff.length - 1){
            diff[right + 1] -=value;
        }
    }

    public int[] getRes(){
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++){
            res[i] = diff[i] + res[i-1];
        }
        return res;
    }
}
