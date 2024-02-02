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
}
