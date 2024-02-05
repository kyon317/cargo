import java.util.ArrayList;
import java.util.List;

public class Sort {
    int[] temp;
    private void mergeSort(int[] nums, int lo, int hi){
        if(lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);
        merge(nums, lo, hi, mid);
    }
    private void merge(int[] nums, int lo, int hi, int mid){
        for(int i = lo; i <= hi; i++){
            temp[i] = nums[i];
        }
        int i = lo, j = mid + 1;
        for(int p = lo; p <= hi; p++){
            if(i == mid + 1){
                nums[p] = temp[j++];
            }else if(j == hi + 1){
                nums[p] = temp[i++];
            }else if(temp[i] > temp[j]){
                nums[p] = temp[j++];
            }else{
                nums[p] = temp[i++];
            }
        }
    }

    // 315
    class count_Smaller_after_self {
        private class Pair{
            int val,index;
            public Pair(int val, int index){
                this.val = val;
                this.index = index;
            }
        }
        int[] counter;
        Pair[] temp;
        public List<Integer> countSmaller(int[] nums) {
            int n = nums.length;
            temp = new Pair[n];
            Pair[] arr = new Pair[n];
            counter = new int[n];
            List<Integer> res = new ArrayList<>();
            for(int i = 0; i < n ; i++){
                arr[i] = new Pair(nums[i], i);
            }
            // mergesort
            mergeSort(arr, 0, n - 1);
            for(int c:counter){
                res.add(c);
            }
            return res;
        }

        private void mergeSort(Pair[] arr, int lo, int hi){
            if(lo == hi) return;
            int mid = lo + (hi - lo) / 2;
            mergeSort(arr, lo, mid);
            mergeSort(arr, mid + 1, hi);
            merge(arr, lo, mid, hi);
        }

        private void merge(Pair[] arr, int lo, int mid, int hi){
            for(int i = lo; i <= hi; i++){
                temp[i] = arr[i];
            }
            int i = lo, j = mid + 1;
            for(int p = lo; p <= hi; p++){
                if(i == mid + 1){
                    arr[p] = temp[j++];
                }
                else if(j == hi + 1){
                    arr[p] = temp[i++];
                    counter [arr[p].index] += j - mid - 1;
                }else if(temp[i].val > temp[j].val){
                    arr[p] = temp[j++];
                }else{
                    arr[p] = temp[i++];
                    counter [arr[p].index] += j - mid - 1;
                }
            }
        }
    }


}
