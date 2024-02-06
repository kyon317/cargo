import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    // quickSort
    private void quickSort(int[] nums, int lo, int hi){
        if(lo >= hi) return;
        int p = partition(nums, lo, hi);
        quickSort(nums, lo, p - 1);
        quickSort(nums, p + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi){
        int pivot = nums[lo];
        int i = lo + 1, j = hi;
        while(i <= j){
            while(i <= hi && nums[i] <= pivot){
                i++;
            }
            while(j >= i && nums[j] > pivot) j--;
            if(i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, lo, j);
        return j;
    }
    private static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    private static void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0 ; i < n; i++) {
            // 生成 [i, n - 1] 的随机数
            int r = i + rand.nextInt(n - i);
            swap(nums, i, r);
        }
    }
}
